package net.datenwerke.gf.client.treedb;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;
import com.sencha.gxt.data.shared.TreeStore;

import net.datenwerke.gf.client.history.HistoryCallback;
import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.utils.handlers.GenericStoreHandler;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TreeDBHistoryCallback implements HistoryCallback {

   public static final String HISTORY_PARAMETER_TREE_PATH = "path";
   private static final int MAX_RETRY_CNT = 50;

   private final Provider<UITree> treeProvider;
   private final EventBus eventbus;
   private final Provider<?> submoduleComponentProvider;
   private final String submoduleParentId;

   private boolean selectParent;

   public TreeDBHistoryCallback(Provider<UITree> treeProvider, EventBus eventbus,
         Provider<?> submoduleComponentProvider, String submoduleParentId) {
      this.treeProvider = treeProvider;
      this.eventbus = eventbus;
      this.submoduleComponentProvider = submoduleComponentProvider;
      this.submoduleParentId = submoduleParentId;
   }

   public TreeDBHistoryCallback(Provider<UITree> treeProvider, EventBus eventbus, final Widget submodule,
         String submoduleParentId) {
      this.treeProvider = treeProvider;
      this.eventbus = eventbus;
      this.submoduleComponentProvider = new Provider<Widget>() {
         @Override
         public Widget get() {
            return submodule;
         }
      };
      this.submoduleParentId = submoduleParentId;
   }

   @Override
   public void locationChanged(HistoryLocation location) {
      if (location.hasParameter(HISTORY_PARAMETER_TREE_PATH)) {
         String[] pathElements = location.getParameterValue(HISTORY_PARAMETER_TREE_PATH).split("\\.");
         final ArrayList<Long> nodes = new ArrayList<Long>();
         for (String elem : pathElements) {
            nodes.add(Long.valueOf(elem));
         }

         /* display module */
         eventbus.fireEvent(new SubmoduleDisplayRequest(submoduleComponentProvider.get(), submoduleParentId));
         if (nodes.isEmpty())
            return;

         if (selectParent)
            nodes.remove(nodes.size() - 1);

         /* load tree and select correct item */
         final UITree tree = treeProvider.get();
         final TreeStore<AbstractNodeDto> store = tree.getStore();

         /* wait for root to load or begin expansion directly */
         if (store.getRootItems().isEmpty()) {
            final List<HandlerRegistration> dummy = new ArrayList<HandlerRegistration>();
            HandlerRegistration addStoreAddHandler = store.addStoreHandlers(new GenericStoreHandler<AbstractNodeDto>() {
               @Override
               protected void handleDataChangeEvent() {
                  if (store.getRootCount() > 0) {
                     for (HandlerRegistration reg : dummy)
                        reg.removeHandler();
                     tree.expandPathByIds(new ArrayList<Long>(nodes));
                  }
               }
            });

            dummy.add(addStoreAddHandler);
         } else
            tree.expandPathByIds(new ArrayList<Long>(nodes));

      }
   }

   protected void doHandleEvent(final ArrayList<Long> nodes, final UITree tree,
         final TreeStore<AbstractNodeDto> store) {
   }

   public void setSelectParent(boolean b) {
      this.selectParent = b;
   }

}
