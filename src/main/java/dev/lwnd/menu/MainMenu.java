package dev.lwnd.menu;

import java.util.HashMap;
import java.util.Map;

import org.jline.terminal.Terminal;

import dev.lwnd.Library;
import dev.lwnd.member.Member;
import dev.lwnd.member.MemberNotFoundException;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.other.Runner;
import dev.lwnd.util.ScreenUtil;

/**
 * The MainMenu class represents the main menu of the library application.
 * It allows users to select different options such as book management and user management.
 */
public class MainMenu extends LocalMenu{
    private Library library;

    private Member currentMember;

    private Map<String, Runner> menuFuncs = new HashMap<>();

    /**
     * Constructs a MainMenu object with the specified terminal and library.
     * It sets the current member to the "admin" member and runs the menu.
     *
     * @param terminal the terminal used for input/output
     * @param library the library object
     */
    public MainMenu(Terminal terminal, Library library){
        super(terminal);
        this.terminal = terminal;
        this.library = library;
        try {
            currentMember = library.getMemberCollection().getMember("admin");
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return;
        }

        runMenu();
    }

    /**
     * Runs the main menu loop.
     * It displays the menu options and handles user input.
     */
    @MenuEntry
    void runMenu(){
        while (true) {
            ScreenUtil.clearScreen();  

            menuFuncs.clear();
            menuFuncs.put("Book Management", () -> new BookManagementMenu(terminal, library.getBookCollection(), currentMember));
            menuFuncs.put("User Management", () -> currentMember = MemberMenuGlobal.Menu(terminal, library, currentMember));

            System.out.println("Welcome to the Library!");
            System.out.println("Current user: " + currentMember.getUsername());
            System.out.println("Please select an option:");

            Integer i = 1;
            for (String menuName : menuFuncs.keySet()) {
                System.out.println(i + ". " + menuName);
                i++;
            }
            System.out.println(i + ". Exit");

            System.out.println("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            if (choice < 1 || choice > menuFuncs.size() + 1) {
                System.out.println("Invalid input");
                continue;
            }

            Integer j = 1;
            for (String menuName : menuFuncs.keySet()) {
                if (choice == i) {
                    System.exit(0);
                } else if (choice == j) {
                    menuFuncs.get(menuName).run();
                }
                j++;
            }
        }
    }
}