package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    /** Реализация Searchable
     *
     */
    @Override
    public String getSearchTerm() {
        /** Поисковый термин – вся строка "название + текст"
         *
         */
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    /** getStringRepresentation() можно не переопределять – подойдёт default-реализация
     *
     */
}
