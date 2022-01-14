package net.datenwerke.gf.client.treedb.dnd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class UITreeDragDropConfiguration {

   final private Map<String, Boolean> cache = new HashMap<String, Boolean>();

   final private Map<Class<? extends AbstractNodeDto>, Set<Class<? extends AbstractNodeDto>>> specifiedDropTargets = new HashMap<Class<? extends AbstractNodeDto>, Set<Class<? extends AbstractNodeDto>>>();
   final private Map<Class<? extends AbstractNodeDto>, Set<Class<? extends AbstractNodeDto>>> deniedDropCombinations = new HashMap<Class<? extends AbstractNodeDto>, Set<Class<? extends AbstractNodeDto>>>();
   final private Set<Class<? extends AbstractNodeDto>> deniedSources = new HashSet<Class<? extends AbstractNodeDto>>();

   /**
    * Marks the target a drop target for the supplied types.
    * 
    * @param target
    * @param dragTypes
    */
   public void addDropTarget(Class<? extends AbstractNodeDto> target, Class<? extends AbstractNodeDto>... dragTypes) {
      if (!specifiedDropTargets.containsKey(target))
         specifiedDropTargets.put(target, new HashSet<Class<? extends AbstractNodeDto>>());

      Set<Class<? extends AbstractNodeDto>> dragTypeSet = specifiedDropTargets.get(target);
      for (Class<? extends AbstractNodeDto> dragType : dragTypes)
         dragTypeSet.add(dragType);
   }

   public void denyDropCombination(Class<? extends AbstractNodeDto> dropTarget,
         Class<? extends AbstractNodeDto>... dragSources) {
      if (!deniedDropCombinations.containsKey(dropTarget))
         deniedDropCombinations.put(dropTarget, new HashSet<Class<? extends AbstractNodeDto>>());

      Set<Class<? extends AbstractNodeDto>> dragTypeSet = deniedDropCombinations.get(dropTarget);
      for (Class<? extends AbstractNodeDto> dragType : dragSources)
         dragTypeSet.add(dragType);
   }

   public void denyDragSource(Class<? extends AbstractNodeDto> target) {
      deniedSources.add(target);
   }

   public boolean isDropTarget(Class<? extends AbstractNodeDto> source, Class<? extends AbstractNodeDto> target) {
      /* check in cache */
      String key = source.getName() + "-" + target.getName();
      if (cache.containsKey(key))
         return cache.get(key);

      boolean allowed = _isDropTarget(source, target);
      cache.put(key, allowed);

      return allowed;
   }

   private boolean _isDropTarget(Class<? extends AbstractNodeDto> source, Class<? extends AbstractNodeDto> target) {
      Class<?> currentTarget = target;
      boolean dropTargetAllowed = false;
      while (null != currentTarget) {
         if (specifiedDropTargets.containsKey(currentTarget)) {
            dropTargetAllowed = true;
            break;
         }
         currentTarget = currentTarget.getSuperclass();
      }
      if (!dropTargetAllowed)
         return false;

      Class<?> currentSource = source;
      while (null != currentSource) {
         if (deniedSources.contains(source))
            return false;

         /* check combo */
         currentTarget = target;
         while (null != currentTarget) {
            if (deniedDropCombinations.containsKey(currentTarget)
                  && deniedDropCombinations.get(currentTarget).contains(currentSource))
               return false;
            currentTarget = currentTarget.getSuperclass();
         }

         currentSource = currentSource.getSuperclass();
      }

      currentTarget = target;
      boolean foundSpecifiedDropTarget = false;
      while (null != currentTarget) {
         if (specifiedDropTargets.containsKey(currentTarget) && !specifiedDropTargets.get(currentTarget).isEmpty()) {
            foundSpecifiedDropTarget = true;

            currentSource = source;
            while (null != currentSource) {
               if (specifiedDropTargets.get(currentTarget).contains(currentSource))
                  return true;

               currentSource = currentSource.getSuperclass();
            }
         }
         currentTarget = currentTarget.getSuperclass();
      }

      if (foundSpecifiedDropTarget)
         return false;
      return true;
   }

}
