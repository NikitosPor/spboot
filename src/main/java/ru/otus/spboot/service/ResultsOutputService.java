package ru.otus.spboot.service;

import org.springframework.context.MessageSource;
import ru.otus.spboot.domain.Student;

import java.util.Locale;

public class ResultsOutputService {
    private final MessageSource messages;

    public ResultsOutputService(MessageSource messages) {
        this.messages = messages;
    }

    void printResults(Student student, int rightAnswersCounter, int minRightAnswers) {
        if (rightAnswersCounter < minRightAnswers) {
            System.out.println(student.getFullName() + " " + messages.getMessage("properties.output.exam.negative", null, Locale.getDefault()));
        } else {
            System.out.println(student.getFullName() + " " + messages.getMessage("properties.output.exam.positive", null, Locale.getDefault()));
        }
    }
}
