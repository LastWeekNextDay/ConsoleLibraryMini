package dev.lwnd.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * This class represents an aspect for logging object creation in a Java application.
 * It provides functionality to log the creation of objects annotated with the @Loggable annotation.
 */
@Aspect
public class LoggingAspect {
    private Path logFilePath = null;

    /**
     * Logs the creation of objects annotated with the @Loggable annotation.
     * This advice is executed after the execution of any constructor within a class annotated with @Loggable.
     *
     * @param joinPoint The join point at which the advice is applied.
     */
    @After("execution(*.new(..)) && within(@Loggable *)")
    public void logObjectCreation(JoinPoint joinPoint) {
        LogFileValidation();
        LogObject(joinPoint);
    }

    /**
     * Validates the log file path and creates the log file if it doesn't exist.
     */
    private void LogFileValidation() {
        if (logFilePath != null) {
            return;
        }

        Path path = Paths.get("src/main/java/lt/nlav/logs");
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        Path logFilePathLocal = Paths.get(path.toString() + "/log.txt");
        if (Files.notExists(logFilePathLocal)){
            try {
                Files.createFile(logFilePathLocal);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        logFilePath = logFilePathLocal;
    }

    /**
     * Logs the object creation information to the log file.
     *
     * @param joinPoint The join point representing the object creation.
     */
    void LogObject(JoinPoint joinPoint) {
        String text = new Date().toString() + ": Created object of class " 
        + joinPoint.getTarget().getClass().getName() + " | "
        + joinPoint.getTarget().toString() + "\n";
        try {
            OutputStream os = new FileOutputStream(new File(logFilePath.toString()), true);
            os.write(text.getBytes(), 0, text.length());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
