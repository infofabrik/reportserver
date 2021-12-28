package net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.localization.Messages;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface CliHelpMessage {

   Class<? extends Messages> messageClass();

   String description();

   String name();

   String version() default "0.1";

   Argument[] args() default {};

   NonOptArgument[] nonOptArgs() default {};
}
