package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSessionDurationTest {

    private final MaxSessionDuration analyzer =
            new MaxSessionDuration();

    @Test
    void shouldFindMaxDuration() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 22, 0),
                        LocalDateTime.of(2025, 10, 3, 8, 0),
                        SleepQuality.GOOD
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(600L, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult result =
                analyzer.analizer(List.of());

        assertEquals(0L, result.getValue());
    }
}