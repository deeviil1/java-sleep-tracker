package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageSessionDurationAnalyzerTest {

    AverageSessionDurationAnalyzer analyzer = new AverageSessionDurationAnalyzer();

    @Test
    void testAverageNonEmpty() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 30),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(495L, result.getValue());
    }

    @Test
    void testAverageEmpty() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(0L, result.getValue());
    }
}