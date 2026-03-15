module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens aaronbearon.chapter16.lesson16 to javafx.fxml;
    exports aaronbearon.chapter16.lesson16;
 }
