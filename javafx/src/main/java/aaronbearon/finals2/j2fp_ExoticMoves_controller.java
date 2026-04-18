package aaronbearon.finals2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class j2fp_ExoticMoves_controller {
    Jdbi jdbi;

    @FXML
    public VBox brands;
    @FXML
    public VBox colors;
    @FXML
    public VBox types;
    @FXML
    public ToggleGroup powerTrain;

    public void initialize() throws Exception {
        URL resource = getClass().getResource("/aaronbearon/finals2/cars.csv");
        assert(resource != null);
        String path = Path.of(resource.toURI()).toString().replace('\\', '/');

        jdbi = Jdbi.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        jdbi.registerRowMapper(Car.class, ConstructorMapper.of(Car.class));
        jdbi.withHandle(handle -> {
            handle.execute("CREATE TABLE cars AS SELECT * FROM CSVREAD('" + path + "');");
            return null;
        });

        // TODO: we could actually initialize these checkboxes by using database queries instead of constants.

        initializeCheckBoxOptions(brands, Car.BRANDS);
        initializeCheckBoxOptions(colors, Car.COLORS);
        initializeCheckBoxOptions(types, Car.TYPES);
        updateFilters(null);
    }

    @FXML
    public TextArea queryTest;

    public void initializeCheckBoxOptions(VBox parent, String[] options) {
        parent.getChildren().clear();
        for (String option : options) {
            CheckBox cb = new CheckBox(option);

            // Add a listener to trigger whenever the value changes.
            cb.setOnAction(this::updateFilters);
            parent.getChildren().add(cb);
        }
    }

    @FXML
    public void updateFilters(ActionEvent e) {
        // Force a refresh on the car list.
        List<Car> cars = getFilteredCars(
                selectedCheckboxItems(brands),
                selectedCheckboxItems(colors),
                selectedCheckboxItems(types),
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

    // Query the database for a list of cars matching the selected filters.
    public List<Car> getFilteredCars(List<String> selectedBrands,
                                     List<String> selectedColors,
                                     List<String> selectedTypes,
                                     Boolean isElectric) {

        return jdbi.withHandle(handle -> {
            // Build a query using strings because we need a dynamic number of filters.
            StringBuilder sql = new StringBuilder("SELECT * FROM cars WHERE 1=1");
            if (!selectedBrands.isEmpty()) {
                sql.append(" AND brand IN (<brands>)");
            }
            if (!selectedColors.isEmpty()) {
                sql.append(" AND color IN (<colors>)");
            }
            if (!selectedTypes.isEmpty()) {
                sql.append(" AND type IN (<types>)");
            }
            if (isElectric != null) {
                sql.append(" AND isElectric = :isElectric");
            }
            // TODO: more filters?

            // Bind the query values that we actually used.
            var query = handle.createQuery(sql.toString());
            if (!selectedBrands.isEmpty()) {
                query.bindList("brands", selectedBrands);
            }
            if (!selectedColors.isEmpty()) {
                query.bindList("colors", selectedColors);
            }
            if (!selectedTypes.isEmpty()) {
                query.bindList("types", selectedTypes);
            }
            if (isElectric != null) {
                query.bind("isElectric", isElectric);
            }
            // TODO: more filters?

            return query.mapTo(Car.class).list();
        });
    }
}
