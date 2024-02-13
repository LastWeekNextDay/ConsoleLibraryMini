package dev.lwnd.other;

import java.lang.annotation.*;

/**
 * This annotation is used to mark a class as loggable.
 * Classes annotated with @Loggable will have logging functionality enabled at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Loggable {
}