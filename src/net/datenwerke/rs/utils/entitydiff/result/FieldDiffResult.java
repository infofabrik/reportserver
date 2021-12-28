package net.datenwerke.rs.utils.entitydiff.result;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public abstract class FieldDiffResult {

   protected final Field field;
   protected final Object valueA;
   protected final Object valueB;

   public FieldDiffResult(Field field, Object valueA, Object valueB) {
      super();
      this.field = field;
      this.valueA = valueA;
      this.valueB = valueB;
   }

   public Field getField() {
      return field;
   }

   public Object getValueA() {
      return valueA;
   }

   public Object getValueB() {
      return valueB;
   }

   public boolean isEqual() {
      if (null == valueA && null == valueB)
         return true;
      if (null != valueA && null == valueB)
         return false;
      if (null == valueA && null != valueB)
         return false;

      if (valueA instanceof Collection && valueB instanceof Collection) {
         return CollectionUtils.isEqualCollection((Collection) valueA, (Collection) valueB);
      } else if (valueA instanceof Map && valueB instanceof Map) {
         Map mapA = (Map) valueA;
         Map mapB = (Map) valueB;

         Set keySetA = mapA.keySet();
         Set keySetB = mapB.keySet();

         if (!CollectionUtils.isEqualCollection(keySetA, keySetB))
            return false;

         for (Object key : keySetA) {
            Object a = mapA.get(key);
            Object b = mapB.get(key);

            if (!new FieldDiffResult(null, a, b) {
            }.isEqual())
               return false;
         }
      }

      return valueA.equals(valueB);
   }

}
