package org.skypro.skyshop.exception;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchQuery) {
        super("Не нашлось подходящей статьи для запроса: \"" + searchQuery + "\"");
    }
}