package ru.otus.spboot.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spboot.service.AppRunService;

import java.util.Objects;

@ShellComponent
public class ApplicationShellController {

    private String personalInfoUsageConfirmation;
    private final AppRunService app;

    @Autowired
    public ApplicationShellController(AppRunService app) {
        this.app = app;
    }

    @ShellMethod(value = "Personal information usage confirmation", key = {"c", "confirmation"})
    public String askForPersonalInfoUsageConfirmation(@ShellOption(defaultValue = "no") String confirmation) {
        if (Objects.equals(confirmation, "yes")) {
            this.personalInfoUsageConfirmation = confirmation;
            return "Вы подтвердили ваше согласие на обработку персональных данных";
        } else {
            return "Вы не подтвердили ваше согласие на обработку персональных данных. Если хотите подтвердить, то введите команду 'c yes'";
        }
    }

    @ShellMethod(value = "Test initiation", key = {"e", "exam"})
    @ShellMethodAvailability(value = "isConfirmationReceived")
    public String askForTestStart(String studentSolution) throws Exception {
        if (Objects.equals(studentSolution, "start")) {
            app.run();
            return "Тест завершен";
        } else {
            return "Если хотите запустить тест сначала, то введите команду 'e start'";
        }
    }

    private Availability isConfirmationReceived() {
        return Objects.equals(personalInfoUsageConfirmation, "yes")
                ? Availability.available()
                : Availability.unavailable("Сначала подтвердите ваше согласие на обработку персональных данных, вызвав команду 'c yes'");
    }
}

