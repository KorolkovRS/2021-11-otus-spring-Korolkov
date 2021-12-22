package ru.korolkovrs.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.korolkovrs.spring.service.*;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final TestService testService;
    private final LanguageChooseService languageChooseService;


    @ShellMethod(value = "Start test command", key = {"start", "test", "go"})
    public void startTest() {
        testService.test();
    }

    @ShellMethod(value = "Select language", key = {"select_lang", "sl"})
    public void selectLanguage() {
        languageChooseService.choose();
    }
}
