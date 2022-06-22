package ru.otus.spboot.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.spboot.service.AppRunService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты shell команд")
@SpringBootTest
class ApplicationShellControllerTest {

    @MockBean
    private AppRunService appRunService;

    @Autowired
    private Shell shell;

    private static final String PERSONAL_INFO_CONFIRMATION_REFUSE_ERROR = "Вы подтвердили ваше согласие на обработку персональных данных";
    private static final String PERSONAL_INFO_CONFIRMATION_INPUT_ERROR = "Вы не подтвердили ваше согласие на обработку персональных данных. Если хотите подтвердить, то введите команду 'c yes'";
    private static final String QUIZ_COMMAND_START_ERROR = "Если хотите запустить тест сначала, то введите команду 'e start'";
    private static final String PERSONAL_INFO_CONFIRMATION_ERROR = "Сначала подтвердите ваше согласие на обработку персональных данных, вызвав команду 'c yes'";
    private static final String EXAM_PASS_CONFIRMATION = "Тест завершен";

    private static final String AGREEMENT_CONFIRMATION_COMMAND = "confirmation";
    private static final String EXAM_START_COMMAND = "exam";
    private static final String YES_COMMAND = "yes";
    private static final String START_COMMAND = "start";
    private static final String WRONG_COMMAND = "abrakodabra";
    private static final String COMMAND_INPUT_PATTERN = "%s %s";

    @DisplayName("Позитивный тест подтверждения согласия на обработку персональных данных")
    @Test
    void askForPersonalInfoUsageConfirmationPositiveTest() {
        String res = (String) shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, AGREEMENT_CONFIRMATION_COMMAND, YES_COMMAND));
        assertThat(res).isEqualTo(PERSONAL_INFO_CONFIRMATION_REFUSE_ERROR);
    }

    @DisplayName("Позитивный тест старта экзамена c правильным ключем")
    @Test
    void examStartWithPersonalInfoUsageConfirmationPositiveTest() {
        shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, AGREEMENT_CONFIRMATION_COMMAND, YES_COMMAND));
        String res = (String) shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, EXAM_START_COMMAND, START_COMMAND));
        assertThat(res).isEqualTo(EXAM_PASS_CONFIRMATION);
    }

    @DisplayName("Негативный тест подтверждения согласия на обработку персональных данных")
    @Test
    void askForPersonalInfoUsageConfirmationNegativeTest() {
        String res = (String) shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, AGREEMENT_CONFIRMATION_COMMAND, WRONG_COMMAND));
        assertThat(res).isEqualTo(PERSONAL_INFO_CONFIRMATION_INPUT_ERROR);
    }

    @DisplayName("Негативный тест попытки старта экзамена c неправильной командой")
    @Test
    void examStartWithWrongCommandNegativeTest() {
        shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, AGREEMENT_CONFIRMATION_COMMAND, YES_COMMAND));
        String res = (String) shell.evaluate(() -> String.format(COMMAND_INPUT_PATTERN, EXAM_START_COMMAND, WRONG_COMMAND));
        assertThat(res).isEqualTo(QUIZ_COMMAND_START_ERROR);
    }

}
