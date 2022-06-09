package ru.otus.spboot.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@DisplayName("Тестирование создания инстанса класса Student")
@SpringBootTest(classes = StudentCreationService.class)
public class StudentCreationServiceTest {

    @Autowired
    ApplicationContext context;




}
