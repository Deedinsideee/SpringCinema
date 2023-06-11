package com.sbercourses.spring.Cinema.Model;

public enum Genre {
    FANTASY("Фантастика"),
    SCIENCE_FICTION("Научная фантастика"),
    DRAMA("Драма"),
    NOVEL("Роман"),
    HISTORICAL ("Исторический");

    private final String genreTextDisplay;

    Genre(String text) {
        this.genreTextDisplay = text;
    }

    public String getGenreTextDisplay() {
        return this.genreTextDisplay;
    }
}
