package dev.lwnd.book;

import java.util.ArrayList;
import java.util.List;

import dev.lwnd.Library;

/**
 * The BookCollectionBuilder class is responsible for building a BookCollection object.
 * It provides methods to add books to the collection and finally build the BookCollection.
 */
public class BookCollectionBuilder {
    private List<Book> books;
    private BookCollection bookCollection;

    /**
     * Constructs a new BookCollectionBuilder object with the given Library.
     *
     * @param library the Library object to associate with the BookCollection
     */
    public BookCollectionBuilder(Library library) {
        this.books = new ArrayList<>();
        this.bookCollection = new BookCollection(library);
    }

    /**
     * Adds a book to the collection being built.
     *
     * @param book the Book object to add
     * @return the BookCollectionBuilder object for method chaining
     */
    public BookCollectionBuilder addBook(Book book) {
        this.books.add(book);
        return this;
    }

    /**
     * Adds a list of books to the collection being built.
     *
     * @param books the list of Book objects to add
     * @return the BookCollectionBuilder object for method chaining
     */
    public BookCollectionBuilder addBooks(List<Book> books) {
        this.books.addAll(books);
        return this;
    }

    /**
     * Builds the BookCollection object with the added books.
     * The books are added to the associated Library.
     *
     * @return the built BookCollection object
     */
    public BookCollection build() {
        bookCollection.addBooks(this.books);
        return bookCollection;
    }
}
