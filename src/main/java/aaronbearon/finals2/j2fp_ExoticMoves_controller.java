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
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Aaron Blum, CIST 2372 Java 2, Final Project
 * Description: Refer to the Summary
 */
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
    private DatePicker ccExpDate;
    @FXML
    private TextField cvvCode;

    // Purchase error message
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

    // Final Receipt
    @FXML
    private Label receiptCustomer;
    @FXML
    private Label receiptDateTime;
    @FXML
    private Label receiptCarName;
    @FXML
    private Label receiptCarPrice;

    private final Database database = new Database();

    private Car selectedCar = null;
    private PurchaseState purchaseState = PurchaseState.UNSELECTED;


    public void initialize() {
        initializeCheckBoxOptions(brands, Car.BRANDS);
        initializeCheckBoxOptions(types, Car.TYPES);
        initializeCheckBoxOptions(colors, Car.COLORS);

        updatePurchaseFlow();

        // Set up the initial filters.
        updateFilters();

        // The 3 parameters are Observable, oldValue, and newValue
        firstName.textProperty().addListener((_, _, _) -> formTextChanged());
        lastName.textProperty().addListener((_, _, _) -> formTextChanged());
        ccNumber.textProperty().addListener((_, _, _) -> formTextChanged());
        ccExpDate.valueProperty().addListener((_, _, _) -> formTextChanged());
        cvvCode.textProperty().addListener((_, _, _) -> formTextChanged());

        firstName.focusedProperty().addListener((_, _, _) -> formTextChanged());
        lastName.focusedProperty().addListener((_, _, _) -> formTextChanged());
        ccNumber.focusedProperty().addListener((_, _, _) -> formTextChanged());
        ccExpDate.focusedProperty().addListener((_, _, _) -> formTextChanged());
        cvvCode.focusedProperty().addListener((_, _, _) -> formTextChanged());
    }

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

    // Show/hide different nodes based on the PurchaseState enum.
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
        // Clear the list before determining the cars to display
        inventoryFlowPane.getChildren().clear();

        for (Car car : cars) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("car_card.fxml"));
                VBox card = loader.load();

                // Find elements by ID
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
        resetForm();
        selectedCar = car;
        purchaseState = PurchaseState.SELECTED;

        // Set the Image
        var stream = getClass().getResourceAsStream(car.imageName());
        if (stream != null) {
            detailImage.setImage(new Image(stream));
            // Make the image fill width of the right panel
            detailImage.fitWidthProperty().bind(detailsPane.widthProperty().subtract(40));
        }

        // Populate Text
        detailName.setText(car.brand() + " " + car.type());
        detailPrice.setText(String.format("$%,.0f", car.price()));

        String specs = String.format(
                """
                        Color: %s
                        0-60 mph: %.1fs
                        Cylinders: %d
                        Engine: %s""",
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
        resetForm();
        purchaseState = PurchaseState.UNSELECTED;
        updatePurchaseFlow();
    }

    @FXML
    public void purchaseButtonClicked() {
        purchaseState = PurchaseState.FORM;
        updatePurchaseFlow();
    }

    // Make the receipt
    @FXML
    public void completeButtonClicked() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy | h:mm a");
        String formattedDate = now.format(formatter);

        receiptCustomer.setText("CUSTOMER: " + firstName.getText().toUpperCase() + " " + lastName.getText().toUpperCase());
        receiptDateTime.setText("PURCHASED: " + formattedDate);

        if (selectedCar != null) {
            receiptCarName.setText(selectedCar.brand().toUpperCase() + " " + selectedCar.type().toUpperCase());
            receiptCarPrice.setText(String.format("TOTAL: $%,.2f", selectedCar.price()));
        }

        // Print the receipt
        purchaseState = PurchaseState.RECEIPT;
        updatePurchaseFlow();
    }

    @FXML
    public void formTextChanged() {
        boolean isValid = new NameFieldValidator(firstName, errFirstName).check(firstName.getText());
        // Note: Use a single & to prevent short-circuit evaluation.
        // Each new validator must be checked to determine whether that error message is necessary.
        isValid = isValid & new NameFieldValidator(lastName, errLastName).check(lastName.getText());
        isValid = isValid & new NumValidator(ccNumber, 16, errCcNum).check(ccNumber.getText());
        isValid = isValid & new ExpDateValidator(ccExpDate, errCcExpDate).check(ccExpDate.getValue());
        isValid = isValid & new NumValidator(cvvCode, 3, errCvvCode).check(cvvCode.getText());
        completeButton.setDisable(!isValid);
    }

    // Clear the form anytime the right side image view is closed or another car is clicked.
    private void resetForm() {
        // Clear the text fields
        firstName.clear();
        lastName.clear();
        ccNumber.clear();
        ccExpDate.setValue(null);
        cvvCode.clear();

        ccExpDate.getEditor().clear();

        // Clear error labels
        errFirstName.setText("");
        errLastName.setText("");
        errCcNum.setText("");
        errCcExpDate.setText("");
        errCvvCode.setText("");
    }

    private enum PurchaseState {
        UNSELECTED,
        SELECTED,
        FORM,
        RECEIPT
    }
}

/**
 * This abstract class is used for validating the content of a field.
 * Subclasses specify the type of field and the validation conditions.
 * If the conditions aren't met, an exception instance is stored in a variable in the check method.
 *
 * @param <T> Generic type. Subclasses provide a concrete type.
 */
abstract class FieldValidator<T> {
    private final Node input;
    private final Label errLabel;

    public FieldValidator(Node input, Label errLabel) {
        this.input = input;
        this.errLabel = errLabel;
    }

    // Every FieldValidator instance needs to handle the validation conditions.
    // This method isn't overridden in subclasses.
    public boolean check(T value) {
        Exception err = validate(value);
        if (err == null) {
            return true;
        }
        if (input.isFocused()) {
            errLabel.setText(""); // ignore errors if the user is typing
        } else {
            errLabel.setText(err.getMessage());
        }
        return false;
    }

    // Every subclass must provide its own validation conditions.
    public abstract Exception validate(T value);
}

/**
 * Check the text field for a non-empty String of only letters.
 */
class NameFieldValidator extends FieldValidator<String> {
    public NameFieldValidator(TextField input, Label errLabel) {
        super(input, errLabel);
    }

    @Override
    public Exception validate(String value) {
        if (value.isEmpty()) {
            return new Exception(); // no error message; but don't enable the button
        }

        // Regex: ^ (start), [a-zA-Z] (letters), + (one or more), $ (end)
        if (!value.matches("^[a-zA-Z]+$")) {
            return new Exception("Error, Invalid Name");
        }
        return null;
    }
}

/**
 * Check the text field for a non-empty String of only digits.
 */
class NumValidator extends FieldValidator<String> {
    private final int count;

    public NumValidator(TextField input, int count, Label errLabel) {
        super(input, errLabel);
        this.count = count;
    }

    @Override
    public Exception validate(String value) {
        if (value.isEmpty()) {
            return new Exception(); // no error message; but don't enable the button
        }
        if (!value.matches("^\\d{" + count + "}$")) {
            return new Exception("Error, expected " + count + " digit number");
        }
        return null;
    }
}

/**
 * Check the date picker for a non-empty valid date not before the current date.
 */
class ExpDateValidator extends FieldValidator<LocalDate> {
    public ExpDateValidator(DatePicker input, Label errLabel) {
        super(input, errLabel);
    }

    @Override
    public Exception validate(LocalDate value) {
        if (value == null) {
            return new Exception(); // no error message; but don't enable the button
        }
        if (value.isBefore(LocalDate.now())) {
            return new Exception("Credit Card Expired");
        }
        if (value.isAfter(LocalDate.now().plusYears(10))) {
            return new Exception("Credit Card Expiration Invalid");
        }
        return null;
    }
}

/*

Refer to documentation and updated doc.

*/
