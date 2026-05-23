package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;
import java.util.*;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    /**
     * Возвращает все подходящие результаты, отсортированные по имени.
     * @return TreeMap с ключом – имя объекта, значением – сам объект Searchable
     */
    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> resultMap = new TreeMap<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                resultMap.put(item.getName(), item);
            }
        }
        return resultMap;
    }

    /**
     * Ищет объект с максимальным количеством неперекрывающихся вхождений search.
     * @throws BestResultNotFound если ни одного вхождения не найдено
     */
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;
        for (Searchable item : items) {
            int count = countOccurrences(item.getSearchTerm(), search);
            if (count > maxCount) {
                maxCount = count;
                best = item;
            }
        }
        if (maxCount == 0) {
            throw new BestResultNotFound(search);
        }
        return best;
    }

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
