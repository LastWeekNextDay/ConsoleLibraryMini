package dev.lwnd.menu;

import org.jline.terminal.Terminal;

import dev.lwnd.BorrowerCatalogue;
import dev.lwnd.Library;
import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.member.Member;
import dev.lwnd.other.MenuEntry;

/**
 * Represents a menu for borrowing books in a library.
 * Extends the LocalMenu class.
 */
public class BorrowBookMenu extends LocalMenu {
    private final BookCollection bookCollection;
    private final Library library;
    private final Member member;

    /**
     * Constructs a BorrowBookMenu object with the specified terminal, book collection, library, and member.
     * @param terminal the terminal used for input/output
     * @param bookCollection the collection of books in the library
     * @param library the library object
     * @param currentMember the current member borrowing the book
     */
    public BorrowBookMenu(Terminal terminal, BookCollection bookCollection, Library library, Member currentMember) {
        super(terminal);
        this.bookCollection = bookCollection;
        this.library = library;
        this.member = currentMember;

        run();
    }

    /**
     * Runs the borrow book menu.
     * Searches for a book, adds the borrower to the borrower catalogue if necessary,
     * and adds the book to the borrower's list of borrowed books.
     */
    @MenuEntry
    void run() {
        Book book = BookCollection.searchBookMenu(terminal, bookCollection.getAllBooks());
        if (book == null) {
            return;
        }

        BorrowerCatalogue borrowerCatalogue = library.getBorrowerCatalogue();
        if (!borrowerCatalogue.hasBorrower(member)) {
            borrowerCatalogue.addBorrower(member);
        }

        borrowerCatalogue.addBookToBorrower(member, book);
    }
}
