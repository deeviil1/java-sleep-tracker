package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MaxSessionDuration implements SleepAnalizer {
    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        if (session.isEmpty()) {
            return new SleepAnalysisResult("Максимальная длительность сессии", 0L);
        }
        long maxMinutes = session.stream()
                .mapToLong(sessions -> Duration.between(sessions.getStartSession(), sessions.getEndSession()).toMinutes())
                .max()
                .orElse(0);

        return new SleepAnalysisResult("Максимальная продолжительность сессии (минуты) ", maxMinutes);
    }

}
