package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAnalyzerTest {
    private final SleeplessNightsAnalyzer analyzer = new SleeplessNightsAnalyzer();

    @Test
    void shouldCountSleeplessNightsCorrectly() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 30), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2025, 10, 3, 14, 0),
                        LocalDateTime.of(2025, 10, 3, 15, 0), SleepQuality.NORMAL),

                new SleepingSession(LocalDateTime.of(2025, 10, 4, 13, 0),
                        LocalDateTime.of(2025, 10, 4, 14, 0), SleepQuality.NORMAL),

                new SleepingSession(LocalDateTime.of(2025, 10, 5, 14, 0),
                        LocalDateTime.of(2025, 10, 5, 15, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(3L, result.getValue());
        assertEquals("Количество бессонных ночей", result.getDescription());
    }
}

