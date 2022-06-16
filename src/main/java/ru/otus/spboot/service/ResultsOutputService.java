package ru.otus.spboot.service;

import org.springframework.context.MessageSource;
import ru.otus.spboot.domain.Student;

import java.util.Locale;

public class ResultsOutputService {
    private final MessageSource messages;
    private final IOServiceStreams ioService;

    public ResultsOutputService(MessageSource messages, IOServiceStreams ioService) {
        this.messages = messages;
        this.ioService = ioService;
    }

    void printResults(Student student, int rightAnswersCounter, int minRightAnswers) {
        if (rightAnswersCounter < minRightAnswers) {
            ioService.outputString(student.getFullName() + " " + messages.getMessage("properties.output.exam.negative", null, Locale.getDefault()));
        } else {
            ioService.outputString(student.getFullName() + " " + messages.getMessage("properties.output.exam.positive", null, Locale.getDefault()));
        }
    }
}
