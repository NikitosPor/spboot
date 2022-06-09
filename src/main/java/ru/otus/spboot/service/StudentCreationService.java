package ru.otus.spboot.service;

import org.springframework.context.MessageSource;
import ru.otus.spboot.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class StudentCreationService {
    private final MessageSource messages;

    public StudentCreationService(MessageSource messages) {
        this.messages = messages;
    }

    public Student askNameAndCreateStudent() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(this.messages.getMessage("properties.input.name",null, Locale.getDefault()));
        String inputLine = reader.readLine();
        String name = inputLine.substring(0, inputLine.indexOf(" "));
        String sureName = inputLine.substring(inputLine.indexOf(" ") + 1);
        return new Student(name, sureName);
    }
}
