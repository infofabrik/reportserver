package net.datenwerke.rs.utils.entitymerge.service;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class EntityMergeServiceImpl implements EntityMergeService{
   
   private final HookHandlerService hookHandlerService;
   
   @Inject
   public EntityMergeServiceImpl(HookHandlerService hookHandlerService) {
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   public void mergeEntity(Object a, Object b) throws IllegalArgumentException, IllegalAccessException{
      List<EntityMergeHook> hooks = hookHandlerService.getHookers(EntityMergeHook.class)
         .stream()
         .filter(hook -> hook.consumes(a, b))
         .collect(toList());

      /* if no hooker that applies to a and b is found throw exception */
      if (hooks.isEmpty())
         throw new IllegalArgumentException(String.format("No EntityMergeHook found that applies to %s and %s",
               a.getClass().toString(), b.getClass().toString()));

      hooks.forEach(rethrowConsumer(hook -> hook.mergeEntity(a, b)));
   }
}
