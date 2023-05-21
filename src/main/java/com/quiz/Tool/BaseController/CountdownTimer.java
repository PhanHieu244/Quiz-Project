package com.quiz.Tool.BaseController;
import com.quiz.Exam.QuizController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer {

    private long remainingHours;
    private long remainingMinutes;
    private long remainingSeconds;
    private long endTime;
    private Label timerLabel;
    private Timeline timeline;
    QuizController quizController;

    public CountdownTimer(int minutes, QuizController quizController) {
        remainingHours = minutes / 60;
        remainingMinutes = minutes % 60;
        this.quizController = quizController;
    }

    public void createCountdownLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
        remainingSeconds = remainingHours * 3600 + remainingMinutes * 60;
        endTime = remainingSeconds;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        updateTimerLabel();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            updateTimerLabel();

            if (remainingSeconds <= 0) {
                timeline.stop();
                quizController.finish();
                // TODO: Perform any actions when the countdown reaches zero
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

    }

    private void updateTimerLabel() {
        long hours = remainingSeconds / 3600;
        long minutes = (remainingSeconds % 3600) / 60;
        long seconds = remainingSeconds % 60;
        timerLabel.setText(String.format("Time left %02d:%02d:%02d", hours, minutes, seconds));
    }

    public long getTimeTaken() {
        return endTime - remainingSeconds;
    }

    public interface FinishCallback {
        void onFinish();
    }
}

