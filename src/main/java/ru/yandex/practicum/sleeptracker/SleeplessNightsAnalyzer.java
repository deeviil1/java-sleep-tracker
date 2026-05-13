package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class SleeplessNightsAnalyzer implements SleepAnalizer {

    // Константы для определения границ ночного периода
    private static final int NIGHT_END_HOUR = 6;  // Ночь заканчивается в 6 утра
    private static final int DAY_START_HOUR_FOR_NIGHT_CALCULATION = 12;  // Если сессия началась после 12 дня,
    // она относится к следующей ночи
    private static final int NEXT_DAY_SHIFT = 1;                    // Сдвиг на следующий день

    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        if (session.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        // Определяем начало и конец логирования
        LocalDateTime firstSleep = session.stream()
                .map(SleepingSession::getStartSession)
                .min(LocalDateTime::compareTo)
                .get();

        LocalDateTime lastSleep = session.stream()
                .map(SleepingSession::getEndSession)
                .max(LocalDateTime::compareTo)
                .get();

        // Первая потенциальная ночь
        LocalDate startNight = firstSleep.getHour() >= DAY_START_HOUR_FOR_NIGHT_CALCULATION
                ? firstSleep.toLocalDate().plusDays(NEXT_DAY_SHIFT)
                : firstSleep.toLocalDate();

        LocalDate endNight = lastSleep.toLocalDate();

        long sleeplessCount = Stream
                .iterate(startNight, date -> !date.isAfter(endNight), date -> date.plusDays(NEXT_DAY_SHIFT))
                .filter(date -> {
                    LocalDateTime nightStart = date.atStartOfDay();
                    LocalDateTime nightEnd = date.atTime(NIGHT_END_HOUR, 0);

                    return session.stream().noneMatch(sessions ->
                            sessions.getStartSession().isBefore(nightEnd) &&
                                    sessions.getEndSession().isAfter(nightStart)
                    );
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessCount);
    }
}

