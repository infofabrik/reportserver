package net.datenwerke.rs.utils.entitymerge.service.hooker;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeCollection;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public abstract class EntityMergeHooker  implements EntityMergeHook{
   
   
   protected final Class<?> targetClazz; // class to compare in consumes()
   protected final Provider<EntityClonerService> cloneService;
   
   public EntityMergeHooker(Class<?> targetClazz, Provider<EntityClonerService> cloneService) {
      this.targetClazz = targetClazz;
      this.cloneService = cloneService;
   }
   
   /**
    * Evaluates if the hook should apply to the supplied objects.
    * Should only return true if both are of type targetClazz
    */
   @Override
   public boolean consumes(Object oldInstance, Object newInstance) {
      Class<?> oldInstanceClazz = oldInstance.getClass();
      Class<?> newInstanceClazz = newInstance.getClass();
      if(oldInstanceClazz == targetClazz && newInstanceClazz == targetClazz)
         return true;
      return false;
   }
   

   /**
    * This method is supposed to commit changes of the updated old instance to the database
    * using their respective services e.g. Datasinks -> DatasinkService etc
    * @param oldInstance
    */
   protected abstract void commitChanges(Object oldInstance);
   
   /**
    * If there is a complex field that can not be copy pasted
    * then there you can do do your complex operations here
    * @param oldInstance: to be updated
    * @param newInstance new instance: source for update values
    */
   protected abstract void mergeSpecialFields(Object oldInstance, Object newInstance);
   
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void mergeEntity(Object oldInstance, Object newInstance) throws IllegalAccessException {
      // Annotation Merge
      mergeAnnotations(oldInstance, newInstance);
      // Merge special fields
      mergeSpecialFields(oldInstance, newInstance);
      // Save 
      commitChanges(oldInstance);
   }
   /**
    * This performs a simple value copy from instanceB
    * to instanceA. This occurs for all field that 
    * are annotated with the EntityMergeField annotation
    * @param instanceA old instance: to be updated
    * @param instanceB new instance: source for update values
    * @throws IllegalAccessException  
    */
   private void mergeAnnotations(Object instanceA, Object instanceB) throws IllegalAccessException {
      Class<?> currentClazz = instanceA.getClass();
      List<Class<?>> classes = new ArrayList<Class<?>>();
      /* collect all classes (base and super classes) */
      do {
         classes.add(currentClazz);
         currentClazz = currentClazz.getSuperclass();
      } while (Objects.nonNull(currentClazz));
      /*
       * 1. collect all field from all found classes 
       * 2. filter to find annotated field
       * 3. transfer value from B to A
       */
      classes
         .stream()
         .map(clazz -> clazz.getDeclaredFields())
         .flatMap(Arrays::stream)
         .filter(field -> field.isAnnotationPresent(EntityMergeField.class))
         .filter(field -> field.getAnnotation(EntityMergeField.class).defaultMerge())
         .forEach(rethrowConsumer(field -> {
            boolean fieldAccess = field.isAccessible();
            field.setAccessible(true);
            Object valueB = field.get(instanceB);
            EntityMergeField annotation = field.getAnnotation(EntityMergeField.class);
            valueB = annotation.toClone()? cloneService.get().cloneEntity(valueB, valueB.getClass()) : valueB;
            field.set(instanceA, valueB);
            field.setAccessible(fieldAccess);

         }));
      /* merge collections */
      classes
      .stream()
      .map(clazz -> clazz.getDeclaredFields())
      .flatMap(Arrays::stream)
      .filter(field -> field.isAnnotationPresent(EntityMergeCollection.class))
      .filter(field -> field.getAnnotation(EntityMergeCollection.class).defaultMerge())
      .forEach(rethrowConsumer(field -> {        
         boolean fieldAccess = field.isAccessible();
         field.setAccessible(true);
         EntityMergeCollection annotation = field.getAnnotation(EntityMergeCollection.class);
         Collection<Object> valueB = (Collection<Object>)field.get(instanceB);
         Collection<Object> valueA = (Collection<Object>)field.get(instanceA);
         valueA.clear();
         /* Fill valueA with elements from valueB */
         if(annotation.toClone()) {
            for(Object element : valueB) {
               Object copy = cloneService.get().cloneEntity(element, element.getClass());
               valueA.add(copy);
            }      
         } else {
            valueA.addAll(valueB);
         }
         field.setAccessible(fieldAccess);

      }));
   }
}
