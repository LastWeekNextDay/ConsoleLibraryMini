package dev.lwnd.menu;

import org.jline.terminal.Terminal;

/**
 * The LocalMenu class represents an abstract menu in a local context.
 * It provides a common base for specific menu implementations.
 */
public abstract class LocalMenu {
    protected Terminal terminal;
    
    /**
     * Constructs a LocalMenu object with the specified terminal.
     * 
     * @param terminal the terminal to be used by the menu
     */
    public LocalMenu(Terminal terminal) {
        this.terminal = terminal;
    }
}
