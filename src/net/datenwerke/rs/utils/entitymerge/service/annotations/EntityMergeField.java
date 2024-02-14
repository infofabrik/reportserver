package net.datenwerke.rs.utils.entitymerge.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation signals that a field is merged
 * when using the EntityMergeService.
 * 
 * defaultMerge() signals if a field is to be merged 
 * automatically. If set to false it need to be handled
 * in the mergeSpecialFields() method. 
 * 
 * toClone() signals if the value is to be cloned 
 * using the EntityClonerService while merging
 * automatically. 
 * 
 * Basic Java types can usually be merged automatically
 * and don't need to be cloned.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntityMergeField {

   boolean defaultMerge() default true;
   boolean toClone() default false;

}
