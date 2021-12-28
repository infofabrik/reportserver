package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.simplequery.PredicateType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface QueryByAttribute {

   Class<?> from() default Void.class;

   String where();

   PredicateType type() default PredicateType.EQUAL;

   String select() default "";

   boolean distinct() default false;

   OrderBy[] orderBy() default {};

   int limit() default -1;

   int offset() default -1;

   boolean throwNoResultException() default false;
}
