package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ReturnObjectValidation {

   public enum Mode {
      THROW_EXCEPTION, FILTER
   }

   /**
    * Specifys whether the parameter is a dto.
    */
   public boolean isDto() default false;

   /**
    * Defines whether the return value should be filtered or whether an exception
    * should be thrown on violation.
    * 
    */
   public Mode mode() default Mode.THROW_EXCEPTION;

   /**
    * The list of rights to be verifyed.
    */
   public RightsVerification[] verify() default {};

   public RightsVerification[] parentChecks() default {};
}
