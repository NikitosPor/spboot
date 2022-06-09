 package ru.otus.spboot.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spboot.dao.MinRightQuestionsDao;
import ru.otus.spboot.domain.Student;


@Service
public class AppRunService implements CommandLineRunner {
    int minRightAnswersLimit;
    int rightAnswersCounter;
    private final QuestionAskService questionAskService;
    private final MessageSource messages;

    //@Autowired
    public AppRunService(QuestionAskService questionAskService, MinRightQuestionsDao count, MessageSource messages) {
        this.questionAskService = questionAskService;
        minRightAnswersLimit = count.getMinRightQuestionsCount();
        this.messages=messages;
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new StudentCreationService(messages).askNameAndCreateStudent();
        rightAnswersCounter = questionAskService.askAllQuestionsAndReturnCounter();
        new ResultsOutputService(messages).printResults(student, rightAnswersCounter, minRightAnswersLimit);
    }
}
