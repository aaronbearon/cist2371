package aaronbearon.chapter31.lesson8;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 10
 * Description: App that shows facts about countries.
 */
public class J208_310controller {

    @FXML
    private TableView<Country> tableView;
    @FXML
    private TextField tfCountry;
    @FXML
    private TextField tfCapital;
    @FXML
    private TextField tfPopulation;
    @FXML
    private CheckBox chkDemocratic;

    private ObservableList<Country> data;

    @FXML
    public void initialize() {
        // Defined tableView as a private field and assigned its items to tableView.
        data = FXCollections.observableArrayList(
                new Country("USA", "Washington DC", 280, true),
                new Country("Canada", "Ottawa", 32, true),
                new Country("United Kingdom", "London", 60, true),
                new Country("Germany", "Berlin", 83, true),
                new Country("France", "Paris", 60, true));

        tableView.setItems(data);
    }

    // Handler
    @FXML
    public void handleAddRow() {
        data.add(new Country(tfCountry.getText(), tfCapital.getText(),
                Double.parseDouble(tfPopulation.getText()),
                chkDemocratic.isSelected()));
        tfCountry.clear();
        tfCapital.clear();
        tfPopulation.clear();
    }

    // Same class implementation
    public static class Country {
        private final SimpleStringProperty country;
        private final SimpleStringProperty capital;
        private final SimpleDoubleProperty population;
        private final SimpleBooleanProperty democratic;

        private Country(String country, String capital,
                        double population, boolean democratic) {
            this.country = new SimpleStringProperty(country);
            this.capital = new SimpleStringProperty(capital);
            this.population = new SimpleDoubleProperty(population);
            this.democratic = new SimpleBooleanProperty(democratic);
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
    }
}

/*

Converted everything to Scene Builder.

*/
