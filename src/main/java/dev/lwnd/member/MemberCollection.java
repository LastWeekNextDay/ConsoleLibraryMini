package dev.lwnd.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

import dev.lwnd.Library;
import dev.lwnd.other.ObjectDescription;
import dev.lwnd.util.ScreenUtil;

/**
 * Represents a collection of members in a library.
 */
@ObjectDescription(description = "Represents a collection of members in a library.")
public class MemberCollection {
    private final List<Member> members;
    private final Library membersOfLibrary;

    /**
     * Constructs a MemberCollection object with the specified library.
     *
     * @param library the library associated with the member collection
     */
    public MemberCollection(Library library) {
        members = new ArrayList<>();
        this.membersOfLibrary = library;
    }

    /**
     * Constructs a MemberCollection object with the specified library and members.
     *
     * @param library the library associated with the member collection
     * @param members the initial members to be added to the collection
     */
    public MemberCollection(Library library, List<Member> members) {
        this.members = members;
        this.membersOfLibrary = library;
    }

    /**
     * Returns the library associated with the member collection.
     *
     * @return the library associated with the member collection
     */
    public Library getMembershipLibrary() {
        return membersOfLibrary;
    }

    /**
     * Adds a member to the collection.
     *
     * @param member the member to be added
     */
    public void addMember(Member member) {
        this.members.add(member);
    }

    /**
     * Adds multiple members to the collection.
     *
     * @param members the members to be added
     */
    public void addMembers(List<Member> members) {
        this.members.addAll(members);
    }

    /**
     * Removes a member from the collection.
     *
     * @param member the member to be removed
     * @throws MemberNotFoundException if the member is not found in the collection
     */
    public void removeMember(Member member) throws MemberNotFoundException {
        if (!members.remove(member)) {
            throw new MemberNotFoundException("Member not found");
        }
    }

    /**
     * Retrieves a member from the collection based on the username.
     *
     * @param username the username of the member to retrieve
     * @return the member with the specified username
     * @throws MemberNotFoundException if the member is not found in the collection
     */
    public Member getMember(String username) throws MemberNotFoundException {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        throw new MemberNotFoundException("Member not found");
    }

    /**
     * Checks if a member with the specified username exists in the collection.
     *
     * @param username the username to check
     * @return true if a member with the specified username exists, false otherwise
     */
    public boolean hasMember(String username) {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of all members in the collection.
     *
     * @return a list of all members in the collection
     */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Displays a menu for searching members based on username.
     *
     * @param terminal the terminal to display the menu on
     * @param members the list of members to search from
     * @return the selected member or null if no member is selected
     */
    public static Member searchMemberMenu(Terminal terminal, List<Member> members) {
        NonBlockingReader reader = terminal.reader();

        List<Member> filteredMembers = new ArrayList<>();

        StringBuilder input = new StringBuilder();
        while (true) {
            ScreenUtil.clearScreen();
            filteredMembers.clear();

            System.out.println("1. Type username");
            System.out.println("2. If you are satisfied with the list, press enter");
            System.out.println("3. Select member from list using index or type -1 to go back");
            System.out.println("___________________________________________________________");

            for (Member member : members) {
                if (member.getUsername().toLowerCase().contains(input.toString().toLowerCase())) {
                    filteredMembers.add(member);
                }
            }
            for (int i = 0; i < filteredMembers.size(); i++) {
                System.out.println(i + ". " + filteredMembers.get(i).getUsername());
            }

            System.out.println("Enter title: " + input);

            int ch;
            try {
                ch = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            if (ch == '\n' || ch == '\r') {
                break;
            } else if (ch == '\b'){
                if (!input.isEmpty()) {
                    input = new StringBuilder(input.substring(0, input.length() - 1));
                }
            } else {
                input.append((char) ch);
            }
        }

        if (filteredMembers.isEmpty()) {
            return null;
        } else {
            while (true) {
                ScreenUtil.clearScreen();
                System.out.println("Select member from list using index or type -1 to go back");
                for (int i = 0; i < filteredMembers.size(); i++) {
                    System.out.println(i + ". " + filteredMembers.get(i).getUsername());
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
                } else if (index >= 0 && index < filteredMembers.size()) {
                    return filteredMembers.get(index);
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    /**
     * Filters the members based on the admin rights.
     *
     * @param members the list of members to filter
     * @param hasAdminRights true to filter members with admin rights, false to filter members without admin rights
     * @return a list of members that match the specified admin rights
     */
    public static List<Member> filterMembersByAdminRights(List<Member> members, boolean hasAdminRights) {
        List<Member> filteredMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.hasAdminRights() == hasAdminRights) {
                filteredMembers.add(member);
            }
        }
        return filteredMembers;
    }
}
