package dev.lwnd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dev.lwnd.book.Book;
import dev.lwnd.book.BookCollection;
import dev.lwnd.book.BookCollectionBuilder;
import dev.lwnd.member.Member;
import dev.lwnd.member.MemberCollection;
import dev.lwnd.member.MemberCollectionBuilder;
import dev.lwnd.other.ObjectDescription;

/**
 * The Library class represents a library that contains a collection of books,
 * a collection of members, and a borrower catalogue.
 */
@ObjectDescription(description = "Represents a library that contains a collection of books, a collection of members, and a borrower catalogue.")
public class Library {
    private BookCollection bookCollection;
    private MemberCollection memberCollection;
    private final BorrowerCatalogue borrowerCatalogue;

    /**
     * Constructs a Library object and initializes the book collection, member collection,
     * and borrower catalogue.
     */
    public Library() {
        bookCollection = new BookCollection(this);
        populateBookCollection();
        memberCollection = new MemberCollection(this);
        populateMemberCollection();
        borrowerCatalogue = new BorrowerCatalogue();
    }

    /**
     * Returns the book collection of the library.
     *
     * @return the book collection
     */
    public BookCollection getBookCollection() {
        return bookCollection;
    }

    /**
     * Returns the member collection of the library.
     *
     * @return the member collection
     */
    public MemberCollection getMemberCollection() {
        return memberCollection;
    }

    /**
     * Returns the borrower catalogue of the library.
     *
     * @return the borrower catalogue
     */
    public BorrowerCatalogue getBorrowerCatalogue() {
        return borrowerCatalogue;
    }

    /**
     * Populates the book collection with a list of books.
     */
    void populateBookCollection() {
        ArrayList<Book> books = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        calendar.set(1954, Calendar.JULY, 29);
        books.add(new Book("The Lord of the Rings", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(1937, Calendar.SEPTEMBER, 21);
        books.add(new Book("The Hobbit", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(1977, Calendar.SEPTEMBER, 15);
        books.add(new Book("The Silmarillion", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(2007, Calendar.APRIL, 17);
        books.add(new Book("The Children of Húrin", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(2007, Calendar.APRIL, 17);
        books.add(new Book("The Children of Húrin", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(2018, Calendar.AUGUST, 30);
        books.add(new Book("The Fall of Gondolin", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(1983, Calendar.OCTOBER, 1);
        books.add(new Book("The Book of Lost Tales", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(1984, Calendar.NOVEMBER, 1);
        books.add(new Book("The Book of Lost Tales 2", "J. R. R. Tolkien", calendar.getTime()));

        calendar.set(1983, Calendar.OCTOBER, 1);
        books.add(new Book("The Book of Lost Tales 1", "J. R. R. Tolkien", calendar.getTime()));

        for (int year = 2000; year <= 2005; year++) {
            for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
                calendar.set(year, month, 1);
                books.add(new Book("Book" + (month + 1), "Author" + (month + 1), calendar.getTime()));
            }
        }

        bookCollection = new BookCollectionBuilder(this)
                            .addBooks(books)
                            .build();
    }

    /**
     * Populates the member collection with a list of members.
     */
    void populateMemberCollection() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("admin", true));
        members.add(new Member("user", false));
        members.add(new Member("user2", false));
        members.add(new Member("user3", false));
        members.add(new Member("user4", false));
        members.add(new Member("user5", false));
        members.add(new Member("user6", false));
        members.add(new Member("user7", false));
        members.add(new Member("user8", false));
        members.add(new Member("user9", false));
        members.add(new Member("user10", false));
        memberCollection = new MemberCollectionBuilder(this)
                            .addMembers(members)
                            .build();
    }
}
