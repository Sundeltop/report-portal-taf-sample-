package com.epam.ui.annotations;

import com.epam.extensions.ui.ResourceCleanupExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(ResourceCleanupExtension.class)
public @interface WidgetCleanup {
}
