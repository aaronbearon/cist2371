module aaronbearon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.almasb.fxgl.all;
    requires eu.hansolo.tilesfx;
    requires javafx.web;

    opens aaronbearon.guitemplate;
    exports aaronbearon.guitemplate;

    opens aaronbearon.chapter16.lesson7 to javafx.fxml;
    exports aaronbearon.chapter16.lesson7;

    opens aaronbearon.chapter31.lesson8 to javafx.fxml;
    exports aaronbearon.chapter31.lesson8;

    opens aaronbearon.chapter31.lesson8.interview to javafx.fxml;
    exports aaronbearon.chapter31.lesson8.interview;

    opens aaronbearon.chapter18.lesson9 to javafx.fxml;
    exports aaronbearon.chapter18.lesson9;

    opens aaronbearon.chapter18.lesson9.interview to javafx.fxml;
    exports aaronbearon.chapter18.lesson9.interview;

    opens aaronbearon.chapter20.lesson11 to javafx.fxml;
    exports aaronbearon.chapter20.lesson11;
}
