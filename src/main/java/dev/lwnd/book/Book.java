package dev.lwnd.book;

import java.text.SimpleDateFormat;
import java.util.Date;

import dev.lwnd.other.Loggable;
import dev.lwnd.other.ObjectDescription;

/**
 * Represents a book with a title, author, and publication date.
 */
@Loggable
@ObjectDescription(description = "Represents a book with a title, author, and publication date.")
public class Book {
    private String title;
    private String author;
    private Date publicationDate;

    /**
     * Constructs a Book object with the specified title, author, and publication date.
     *
     * @param title           the title of the book
     * @param author          the author of the book
     * @param publicationDate the publication date of the book
     */
    public Book(String title, String author, Date publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the publication date of the book.
     *
     * @return the publication date of the book
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets the publication date of the book.
     *
     * @param publicationDate the publication date of the book
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Prints the book details to the console.
     */
    public void toConsole() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publication date: " + new SimpleDateFormat("yyyy.MM.dd").format(publicationDate));
    }
}
