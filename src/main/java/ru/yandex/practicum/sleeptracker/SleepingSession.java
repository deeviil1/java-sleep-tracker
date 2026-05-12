package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {

    private final LocalDateTime startSession;
    private final LocalDateTime endSesion;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime startSession, LocalDateTime endSession, SleepQuality quality) {
        this.startSession = startSession;
        this.endSesion = endSession;
        this.quality = quality;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public LocalDateTime getEndSession() {
        return endSesion;
    }

    public SleepQuality getQuality() {
        return quality;
    }
}