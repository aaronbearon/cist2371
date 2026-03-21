module aaronbearon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.almasb.fxgl.all;
    requires eu.hansolo.tilesfx;
    requires javafx.web;

    opens aaronbearon.chapter16.lesson7 to javafx.fxml;
    exports aaronbearon.chapter16.lesson7;
}
