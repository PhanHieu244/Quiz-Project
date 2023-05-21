package com.quiz.Exam;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MarksController {

    @FXML
    private Label gradeLabel;

    @FXML
    private Label marksLabel;

    @FXML
    private Label timeLabel;

    public void setup(long timeTaken, float marks, float maxMarks, float grade){
        //todo grade
        timeLabel.setText(timeString(timeTaken));
        marksLabel.setText(String.format("%.2f/%.2f", marks, maxMarks));
        gradeLabel.setText(gradeString(marks, maxMarks, grade));
    }

    private String timeString(long timeTake){
        long hours = timeTake / 3600;
        long minutes = (timeTake % 3600) / 60;
        long seconds = timeTake % 60;
        String s;
        if (hours > 0){
            s = String.format("%d hours %d min %d secs", hours, minutes, seconds);
        }
        else {
            s = String.format("%d min %d secs", minutes, seconds);
        }
        return s;
    }

    private String gradeString(float marks, float maxMarks, float maxGrade){
        float percent = marks / maxMarks * 100;
        float grade = maxGrade * percent / 100f;
        return  String.format("%.2f out of %.2f (%.2f%%)", grade, maxGrade, percent);
    }
}

