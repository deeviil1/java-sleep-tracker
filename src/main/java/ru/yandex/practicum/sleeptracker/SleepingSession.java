package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {

    private  LocalDateTime startSession;
    private  LocalDateTime endSesion;
    private  SleepQuality quality;

    public SleepingSession(LocalDateTime startSession, LocalDateTime endSession, SleepQuality quality) {
        this.startSession = startSession;
        this.endSesion = endSession;
        this.quality = quality;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public void setEndSesion(LocalDateTime endSesion) {
        this.endSesion = endSesion;
    }

    public void setQuality(SleepQuality quality) {
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