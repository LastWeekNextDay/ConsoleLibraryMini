package dev.lwnd;

/**
 * The App class represents the entry point of the application.
 */
public final class App {

    /**
     * The constructor is private to prevent the creation of instances.
     */
    private App() {}
    
    /**
     * The main method is the entry point of the application.
     * It creates an instance of the LibraryConsole class.
     */
    public static void main(String[] args) {
        new LibraryConsole();
    }
}
