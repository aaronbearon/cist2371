package aaronbearon.chapter18.tree;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class TreeController {
    @FXML
    private Pane pane;

    @FXML
    public void initialize() {
        drawTree(250, 500, 250, 90, 10);
    }

    private void drawTree(double xOld, double yOld, double length, double degrees, int count) {
        double xIncrement = Math.cos(Math.toRadians(degrees)) * length;
        double yIncrement = Math.sin(Math.toRadians(degrees)) * length;
        double xNew = xOld + xIncrement;
        double yNew = yOld - yIncrement;
        Line line = new Line(xOld, yOld, xNew, yNew);
        pane.getChildren().add(line);
        if (count > 1) {
            drawTree(xNew, yNew, length / 2, degrees + 60, count - 1);
            drawTree(xNew, yNew, length / 2, degrees - 60, count - 1);
        }
    }
}
