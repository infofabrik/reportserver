package net.datenwerke.rs.utils.simplequery.byatt;

import java.lang.reflect.Method;

import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;

public class QueryByAttHandler {

   private final Method method;
   private final QueryByAttribute metadata;

   public QueryByAttHandler(Method method) {
      this.method = method;
      this.metadata = method.getAnnotation(QueryByAttribute.class);
   }

   public static QueryByAttHandler from(Method method) {
      return method.isAnnotationPresent(QueryByAttribute.class) ? new QueryByAttHandler(method) : null;
   }

   public QueryByAttribute getMetadata() {
      return metadata;
   }

   public Method getMethod() {
      return method;
   }

}
