package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AverageSessionDurationAnalyzer implements SleepAnalizer {
    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        // Если список пустой, возвращаем 0 минут
        if (session.isEmpty()) {
            return new SleepAnalysisResult("Средняя продолжительность сессии (минуты)", 0L);
        }

        // Ищем среднюю продолжительность
        long avgMinutes = Math.round(
                session.stream()
                        .mapToLong(sessions -> Duration.between(sessions.getStartSession(), sessions.getEndSession()).toMinutes())
                        .average()
                        .orElse(0.0)
        );

        return new SleepAnalysisResult("Средняя продолжительность сессии (минуты)", avgMinutes);
    }

}