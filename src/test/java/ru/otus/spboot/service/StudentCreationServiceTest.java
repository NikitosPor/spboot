package ru.otus.spboot.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spboot.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@DisplayName("Тест сервиса StudentCreationService")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentCreationServiceTest {

    @MockBean
    private IOServiceStreams ioService;

    @MockBean
    private AppRunService appRunService;

    @Autowired
    private StudentCreationService studentCreationService;

    @DisplayName("Тест")
    @Test
    void studentCreationTest() throws Exception {
        given(ioService.readString()).willReturn("ИВАН ДРАГО");

        assertThat(studentCreationService.askNameAndCreateStudent().getFullName()).isEqualTo("ИВАН ДРАГО");
    }
}