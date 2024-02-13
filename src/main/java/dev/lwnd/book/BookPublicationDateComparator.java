package dev.lwnd.book;

import java.util.Comparator;

/**
 * A comparator for comparing books based on their publication dates.
 */
public class BookPublicationDateComparator implements Comparator<Book> {

    /**
     * Compares two books based on their publication dates.
     *
     * @param book1 the first book to compare
     * @param book2 the second book to compare
     * @return a negative integer if book1's publication date is earlier than book2's,
     *         zero if both books have the same publication date,
     *         a positive integer if book1's publication date is later than book2's
     */
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getPublicationDate().compareTo(book2.getPublicationDate());
    }
}
