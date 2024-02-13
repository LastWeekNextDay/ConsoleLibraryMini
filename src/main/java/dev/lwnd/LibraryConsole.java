package dev.lwnd;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import dev.lwnd.menu.MainMenu;


/**
 * The LibraryConsole class represents a console interface for interacting with a library.
 * It provides methods for initializing the library, creating a terminal, and starting the main menu.
 */
public class LibraryConsole {

    /**
     * Constructs a LibraryConsole object and runs the console interface.
     */
    public LibraryConsole(){
        run();
    }

    /**
     * Initializes the library, creates a terminal, and starts the main menu.
     */
    void run(){
        Library library = new Library();

        Terminal terminal;
        try {
            terminal = TerminalBuilder.builder().system(true).build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create terminal");
        }

        new MainMenu(terminal, library);
    }
}
