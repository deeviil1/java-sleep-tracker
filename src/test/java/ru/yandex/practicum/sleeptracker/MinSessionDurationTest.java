package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinSessionDurationTest {

    private final MinSessionDuration analyzer =
            new MinSessionDuration();

    @Test
    void shouldFindMinDuration() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 5, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 30),
                        LocalDateTime.of(2025, 10, 3, 5, 30),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(360L, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        SleepAnalysisResult result =
                analyzer.analizer(List.of());

        assertEquals(0, result.getValue());
    }
}