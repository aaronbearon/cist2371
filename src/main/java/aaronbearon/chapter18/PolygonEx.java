package aaronbearon.chapter18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PolygonEx extends Application {

    @Override
    public void start(Stage stage) {
        /**
        ----→ X
        │
        ↓
        Y        */


        /*┌────────────┬────────────┬────────────┐
          │  300x300   │  300x300   │  300x300   │   ← 1st row
          ├────────────┼────────────┼────────────┤
          │  300x300   │  300x300   │  300x300   │   ← 2nd row
          └────────────┴────────────┴────────────┘*/

        int width = 900;
        int height = 600;
        // --- Cell dimensions ---
        int cellW = 300;
        int cellH = 300;

        //1.UI components --------------------------
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //2.Container ------------------------------
        StackPane root = new StackPane(canvas);
        // Center vertically & horizontally

        //3.Scene ----------------------------------
        Scene scene = new Scene(root, width, height);
        stage.setTitle("fllPolygon");
        stage.setScene(scene);
        stage.show();

        // Triangle
        gc.setFill(Color.RED);
        gc.fillPolygon(
                //           top          bottom L  bottom R
                new double[]{cellW * 0.5, cellW * 0.2, cellW * 0.8},// |---|---|
                new double[]{50, 250, 250},                         // |-------|
                3
        );

        // Square
        gc.setFill(Color.BLUE);
        gc.fillPolygon(
               /* (350,50) ┌────────────┐ (550,50)
                           │   Square   │
                  (350,250)└────────────┘ (550,250)*/
                //           top L       top R        bottom R     bottom L
                new double[]{cellW + 50, cellW + 250, cellW + 250, cellW + 50},
                new double[]{50, 50, 250, 250},
                4
        );

        // Diamond
        gc.setFill(Color.GREEN);
        gc.fillPolygon(
                //           top center       R                Bottom           L
                new double[]{2 * cellW + 150, 2 * cellW + 250, 2 * cellW + 150, 2 * cellW + 50},
                new double[]{50, 150, 250, 150},
                4
        );

        // Pentagon
        gc.setFill(Color.ORANGE);
        gc.fillPolygon(
                //          UL   Top  UR   LR   LL
                new double[]{50, 150, 250, 200, 100},
                new double[]{400, 320, 400, 500, 500},
                5
        );

        // Star
        gc.setFill(Color.PURPLE);
                     // centerX, centerY, outerR, innerR, numPoints
        //drawStar(gc, cellW + 150, cellH + 450, 80, 35, 5);

        gc.fillPolygon(
                //           top          UR tip       R            LR mid       BR
                new double[]{cellW + 150, cellW + 190, cellW + 250, cellW + 200, cellW + 230,
                        cellW + 150, cellW + 70,  cellW + 100, cellW + 50,  cellW + 110
                        //   LC           BL          LL mid       L           UL tip
                },
                new double[]{
                        cellH + 40,  cellH + 90,  cellH + 90,  cellH + 120, cellH + 190,
                        cellH + 150, cellH + 190, cellH + 120, cellH + 90,  cellH + 90
                },
                10
        );

        // Circle (in bottom right cell)
        gc.setFill(Color.TEAL);
        //            x               y       width    height
        gc.fillOval(2 * cellW + 75, 350, 150, 150);  // Centered 150×150 circle

    }
    private void drawStar(GraphicsContext gc, double centerX, double centerY, double outerRadius, double innerRadius, int numPoints) {
        double[] xPoints = new double[numPoints * 2];
        double[] yPoints = new double[numPoints * 2];
        double angleStep = Math.PI / numPoints;

        for (int i = 0; i < numPoints * 2; i++) {
            double angle = i * angleStep - Math.PI / 2; // Start pointing up
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            xPoints[i] = centerX + Math.cos(angle) * radius;
            yPoints[i] = centerY + Math.sin(angle) * radius;
        }

        gc.fillPolygon(xPoints, yPoints, numPoints * 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
