package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SleepTrackerAppTest {

    @Test
    void mainShouldRunWithoutErrors() {
        assertDoesNotThrow(() ->
                SleepTrackerApp.main(new String[]{})
        );
    }
}
