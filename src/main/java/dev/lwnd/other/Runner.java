package dev.lwnd.other;

/**
 * Functional interface representing a runner.
 * This interface defines a single method, run(), which is used to execute a task.
 */
@FunctionalInterface
public interface Runner {
    /**
     * Executes the task.
     */
    void run();
}