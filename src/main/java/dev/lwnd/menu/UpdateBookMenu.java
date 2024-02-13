package dev.lwnd.menu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.jline.terminal.Terminal;

import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.book.BookNotFoundException;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.util.ScreenUtil;

/**
 * Represents a menu for updating a book in the library.
 * Extends the LocalMenu class.
 */
public class UpdateBookMenu extends LocalMenu {
    BookCollection bookCollection;

    /**
     * Constructs an UpdateBookMenu object.
     *
     * @param terminal       the terminal used for input/output
     * @param bookCollection the collection of books in the library
     */
    public UpdateBookMenu(Terminal terminal, BookCollection bookCollection) {
        super(terminal);
        this.terminal = terminal;
        this.bookCollection = bookCollection;

        runMenu();
    }

    /**
     * Runs the update book menu.
     */
    @MenuEntry
    void runMenu() {
        ScreenUtil.clearScreen();

        System.out.println("Select book to modify. Press enter to continue...");
        new Scanner(System.in).nextLine();

        Book book = BookCollection.searchBookMenu(terminal, bookCollection.getAllBooks());
        if (book == null) {
            return;
        }

        ScreenUtil.clearScreen();

        System.out.println("Modify:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Publication date");
        System.out.println("4. Back");

        System.out.println("Enter your choice: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        String newTitle = book.getTitle();
        String newAuthor = book.getAuthor();
        Date newPublicationDate = book.getPublicationDate();

        ScreenUtil.clearScreen();

        switch (choice) {
            case 1:
                System.out.println("___OLD TITLE___");
                System.out.println(book.getTitle());
                System.out.println("_______________");
                System.out.println("Enter new title:");
                newTitle = new Scanner(System.in).nextLine();
                break;
            case 2:
                System.out.println("___OLD AUTHOR___");
                System.out.println(book.getAuthor());
                System.out.println("_________________");
                System.out.println("Enter new author:");
                newAuthor = new Scanner(System.in).nextLine();
                break;
            case 3:
                System.out.println("___OLD PUBLICATION DATE___");
                System.out.println(new SimpleDateFormat("yyyy.MM.dd").format(book.getPublicationDate()));
                System.out.println("___________________________");
                System.out.println("Enter new publication year:");
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

                System.out.println("Enter new publication month:");
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

                System.out.println("Enter new publication day:");
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
                newPublicationDate = calendar.getTime();
                break;
            default:
                return;
        }

        try {
            bookCollection.updateBook(book, new Book(newTitle, newAuthor, newPublicationDate));
        } catch (BookNotFoundException e) {
            System.out.println("Book not found");
        }
    }
}
