package net.datenwerke.rs.utils.entitydiff.result;

import java.lang.reflect.Field;

public class EnclosedEntityFieldDiffResult extends BaseFieldDiffResult {

   public EnclosedEntityFieldDiffResult(Field field, Object valueA, Object valueB) {
      super(field, valueA, valueB);
   }

}
