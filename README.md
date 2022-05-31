# **Задача № 2 Валидация прочитанных событий**

## **Цель**:
1. Создать программу проверки коррекности прочитанных значений. Для этого подготовим список событий - такими событиями (event) могут быть фильмы/спектакли.

### *Пример*:

``` Пример 1
1. Создание заранее подготовленного списока событий - отдельно для кино и отдельно для театров;
2. Возможность валидации каждолго из списка событий с помощью универсального метода;
3. Если хотя бы одно из событий содержит поля со значением null или 0, завершить работу программы и вывести это событие;
4. Если ошибок не возникло, вывести сообщение "Все события корректны".
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *getMovies()* - список фильмов;
  * *getTheatres()* - список спектаклей.

#### Класс **Program**:
``` java
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
```

* **Event** - абстрактный класс Event,  являющийся суперклассом для классов: **Movie** и **Theatre**. имеет геттер-методы для доступа к полям:  author, tittle, releaseYear, accessAge, rating. Также класс имеет переопределенный метод *toString()*.
#### Класс **Event**:
```java
public abstract class Event {
    protected String author;
    protected String tittle;
    protected int releaseYear;
    protected int accessAge;
    protected double rating;

    protected Event(String author, String tittle, int releaseYear, double rating, int accessAge) {
        this.author = author;
        this.tittle = tittle;
        this.releaseYear = releaseYear;
        if (rating > 0.0 && rating <= 10.0) {
            this.rating = rating;
        } else {
            this.rating = 10.0;
        }
        this.accessAge = accessAge;
    }

    public String getTittle() {
        return tittle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public int getAccessAge() {
        return accessAge;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return author + ". " + tittle + ". год выпуска: " + releaseYear + ", рейтинг: " + rating +
                ", необходимый возраст: " + accessAge;
    }
}
```

* **EventValidator** - класс, позволяющий валидировать событие. Имеет ```void static``` метод *validate()*, который проверяет все ли поля введены в событии.

#### Класс **EventValidator**:
```java
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* демонстрация работы:
``` 
Фильмы:
Следующие событие корректно!
Кристофер Нолан. Начало. год выпуска: 2010, рейтинг: 9.8, необходимый возраст: 16
Следующие событие корректно!
Мэтт Ривз. Бетмен. год выпуска: 2022, рейтинг: 3.4, необходимый возраст: 16
Следующие событие корректно!
Кристофер Нолан. Темный рыцарь. год выпуска: 2010, рейтинг: 9.5, необходимый возраст: 16
Следующие событие корректно!
Дисней. Рапунцель. год выпуска: 2010, рейтинг: 8.9, необходимый возраст: 6
*********************************************
Спектакли:
Следующие событие корректно!
Уильям Шекспир. Макбет. год выпуска: 2021, рейтинг: 9.1, необходимый возраст: 16
Следующие событие корректно!
Уильям Шекспир. Ромео и Джульетта. год выпуска: 2019, рейтинг: 4.2, необходимый возраст: 16
Следующие событие корректно!
Лев Толстой. Анна Каренина. год выпуска: 2010, рейтинг: 5.9, необходимый возраст: 18
Exception in thread "main" java.lang.RuntimeException: Ошибка! Поле необходимого возраста пусто.
```