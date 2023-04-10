module com.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    exports com.quiz;
    opens com.quiz to javafx.fxml;

}