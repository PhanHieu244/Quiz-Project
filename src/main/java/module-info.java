module com.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    exports com.quiz.TabPane;
    opens com.quiz.TabPane to javafx.fxml;
    exports com.quiz.MainUI;
    opens com.quiz.MainUI to javafx.fxml;
    exports com.quiz.AddQuestion;
    opens com.quiz.AddQuestion to javafx.fxml;
    exports com.quiz.TabPane.QuestionTab;
    opens com.quiz.TabPane.QuestionTab to javafx.fxml;

}