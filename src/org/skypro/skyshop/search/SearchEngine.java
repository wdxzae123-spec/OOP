package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int size;

    /** текущее количество добавленных элементов
     *
     */

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    /**
     * Добавляет объект в поисковый индекс.
     * Если массив заполнен, выводит предупреждение и не добавляет.
     */
    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        } else {
            System.out.println("Невозможно добавить объект в SearchEngine – массив заполнен");
        }
    }

    /**
     * Ищет элементы, у которых в поисковом термине содержится переданная строка.
     * Возвращает массив из 5 элементов – первые найденные результаты (или null, если результат отсутствует).
     */
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getSearchTerm().contains(query)) {
                results[found] = items[i];
                found++;
                if (found == 5) {
                    break;
                    /** достаточно пяти результатов
                     *
                     */
                }
            }
        }
        return results;
    }
}
