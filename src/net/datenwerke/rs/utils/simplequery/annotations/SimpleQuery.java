package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface SimpleQuery {

   Class<?> from() default Void.class;

   Predicate[] where() default {};

   OrderBy[] orderBy() default {};

   String select() default "";

   boolean distinct() default false;

   int limit() default -1;

   int offset() default -1;

   Join[] join() default {};

   boolean throwNoResultException() default false;
}
