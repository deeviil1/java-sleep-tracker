package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepLog {

      protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

      protected List<SleepingSession> read(String path) {



            try {
                return Files.lines(Path.of(path))
                        .map(line -> {
                            String[] parts = line.split(";");

                            LocalDateTime fallingAsleep =
                                    LocalDateTime.parse(parts[0], formatter);

                            LocalDateTime wakingUp =
                                    LocalDateTime.parse(parts[1], formatter);

                            SleepQuality quality =
                                    SleepQuality.valueOf(parts[2]);

                            return new SleepingSession(
                                    fallingAsleep,
                                    wakingUp,
                                    quality
                            );
                        })
                        .toList();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка чтения файла сна", e);
            }

        }

    }

