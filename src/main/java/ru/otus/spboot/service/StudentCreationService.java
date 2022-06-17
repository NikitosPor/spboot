package ru.otus.spboot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spboot.domain.Student;

import java.io.IOException;
import java.util.Locale;

@Service
public class StudentCreationService {
    private final MessageSource messages;
   // private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final IOServiceStreams ioService;
   // private final Scanner reader = new Scanner(System.in);

    public StudentCreationService(MessageSource messages, IOServiceStreams ioService) {
        this.messages = messages;
        this.ioService = ioService;
    }


    public Student askNameAndCreateStudent() throws IOException {
        ioService.outputString(this.messages.getMessage("properties.input.name",null, Locale.getDefault()));
        String inputLine = ioService.readString();
        String name = inputLine.substring(0, inputLine.indexOf(" "));
        String sureName = inputLine.substring(inputLine.indexOf(" ") + 1);
        return new Student(name, sureName);
    }
}
