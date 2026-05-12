package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;

public class UserChronotypeAnalyzer implements SleepAnalizer {

    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        // фильтруем только ночные сессии: сон после 18:00 и до 6:00
        List<SleepingSession> nightSessions = session.stream()
                .filter(s -> {
                    LocalTime fall = s.getStartSession().toLocalTime();
                    return fall.isAfter(LocalTime.of(18, 0)) || fall.isBefore(LocalTime.of(6, 0));
                })
                .toList();

        long sovy = nightSessions.stream()
                .filter(s -> s.getStartSession().toLocalTime().isAfter(LocalTime.of(23, 0))
                        && s.getEndSession().toLocalTime().isAfter(LocalTime.of(9, 0)))
                .count();

        long zhavoronki = nightSessions.stream()
                .filter(s -> s.getStartSession().toLocalTime().isBefore(LocalTime.of(22, 0))
                        && s.getEndSession().toLocalTime().isBefore(LocalTime.of(7, 0)))
                .count();

        long golubi = nightSessions.size() - sovy - zhavoronki;

        Chronotype userType;
        if (sovy > zhavoronki && sovy > golubi) {
            userType = Chronotype.СОВА;
        } else if (zhavoronki > sovy && zhavoronki > golubi) {
            userType = Chronotype.ЖАВОРОНОК;
        } else {
            userType = Chronotype.ГОЛУБЬ;
        }

        return new SleepAnalysisResult("Хронотип пользователя", userType);
    }
}


