package org.skypro.skyshop.search;

public interface Searchable {
    /** Возвращает текст, по которому происходит поиск
     *
     */
    String getSearchTerm();

    /** Возвращает тип контента (например, "PRODUCT" или "ARTICLE")
     *
     */
    String getContentType();

    /** Возвращает имя объекта
     *
     */
    String getName();

    /** Строковое представление для результатов поиска
     *
     */
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}