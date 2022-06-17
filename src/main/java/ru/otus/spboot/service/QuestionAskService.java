package ru.otus.spboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spboot.dao.LinesFromCsvFileDao;
import ru.otus.spboot.domain.QuestionWithAnswers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.context.MessageSource;

@Service
public class QuestionAskService {
    private final LinesFromCsvFileDao linesFromCsvFileDao;
    private final MessageSource messages;
    private final IOServiceStreams ioService;

    //@Autowired
    public QuestionAskService(LinesFromCsvFileDao linesFromCsvFileDao, IOServiceStreams ioService, MessageSource messages) {
        this.linesFromCsvFileDao = linesFromCsvFileDao;
        this.messages = messages;
        this.ioService = ioService;
    }

    public Integer askAllQuestionsAndReturnCounter() throws IOException {
        int counterOfRightAnswers = 0;
        List<QuestionWithAnswers> listOfQuestionsWithAnswers = linesFromCsvFileDao.getAllQuestionsAndAnswers();
        for (QuestionWithAnswers stringLine : listOfQuestionsWithAnswers) {
            String answer;
            String rightAnswer = stringLine.getRightAnswer();
            ioService.outputString(stringLine.getQuestion() + " "
                    + stringLine.getAnswerX(0) + " "
                    + stringLine.getAnswerX(1) + " "
                    + stringLine.getAnswerX(2)
            );
            ioService.outputString(messages.getMessage("properties.input.answer", null, Locale.getDefault()));
            answer = this.ioService.readString();
            if (Objects.equals(answer, rightAnswer)) {
                counterOfRightAnswers++;
            }
        }

        return counterOfRightAnswers;
    }
}
