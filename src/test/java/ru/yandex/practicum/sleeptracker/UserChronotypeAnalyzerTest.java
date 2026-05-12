package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserChronotypeAnalyzerTest {

    private final UserChronotypeAnalyzer analyzer = new UserChronotypeAnalyzer();

    @Test
    void shouldDetectSovaChronotype() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 15),
                        LocalDateTime.of(2025, 10, 3, 10, 0),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(Chronotype.СОВА, result.getValue());
        assertEquals("Хронотип пользователя", result.getDescription());
    }

    @Test
    void shouldDetectZhavoronokChronotype() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 20, 30),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(Chronotype.ЖАВОРОНОК, result.getValue());
        assertEquals("Хронотип пользователя", result.getDescription());
    }

    @Test
    void shouldDetectGolubChronotype() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 30),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 8, 30),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(Chronotype.ГОЛУБЬ, result.getValue());
        assertEquals("Хронотип пользователя", result.getDescription());
    }

    @Test
    void shouldReturnGolubIfTieBetweenTypes() {
        // 1 сова, 1 жаворонок -> по ТЗ выбираем голубь
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 10, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 0),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        SleepQuality.GOOD
                )
        );

        SleepAnalysisResult result = analyzer.analizer(sessions);

        assertEquals(Chronotype.ГОЛУБЬ, result.getValue());
    }
}
