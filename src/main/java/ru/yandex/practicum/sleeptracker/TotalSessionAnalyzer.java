package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSessionAnalyzer implements SleepAnalizer {
    @Override
    public SleepAnalysisResult analizer(List<SleepingSession> session) {
        return new SleepAnalysisResult("Всего сессий сна ", session.size());
    }
}
