package net.datenwerke.rs.utils.entitycloner.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface ClonePostProcessor {

}
