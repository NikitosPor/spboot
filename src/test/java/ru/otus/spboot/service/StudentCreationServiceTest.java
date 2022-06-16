package ru.otus.spboot.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.spboot.domain.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест сервиса StudentCreationService")

@ExtendWith(MockitoExtension.class)
class StudentCreationServiceTest {

    @Mock
    private MessageSource messages;

    @InjectMocks
    private StudentCreationService studentCreationService;

    @BeforeAll
    static void beforeAll() {
        var is = new ByteArrayInputStream("ИВАН ДРАГО".getBytes(StandardCharsets.UTF_8));
        System.setIn(is);
    }

    @DisplayName("Тест")
    @Test
    void studentCreationTest() throws Exception {
        Student student = studentCreationService.askNameAndCreateStudent();

        assertThat(student.getFullName()).isEqualTo("ИВАН ДРАГО");
    }
}