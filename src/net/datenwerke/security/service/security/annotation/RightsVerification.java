package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.action.SecurityAction;
import net.datenwerke.security.service.security.rights.Right;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RightsVerification {

   /**
    * Is only evaluated for the specified rights.
    */
   public Class<? extends Securee> securee() default SecurityServiceSecuree.class;

   public Class<? extends Right>[] rights() default {};

   public Class<? extends SecurityAction>[] actions() default {};
}
