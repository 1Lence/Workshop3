package org.example.synthetichumancorestarter.annotations;

import org.example.synthetichumancorestarter.annotations.model.LoggingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation required for the audit <p>
 * If you use {@code type = LoggingType.CONSOLE_LOG}, you can leave the field topic and url blank <p>
 * For example:
 *
 * <pre>{@code
 *     @WeylandWatchingYou(type = LoggingType.CONSOLE_LOG, topic = "")
 *     public void example(){}
 * }</pre>
 *
 * The default value of the type() is {@code CONSOLE_LOG} <p>
 * @implNote
 * Methods called inside the class will <b>not</b> be logged.
 * @author Petrov K.A.
 * @version 1.0
 * @since 2025-07-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WeylandWatchingYou {

    LoggingType type() default LoggingType.CONSOLE_LOG;

    String topic();
}
