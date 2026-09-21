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

public class Exercise36_06 extends Application {
    private final TextField tfUSDollar = new TextField();
    private final TextField a1 = new TextField();
    private final TextField b1 = new TextField();
    private final TextField a2 = new TextField();
    private final TextField b2 = new TextField();
    private final TextField a3 = new TextField();
    private final TextField b3 = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Outer most pane
        VBox vBox = new VBox(5);

        // 1. Prompt for amount of  money
        vBox.setAlignment(Pos.CENTER_LEFT);
        Label initDollar = new Label("Enter Dollar Amount");

        // 2. Enter US dollars.
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);

        Label usMoney = new Label("US Dollars");

        tfUSDollar.setAlignment(Pos.BASELINE_RIGHT);
        tfUSDollar.setPrefWidth(285);

        Button convert = new Button("Convert");

        hBox.getChildren().addAll(usMoney, tfUSDollar, convert);

        // 3 Label for table
        Label displayExg = new Label("Display Exchange");

        GridPane gridPane = getGridPane();
        vBox.getChildren().addAll(initDollar, hBox, displayExg, gridPane);

        convert.setOnAction(_ -> convert());

        Pane mainPane = new Pane(vBox);

        Scene scene = new Scene(mainPane, 400, 200);
        primaryStage.setTitle("Exercise36_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Builds the grid pane separately, using the given components
    public GridPane getGridPane() {
        final int textFieldWidth = 150;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);

        Label exgRate = new Label("Exchange Rate");
        Label converted = new Label("Converted Amount");
        Label canadian = new Label("Canadian Dollars");
        Label euro = new Label("Euro");
        Label british = new Label("British Pounds");

        b1.setEditable(false);
        b2.setEditable(false);
        b3.setEditable(false);
        a1.setAlignment(Pos.BASELINE_RIGHT);
        b1.setAlignment(Pos.BASELINE_RIGHT);
        a2.setAlignment(Pos.BASELINE_RIGHT);
        b2.setAlignment(Pos.BASELINE_RIGHT);
        a3.setAlignment(Pos.BASELINE_RIGHT);
        b3.setAlignment(Pos.BASELINE_RIGHT);
        a1.setPrefWidth(textFieldWidth);
        b1.setPrefWidth(textFieldWidth);
        a2.setPrefWidth(textFieldWidth);
        b2.setPrefWidth(textFieldWidth);
        a3.setPrefWidth(textFieldWidth);
        b3.setPrefWidth(textFieldWidth);

        gridPane.add(exgRate, 1, 0);
        gridPane.add(converted, 2, 0);
        gridPane.add(canadian, 0, 1);
        gridPane.add(a1, 1, 1);
        gridPane.add(b1, 2, 1);
        gridPane.add(euro, 0, 2);
        gridPane.add(a2, 1, 2);
        gridPane.add(b2, 2, 2);
        gridPane.add(british, 0, 3);
        gridPane.add(a3, 1, 3);
        gridPane.add(b3, 2, 3);
        return gridPane;
    }

    // Converts the US dollar amount to the foreign currencies using the exchange rates.
    public void convert() {
        double USDollars;
        // If the US Dollar amount contains invalid data, don't even attempt to convert.
        try {
            USDollars = Double.parseDouble(tfUSDollar.getText());
            USDollars = Math.round(USDollars * 100) / 100.0;
        } catch (Exception e) {
            tfUSDollar.setText("ERR");
            return;
        }

        // Each converter has an input, a country, and an output.
        MoneyConverter[] converters = {
                new MoneyConverter(a1, b1, NumberFormat.getCurrencyInstance(Locale.CANADA)),
                new MoneyConverter(a2, b2, NumberFormat.getCurrencyInstance(Locale.FRANCE)),
                new MoneyConverter(a3, b3, NumberFormat.getCurrencyInstance(Locale.UK))
        };

        // Validates each input one at a time.
        for (MoneyConverter converter : converters) {
            if (isValidField(converter.exgRate(), converter.convertedRate())) {
                double exchangeRate = Double.parseDouble(converter.exgRate().getText());
                converter.convertedRate().setText(converter.currencyFormat().format(USDollars * exchangeRate));
            }
        }
    }

    // If the input field is invalid, display "ERR" in the field and clear the output field.
    public boolean isValidField(TextField fieldIn, TextField fieldOut) {
        try {
            Double.parseDouble(fieldIn.getText());
        } catch (Exception e) {
            fieldIn.setText("ERR");
            fieldOut.clear();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

record MoneyConverter(TextField exgRate, TextField convertedRate, NumberFormat currencyFormat) {
}
