package ru.yandex.practicum.sleeptracker;

import java.util.List;

public interface SleepAnalizer {
    SleepAnalysisResult analizer(List<SleepingSession> session);
}
