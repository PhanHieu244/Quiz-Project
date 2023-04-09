module com.quiz {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.quiz;
    opens com.quiz to javafx.fxml;

}