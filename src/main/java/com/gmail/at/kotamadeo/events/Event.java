package com.gmail.at.kotamadeo.events;

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
