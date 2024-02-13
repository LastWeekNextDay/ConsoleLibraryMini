package dev.lwnd.book;

import java.util.Comparator;

/**
 * A comparator for comparing books based on their titles.
 */
public class BookTitleComparator implements Comparator<Book> {
    /**
     * Compares two books based on their titles.
     *
     * @param book1 the first book to compare
     * @param book2 the second book to compare
     * @return a negative integer if book1's title comes before book2's title,
     *         zero if the titles are equal, or a positive integer if book1's title comes after book2's title
     */
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getTitle().compareTo(book2.getTitle());
    }
}