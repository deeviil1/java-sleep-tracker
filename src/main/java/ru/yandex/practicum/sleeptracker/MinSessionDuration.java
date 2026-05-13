package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MinSessionDuration implements SleepAnalizer {
    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        if (session.isEmpty()) {
            return new SleepAnalysisResult("Минимальная продолжительность сессии: ", 0);
        }
        long minMinutes = session.stream()
                .mapToLong(sessions -> Duration.between(sessions.getStartSession(), sessions.getEndSession()).toMinutes())
                .min()
                .orElse(0);

        return new SleepAnalysisResult("Минимальная продолжительность сессии (минуты)", minMinutes);
    }
}
