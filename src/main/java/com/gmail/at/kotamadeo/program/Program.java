package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.events.Movie;
import com.gmail.at.kotamadeo.events.Theatre;
import com.gmail.at.kotamadeo.utils.EventValidator;
import com.gmail.at.kotamadeo.utils.Utils;

public class Program {
    /** Чтобы выпали исключения необходимо раскомментить последние две строки в методах */
    public void start() {
        System.out.println(Utils.ANSI_PURPLE + "Фильмы:" + Utils.ANSI_RESET);
        for (Movie movie : getMovies()) {
            EventValidator.validate(movie);
        }
        Utils.printDelim();
        System.out.println(Utils.ANSI_PURPLE + "Спектакли:" + Utils.ANSI_RESET);
        for (Theatre theatre : getTheatres()) {
            EventValidator.validate(theatre);
        }
    }

    private static Movie[] getMovies() {
        return new Movie[]{
                new Movie("Кристофер Нолан", "Начало", 2010, 9.8, 16),
                new Movie("Мэтт Ривз", "Бетмен", 2022, 3.4, 16),
                new Movie("Кристофер Нолан", "Темный рыцарь", 2010, 9.5, 16),
                new Movie("Дисней", "Рапунцель", 2010, 8.9, 6),
                //new Movie(null, "test", 2020, 3, 10)
        };
    }

    private static Theatre[] getTheatres() {
        return new Theatre[]{
                new Theatre("Уильям Шекспир", "Макбет", 2021, 9.1, 16),
                new Theatre("Уильям Шекспир", "Ромео и Джульетта", 2019, 4.2, 16),
                new Theatre("Лев Толстой", "Анна Каренина", 2010, 5.9, 18),
                //new Theatre("test", "", 2022, 3.2, 10)
        };
    }
}

