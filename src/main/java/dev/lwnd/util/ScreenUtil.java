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
        if (operatingSys.isEmpty()) {
            operatingSys = System.getProperty("os.name");
        }

        if (System.console() == null) {
            for (int i = 0; i < 100; i++) {
                System.out.println("\n");
            }
        } else  {
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
}
