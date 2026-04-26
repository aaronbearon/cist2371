package aaronbearon.finals2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class j2fp_ExoticMoves_controller {
    // Right hand side purchase flow.
    @FXML
    private ScrollPane purchasePanel;
    @FXML
    private HBox purchaseButtonPane;
    @FXML
    private VBox purchaseForm;
    @FXML
    private VBox receipt;

    // Car details
    @FXML
    private VBox detailsPane;
    @FXML
    private ImageView detailImage;
    @FXML
    private Label detailName;
    @FXML
    private Label detailPrice;
    @FXML
    private Label detailSpecs;

    // Purchase form
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ccNumber;
    @FXML
    private TextField ccExpDate;
    @FXML
    private TextField cvvCode;

    @FXML
    private Label errFirstName;
    @FXML
    private Label errLastName;
    @FXML
    private Label errCcNum;
    @FXML
    private Label errCcExpDate;
    @FXML
    private Label errCvvCode;

    @FXML
    private Button completeButton;


    // Main image area field
    @FXML
    private FlowPane inventoryFlowPane;

    // Filter fields
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
    @FXML
    private Slider cylinderMin;
    @FXML
    private Slider cylinderMax;
    @FXML
    private Label cylinderMinLabel;
    @FXML
    private Label cylinderMaxLabel;

    private final Database database = new Database();

    private Car selectedCar = null;
    private PurchaseState purchaseState = PurchaseState.UNSELECTED;


    public void initialize() {
        // TODO: we could actually initialize these checkboxes by using database queries instead of constants.
        initializeCheckBoxOptions(brands, Car.BRANDS);
        initializeCheckBoxOptions(types, Car.TYPES);
        initializeCheckBoxOptions(colors, Car.COLORS);

        updatePurchaseFlow();

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
            cb.setOnAction((_) -> updateFilters());
            parent.getChildren().add(cb);
        }
    }

    @FXML
    public void updateFilters() {
        priceMinLabel.setText(String.format("Min: $%,.0f", priceMin.getValue()));
        priceMaxLabel.setText(String.format("Max: $%,.0f", priceMax.getValue()));
        timeMinLabel.setText(String.format("Min: %,.1f", timeMin.getValue()));
        timeMaxLabel.setText(String.format("Max: %,.1f", timeMax.getValue()));
        cylinderMinLabel.setText(String.format("Min: %,d", (int) cylinderMin.getValue()));
        cylinderMaxLabel.setText(String.format("Max: %,d", (int) cylinderMax.getValue()));

        // Force a refresh on the car list.
        List<Car> cars = database.getFilteredCars(
                selectedCheckboxItems(brands),
                selectedCheckboxItems(types),
                selectedCheckboxItems(colors),
                priceMin.getValue(),
                priceMax.getValue(),
                timeMin.getValue(),
                timeMax.getValue(),
                isElectricValue(),
                (int) cylinderMin.getValue(),
                (int) cylinderMax.getValue()
        );

        displayCars(cars);
    }

    public void updatePurchaseFlow() {
        if (purchaseState == PurchaseState.UNSELECTED) {
            purchasePanel.setVisible(false);
            purchasePanel.setManaged(false);
        } else {
            purchasePanel.setVisible(true);
            purchasePanel.setManaged(true);
        }

        if (purchaseState == PurchaseState.SELECTED) {
            purchaseButtonPane.setVisible(true);
            purchaseButtonPane.setManaged(true);
        } else {
            purchaseButtonPane.setVisible(false);
            purchaseButtonPane.setManaged(false);
        }

        if (purchaseState == PurchaseState.FORM) {
            purchaseForm.setVisible(true);
            purchaseForm.setManaged(true);
        } else {
            purchaseForm.setVisible(false);
            purchaseForm.setManaged(false);
        }

        if (purchaseState == PurchaseState.RECEIPT) {
            receipt.setVisible(true);
            receipt.setManaged(true);
        } else {
            receipt.setVisible(false);
            receipt.setManaged(false);
        }

        // Make sure the form errors up to date.
        formTextChanged();
    }

    private void displayCars(List<Car> cars) {
        inventoryFlowPane.getChildren().clear();

        for (Car car : cars) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("car_card.fxml"));
                VBox card = loader.load();

                // Find elements by ID (excluding performance now)
                Label name = (Label) card.lookup("#carName");
                ImageView iv = (ImageView) card.lookup("#carImage");
                Label price = (Label) card.lookup("#carPrice");

                // Populate the data
                name.setText(car.brand() + " " + car.type());
                price.setText(String.format("$%,.0f", car.price()));

                // Load Image
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(car.imageName())));
                iv.setImage(img);

                inventoryFlowPane.getChildren().add(card);
                card.setOnMouseClicked(_ -> showCarDetails(car));
                // Change mouse shape on hover
                card.setCursor(Cursor.HAND);

            } catch (IOException e) {
                System.err.println("Error loading car card: " + e.getMessage());
            }
        }
    }

    // Turns a list of checkboxes into a list of "selected" string values like brands or colors.
    private Set<String> selectedCheckboxItems(Parent parent) {
        Set<String> result = new HashSet<>();
        for (Node child : parent.getChildrenUnmodifiable()) {
            if (child instanceof CheckBox cb && cb.isSelected()) {
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

    private void showCarDetails(Car car) {
        selectedCar = car;
        purchaseState = PurchaseState.SELECTED;

        // 1. Set the Image
        var stream = getClass().getResourceAsStream(car.imageName());
        if (stream != null) {
            detailImage.setImage(new Image(stream));
            // Make the image fill width of the right panel
            detailImage.fitWidthProperty().bind(detailsPane.widthProperty().subtract(40));
        }

        // 2. Populate Text
        detailName.setText(car.brand() + " " + car.type());
        detailPrice.setText(String.format("$%,.0f", car.price()));

        String specs = String.format(
                "Color: %s\n" +
                        "0-60 mph: %.1fs\n" +
                        "Cylinders: %d\n" +
                        "Engine: %s",
                car.color(),
                car.zeroToSixty(),
                car.cylinders(),
                car.isElectric() ? "Electric Motor" : "Internal Combustion"
        );
        detailSpecs.setText(specs);

        updatePurchaseFlow();
    }

    @FXML
    public void updateMinCylinders() {
        if (cylinderMax.getValue() < cylinderMin.getValue()) {
            cylinderMax.setValue(cylinderMin.getValue());
        }
        updateFilters();
    }

    @FXML
    public void updateMaxCylinders() {
        if (cylinderMax.getValue() < cylinderMin.getValue()) {
            cylinderMin.setValue(cylinderMax.getValue());
        }
        updateFilters();
    }

    @FXML
    public void updateMinPrice() {
        if (priceMax.getValue() < priceMin.getValue()) {
            priceMax.setValue(priceMin.getValue());
        }
        updateFilters();
    }

    @FXML
    public void updateMaxPrice() {
        if (priceMax.getValue() < priceMin.getValue()) {
            priceMin.setValue(priceMax.getValue());
        }
        updateFilters();
    }

    @FXML
    public void updateMinTime() {
        if (timeMax.getValue() < timeMin.getValue()) {
            timeMax.setValue(timeMin.getValue());
        }
        updateFilters();
    }

    @FXML
    public void updateMaxTime() {
        if (timeMax.getValue() < timeMin.getValue()) {
            timeMin.setValue(timeMax.getValue());
        }
        updateFilters();
    }

    @FXML
    public void closeDetailsClicked() {
        purchaseState = PurchaseState.UNSELECTED;
        updatePurchaseFlow();
    }

    @FXML
    public void purchaseButtonClicked() {
        purchaseState = PurchaseState.FORM;
        updatePurchaseFlow();
    }

    @FXML
    public void completeButtonClicked() {
        purchaseState = PurchaseState.RECEIPT;
        updatePurchaseFlow();
    }

    @FXML
    public void formTextChanged() {
        errFirstName.setText("");
        errLastName.setText("");
        errCcNum.setText("");
        errCcExpDate.setText("");
        errCvvCode.setText("");

        // TODO: check every input field.
        // If any fields contain non-empty, invalid data, update the error label. Otherwise, clear the error label.

        // TODO: if every field is valid and no fields are empty, enable the complete purchase button.
    }

    private enum PurchaseState {
        UNSELECTED,
        SELECTED,
        FORM,
        RECEIPT
    }
}
