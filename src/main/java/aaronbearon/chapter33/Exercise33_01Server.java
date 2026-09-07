package aaronbearon.chapter33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 3, Server.
 */
public class Exercise33_01Server extends Application {
    ServerSocket serverSocket;
    ExecutorService executor = Executors.newCachedThreadPool();

    TextArea taOutput;

    // We need a lock for open sockets so we can cleanly close sockets and sync up across threads.
    final Object openSocketsLock = new Object();
    Set<Socket> openSockets = new HashSet<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        taOutput = new TextArea();
        taOutput.setEditable(false);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(taOutput);

        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setTitle("Exercise33_01Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        serverSocket = new ServerSocket(8000);
        executor.submit(this::runSocketListener);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Server shutting down");

        // No new clients can join
        if (serverSocket != null) {
            serverSocket.close();
        }
        // Store sockets to close and nullify open sockets
        Set<Socket> sockets;
        synchronized (openSocketsLock) {
            sockets = openSockets;
            openSockets = null;
        }
        // Close sockets
        for (Socket s : sockets) {
            try {
                s.close();
            } catch (Exception e) {
                // Ignore and continue closing sockets
            }
        }

        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            // After 1 minute of waiting, some thread must be blocked. This is a bug! Exit anyway.
            System.out.println("Server exiting after timeout");
            Runtime.getRuntime().exit(1);
        }
        System.out.println("Server stopped");
    }

    // Global UTC time format for logs.
    private static final DateTimeFormatter logTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC);

    // Logs a message into the log textbox.
    private void log(String message) {
        String timestamp = logTimeFormatter.format(Instant.now());
        String text = timestamp + ": " + message + String.format("%n");
        // Must run on UI thread.
        Platform.runLater(() -> taOutput.appendText(text));
    }

    /**
     * Loops forever on the server socket, accepting new incoming client connections.
     * Each connection needs its own handler thread to run in.
     */
    public void runSocketListener() {
        try {
            log("Exercise33_01Server started at " + new Date());

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                log("Connected to a client at " + new Date());

                // Add a socket unless the server is closing.
                synchronized (openSocketsLock) {
                    if (openSockets != null) {
                        openSockets.add(socket);
                    } else {
                        // The server is already shutting down, just close the socket immediately.
                        socket.close();
                        continue;
                    }
                }

                // Each client socket handler runs in its own thread.
                // Make sure the socket is closed and removed from the
                // openSockets list once the handler is done.
                executor.submit(() -> {
                    try {
                        handleClientConnection(socket);
                        socket.close();
                    } catch (IOException e) {
                        // Ignore, just making sure the socket is closed.
                    } finally {
                        synchronized (openSocketsLock) {
                            if (openSockets != null) {
                                openSockets.remove(socket);
                            }
                        }
                    }
                });
            }
        } catch (SocketException ex) {
            // Ignore, server is shutting down
        } catch (IOException ex) {
            // This is unexpected
            throw new RuntimeException(ex);
        }
    }

    /**
     * Listen for weight and height from client, send BMI to client.
     * Handle each incoming request, and send a response. Loop until the client
     * closes the connection, or the server is shutting down.
     */
    public void handleClientConnection(Socket socket) {
        try {
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
            while (socket.isConnected()) {
                double weight = inputFromClient.readDouble();
                double height = inputFromClient.readDouble();
                final double KILOGRAMS_PER_POUND = 0.45359237;
                final double METERS_PER_INCH = 0.0254;
                double weightInKilograms = weight * KILOGRAMS_PER_POUND;
                double heightInMeters = height * METERS_PER_INCH;
                double bmi = weightInKilograms / (heightInMeters * heightInMeters);
                String status;
                if (bmi < 18.5) {
                    status = "Underweight";
                } else if (bmi < 25) {
                    status = "Normal";
                } else if (bmi < 30) {
                    status = "Overweight";
                } else {
                    status = "Obese";
                }
                outputToClient.writeDouble(bmi);
                outputToClient.writeUTF(status);
                outputToClient.flush();
                log(String.format("Weight is %.2f", weight));
                log(String.format("Height is %.2f", height));
                log(String.format("BMI is %.2f. %s", bmi, status));
            }
        } catch (SocketException ex) {
            // ignore, the server is probably closing or the client hung up.
        } catch (IOException ex) {
            // This is unexpected.
            System.out.println("DEBUG exception in handler " + ex.getMessage());
        } finally {
            log("Client disconnected");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
