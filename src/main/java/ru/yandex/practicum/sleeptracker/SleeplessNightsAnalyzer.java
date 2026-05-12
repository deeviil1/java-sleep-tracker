package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class SleeplessNightsAnalyzer implements SleepAnalizer{
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
        LocalDate startNight = firstSleep.getHour() >= 12
                ? firstSleep.toLocalDate().plusDays(1)
                : firstSleep.toLocalDate();

        LocalDate endNight = lastSleep.toLocalDate();

        long sleeplessCount = Stream
                .iterate(startNight, date -> !date.isAfter(endNight), date -> date.plusDays(1))
                .filter(date -> {
                    LocalDateTime nightStart = date.atStartOfDay();
                    LocalDateTime nightEnd = date.atTime(6, 0);

                    return session.stream().noneMatch(sessions ->
                            sessions.getStartSession().isBefore(nightEnd) &&
                                    sessions.getEndSession().isAfter(nightStart)
                    );
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessCount);
    }
}

