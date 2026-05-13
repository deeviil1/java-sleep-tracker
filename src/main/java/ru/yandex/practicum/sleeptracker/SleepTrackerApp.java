package ru.yandex.practicum.sleeptracker;
import java.io.IOException;
import java.util.List;


public class SleepTrackerApp {

    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/sleep_log.txt";

        // Читаем сессии сна
        SleepLog reader = new SleepLog();
        List<SleepingSession> sessions = reader.read(path);

        // Список аналитических функций
        List<SleepAnalizer> analyzers = List.of(
                new TotalSessionAnalyzer(),
                new MaxSessionDuration(),
                new AverageSessionDurationAnalyzer(),
                new BadQualitySessionsAnalyzer(),
                new SleeplessNightsAnalyzer(),
                new UserChronotypeAnalyzer()
        );

        // Запускаем все функции и выводим результат
        analyzers.forEach(analyzer -> {
            SleepAnalysisResult result = analyzer.analizer(sessions);
            System.out.println(result);
        });
    }
}