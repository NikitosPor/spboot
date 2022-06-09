package ru.otus.spboot.service;

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

    //@Autowired
    public QuestionAskService(LinesFromCsvFileDao linesFromCsvFileDao, MessageSource messages) {
        this.linesFromCsvFileDao = linesFromCsvFileDao;
        this.messages = messages;
    }

    public Integer askAllQuestionsAndReturnCounter() throws IOException {
        int counterOfRightAnswers = 0;
        List<QuestionWithAnswers> listOfQuestionsWithAnswers = linesFromCsvFileDao.getAllQuestionsAndAnswers();
        for (QuestionWithAnswers stringLine : listOfQuestionsWithAnswers) {
            String answer;
            String rightAnswer = stringLine.getRightAnswer();
            System.out.println(stringLine.getQuestion() + " "
                    + stringLine.getAnswerX(0) + " "
                    + stringLine.getAnswerX(1) + " "
                    + stringLine.getAnswerX(2)
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(messages.getMessage("properties.input.answer", null, Locale.getDefault()));
            answer = reader.readLine();
            if (Objects.equals(answer, rightAnswer)) {
                counterOfRightAnswers++;
            }
        }

        return counterOfRightAnswers;
    }
}
