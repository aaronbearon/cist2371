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
import java.util.Arrays;
import java.util.List;

public class Northwind extends Application {
    private final TabHolderContainer tabHolders = new TabHolderContainer(new TabHolder[]{
            new TabHolder("Order Total",
                    "Enter order number to view total cost (but not freight). ",
                    """
                            SELECT SUM(Quantity * (UnitPrice - Discount)) AS `Order Total`
                            	FROM `order details`
                            	WHERE OrderID = ?;
                            """),
            new TabHolder("Order Details",
                    "Enter order number to view details. ",
                    """
                            SELECT o.OrderDate, o.Freight, p.ProductName, p.UnitPrice AS 'Product Unit Price', od.Quantity, od.UnitPrice AS 'Order Unit Price', od.Discount
                            	FROM `order details` od, orders o, products p
                            	WHERE o.OrderID = od.OrderID
                            		AND od.ProductID = p.ProductID
                                    AND o.OrderID = ?;
                            """),
            new TabHolder("State Customers",
                    "Enter state to see cities customers live in that state. ",
                    """
                            SELECT ContactName, City
                            	FROM customers
                                WHERE Country = 'USA' AND Region = ?
                                ORDER BY City;
                            """),
            new TabHolder("Employee Birthdays",
                    "Enter year to see all employees born that year. ",
                    """
                            SELECT FirstName, LastName
                            	FROM employees
                                WHERE YEAR(BirthDate) = ?
                                ORDER BY lastName;
                            """),
    });

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        for (int i = 0; i < tabHolders.size(); i++) {
            tabPane.getTabs().add(tabHolders.get(i).tab);
        }
        Scene scene = new Scene(tabPane, 450, 300);
        primaryStage.setTitle("Northwind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Ensure the database connection is closed before exiting.
    @Override
    public void stop() throws Exception {
        for (int i = 0; i <= tabHolders.size(); i++) {
            tabHolders.get(i).conn.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class TabHolder {
    public final Connection conn;
    public final Tab tab = new Tab();
    public final HBox hBox = new HBox();
    public final Label lbl = new Label();
    public final TextField tf = new TextField();
    public final Button btn = new Button("Submit");
    public final GridPane gridPane = new GridPane();
    public final ScrollPane scrollPane = new ScrollPane(gridPane);
    public final BorderPane borderPane = new BorderPane();
    public final String sql;

    public TabHolder(String tabTitle, String promptLabel, String sql) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tab.setText(tabTitle);
        lbl.setText(promptLabel);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(lbl, tf, btn);
        borderPane.setTop(hBox);
        borderPane.setCenter(scrollPane);
        tab.setContent(borderPane);
        this.sql = sql;
        btn.setOnAction(_ -> refreshData(tf.getText()));
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

    private List<List<String>> queryTableRows(String userField) throws SQLException {
        List<List<String>> rows = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userField);
            ResultSet rs = pstmt.executeQuery();

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

class TabHolderContainer {
    private final TabHolder[] tabHolders;

    public TabHolderContainer(TabHolder[] tabHolders) {
        this.tabHolders = Arrays.copyOf(tabHolders, tabHolders.length);
    }

    public int size() {
        return tabHolders.length;
    }

    public TabHolder get(int i) {
        return tabHolders[i];
    }
}
