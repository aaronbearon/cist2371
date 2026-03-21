package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 8
 * Description: Add a Greenwich Mean Time column with time information.
 */
public class J208_38 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        TableView<Country> tableView = new TableView<>();
        ObservableList<Country> data =
                FXCollections.observableArrayList(
                        //* Added a gmt string as the last argument.
                        new Country("USA", "Washington DC", 280, true, "GMT-5 to GMT-10"),
                        new Country("Canada", "Ottawa", 32, true, "GMT-3.5 to GMT-8"),
                        new Country("United Kingdom", "London", 60, true, "GMT+0"),
                        new Country("Germany", "Berlin", 83, true, "GMT+1"),
                        new Country("France", "Paris", 60, true, "GMT+1"));
        tableView.setItems(data);

        TableColumn countryColumn = new TableColumn("Country");
        countryColumn.setMinWidth(100);
        countryColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("country"));

        TableColumn capitalColumn = new TableColumn("Capital");
        capitalColumn.setMinWidth(100);
        capitalColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("capital"));

        TableColumn populationColumn =
                new TableColumn("Population (million)");
        populationColumn.setMinWidth(200);
        populationColumn.setCellValueFactory(
                new PropertyValueFactory<Country, Double>("population"));

        TableColumn democraticColumn =
                new TableColumn("Is Democratic?");
        democraticColumn.setMinWidth(200);
        democraticColumn.setCellValueFactory(
                new PropertyValueFactory<Country, Boolean>("democratic"));

        //* Added the new column definition.
        TableColumn gmtColumn = new TableColumn("Greenwich Mean Time");
        gmtColumn.setMinWidth(200);
        gmtColumn.setCellValueFactory(
                new PropertyValueFactory<Country, String>("gmt"));

        //* New column is added here.
        tableView.getColumns().addAll(countryColumn, capitalColumn,
                populationColumn, democraticColumn, gmtColumn);

        Pane pane = new Pane();
        pane.getChildren().add(tableView);
        Scene scene = new Scene(pane, 900, 500);
        primaryStage.setTitle("TableViewDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    public static class Country {
        private final SimpleStringProperty country;
        private final SimpleStringProperty capital;
        private final SimpleDoubleProperty population;
        private final SimpleBooleanProperty democratic;
        //* New field
        private final SimpleStringProperty gmt;

        private Country(String country, String capital,
                        double population, boolean democratic, String gmt) {
            this.country = new SimpleStringProperty(country);
            this.capital = new SimpleStringProperty(capital);
            this.population = new SimpleDoubleProperty(population);
            this.democratic = new SimpleBooleanProperty(democratic);
            //* Set new parameter
            this.gmt = new SimpleStringProperty(gmt);
        }

        public String getCountry() {
            return country.get();
        }

        public void setCountry(String country) {
            this.country.set(country);
        }

        public String getCapital() {
            return capital.get();
        }

        public void setCapital(String capital) {
            this.capital.set(capital);
        }

        public double getPopulation() {
            return population.get();
        }

        public void setPopulation(double population) {
            this.population.set(population);
        }

        public boolean isDemocratic() {
            return democratic.get();
        }

        public void setDemocratic(boolean democratic) {
            this.democratic.set(democratic);
        }

        //* New getter and setter
        public String getGmt() {
            return gmt.get();
        }

        public void setGmt(String gmt) {
            this.gmt.set(gmt);
        }
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
