package dev.lwnd.util;

/**
 * The ScreenUtil class provides utility methods for interacting with the screen.
 */
public class ScreenUtil {
    private static String operatingSys = "";

    /**
     * Clears the screen based on the operating system.
     */
    public static void clearScreen() {
        if (operatingSys.equals("")) {
            operatingSys = System.getProperty("os.name");
        }

        if (operatingSys.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing screen");
            }
        } else {
            throw new UnsupportedOperationException("Unsupported operating system");
        }
    }
}