package net.datenwerke.rs.utils.simplequery.simple;

import java.lang.reflect.Method;

import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;

public class SimpleQueryHandler {

   private final Method method;
   private final SimpleQuery metadata;

   public SimpleQueryHandler(Method method) {
      this.method = method;
      this.metadata = method.getAnnotation(SimpleQuery.class);
   }

   public static SimpleQueryHandler from(Method method) {
      return method.isAnnotationPresent(SimpleQuery.class) ? new SimpleQueryHandler(method) : null;
   }

   public SimpleQuery getMetadata() {
      return metadata;
   }

   public Method getMethod() {
      return method;
   }

}
