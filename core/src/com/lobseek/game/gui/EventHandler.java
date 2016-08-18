package com.lobseek.game.gui;

import java.lang.annotation.*;

/**
 * Аннотация обработчика события
 *
 * @author phdengelhardt
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
