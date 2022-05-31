package com.gmail.at.kotamadeo.utils;

import com.gmail.at.kotamadeo.events.Event;

public class EventValidator {
    private static final int CURRENT_YEAR = 2022;
    private static final int MINIMUM_POSSIBLE_YEAR = 1930;

    public static void validate(Event event) {
        if (event.getAuthor() == null || "".equals(event.getAuthor())) {
            throw new RuntimeException("Ошибка! Поле автора пусто.");
        } else if (event.getTittle() == null || "".equals(event.getTittle())) {
            throw new RuntimeException("Ошибка! Поле названия пусто.");
        } else if (event.getReleaseYear() == 0 || event.getReleaseYear() < MINIMUM_POSSIBLE_YEAR ||
                event.getReleaseYear() > CURRENT_YEAR) {
            throw new RuntimeException("Ошибка! Поле года выпуска пусто или заполнено неверно.");
        } else if (event.getRating() == 0) {
            throw new RuntimeException("Ошибка! Поле рейтинга пусто.");
        } else if (event.getAccessAge() == 0) {
            throw new RuntimeException("Ошибка! Поле необходимого возраста пусто.");
        } else {
            System.out.println(Utils.ANSI_GREEN + "Следующие событие корректно!\n" + event + Utils.ANSI_RESET);
        }
    }
}
