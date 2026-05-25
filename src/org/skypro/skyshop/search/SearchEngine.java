package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items;

    public SearchEngine() {
        this.items = new HashSet<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> comparator = (s1, s2) -> {
            int len1 = s1.getName().length();
            int len2 = s2.getName().length();
            if (len1 != len2) {
                return Integer.compare(len2, len1);
                /** более длинные — первыми
                 */
            }
            return s1.getName().compareTo(s2.getName());
        };

        return items.stream()
                .filter(item -> item.getSearchTerm().contains(query))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

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
        if (str == null || substring == null || substring.isEmpty()) return 0;
        int count = 0, index = 0, subLen = substring.length();
        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += subLen;
        }
        return count;
    }
}
