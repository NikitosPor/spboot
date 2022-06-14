package ru.otus.spboot.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.spboot.domain.Student;

import java.io.*;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест сервиса StudentCreationService")
@SpringBootTest
class StudentCreationServiceTest {

    @Mock
    private MessageSource messages;

    @Mock
    private BufferedReader reader;

    @InjectMocks
    private StudentCreationService studentCreationService;


    @DisplayName("Тест")
    @Test
    void studentCreationTest() throws IOException {
        Scanner reader = Mockito.mock(Scanner.class);
        when(reader.nextLine()).thenReturn("ИВАН ДРАГО");
        Student student = studentCreationService.askNameAndCreateStudent();

        assertThat(student.getFullName()).isEqualTo("ИВАН ДРАГО");
    }
}