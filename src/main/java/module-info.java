module com.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.media;
    requires org.apache.poi.ooxml;
    requires okhttp3;
    requires itextpdf;
    requires org.apache.pdfbox;

    exports com.quiz.TabPane;
    opens com.quiz.TabPane to javafx.fxml;
    exports com.quiz.MainUI;
    opens com.quiz.MainUI to javafx.fxml;
    exports com.quiz.AddQuestion;
    opens com.quiz.AddQuestion to javafx.fxml;
    exports com.quiz.TabPane.QuestionTab;
    opens com.quiz.TabPane.QuestionTab to javafx.fxml;
    exports com.quiz.Tool.BaseController;
    opens com.quiz.Tool.BaseController to javafx.fxml;
    exports com.quiz.QuizTab;
    opens com.quiz.QuizTab to javafx.fxml;
}