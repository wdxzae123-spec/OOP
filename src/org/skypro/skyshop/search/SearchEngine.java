package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

public class SearchEngine {
    private final Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        } else {
            System.out.println("Невозможно добавить объект в SearchEngine – массив заполнен");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getSearchTerm().contains(query)) {
                results[found] = items[i];
                found++;
                if (found == 5) {
                    break;
                }
            }
        }
        return results;
    }

    /**
     * Ищет объект Searchable с максимальным количеством неперекрывающихся вхождений search.
     * @param search поисковая строка
     * @return наиболее подходящий объект
     * @throws BestResultNotFound если ни в одном searchTerm нет вхождений search
     */
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;

        for (int i = 0; i < size; i++) {
            String term = items[i].getSearchTerm();
            int count = countOccurrences(term, search);
            if (count > maxCount) {
                maxCount = count;
                best = items[i];
            }
        }

        if (maxCount == 0) {
            throw new BestResultNotFound(search);
        }
        return best;
    }

    /** Вспомогательный метод для подсчёта неперекрывающихся вхождений подстроки
     *
     */
    private int countOccurrences(String str, String substring) {
        if (str == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        int subLen = substring.length();
        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += subLen;
        }
        return count;
    }
}