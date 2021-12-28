package net.datenwerke.rs.utils.entitydiff.result;

import java.lang.reflect.Field;

public class BaseFieldDiffResult extends FieldDiffResult {

   public BaseFieldDiffResult(Field field, Object valueA, Object valueB) {
      super(field, valueA, valueB);
   }

}
