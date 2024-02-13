package dev.lwnd;

import java.util.ArrayList;
import java.util.List;

import dev.lwnd.book.Book;
import dev.lwnd.member.Member;
import dev.lwnd.other.ObjectDescription;
import dev.lwnd.other.Pair;

/**
 * The BorrowerCatalogue class represents a catalogue of borrowers in a library.
 * It stores a list of pairs, where each pair consists of a member and a list of books borrowed by that member.
 */
@ObjectDescription(description = "Represents a catalogue of borrowers in a library.")
public class BorrowerCatalogue {
    List<Pair<Member, List<Book>>> borrowerList = new ArrayList<>();

    /**
     * Constructs an empty BorrowerCatalogue.
     */
    public BorrowerCatalogue() {
    }

    /**
     * Adds a borrower to the catalogue.
     *
     * @param member the member to be added as a borrower
     */
    public void addBorrower(Member member) {
        this.borrowerList.add(new Pair<>(member, new ArrayList<>()));
    }

    /**
     * Adds a book to the list of books borrowed by a specific member.
     *
     * @param member the member who borrowed the book
     * @param book   the book to be added to the member's list of borrowed books
     */
    public void addBookToBorrower(Member member, Book book) {
        this.borrowerList.get(getIndexOfBorrower(member)).getSecond().add(book);
    }

    /**
     * Removes a book from the list of books borrowed by a specific member.
     *
     * @param member the member who borrowed the book
     * @param book   the book to be removed from the member's list of borrowed books
     */
    public void removeBookFromBorrower(Member member, Book book) {
        if (hasBorrower(member) == false) {
            return;
        }

        this.borrowerList.get(getIndexOfBorrower(member)).getSecond().remove(book);
    }

    /**
     * Removes a borrower from the catalogue.
     *
     * @param member the member to be removed from the catalogue
     */
    public void removeBorrower(Member member) {
        if (hasBorrower(member) == false) {
            return;
        }

        this.borrowerList.remove(getIndexOfBorrower(member));
    }

    /**
     * Returns the list of books borrowed by a specific member.
     *
     * @param member the member whose list of borrowed books is to be retrieved
     * @return the list of books borrowed by the member, or null if the member is not found
     */
    public List<Book> getBooksOfBorrower(Member member) {
        if (hasBorrower(member) == false) {
            return null;
        }

        return this.borrowerList.get(getIndexOfBorrower(member)).getSecond();
    }

    /**
     * Returns the list of all borrowers in the catalogue.
     *
     * @return the list of all borrowers
     */
    public List<Member> getBorrowers() {
        List<Member> members = new ArrayList<>();
        for (Pair<Member, List<Book>> pair : this.borrowerList) {
            members.add(pair.getFirst());
        }

        return members;
    }

    /**
     * Returns the list of pairs representing the borrower catalogue.
     *
     * @return the list of pairs representing the borrower catalogue
     */
    public List<Pair<Member, List<Book>>> getBorrowerList() {
        return borrowerList;
    }

    /**
     * Checks if a specific member exists in the borrower catalogue.
     *
     * @param member the member to be checked
     * @return true if the member exists in the catalogue, false otherwise
     */
    public boolean hasBorrower(Member member) {
        boolean hasBorrower = false;

        for (Pair<Member, List<Book>> pair : this.borrowerList) {
            if (pair.getFirst().equals(member)) {
                hasBorrower = true;
                break;
            }
        }

        return hasBorrower;
    }

    /**
     * Returns the index of a specific member in the borrower catalogue.
     *
     * @param member the member whose index is to be retrieved
     * @return the index of the member, or -1 if the member is not found
     */
    int getIndexOfBorrower(Member member) {
        int index = -1;

        for (Pair<Member, List<Book>> pair : this.borrowerList) {
            if (pair.getFirst().equals(member)) {
                index = this.borrowerList.indexOf(pair);
                break;
            }
        }

        return index;
    }
}
