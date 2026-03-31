package aaronbearon.chapter18.lesson9;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class J209_6controller {
    @FXML
    private TextField txtIn;

    @FXML
    private Button makeSquares;

    @FXML
    private Slider slider;

    @FXML
    private Pane pane;

    private double rotate = -0.5;

    @FXML
    public void makeShapes() {
        rotate = slider.getValue() / 100.0;
        pane.getChildren().clear();
        // rotate = ((System.currentTimeMillis() % 10000) / 10000.0) - 0.5;

        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();

        printSquare(0, 0, 100, 0, 100, 100, 0, 100, 21, line1, line2, line3, line4);
        pane.getChildren().addAll(line1, line2, line3, line4);

    }

    // TODO: make an array a fixed size of 8
    public void printSquare(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, int count, Line line1, Line line2, Line line3, Line line4) {
        if (count >= 1) {
            double x5 = (x1 + x2) / 2;
            double y5 = (y1 + y2) / 2;
            double x6 = (x2 + x3) / 2;
            double y6 = (y2 + y3) / 2;
            double x7 = (x3 + x4) / 2;
            double y7 = (y3 + y4) / 2;
            double x8 = (x4 + x1) / 2;
            double y8 = (y4 + y1) / 2;

            x5 -= (x2 - x1) * rotate;
            y5 -= (y2 - y1) * rotate;
            x6 -= (x3 - x2) * rotate;
            y6 -= (y3 - y2) * rotate;
            x7 -= (x4 - x3) * rotate;
            y7 -= (y4 - y3) * rotate;
            x8 -= (x1 - x4) * rotate;
            y8 -= (y1 - y4) * rotate;

            Line line5 = new Line();
            Line line6 = new Line();
            Line line7 = new Line();
            Line line8 = new Line();

            printSquare(x5, y5, x6, y6, x7, y7, x8, y8, count - 1, line5, line6, line7, line8);
            pane.getChildren().addAll(line5, line6, line7, line8);
        }

        // Required for calculations based on the smaller window dimension.
        NumberBinding minDimension = Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.8);
        NumberBinding xOffset = pane.widthProperty().subtract(minDimension).divide(2);
        NumberBinding yOffset = pane.heightProperty().subtract(minDimension).divide(2);

        line1.startXProperty().bind(getVal(xOffset, minDimension, x1));
        line1.startYProperty().bind(getVal(yOffset, minDimension, y1));
        line1.endXProperty().bind(getVal(xOffset, minDimension, x2));
        line1.endYProperty().bind(getVal(yOffset, minDimension, y2));

        line2.startXProperty().bind(getVal(xOffset, minDimension, x2));
        line2.startYProperty().bind(getVal(yOffset, minDimension, y2));
        line2.endXProperty().bind(getVal(xOffset, minDimension, x3));
        line2.endYProperty().bind(getVal(yOffset, minDimension, y3));

        line3.startXProperty().bind(getVal(xOffset, minDimension, x3));
        line3.startYProperty().bind(getVal(yOffset, minDimension, y3));
        line3.endXProperty().bind(getVal(xOffset, minDimension, x4));
        line3.endYProperty().bind(getVal(yOffset, minDimension, y4));

        line4.startXProperty().bind(getVal(xOffset, minDimension, x4));
        line4.startYProperty().bind(getVal(yOffset, minDimension, y4));
        line4.endXProperty().bind(getVal(xOffset, minDimension, x1));
        line4.endYProperty().bind(getVal(yOffset, minDimension, y1));
    }

    public NumberBinding getVal(NumberBinding offset, NumberBinding minDimension, double times) {
        return offset.add((minDimension).multiply(times / 100.0));
    }
}

/*
TODO
*/
