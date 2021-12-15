package ru.korolkovrs.spring.shell.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartTestEventPublisher implements EventsPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new StartTestEvent(this));
    }
}
