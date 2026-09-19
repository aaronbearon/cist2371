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

public class Exercise36_06 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        final int tfWidthMain = 150;
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        Label initDollar = new Label("Enter Dollar Amount");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        Label usMoney = new Label("US Dollars");
        TextField tfUSDollar = new TextField();
        tfUSDollar.setAlignment(Pos.BASELINE_RIGHT);
        tfUSDollar.setPrefWidth(285);
        Button convert = new Button("Convert");
        hBox.getChildren().addAll(usMoney, tfUSDollar, convert);
        Label displayExg = new Label("Display Exchange");

        Label exgRate = new Label("Exchange Rate");
        Label converted = new Label("Converted Amount");
        Label canadian = new Label("Canadian Dollars");
        Label euro = new Label("Euro");
        Label british = new Label("British Pounds");

        NumberFormat n = null;
        System.out.println(n);
        TextField a1 = new TextField();
        TextField b1 = new TextField();
        TextField a2 = new TextField();
        TextField b2 = new TextField();
        TextField a3 = new TextField();
        TextField b3 = new TextField();
        a1.setAlignment(Pos.BASELINE_RIGHT);
        b1.setAlignment(Pos.BASELINE_RIGHT);
        a2.setAlignment(Pos.BASELINE_RIGHT);
        b2.setAlignment(Pos.BASELINE_RIGHT);
        a3.setAlignment(Pos.BASELINE_RIGHT);
        b3.setAlignment(Pos.BASELINE_RIGHT);
        a1.setPrefWidth(tfWidthMain);
        b1.setPrefWidth(tfWidthMain);
        a2.setPrefWidth(tfWidthMain);
        b2.setPrefWidth(tfWidthMain);
        a3.setPrefWidth(tfWidthMain);
        b3.setPrefWidth(tfWidthMain);

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

        vBox.getChildren().addAll(initDollar, hBox, displayExg, gridPane);

        Pane mainPane = new Pane(vBox);

        Scene scene = new Scene(mainPane, 400, 200);
        primaryStage.setTitle("Exercise36_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
