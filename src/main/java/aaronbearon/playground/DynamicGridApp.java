package aaronbearon.playground;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DynamicGridApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(2); // Spacing between columns
        gridPane.setVgap(2); // Spacing between rows

        int numCols = 5;
        int numRows = 10;

        // Populate header row (Row 0)
        for (int col = 0; col < numCols; col++) {
            if (col == 0) {
                Label headerLabel = new Label("Headers Everywhere! " + (col + 1));
                headerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

                StackPane cell = createCell(headerLabel, "#2c3e50"); // Dark background for header
                gridPane.add(cell, col, 0);
            } else {
                Label headerLabel = new Label("H! " + (col + 1));
                headerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

                StackPane cell = createCell(headerLabel, "#2c3e50"); // Dark background for header
                gridPane.add(cell, col, 0);
            }
        }

        // Populate data rows (Rows 1 to N)
        for (int row = 1; row <= numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Label dataLabel = new Label("R" + row + "C" + col + "\r\n" + "!");
                dataLabel.setStyle("-fx-text-fill: #333333;");

                // Alternate background color for clean spreadsheet look
                String bgColor = (row % 2 == 0) ? "#f8f9fa" : "#ffffff";
                StackPane cell = createCell(dataLabel, bgColor);

                gridPane.add(cell, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Dynamic GridPane Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create a bordered cell container
    private StackPane createCell(Label content, String backgroundColor) {
        StackPane cell = new StackPane(content);
        cell.setAlignment(Pos.CENTER);
        // cell.setPrefSize(100, 35);
        cell.setStyle(String.format("-fx-background-color: %s; -fx-border-color: #dcdde1; -fx-border-width: 1;", backgroundColor));
        return cell;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
