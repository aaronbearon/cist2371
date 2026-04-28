package aaronbearon.chapter18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;//drawing
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.StackPane;

public class SierpinskiTriangle extends Application {
    @Override
    public void start(Stage stage) {
        //1.UI components --------------------------
        int width = 600;
        int height = 600;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawXYGuide(gc); // coordinate guide
        //2.Container ------------------------------
        StackPane sp = new StackPane(canvas);
        // Center vertically & horizontally

        //3.Scene ----------------------------------
        Scene scene = new Scene(sp, width, height);
        stage.setTitle("Sierpiński Triangle");
        stage.setScene(scene);
        stage.show();

        //5.event handler --------------------------

        // Draw initial triangle and recurse
        drawSierpinski(gc,
                width / 2.0, 0,        // top
                0, height ,                  // bottom left
                width, height,               // bottom right
                2                            // recursion depth
        );
    }
    private void drawSierpinski(GraphicsContext gc,
                                double x1, double y1,
                                double x2, double y2,
                                double x3, double y3,
                                int depth) {
        // Print the triangle points
        printPoints(depth, x1, y1, x2, y2, x3, y3);

        if (depth == 0) {
            //Fill a polygon with 3 points at (x1, y1), (x2, y2), and (x3, y3)
            //closed shape
            gc.setFill(Color.GRAY);
            gc.fillPolygon(
                    new double[]{x1, x2, x3},
                    new double[]{y1, y2, y3},
                    3
            );
        } else {
            // Calculate midpoints
            double mx1 = (x1 + x2) / 2;
            double my1 = (y1 + y2) / 2;

            double mx2 = (x2 + x3) / 2;
            double my2 = (y2 + y3) / 2;

            double mx3 = (x3 + x1) / 2;
            double my3 = (y3 + y1) / 2;

            // Recurse into 3 outer triangles
            drawSierpinski(gc, x1, y1, mx1, my1, mx3, my3, depth - 1);
            drawSierpinski(gc, mx1, my1, x2, y2, mx2, my2, depth - 1);
            drawSierpinski(gc, mx3, my3, mx2, my2, x3, y3, depth - 1);
        }
    }

    private void printPoints(int depth, double x1, double y1, double x2, double y2, double x3, double y3) {
        //top, BottomLeft, BottomRight
        System.out.printf("%d: (%.1f,%.1f) (%.1f,%.1f) (%.1f,%.1f)%n", depth, x1, y1, x2, y2, x3, y3);
    }
    private void drawXYGuide(GraphicsContext gc) {
        //gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(2);

        // Draw Y arrow ↓
        gc.strokeLine(20, 20, 20, 60);  // vertical line
        gc.strokeLine(15, 55, 20, 60);  // left arrowhead
        gc.strokeLine(25, 55, 20, 60);  // right arrowhead
        gc.strokeText("Y", 25, 60);

        // Draw X arrow →
        gc.strokeLine(20, 20, 60, 20);  // horizontal line
        gc.strokeLine(55, 15, 60, 20);  // top arrowhead
        gc.strokeLine(55, 25, 60, 20);  // bottom arrowhead
        gc.strokeText("X", 60, 15);

        // Optional dashed guide lines
        gc.setLineDashes(5);
        gc.setLineWidth(1);
        gc.strokeLine(20, 20, 60, 20); // horizontal dashed
        gc.strokeLine(20, 20, 20, 60); // vertical dashed
        gc.setLineDashes(0); // reset dashes
    }

    public static void main(String[] args) {
        launch();
    }
}
/**
 function drawSierpinski(x1, y1, x2, y2, x3, y3, depth):
 if depth == 0:
 drawTriangle(x1, y1, x2, y2, x3, y3)  // Draw happens only at base
 else:
 // divide triangle
 drawSierpinski(depth-1)  // Recursive calls
 */

