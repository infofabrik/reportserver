package net.datenwerke.security.service.eventlogger.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.eventbus.Event;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface FireEvent {
   Class<? extends Event> event();

   EventProperty[] properties() default {};
}
