package dev.lwnd.book;

import java.util.Comparator;

/**
 * A comparator for comparing books based on their authors.
 */
public class BookAuthorComparator implements Comparator<Book> {
    /**
     * Compares two books based on their authors.
     *
     * @param book1 the first book to compare
     * @param book2 the second book to compare
     * @return a negative integer if book1's author is lexicographically less than book2's author,
     * zero if both authors are equal, and a positive integer if book1's author is lexicographically greater than book2's author
     */
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
