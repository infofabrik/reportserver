package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.security.service.security.GenericSecurityTargetMarker;

/**
 * Allows to request the verification access rights on generic targets.
 * 
 * <p>
 * Used together with {@link SecurityChecked}.
 * </p>
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenericTargetVerification {

   /**
    * The generic target that should be checked.
    */
   public Class<? extends GenericSecurityTargetMarker> target();

   /**
    * The list of rights to be verifyed.
    */
   public RightsVerification[] verify() default {};
}
