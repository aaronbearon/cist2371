package aaronbearon.chapter36;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 5, Money Converter.
 */
public class Exercise36_06 extends Application {
    // Input field for entering original dollar amount.
    private final TextField inputUsDollars = new TextField();

    // Input fields to set the exchange rate for each country.
    private final TextField[] exchangeInputs = new TextField[]{
            new TextField(),
            new TextField(),
            new TextField(),
    };

    // Output fields to display the result currency for each country.
    private final TextField[] countryOutputs = new TextField[]{
            new TextField(),
            new TextField(),
            new TextField(),
    };

    // Each converter has an input, a country, and an output.
    private final MoneyConverter[] converters = {
            new MoneyConverter(exchangeInputs[0], countryOutputs[0], NumberFormat.getCurrencyInstance(Locale.CANADA)),
            new MoneyConverter(exchangeInputs[1], countryOutputs[1], NumberFormat.getCurrencyInstance(Locale.FRANCE)),
            new MoneyConverter(exchangeInputs[2], countryOutputs[2], NumberFormat.getCurrencyInstance(Locale.UK)),
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Outermost pane
        VBox vBox = new VBox(5);

        // 1. Prompt for amount of money.
        vBox.setAlignment(Pos.CENTER_LEFT);
        Label initDollar = new Label("Enter Dollar Amount");

        // 2. Enter US dollars.
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);

        Label usMoney = new Label("US Dollars");

        inputUsDollars.setAlignment(Pos.BASELINE_RIGHT);
        inputUsDollars.setPrefWidth(285);

        Button convert = new Button("Convert");

        hBox.getChildren().addAll(usMoney, inputUsDollars, convert);

        // 3. Label for table.
        Label displayExg = new Label("Display Exchange");

        // 4. Grid pane.
        GridPane gridPane = getGridPane();
        vBox.getChildren().addAll(initDollar, hBox, displayExg, gridPane);

        convert.setOnAction(_ -> convert());

        Pane mainPane = new Pane(vBox);

        Scene scene = new Scene(mainPane, 400, 200);
        primaryStage.setTitle("Exercise36_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Builds the grid pane separately, using the instance's text components.
    public GridPane getGridPane() {
        final int textFieldWidth = 150;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);

        Label exgRate = new Label("Exchange Rate");
        Label converted = new Label("Converted Amount");

        Label[] countries = new Label[]{
                new Label("Canadian Dollars"),
                new Label("Euro"),
                new Label("British Pounds"),
        };

        for (TextField tf : exchangeInputs) {
            tf.setAlignment(Pos.BASELINE_RIGHT);
            tf.setPrefWidth(textFieldWidth);
        }

        for (TextField tf : countryOutputs) {
            tf.setAlignment(Pos.BASELINE_RIGHT);
            tf.setPrefWidth(textFieldWidth);
            tf.setEditable(false);
        }

        gridPane.add(exgRate, 1, 0);
        gridPane.add(converted, 2, 0);

        for (int i = 0; i < 3; i++) {
            gridPane.add(countries[i], 0, i + 1);
            gridPane.add(exchangeInputs[i], 1, i + 1);
            gridPane.add(countryOutputs[i], 2, i + 1);
        }
        return gridPane;
    }

    // Converts the US dollar amount to the foreign currencies using the exchange rates.
    public void convert() {
        // If the US Dollar amount contains invalid data, don't even attempt to convert.
        try {
            double usDollars = Double.parseDouble(inputUsDollars.getText());
            usDollars = Math.round(usDollars * 100) / 100.0;

            // Validates each input one at a time.
            for (MoneyConverter converter : converters) {
                try {
                    double exchangeRate = Double.parseDouble(converter.exgRate().getText());
                    converter.convertedRate().setText(converter.currencyFormat().format(usDollars * exchangeRate));
                } catch (Exception e) {
                    converter.exgRate().setText("ERR");
                    converter.convertedRate().clear();
                }
            }
        } catch (Exception e) {
            inputUsDollars.setText("ERR");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

record MoneyConverter(TextField exgRate, TextField convertedRate, NumberFormat currencyFormat) {
}
