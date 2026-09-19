package aaronbearon.chapter34;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise32_06 extends Application {
    private Connection conn;

    // Our main data table.
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabook", "root", "password");

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);

        Label lblTable = new Label("Table Name");

        // Fill up the combo box with table names from SQL.
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(queryTableNames());
        comboBox.getSelectionModel().select(0);

        // Action button.
        Button btnShow = new Button("Show Contents");
        btnShow.setOnAction(_ -> refreshData(comboBox.getValue()));

        hBox.getChildren().addAll(lblTable, comboBox, btnShow);

        // Scroll bars for data grid.
        ScrollPane scrollPane = new ScrollPane(gridPane);

        BorderPane outerPane = new BorderPane();
        outerPane.setTop(hBox);
        outerPane.setCenter(scrollPane);

        Scene scene = new Scene(outerPane, 600, 400);
        primaryStage.setTitle("Exercise32_06");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    // Fetch data from the table and display it in the data grid.
    private void refreshData(String tableName) {
        // Fetch the data.
        List<String> columnNames;
        List<List<String>> rows;
        try {
            columnNames = queryTableColumnNames(tableName);
            rows = queryTableRows(tableName);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Redraw the grid.
        gridPane.getChildren().clear();
        for (int col = 0; col < columnNames.size(); col++) {
            Label headerLabel = new Label(columnNames.get(col));
            headerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

            StackPane cell = createCell(headerLabel, "#2c3e50"); // Dark background for header
            gridPane.add(cell, col, 0);
        }

        // Populate data rows (Rows 1 to N)
        for (int row = 0; row < rows.size(); row++) {
            for (int col = 0; col < rows.get(row).size(); col++) {
                Label dataLabel = new Label(rows.get(row).get(col));
                dataLabel.setStyle("-fx-text-fill: #333333;");

                // Alternate background color for clean spreadsheet look
                String bgColor = (row % 2 == 0) ? "#f8f9fa" : "#ffffff";
                StackPane cell = createCell(dataLabel, bgColor);
                gridPane.add(cell, col, row + 1);
            }
        }
    }

    // Helper method to create a bordered cell container
    private static StackPane createCell(Label content, String backgroundColor) {
        StackPane cell = new StackPane(content);
        cell.setAlignment(Pos.CENTER);
        cell.setStyle(String.format("-fx-background-color: %s; -fx-border-color: #dcdde1; -fx-border-width: 1;", backgroundColor));
        cell.setPadding(new Insets(1, 8, 1, 8));
        return cell;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private List<String> queryTableNames() throws SQLException {
        List<String> tables = new ArrayList<>();
        ResultSet resultSet = conn.createStatement().executeQuery("SHOW TABLES;");
        while (resultSet.next()) {
            String tableName = resultSet.getString(1);
            tables.add(tableName);
        }
        tables.sort(String.CASE_INSENSITIVE_ORDER);
        return tables.stream().map(this::toTitleCase).toList();
    }

    private List<String> queryTableColumnNames(String table) throws SQLException {
        List<String> columns = new ArrayList<>();

        final String queryColumnsSql = """
                SELECT COLUMN_NAME
                FROM INFORMATION_SCHEMA.COLUMNS
                WHERE TABLE_SCHEMA = 'javabook' AND TABLE_NAME = ?
                ORDER BY ORDINAL_POSITION;
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(queryColumnsSql)) {
            // Bind dynamic values to the placeholders (Indexes start at 1)
            pstmt.setString(1, table);

            // Execute the statement
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String columnName = resultSet.getString(1);
                columns.add(columnName);
            }
        }
        return columns;
    }

    private List<List<String>> queryTableRows(String table) throws SQLException {
        List<List<String>> rows = new ArrayList<>();

        // We safely use the table name in the query since it comes from our own dropdown selection
        String queryRowsSql = "SELECT * FROM " + table;

        try (PreparedStatement pstmt = conn.prepareStatement(queryRowsSql)) {
            ResultSet resultSet = pstmt.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    rowData.add(value);
                }

                rows.add(rowData);
            }
        }
        return rows;
    }

    private String toTitleCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
