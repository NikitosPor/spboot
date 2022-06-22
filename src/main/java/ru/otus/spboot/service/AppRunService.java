package ru.otus.spboot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spboot.dao.MinRightQuestionsDao;
import ru.otus.spboot.domain.Student;


@Service
public class AppRunService {
    private final int minRightAnswersLimit;
    private final QuestionAskService questionAskService;
    private final StudentCreationService studentCreationService;
    private final ResultsOutputService resultsOutputService;

    //@Autowired
    public AppRunService(QuestionAskService questionAskService, MinRightQuestionsDao count, MessageSource messages, IOServiceStreams ioService, StudentCreationService studentCreationService, ResultsOutputService resultsOutputService) {
        this.questionAskService = questionAskService;
        minRightAnswersLimit = count.getMinRightQuestionsCount();
        this.studentCreationService = studentCreationService;
        this.resultsOutputService = resultsOutputService;
    }

    public void run() throws Exception {
        Student student = studentCreationService.askNameAndCreateStudent();
        int rightAnswersCounter = questionAskService.askAllQuestionsAndReturnCounter();
        resultsOutputService.printResults(student, rightAnswersCounter, minRightAnswersLimit);
    }
}
