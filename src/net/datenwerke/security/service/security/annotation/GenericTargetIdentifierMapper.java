package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.security.service.security.GenericSecurityTargetMarker;

/**
 * Can be used to to map "transport" GeneruciSecurityTargetMarkers from client
 * to server.
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenericTargetIdentifierMapper {
   Class<? extends GenericSecurityTargetMarker> value();
}
