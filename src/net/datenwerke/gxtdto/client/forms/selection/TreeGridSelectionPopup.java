package net.datenwerke.gxtdto.client.forms.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 * @param <G> grid type
 */
public class TreeGridSelectionPopup<G> extends Grid<G> {

   private final UITree tree;
   private final Class<? extends RsDto>[] types;

   public TreeGridSelectionPopup(Map<ValueProvider<G, String>, String> displayProperties, int columnWidth,
         ListStore<G> selectedStore, UITree tree, Class<? extends RsDto>... types) {
      super(selectedStore, init(displayProperties, columnWidth));
      this.tree = tree;
      this.types = types;

      initializeUI();
   }

   private static <M> ColumnModel<M> init(Map<ValueProvider<M, String>, String> displayProperties, int columnWidth) {
      /* create columns */
      List<ColumnConfig<M, ?>> configs = new ArrayList<ColumnConfig<M, ?>>();

      /* add main column */
      for (ValueProvider<M, String> vp : displayProperties.keySet()) {
         ColumnConfig<M, String> cc = new ColumnConfig<M, String>(vp, 300, displayProperties.get(vp));
         if (columnWidth != -1)
            cc.setWidth(columnWidth);
         cc.setMenuDisabled(true);
         configs.add(cc);
      }

      return new ColumnModel<M>(configs);
   }

   private void initializeUI() {
      getView().setTrackMouseOver(false);

      getView().setEmptyText(FormsMessages.INSTANCE.noDataSelected());
      if (getColumnModel().getColumnCount() != 0)
         getView().setAutoExpandColumn(getColumnModel().getColumn(getColumnModel().getColumnCount() - 1));

      setHeight(200);

      /* set selection model - has to be set after column model */
      setSelectionModel(new GridSelectionModel<G>());

      /* from Grid constructor */
      setAllowTextSelection(false);
   }

   protected void onItemsSelected(List<AbstractNodeDto> selectedItems) {
      /* add members */
      store.clear();
      for (AbstractNodeDto item : selectedItems) {
         if (null == store.findModelWithKey(String.valueOf(item.getId()))) {
            store.add((G) convertToStrippedDownObject(item));
         }
      }
   }

   @Override
   protected void onDoubleClick(Event e) {
      super.onDoubleClick(e);

      TreeSelectionPopup popup = new TreeSelectionPopup(tree, types) {
         @Override
         protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
            onItemsSelected(selectedItems);
         }

         @Override
         protected void validateSelectedItems(List<AbstractNodeDto> selectedItems,
               AsyncCallback<Boolean> itemsValidatedCallback) {
            TreeGridSelectionPopup.this.validateSelectedItems(selectedItems, itemsValidatedCallback);
         }
      };
      List<AbstractNodeDto> selectedUsers = new ArrayList<>();
      for (G strippedMember : store.getAll()) {
         selectedUsers.add(convertFromStrippedDownObject(strippedMember));
      }
      popup.setSelectedValues(selectedUsers.toArray(new AbstractNodeDto[] {}));
      popup.setSelectionMode(SelectionMode.MULTI);
      popup.setHeaderIcon(BaseIcon.ADD);
      popup.setHeading(BaseMessages.INSTANCE.add());
      popup.show();

   }

   protected void validateSelectedItems(List<AbstractNodeDto> selectedItems,
         AsyncCallback<Boolean> itemsValidatedCallback) {
      itemsValidatedCallback.onSuccess(true);
   }

   private Object convertToStrippedDownObject(AbstractNodeDto node) {
      if (node instanceof UserDto)
         return StrippedDownUser.fromUser((UserDto) node);
      if (node instanceof GroupDto)
         return StrippedDownGroup.fromGroup((GroupDto) node);

      throw new IllegalArgumentException("Node must be userDto or groupDto");
   }

   private AbstractNodeDto convertFromStrippedDownObject(G stripped) {
      if (stripped instanceof StrippedDownUser) {
         UserDtoDec node = new UserDtoDec();
         node.setId(((StrippedDownUser) stripped).getId());
         node.setFirstname(((StrippedDownUser) stripped).getFirstname());
         node.setLastname(((StrippedDownUser) stripped).getLastname());
         return node;
      }
      if (stripped instanceof StrippedDownGroup) {
         UserDtoDec node = new UserDtoDec();
         node.setId(((StrippedDownGroup) stripped).getId());
         node.setFirstname(((StrippedDownGroup) stripped).getName());
         return node;
      }

      throw new IllegalArgumentException("Node must be user or group");
   }

}
