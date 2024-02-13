package dev.lwnd.menu;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jline.terminal.Terminal;

import dev.lwnd.BorrowerCatalogue;
import dev.lwnd.Library;
import dev.lwnd.book.Book;
import dev.lwnd.member.Member;
import dev.lwnd.other.MenuEntry;
import dev.lwnd.other.Runner;
import dev.lwnd.util.ScreenUtil;


/**
 * The MemberMenuGlobal class represents a menu for managing members in a library system.
 * It provides options for registering new members, logging in existing members, and viewing borrowers.
 */
public class MemberMenuGlobal {
    static Member member;
    static Terminal terminal;

    /**
     * Displays the member management menu and handles user input.
     *
     * @param terminal The terminal object used for input/output.
     * @param library The library object containing the member collection and borrower catalogue.
     * @param currentMember The currently logged in member.
     * @return The updated member object after performing the selected menu option.
     */
    @MenuEntry
    public static Member Menu(Terminal terminal, Library library, Member currentMember) {
        Map<String, Runner> menuFuncs = new LinkedHashMap<>();
        member = currentMember;

        if (member.hasAdminRights()) {
            menuFuncs.put("View Borrowers", () -> ViewBorrowers(library));
        }
        menuFuncs.put("Register", () -> member = RegisterMember(library));
        menuFuncs.put("Login", () -> member = LoginMember(library));

        while (true) {
            ScreenUtil.clearScreen();

            System.out.println("Member Management");
            System.out.println("Please select an option:");

            int i = 1;
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

            int j = 1;
            for (String menuName : menuFuncs.keySet()) {
                if (choice == j) {
                    menuFuncs.get(menuName).run();
                    break;
                }
                j++;
            }

            if (choice == j) {
                return member;
            }
        }
    }

    /**
     * Registers a new member in the library.
     *
     * @param library The library object containing the member collection.
     * @return The newly registered member.
     */
    static Member RegisterMember(Library library) {
        ScreenUtil.clearScreen();

        Member user = null;
        while (user == null) {
            System.out.println("Enter username:");
            String username = new Scanner(System.in).nextLine();
            try {
                library.getMemberCollection().getMember(username);
                System.out.println("Username already exists");
            } catch (Exception e) {
                user = new Member(username);
            }
        }
        return user;
    }

    /**
     * Logs in an existing member in the library.
     *
     * @param library The library object containing the member collection.
     * @return The logged in member.
     */
    static Member LoginMember(Library library) {
        ScreenUtil.clearScreen();

        Member user = null;
        while (user == null) {
            System.out.println("Enter username:");
            String username = new Scanner(System.in).nextLine();
            try {
                user = library.getMemberCollection().getMember(username);
            } catch (Exception e) {
                System.out.println("Username does not exist");
            }
        }
        return user;
    }

    /**
     * Displays a list of borrowers and the books they have borrowed.
     *
     * @param library The library object containing the borrower catalogue.
     */
    static void ViewBorrowers(Library library) {
        ScreenUtil.clearScreen();

        BorrowerCatalogue borrowerCatalogue = library.getBorrowerCatalogue();
        System.out.println("Borrowers:");
        List<Member> borrowers = borrowerCatalogue.getBorrowers();
        for (Member borrower : borrowers) {
            System.out.println(borrower.getUsername() + " borrowed: ");
            for (Book book : borrowerCatalogue.getBooksOfBorrower(borrower)) {
                System.out.println("    " + book.getTitle() + " by " + book.getAuthor() + " published on " + new SimpleDateFormat("yyyy.MM.dd").format(book.getPublicationDate()));
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
        System.out.println("Press enter to continue");
        new Scanner(System.in).nextLine();
    }
}
