package dev.lwnd.menu;

import org.jline.terminal.Terminal;

import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.book.BookNotFoundException;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.util.ScreenUtil;

/**
 * The RemoveBookMenu class represents a menu for removing a book from a book collection.
 * It extends the LocalMenu class and provides functionality to interact with the user
 * and remove a book based on the user's input.
 */
public class RemoveBookMenu extends LocalMenu {
    private BookCollection bookCollection;
    
    /**
     * Constructs a RemoveBookMenu object with the specified terminal and book collection.
     * 
     * @param terminal the terminal used for user input and output
     * @param bookCollection the book collection from which the book will be removed
     */
    public RemoveBookMenu(Terminal terminal, BookCollection bookCollection) {
        super(terminal);
        this.terminal = terminal;
        this.bookCollection = bookCollection;
        
        runMenu();
    }

    /**
     * Runs the remove book menu.
     * Prompts the user to enter a book title and removes the book from the book collection.
     * Displays an error message if the book is not found in the collection.
     */
    @MenuEntry
    void runMenu() {    
        Book book = BookCollection.searchBookMenu(terminal, bookCollection.getAllBooks());

        if (book == null) {
            return;
        }

        try {
            bookCollection.removeBook(book);
            ScreenUtil.clearScreen();
            System.out.println("Book removed successfully!");
            System.out.println("Press enter to continue");
        } catch (BookNotFoundException e) {
            ScreenUtil.clearScreen();
            System.out.println("Book not found!");
            System.out.println("Press enter to continue");
        }
    }
}
