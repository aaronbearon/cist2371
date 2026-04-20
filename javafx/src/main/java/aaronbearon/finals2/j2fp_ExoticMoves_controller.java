package aaronbearon.finals2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class j2fp_ExoticMoves_controller {
    @FXML
    private VBox brands;
    @FXML
    private VBox types;
    @FXML
    private VBox colors;
    @FXML
    private ToggleGroup powerTrain;
    @FXML
    private Slider priceMin;
    @FXML
    private Slider priceMax;
    @FXML
    private Label priceMinLabel;
    @FXML
    private Label priceMaxLabel;
    @FXML
    private Slider timeMin;
    @FXML
    private Slider timeMax;
    @FXML
    private Label timeMinLabel;
    @FXML
    private Label timeMaxLabel;

    private final Database database = new Database();

    public void initialize() throws Exception {
        // TODO: we could actually initialize these checkboxes by using database queries instead of constants.
        initializeCheckBoxOptions(brands, Car.BRANDS);
        initializeCheckBoxOptions(types, Car.TYPES);
        initializeCheckBoxOptions(colors, Car.COLORS);

        // Set up the initial filters.
        updateFilters();
    }

    @FXML
    private TextArea queryTest;

    public void initializeCheckBoxOptions(VBox parent, String[] options) {
        parent.getChildren().clear();
        for (String option : options) {
            CheckBox cb = new CheckBox(option);

            // Add a listener to trigger whenever the value changes.
            cb.setOnAction((e) -> updateFilters());
            parent.getChildren().add(cb);
        }
    }

    @FXML
    public void updateFilters() {
        priceMinLabel.setText(String.format("Min: $%,.0f", priceMin.getValue()));
        priceMaxLabel.setText(String.format("Max: $%,.0f", priceMax.getValue()));
        timeMinLabel.setText(String.format("Min: %,.1f", timeMin.getValue()));
        timeMaxLabel.setText(String.format("Max: %,.1f", timeMax.getValue()));

        // Force a refresh on the car list.
        List<Car> cars = database.getFilteredCars(
                selectedCheckboxItems(brands),
                selectedCheckboxItems(types),
                selectedCheckboxItems(colors),
                priceMin.getValue(),
                priceMax.getValue(),
                timeMin.getValue(),
                timeMax.getValue(),
                isElectricValue()
        );

        // TODO: actually build some kind of list display with images on all the cars. This is just debug text.
        queryTest.setText(String.join("\n", cars.stream().map(Car::toString).toList()));
    }

    // Turns a list of checkboxes into a list of "selected" string values like brands or colors.
    private List<String> selectedCheckboxItems(Parent parent) {
        List<String> result = new ArrayList<>();
        for (Node child : parent.getChildrenUnmodifiable()) {
            CheckBox cb = (CheckBox) child;
            if (cb.isSelected()) {
                result.add(cb.getText());
            }
        }
        return result;
    }

    // Turns the power train radio buttons into a True, False, or Null for electric filter.
    private Boolean isElectricValue() {
        String v = (String) powerTrain.getSelectedToggle().getUserData();
        if (v.isEmpty()) {
            return null;
        }
        return Boolean.valueOf(v);
    }
}
