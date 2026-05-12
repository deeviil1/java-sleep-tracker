package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadQualitySessionsAnalizerTest {

    private final BadQualitySessionsAnalyzer analyzer =
            new BadQualitySessionsAnalyzer();

    @Test
    void shouldCountBadQualitySessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 22, 30),
                        LocalDateTime.of(2025, 10, 4, 6, 0),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(2L, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult result =
                analyzer.analizer(List.of());

        assertEquals(0L, result.getValue());
    }
}
