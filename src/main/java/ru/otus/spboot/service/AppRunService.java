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
    private final IOServiceStreams ioService;

    //@Autowired
    public AppRunService(QuestionAskService questionAskService, MinRightQuestionsDao count, MessageSource messages, IOServiceStreams ioService) {
        this.questionAskService = questionAskService;
        minRightAnswersLimit = count.getMinRightQuestionsCount();
        this.messages=messages;
        this.ioService = ioService;
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new StudentCreationService(messages, ioService).askNameAndCreateStudent();
        rightAnswersCounter = questionAskService.askAllQuestionsAndReturnCounter();
        new ResultsOutputService(messages, ioService).printResults(student, rightAnswersCounter, minRightAnswersLimit);
    }
}
