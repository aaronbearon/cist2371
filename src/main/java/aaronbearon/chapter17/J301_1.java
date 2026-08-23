package aaronbearon.chapter17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 1
 * Description: Address book manager.
 */
public class J301_1 extends Application {
    private static final int H_BOXES = 4;
    private static final int TEXT_ROWS = 3;

    // Components

    Label lblName = new Label("Name");
    Label lblStreet = new Label("Street");
    Label lblCity = new Label("City");
    Label lblState = new Label("State");
    Label lblZip = new Label("Zip");

    TextField tfName = createTextField(0, 32);
    TextField tfStreet = createTextField(0, 32);
    TextField tfCity = createTextField(0, 20);
    TextField tfState = createTextField(2, 2);
    TextField tfZip = createTextField(4, 5);
    TextField[] textFields = {tfName, tfStreet, tfCity, tfState, tfZip};

    Button btnAdd = new Button("Add");
    Button btnFirst = new Button("First");
    Button btnNext = new Button("Next");
    Button btnPrevious = new Button("Previous");
    Button btnLast = new Button("Last");
    Button btnUpdate = new Button("Update");

    FileManager manager = new FileManager("data.bin");

    // For dirty check.
    Address currentAddr = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws IOException {
        // 2. Each row will have an hBox, containing a label and a text field.
        // The hBoxes array will be needed later.
        HBox[] hBoxes = new HBox[H_BOXES];
        Label[] labels = {lblName, lblStreet, lblCity};
        TextField[] growableTextFields = {tfName, tfStreet, tfCity};

        // 3. Set up the hBoxes with properties.
        for (int i = 0; i < TEXT_ROWS; i++) {
            labels[i].setPrefWidth(33);
            hBoxes[i] = new HBox(5, labels[i], growableTextFields[i]);
            HBox.setHgrow(growableTextFields[i], Priority.ALWAYS);
            hBoxes[i].setAlignment(Pos.CENTER_LEFT);
        }

        // 4. Fill the rest of the row with state and zip.
        hBoxes[TEXT_ROWS - 1].getChildren().addAll(lblState, tfState, lblZip, tfZip);

        // 5. Create a buttons hBox.
        HBox buttons = new HBox(5, btnAdd, btnFirst, btnNext, btnPrevious, btnLast, btnUpdate);
        buttons.setAlignment(Pos.CENTER);

        // 6. Add this to the last element of the hBoxes array.
        hBoxes[H_BOXES - 1] = buttons;

        // 7. Add the hBoxes from the array to the vBox.
        // Add the vBox to the outer pane.
        VBox vBox = createVBox(hBoxes);
        vBox.setMaxSize(375, VBox.USE_PREF_SIZE);
        StackPane outerPane = new StackPane();
        outerPane.getChildren().add(vBox);

        // Six handlers below.
        btnAdd.setOnAction(_ -> {
            Address addr = new Address(
                    tfName.getText(),
                    tfStreet.getText(),
                    tfCity.getText(),
                    tfState.getText(),
                    tfZip.getText());
            manager.addRecord(addr);
            currentAddr = addr;
            setButtonStatus();
        });

        btnFirst.setOnAction(_ -> {
            currentAddr = manager.goToFirst();
            updateTextFieldsFromRecord();
            setButtonStatus();
        });

        btnNext.setOnAction(_ -> {
            currentAddr = manager.moveNext();
            updateTextFieldsFromRecord();
            setButtonStatus();
        });

        btnPrevious.setOnAction(_ -> {
            currentAddr = manager.movePrevious();
            updateTextFieldsFromRecord();
            setButtonStatus();
        });

        btnLast.setOnAction(_ -> {
            currentAddr = manager.goToLast();
            updateTextFieldsFromRecord();
            setButtonStatus();
        });

        btnUpdate.setOnAction(_ -> {
            Address addr = new Address(
                    tfName.getText(),
                    tfStreet.getText(),
                    tfCity.getText(),
                    tfState.getText(),
                    tfZip.getText());
            manager.updateRecord(addr);
            currentAddr = addr;
            setButtonStatus();
        });

        // Check the file to try to load the first record.
        if (!manager.isEmpty()) {
            currentAddr = manager.getCurrentRecord();
            updateTextFieldsFromRecord();
            setButtonStatus();
        }

        // Update the buttons based on current text field state.
        for (TextField textField : textFields) {
            textField.textProperty().addListener((_, _, _) -> this.setButtonStatus());
        }

        Scene scene = new Scene(outerPane, 500, 250);
        primaryStage.setTitle("Exercise17_09"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    @Override
    public void stop() {
        manager.closeFile();
    }

    /**
     * Reads the address strings from the file, and they appear in the GUI text fields.
     */
    private void updateTextFieldsFromRecord() {
        tfName.setText(currentAddr.name());
        tfStreet.setText(currentAddr.street());
        tfCity.setText(currentAddr.city());
        tfState.setText(currentAddr.state());
        tfZip.setText(currentAddr.zip());
    }

    /**
     * Text fields are valid if none of them are empty.
     */
    private boolean isValid() {
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * If the current form text doesn't match the current record, isDirty() returns true.
     */
    private boolean isDirty() {
        if (currentAddr == null) {
            return false;
        }
        boolean isClean = currentAddr.name().equals(tfName.getText()) &&
                currentAddr.street().equals(tfStreet.getText()) &&
                currentAddr.city().equals(tfCity.getText()) &&
                currentAddr.state().equals(tfState.getText()) &&
                currentAddr.zip().equals(tfZip.getText());
        return !isClean;
    }

    /**
     * Creates a text field with the desired column count.
     */
    private static TextField createTextField(int columns, int maxSize) {
        TextField textField = new TextField();
        if (columns > 0) {
            textField.setPrefColumnCount(columns);
        }
        textField.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxSize) {
                return change; // Accept the change
            }
            return null; // Reject the change
        }));
        return textField;
    }

    /**
     * Puts the hBoxes in a new vBox and returns the vBox.
     */
    private static VBox createVBox(HBox[] hBoxes) {
        VBox vBox = new VBox(5); // Create a VBox with 5px spacing
        vBox.setPadding(new Insets(5, 5, 5, 5));
        for (HBox hBox : hBoxes) {
            VBox.setMargin(hBox, new Insets(0, 0, 0, 15));
            vBox.getChildren().add(hBox);
        }

        return vBox;
    }

    /**
     * Updates all the form buttons with the correct enabled/disabled status.
     */
    private void setButtonStatus() {
        try {
            boolean atStart = manager.atStart();
            btnFirst.setDisable(atStart);
            btnPrevious.setDisable(atStart);

            boolean atEnd = manager.atEnd();
            btnNext.setDisable(atEnd);
            btnLast.setDisable(atEnd);

            btnAdd.setDisable(!(isValid()));
            btnUpdate.setDisable(!isValid() || manager.isEmpty() || !isDirty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * FileManager stores a RandomAccessFile of Address records and manages reading, writing, and navigation.
 */
class FileManager {
    private static final long REC_SIZE = 182;
    private final RandomAccessFile file;

    public FileManager(String fileName) {
        file = openFile(fileName);
    }

    /**
     * Opens a binary file with the specified name in the current working directory.
     *
     * @param fileName Name of the specified file
     */
    public static RandomAccessFile openFile(String fileName) {
        try {
            // Open with RandomAccessFile for read/write
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            file.seek(0);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns true if the pointer is at the first record.
     */
    public boolean atStart() throws IOException {
        return file.getFilePointer() == 0;
    }

    /**
     * Returns true if the pointer is at the last record.
     */
    public boolean atEnd() throws IOException {
        return file.getFilePointer() >= file.length() - REC_SIZE;
    }

    /**
     * Returns true if the file is empty.
     */
    public boolean isEmpty() throws IOException {
        return file.length() == 0;
    }

    /**
     * Returns the record currently pointed at, or null if file is empty.
     */
    public Address getCurrentRecord() {
        try {
            if (file.getFilePointer() >= file.length()) {
                return null;
            }
            // add to the end of the file
            String name = readFixedString(file, 32);
            String street = readFixedString(file, 32);
            String city = readFixedString(file, 20);
            String state = readFixedString(file, 2);
            String zip = readFixedString(file, 5);
            // seek backwards to start of record
            file.seek(file.getFilePointer() - REC_SIZE);
            return new Address(name, street, city, state, zip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new record to the end of the file.
     */
    public void addRecord(Address addr) {
        try {
            // navigate to the end of the file
            file.seek(file.length());
            writeRecord(addr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the record currently pointed at with new data.
     */
    public void updateRecord(Address addr) {
        try {
            // write at the current position
            writeRecord(addr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeRecord(Address addr) throws IOException {
        writeFixedString(file, addr.name(), 32);
        writeFixedString(file, addr.street(), 32);
        writeFixedString(file, addr.city(), 20);
        writeFixedString(file, addr.state(), 2);
        writeFixedString(file, addr.zip(), 5);
        // always seek backwards to start of record after writing
        file.seek(file.getFilePointer() - REC_SIZE);
    }

    public Address goToFirst() {
        try {
            file.seek(0);
            return getCurrentRecord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Address goToLast() {
        try {
            if (file.length() >= REC_SIZE) {
                file.seek(file.length() - REC_SIZE);
            }
            return getCurrentRecord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Address movePrevious() {
        try {
            if (file.getFilePointer() >= REC_SIZE) {
                file.seek(file.getFilePointer() - REC_SIZE);
            }
            return getCurrentRecord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Address moveNext() {
        try {
            if (file.getFilePointer() < file.length() - REC_SIZE) {
                file.seek(file.getFilePointer() + REC_SIZE);
            }
            return getCurrentRecord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeFile() {
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes exactly length chars at current file position, and advances the file pointer.
     * Text will be truncated or padded to length.
     */
    private static void writeFixedString(RandomAccessFile file, String text, int length) throws IOException {
        if (text.length() >= length) {
            file.writeChars(text.substring(0, length));
        } else {
            file.writeChars(text);
            // Pad the rest with null characters directly
            for (int i = text.length(); i < length; i++) {
                file.writeChar('\u0000');
            }
        }
    }

    /**
     * Reads exactly length chars at the current file position, and advances the file pointer.
     * Excess padding will be discarded.
     */
    private static String readFixedString(RandomAccessFile file, int length) throws IOException {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = file.readChar();
        }
        return new String(chars).replace("\u0000", "");
    }
}

/**
 * Address is a single record in the file.
 */
record Address(
        String name,
        String street,
        String city,
        String state,
        String zip
) {
}
