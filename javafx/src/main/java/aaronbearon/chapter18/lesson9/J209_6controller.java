package aaronbearon.chapter18.lesson9;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class J209_6controller {
    @FXML
    private TextField txtIn;

    @FXML
    private Slider slider;

    @FXML
    private Pane pane;

    private double rotate = -0.5;

    @FXML
    public void makeShapes() {
        rotate = slider.getValue() / 100.0;
        pane.getChildren().clear();

        try {
            double[] points = {0, 0, 100, 0, 100, 100, 0, 100};
            Line[] lines = {new Line(), new Line(), new Line(), new Line()};
            printSquare(points, Integer.parseInt(txtIn.getText()), lines);
            pane.getChildren().addAll(lines);
        } catch (Exception e) {
            txtIn.setText("ERR");
        }
    }

    public void printSquare(double[] points, int count, Line[] lines) {
        double[] newPoints = new double[points.length];
        if (count > 1) {
            for (int i = 0; i < newPoints.length; i++) {
                if (i + 2 < newPoints.length) {
                    newPoints[i] = (points[i] + points[i + 2]) / 2;
                    newPoints[i] -= (points[i + 2] - points[i]) * rotate;
                } else {
                    newPoints[i] = (points[i] + points[i + 2 - newPoints.length]) / 2;
                    newPoints[i] -= (points[i + 2 - newPoints.length] - points[i]) * rotate;
                }
            }

            Line[] newLines = {new Line(), new Line(), new Line(), new Line()};
            printSquare(newPoints, count - 1, newLines);
            pane.getChildren().addAll(newLines);
        }

        // Required for calculations based on the smaller window dimension.
        NumberBinding minDimension = Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.9);
        NumberBinding xOffset = pane.widthProperty().subtract(minDimension).divide(2);
        NumberBinding yOffset = pane.heightProperty().subtract(minDimension).divide(2);

        for (int i = 0; i < lines.length; i++) {
            printLines(lines[i], i, xOffset, yOffset, minDimension, points);
        }
    }

    public void printLines(Line line, int lineNum, NumberBinding x, NumberBinding y, NumberBinding min, double[] points) {
        double[] miniPoints = new double[points.length / 2];
        for (int i = 0; i < miniPoints.length; i++) {
            if (lineNum * 2 + i < points.length) {
                miniPoints[i] = points[(lineNum * 2) + i];
            } else {
                miniPoints[i] = points[lineNum * 2 + i - points.length];
            }
        }

        line.startXProperty().bind(getVal(x, min, miniPoints[0]));
        line.startYProperty().bind(getVal(y, min, miniPoints[1]));
        line.endXProperty().bind(getVal(x, min, miniPoints[2]));
        line.endYProperty().bind(getVal(y, min, miniPoints[3]));
    }

    public NumberBinding getVal(NumberBinding offset, NumberBinding minDimension, double times) {
        return offset.add((minDimension).multiply(times / 100.0));
    }
}

/*
TODO
*/
