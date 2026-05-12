package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSessionAnalyzerTest {

    @Test
    void shouldCountTotalSleepSessions() {
        // подготовка данных
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2026, 1, 1, 23, 0),
                        LocalDateTime.of(2026, 1, 2, 7, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2026, 1, 2, 23, 30),
                        LocalDateTime.of(2026, 1, 3, 6, 30),
                        SleepQuality.NORMAL
                )
        );

        TotalSessionAnalyzer analyzer = new TotalSessionAnalyzer();

        // действие
        SleepAnalysisResult result = analyzer.analizer(sessions);

        // проверка
        assertEquals("Всего сессий сна ", result.getDescription());
        assertEquals(2, result.getValue());
    }
}
