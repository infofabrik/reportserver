package net.datenwerke.gf.service.history;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class HistoryServiceImpl implements HistoryService {

   private final HookHandlerService hookHandler;

   @Inject
   public HistoryServiceImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public List<HistoryLink> buildLinksFor(final Object o) {
      return hookHandler.getHookers(HistoryUrlBuilderHook.class).stream().filter(hooker -> hooker.consumes(o))
            .flatMap(hooker -> hooker.buildLinksFor(o).stream()).collect(toList());
   }

   @Override
   public List<HistoryLink> buildLinksForList(List<? extends Object> objects) {
      return objects.stream().flatMap((Object object) -> buildLinksFor(object).stream()).collect(toList());
   }

}
