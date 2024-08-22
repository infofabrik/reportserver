package net.datenwerke.gf.service.history;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class HistoryServiceImpl implements HistoryService {

   private final HookHandlerService hookHandler;

   @Inject
   public HistoryServiceImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public List<HistoryLink> buildLinksFor(final Object o) {
      return hookHandler.getHookers(HistoryUrlBuilderHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(o))
            .flatMap(hooker -> hooker.buildLinksFor(o).stream())
            .collect(toList());
   }

   @Override
   public List<HistoryLink> buildLinksForList(List<? extends Object> objects) {
      return objects
            .stream()
            .flatMap(object -> buildLinksFor(object).stream())
            .collect(toList());
   }

   @Override
   public List<String> getFormattedObjectPaths(Object o) {
      return buildLinksFor(o)
            .stream()
            .filter(Objects::nonNull)
            .map(historyLink -> historyLink.getObjectCaption() + " (" + historyLink.getHistoryLinkBuilderId() + ")")
            .collect(toList());
   }
   
   @Override
   public List<String> getRealObjectPaths(Object o) {
      return buildLinksFor(o)
            .stream()
            .filter(Objects::nonNull)
            .map(historyLink -> getRealPath(o, historyLink.getObjectCaption()))
            .collect(toList());
   }

   private String getRealPath(Object o, String link) {
      StringBuilder path = new StringBuilder();
      if (null != link) {
         String[] splitedLink = link.split("/");
         String rootNodeName = ((AbstractNode) o).getRootNodeName();
         path.append("/"+rootNodeName);
         for (int i = 1; i < splitedLink.length; i++) {
            path.append("/" + splitedLink[i].replace("+", " "));
         }
      }
      return path.toString();
   }
   

}
