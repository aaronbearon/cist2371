package aaronbearon.midterm3;

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
import java.util.function.UnaryOperator;

/**
 * Aaron Blum, CIST 2373 Java 3, Midterm Project, Northwind MySQL.
 */
public class Northwind extends Application {
    private final Connection conn = createDatabaseConnection();

    private static Connection createDatabaseConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // This application is a tabbed view layout, each tab is specified here.
    private final TabbedView[] tabbedViews = new TabbedView[]{
            // SQL statements and validation conditions
            new TabbedView(conn, "Order Total",
                    "Enter order number (up to 5 digits):",
                    """
                            SELECT SUM(Quantity * (UnitPrice - Discount)) AS `Order Total`
                                FROM `order details`
                                WHERE OrderID = ?;
                            """,
                    change -> {
                        if (change.getControlNewText().length() > 5) {
                            return null;
                        }
                        if (!change.getControlNewText().matches("[0-9]*")) {
                            return null;
                        }
                        return change;
                    }),
            new TabbedView(conn, "Order Details",
                    "Enter order number (up to 5 digits):",
                    """
                            SELECT o.OrderDate, o.Freight, p.ProductName, p.UnitPrice AS 'Product Unit Price', od.Quantity, od.UnitPrice AS 'Order Unit Price', od.Discount
                            	FROM `order details` od, orders o, products p
                            	WHERE o.OrderID = od.OrderID
                            		AND od.ProductID = p.ProductID
                                    AND o.OrderID = ?;
                            """,
                    change -> {
                        if (change.getControlNewText().length() > 5) {
                            return null;
                        }
                        if (!change.getControlNewText().matches("[0-9]*")) {
                            return null;
                        }
                        return change;
                    }),
            new TabbedView(conn, "State Customers",
                    "Enter two-digit state code:",
                    """
                            SELECT ContactName, City
                            	FROM customers
                                WHERE Country = 'USA' AND Region = ?
                                ORDER BY City;
                            """,
                    change -> {
                        change.setText(change.getText().toUpperCase());
                        if (change.getControlNewText().length() > 2) {
                            return null;
                        }
                        if (!change.getControlNewText().matches("[A-Z]*")) {
                            return null;
                        }
                        return change;
                    }),
            new TabbedView(conn, "Employee Birthdays",
                    "Enter four-digit year:",
                    """
                            SELECT FirstName, LastName
                            	FROM employees
                                WHERE YEAR(BirthDate) = ?
                                ORDER BY lastName;
                            """,
                    change -> {
                        if (change.getControlNewText().length() > 4) {
                            return null;
                        }
                        if (!change.getControlNewText().matches("[0-9]*")) {
                            return null;
                        }
                        return change;
                    }),
    };

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        for (TabbedView tabHolder : tabbedViews) {
            tabPane.getTabs().add(tabHolder.getTab());
        }
        Scene scene = new Scene(tabPane, 800, 500);
        primaryStage.setTitle("Northwind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Ensures the database connection is closed before exiting.
    @Override
    public void stop() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class TabbedView {
    // Db connection
    private final Connection conn;
    // The query we will execute.
    private final String sql;
    // The main view container.
    private final Tab tab = new Tab();
    // Main text field for data entry.
    private final TextField tf = new TextField();
    // Output grid for returned SQL data.
    private final GridPane gridPane = new GridPane();

    public TabbedView(Connection conn, String tabTitle, String promptLabel, String sql, UnaryOperator<TextFormatter.Change> textValidator) {
        this.conn = conn;
        this.sql = sql;

        // Submit button.
        Button btn = new Button("Submit");

        // Build the view.
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(new Label(promptLabel), tf, btn);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        borderPane.setCenter(scrollPane);

        tab.setText(tabTitle);
        tab.setContent(borderPane);

        // Wire up event handlers.
        tf.setTextFormatter(new TextFormatter<String>(textValidator));
        tf.setOnAction(_ -> refreshData(tf.getText()));
        btn.setOnAction(_ -> refreshData(tf.getText()));
    }

    public Tab getTab() {
        return this.tab;
    }

    // Fetch data from the table and display it in the data grid.
    private void refreshData(String userField) {
        // Fetch the data.
        List<List<String>> rows;
        try {
            rows = queryTableRows(userField);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Redraw the grid.
        gridPane.getChildren().clear();
        for (int col = 0; col < rows.getFirst().size(); col++) {
            Label headerLabel = new Label(rows.getFirst().get(col));
            headerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

            StackPane cell = createCell(headerLabel, "#2c3e50"); // Dark background for header
            gridPane.add(cell, col, 0);
        }

        // Populates data rows (Rows 1 to N - 1)
        for (int row = 1; row < rows.size(); row++) {
            // Populates data cells in row
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

    // Helper method to create a bordered cell container with style
    private static StackPane createCell(Label content, String backgroundColor) {
        StackPane cell = new StackPane(content);
        cell.setAlignment(Pos.CENTER);
        cell.setStyle(String.format("-fx-background-color: %s; -fx-border-color: #dcdde1; -fx-border-width: 1;", backgroundColor));
        cell.setPadding(new Insets(1, 8, 1, 8));
        return cell;
    }

    // Returns a list of column headings and rows
    private List<List<String>> queryTableRows(String userField) throws SQLException {
        List<List<String>> rows = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userField);
            ResultSet rs = pstmt.executeQuery();

            // Use metaData to print column names as aliases
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String> labels = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                labels.add(metaData.getColumnLabel(i));
            }

            rows.add(labels);
            while (rs.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    rowData.add(value);
                }
                rows.add(rowData);
            }
        }
        return rows;
    }
}
