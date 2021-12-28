package net.datenwerke.gxtdto.client.ui.helper.grid;

import java.util.List;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class DeletableRowsGrid<M> extends Grid<M> {

   public DeletableRowsGrid(ListStore<M> store, ColumnModel<M> cm) {
      super(store, cm);

      getSelectionModel().setSelectionMode(SelectionMode.MULTI);

      /* init context menu */
      initContextMenu();

      new ExtendedKeyNav(this) {
         protected void onSelectAll() {
            getSelectionModel().selectAll();
         };

         @Override
         public void onDelete(NativeEvent evt) {
            deleteSelection();
         }
      };
   }

   protected void initContextMenu() {
      Menu menu = new DwMenu();

      MenuItem delete = new DwMenuItem();
      delete.setText(BaseMessages.INSTANCE.remove());
      menu.add(delete);

      delete.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            deleteSelection();
         }
      });

      MenuItem deleteAll = new DwMenuItem();
      deleteAll.setText(BaseMessages.INSTANCE.removeAll());
      menu.add(deleteAll);

      deleteAll.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            deleteAll();
         }
      });

      setContextMenu(menu);
   }

   public void deleteSelection() {
      List<M> items = this.getSelectionModel().getSelectedItems();

      for (M model : items) {
         this.getStore().remove(model);
         deletedModel(model);
      }
   }

   public void deleteAll() {
      ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.removeAll(),
            BaseMessages.INSTANCE.confirmDeleteMsg(""));
      cmb.addDialogHideHandler(new DialogHideHandler() {
         @Override
         public void onDialogHide(DialogHideEvent event) {
            if (event.getHideButton() == PredefinedButton.YES) {
               DeletableRowsGrid.this.getStore().clear();

               deletedAllModels();
            }

         }
      });
      cmb.show();
   }

   protected void deletedModel(M model) {

   }

   protected void deletedAllModels() {

   }
}
