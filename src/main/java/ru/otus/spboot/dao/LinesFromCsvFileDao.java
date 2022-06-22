package ru.otus.spboot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.spboot.domain.QuestionWithAnswers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@PropertySource("classpath:application.yml")
@Component
public class LinesFromCsvFileDao {
    private final List<QuestionWithAnswers> listOfQuestionsWithAnswers = new ArrayList<>();
    private final String filePathDest;

    public LinesFromCsvFileDao(@Value("#{'/questions_' + @systemProperties['user.language'] + '.csv'}") String filePath) {
        this.filePathDest = filePath;
    }

    public List<QuestionWithAnswers> getAllQuestionsAndAnswers() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects
                .requireNonNull(this.getClass()
                        .getResourceAsStream(filePathDest))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> listOfStrings = new ArrayList<String>();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    listOfStrings.add(data);
                }
                QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers(listOfStrings);
                this.listOfQuestionsWithAnswers.add(questionWithAnswers);
            }

            return this.listOfQuestionsWithAnswers;
        }//try
    }
}
