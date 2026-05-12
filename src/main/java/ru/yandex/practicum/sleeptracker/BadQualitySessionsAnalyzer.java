package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadQualitySessionsAnalyzer implements SleepAnalizer {
    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        // Считаем количество сессий с плохим качеством сна
        long badCount = session.stream()
                .filter(sessions -> sessions.getQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", badCount);
    }
}
