package dev.lwnd.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.jline.terminal.Terminal;

import dev.lwnd.book.BookCollection;
import dev.lwnd.member.Member;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.other.Runner;
import dev.lwnd.util.ScreenUtil;

/**
 * The BookManagementMenu class represents a menu for managing books in a library.
 * It allows users to perform various operations such as adding, removing, updating, borrowing, and listing books.
 */
public class BookManagementMenu extends LocalMenu {
    private final BookCollection bookCollection;
    private final Member currentMember;
    private final Map<String, Runner> menuFuncs = new LinkedHashMap<>();

    /**
     * Constructs a BookManagementMenu object with the specified terminal, book collection, and current member.
     *
     * @param terminal The terminal used for displaying the menu.
     * @param bookCollection The collection of books in the library.
     * @param currentMember The current member accessing the menu.
     */
    public BookManagementMenu(Terminal terminal, BookCollection bookCollection, Member currentMember) {
        super(terminal);
        this.bookCollection = bookCollection;
        this.currentMember = currentMember;

        runMenu();
    }

    /**
     * Runs the book management menu.
     * The menu displays a list of available operations based on the user's role (admin or non-admin).
     * The user can choose an operation by entering a number corresponding to the operation.
     * The chosen operation is then executed.
     * The menu continues to run until the user chooses to go back.
     */
    @MenuEntry
    void runMenu() {
        while (true) {
            ScreenUtil.clearScreen();

            menuFuncs.clear();
            if (currentMember.hasAdminRights()) {
                menuFuncs.put("Add book", () -> new AddBookMenu(terminal, bookCollection));
                menuFuncs.put("Remove book", () -> new RemoveBookMenu(terminal, bookCollection));
                menuFuncs.put("Update book", () -> new UpdateBookMenu(terminal, bookCollection));
            }
            menuFuncs.put("Borrow book", () -> new BorrowBookMenu(terminal, bookCollection, bookCollection.getOwningLibrary(), currentMember));
            menuFuncs.put("List books", () -> new ListBooksMenu(terminal, bookCollection));

            Integer i = 1;
            for (String menuName : menuFuncs.keySet()) {
                System.out.println(i + ". " + menuName);
                i++;
            }
            System.out.println(i + ". Back");

            System.out.println("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            if (choice < 1 || choice > menuFuncs.size() + 1) {
                System.out.println("Invalid input");
                continue;
            }

            int j = 1;
            for (String menuName : menuFuncs.keySet()) {
                if (choice == j) {
                    menuFuncs.get(menuName).run();
                    break;
                }
                j++;
            }
            if (choice == i) {
                break;
            }
        }
    }
}
