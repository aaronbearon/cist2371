module aaronbearon {
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;

    requires com.almasb.fxgl.all;
    requires eu.hansolo.tilesfx;
    requires org.jdbi.v3.core;

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

    opens aaronbearon.chapter21.lesson12 to javafx.fxml;
    exports aaronbearon.chapter21.lesson12;

    opens aaronbearon.finals2 to javafx.fxml;
    exports aaronbearon.finals2;
}
