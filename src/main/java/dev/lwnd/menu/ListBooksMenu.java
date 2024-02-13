package dev.lwnd.menu;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jline.terminal.Terminal;

import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.other.Runner;
import dev.lwnd.util.ScreenUtil;

/**
 * This class represents a menu for listing books in a library.
 * It extends the LocalMenu class and provides functionality to display a list of books,
 * sort the books by different criteria, and view detailed information about a selected book.
 */
public class ListBooksMenu extends LocalMenu {
    private BookCollection bookCollection;
    private Map<String, Runner> menuFuncs = new LinkedHashMap<>();

    private Book pickedBook;
    private List<Book> books;

    /**
     * Constructs a ListBooksMenu object with the specified terminal and book collection.
     * It initializes the list of books and runs the menu.
     *
     * @param terminal       the terminal used for input/output
     * @param bookCollection the collection of books
     */
    public ListBooksMenu(Terminal terminal, BookCollection bookCollection) {
        super(terminal);
        this.bookCollection = bookCollection;

        books = bookCollection.getAllBooks();

        runMenu();
    }

    /**
     * Runs the menu for listing books.
     * It displays the list of books, provides options to sort the books, and view detailed information about a selected book.
     */
    @MenuEntry
    void runMenu() {
        while (true) {
            ScreenUtil.clearScreen();

            menuFuncs.clear();
            menuFuncs.put("Show book info", () -> {
                pickedBook = BookCollection.searchBookMenu(terminal, books);

                ScreenUtil.clearScreen();

                if (pickedBook == null) {
                    return;
                }

                pickedBook.toConsole();

                System.out.println("Press enter to continue");
                System.console().readLine();
            });
            menuFuncs.put("Sort by Title", () -> books = BookCollection.sortByTitle(bookCollection.getAllBooks()));
            menuFuncs.put("Sort by Author", () -> books = BookCollection.sortByAuthor(bookCollection.getAllBooks()));
            menuFuncs.put("Sort by Publication Date", () -> books = BookCollection.sortByPublicationDate(bookCollection.getAllBooks()));

            for (Book book : books) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " published on " + new SimpleDateFormat("YYYY.MM.dd").format(book.getPublicationDate()));
            }

            Integer i = 1;
            for (String menuName : menuFuncs.keySet()) {
                System.out.println(i + ". " + menuName);
                i++;
            }
            System.out.println(i + ". Back");

            System.out.println("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            if (choice < 1 || choice > menuFuncs.size() + 1) {
                System.out.println("Invalid input");
                return;
            }

            Integer j = 1;
            for (String menuName : menuFuncs.keySet()) {
                if (choice == j) {
                    menuFuncs.get(menuName).run();
                }
                j++;
            }
            if (choice == i) {
                break;
            }
        }
    }
}
