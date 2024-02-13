package dev.lwnd.menu;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.jline.terminal.Terminal;

import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.util.ScreenUtil;

/**
 * Represents a menu for adding a book to the book collection.
 * Extends the LocalMenu class.
 */
public class AddBookMenu extends LocalMenu {
    private final BookCollection bookCollection;

    /**
     * Constructs an AddBookMenu object.
     * Initializes the AddBookMenu with the given terminal and book collection.
     * Calls the runMenu() method to start the menu.
     *
     * @param terminal       the terminal used for input/output
     * @param bookCollection the collection of books to add the new book to
     */
    public AddBookMenu(Terminal terminal, BookCollection bookCollection) {
        super(terminal);
        this.bookCollection = bookCollection;

        runMenu();
    }

    /**
     * Runs the add book menu.
     * Prompts the user to enter the book title, author, publication year, month, and day.
     * Validates the input and adds the book to the book collection.
     */
    @MenuEntry
    void runMenu() {
        ScreenUtil.clearScreen();
        System.out.println("Enter book title:");
        String title = new Scanner(System.in).nextLine();

        System.out.println("Enter book author:");
        String author = new Scanner(System.in).nextLine();

        System.out.println("Enter book publication year:");
        boolean validYear = false;
        int publicationYear = 0;
        while (!validYear) {
            try {
                publicationYear = Integer.parseInt(new Scanner(System.in).nextLine());
                validYear = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }

        System.out.println("Enter book publication month:");
        boolean validMonth = false;
        int publicationMonth = 0;
        while (!validMonth) {
            try {
                publicationMonth = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            if (publicationMonth < 1 || publicationMonth > 12) {
                System.out.println("Invalid input");
            } else {
                validMonth = true;
            }
        }

        System.out.println("Enter book publication day:");
        int publicationDay = 0;
        boolean validDay = false;
        while (!validDay) {
            try {
                publicationDay = Integer.parseInt(new Scanner(System.in).nextLine());
                validDay = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            if (publicationDay < 1 || publicationDay > 31) {
                System.out.println("Invalid input");
                validDay = false;
            } else {
                if (publicationMonth == 2 && publicationDay > 29) {
                    System.out.println("Invalid input");
                    validDay = false;
                } else if ((publicationMonth == 4 || publicationMonth == 6 || publicationMonth == 9 || publicationMonth == 11) && publicationDay > 30) {
                    System.out.println("Invalid input");
                    validDay = false;
                }
            }
        }

        Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(publicationYear, publicationMonth - 1, publicationDay);
        Date publicationDate = calendar.getTime();

        bookCollection.addBook(new Book(title, author, publicationDate));
    }
}
