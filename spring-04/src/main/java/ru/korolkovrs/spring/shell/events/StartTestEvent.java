package ru.korolkovrs.spring.shell.events;

import org.springframework.context.ApplicationEvent;

public class StartTestEvent extends ApplicationEvent {
    public StartTestEvent(Object source) {
        super(source);
    }
}
