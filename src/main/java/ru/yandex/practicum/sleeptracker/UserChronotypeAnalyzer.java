package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;

public class UserChronotypeAnalyzer implements SleepAnalizer {

    // Константы для фильтрации ночных сессий
    private static final int eveningStartHour = 18;      // Вечернее время начала ночного сна (18:00)
    private static final int morningEndHour = 6;         // Утреннее время окончания ночного сна (06:00)
    private static final int morningEndMinute = 0;

    // Константы для определения "СОВЫ"
    private static final int owlStartHour = 23;          // Сова засыпает после 23:00
    private static final int owlEndHour = 9;             // Сова просыпается после 09:00

    // Константы для определения "ЖАВОРОНКА"
    private static final int larkStartHour = 22;         // Жаворонок засыпает до 22:00
    private static final int larkEndHour = 7;            // Жаворонок просыпается до 07:00

    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        // фильтруем только ночные сессии: сон после 18:00 и до 6:00
        List<SleepingSession> nightSessions = session.stream()
                .filter(s -> {
                    LocalTime startTime = s.getStartSession().toLocalTime();
                    LocalTime eveningThreshold = LocalTime.of(eveningStartHour, morningEndMinute);
                    LocalTime morningThreshold = LocalTime.of(morningEndHour, morningEndMinute);
                    return startTime.isAfter(eveningThreshold) || startTime.isBefore(morningThreshold);
                })
                .toList();

        long sovy = nightSessions.stream()
                .filter(s -> {
                    LocalTime startTime = s.getStartSession().toLocalTime();
                    LocalTime endTime = s.getEndSession().toLocalTime();
                    LocalTime owlStartThreshold = LocalTime.of(owlStartHour, morningEndMinute);
                    LocalTime owlEndThreshold = LocalTime.of(owlEndHour, morningEndMinute);
                    return startTime.isAfter(owlStartThreshold) && endTime.isAfter(owlEndThreshold);
                })
                .count();

        long zhavoronki = nightSessions.stream()
                .filter(s -> {
                    LocalTime startTime = s.getStartSession().toLocalTime();
                    LocalTime endTime = s.getEndSession().toLocalTime();
                    LocalTime larkStartThreshold = LocalTime.of(larkStartHour, morningEndMinute);
                    LocalTime larkEndThreshold = LocalTime.of(larkEndHour, morningEndMinute);
                    return startTime.isBefore(larkStartThreshold) && endTime.isBefore(larkEndThreshold);
                })
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


