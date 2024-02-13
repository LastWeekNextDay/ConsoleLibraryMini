package dev.lwnd.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

import dev.lwnd.Library;
import dev.lwnd.other.ObjectDescription;
import dev.lwnd.util.ScreenUtil;

/**
 * Represents a collection of books in a library.
 */
@ObjectDescription(description = "Represents a collection of books in a library.")
public class BookCollection {
    private final List<Book> books;
    private final Library owningLibrary;

    /**
     * Constructs a new BookCollection object with the specified owning library.
     *
     * @param owningLibrary the library that owns this book collection
     */
    public BookCollection(Library owningLibrary) {
        books = new ArrayList<>();
        this.owningLibrary = owningLibrary;
    }

    /**
     * Constructs a new BookCollection object with the specified owning library and initial books.
     *
     * @param owningLibrary the library that owns this book collection
     * @param books         the initial books in the collection
     */
    public BookCollection(Library owningLibrary, List<Book> books) {
        this.owningLibrary = owningLibrary;
        this.books = books;
    }

    /**
     * Returns the library that owns this book collection.
     *
     * @return the owning library
     */
    public Library getOwningLibrary() {
        return owningLibrary;
    }

    /**
     * Adds a book to the collection.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Adds a list of books to the collection.
     *
     * @param books the list of books to add
     */
    public void addBooks(List<Book> books) {
        this.books.addAll(books);
    }

    /**
     * Updates a book in the collection with the provided updated book.
     *
     * @param originalBook the original book to update
     * @param updatedBook  the updated book
     * @throws BookNotFoundException if the original book is not found in the collection
     */
    public void updateBook(Book originalBook, Book updatedBook) throws BookNotFoundException {
        if (!books.contains(originalBook)) {
            throw new BookNotFoundException("Book not found");
        }

        int index = books.indexOf(originalBook);
        updateBook(index, updatedBook);
    }

    /**
     * Updates a book in the collection at the specified index with the provided updated book.
     *
     * @param index        the index of the book to update
     * @param updatedBook  the updated book
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= books.size())
     */
    public void updateBook(int index, Book updatedBook) throws IndexOutOfBoundsException {
        if (index < 0 || index >= books.size()) {
            throw new IndexOutOfBoundsException();
        }

        books.set(index, updatedBook);
    }

    /**
     * Removes a book from the collection.
     *
     * @param book the book to remove
     * @throws BookNotFoundException if the book is not found in the collection
     */
    public void removeBook(Book book) throws BookNotFoundException {
        if (!books.contains(book)) {
            throw new BookNotFoundException("Book not found");
        }

        books.remove(book);
    }

    /**
     * Returns the book at the specified index in the collection.
     *
     * @param index the index of the book
     * @return the book at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= books.size())
     */
    public Book getBook(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= books.size()) {
            throw new IndexOutOfBoundsException();
        }

        return books.get(index);
    }

    /**
     * Returns the book with the specified title from the collection.
     *
     * @param title the title of the book
     * @return the book with the specified title
     * @throws BookNotFoundException if the book with the specified title is not found in the collection
     */
    public Book getBook(String title) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }

        throw new BookNotFoundException("Book not found");
    }

    /**
     * Checks if the collection contains the specified book.
     *
     * @param book the book to check
     * @return true if the collection contains the book, false otherwise
     */
    public boolean hasBook(Book book) {
        return books.contains(book);
    }

    /**
     * Checks if the collection contains a book with the specified title.
     *
     * @param title the title of the book
     * @return true if the collection contains a book with the specified title, false otherwise
     */
    public boolean hasBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a list of all the books in the collection.
     *
     * @return a list of all the books
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Sorts the provided list of books by title and returns the sorted list.
     *
     * @param books the list of books to sort
     * @return the sorted list of books
     */
    public static List<Book> sortByTitle(List<Book> books) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(new BookTitleComparator());
        return sortedBooks;
    }

    /**
     * Sorts the provided list of books by author and returns the sorted list.
     *
     * @param books the list of books to sort
     * @return the sorted list of books
     */
    public static List<Book> sortByAuthor(List<Book> books) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(new BookAuthorComparator());
        return sortedBooks;
    }

    /**
     * Sorts the provided list of books by publication date and returns the sorted list.
     *
     * @param books the list of books to sort
     * @return the sorted list of books
     */
    public static List<Book> sortByPublicationDate(List<Book> books) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(new BookPublicationDateComparator());
        return sortedBooks;
    }

    /**
     * Displays a search book menu in the terminal and returns the selected book.
     *
     * @param terminal the terminal to display the menu
     * @param books    the list of books to search from
     * @return the selected book, or null if no book is selected
     */
    public static Book searchBookMenu(Terminal terminal, List<Book> books) {
        NonBlockingReader reader = terminal.reader();

        List<Book> filteredBooks = new ArrayList<>();

        StringBuilder input = new StringBuilder();
        while (true) {
            ScreenUtil.clearScreen();
            filteredBooks.clear();

            System.out.println("1. Type book title");
            System.out.println("2. If you are satisfied with the list, press enter");
            System.out.println("3. Select book from list using index or type -1 to go back");
            System.out.println("___________________________________________________________");

            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(input.toString().toLowerCase())) {
                    filteredBooks.add(book);
                }
            }
            for (int i = 0; i < filteredBooks.size(); i++) {
                System.out.println(i + ". " + filteredBooks.get(i).getTitle());
            }

            System.out.println("Enter title: \n" + input);

            int ch;
            try {
                ch = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            if (ch == '\n' || ch == '\r') {
                break;
            } else if (ch == '\b') {
                if (!input.isEmpty()) {
                    input = new StringBuilder(input.substring(0, input.length() - 1));
                }
            } else {
                input.append((char) ch);
            }
        }

        if (filteredBooks.isEmpty()) {
            return null;
        } else {
            while (true) {
                ScreenUtil.clearScreen();
                System.out.println("Select book from list using index or type -1 to go back");
                for (int i = 0; i < filteredBooks.size(); i++) {
                    System.out.println(i + ". " + filteredBooks.get(i).getTitle());
                }
                System.out.println("Enter index: ");
                int index = 0;
                try {
                    index = Integer.parseInt(new Scanner(System.in).nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
                if (index == -1) {
                    return null;
                } else if (index >= 0 && index < filteredBooks.size()) {
                    return filteredBooks.get(index);
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    /**
     * Filters the provided list of books by title and returns the filtered list.
     *
     * @param books the list of books to filter
     * @param title the title to filter by
     * @return the filtered list of books
     */
    public static List<Book> filterBooksByTitle(List<Book> books, String title) {
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }
}
