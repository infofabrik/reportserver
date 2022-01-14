package net.datenwerke.gxtdto.client.ui.helper.wrapper;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ToolbarWrapperPanel extends DwContentPanel {

   private final DwToolBar toolbar;

   public ToolbarWrapperPanel(Widget main) {
      setHeaderVisible(false);
      setBodyBorder(false);
      setBorders(false);

      NorthSouthContainer cont = new NorthSouthContainer();
      add(cont);

      toolbar = new DwToolBar();
      cont.setNorthWidget(toolbar);

      cont.add(main);
   }

   public ToolBar getToolbar() {
      return toolbar;
   }

   public void addRemoveButtons() {
      addRemoveButtons(BaseMessages.INSTANCE.remove(), BaseMessages.INSTANCE.removeAll());
   }

   public void addRemoveButtons(String removeItem, String removeAll) {
      /* add remove buttons */
      DwSplitButton removeButton = new DwSplitButton(removeItem);
      removeButton.setIcon(BaseIcon.DELETE);
      removeButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            removeButtonClicked(event);
         }
      });

      toolbar.add(removeButton);
      MenuItem removeAllButton = new DwMenuItem(removeAll, BaseIcon.DELETE);
      removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            removeAllButtonClicked(event);
         }
      });
      Menu remMenu = new DwMenu();
      remMenu.add(removeAllButton);
      removeButton.setMenu(remMenu);
   }

   protected void removeAllButtonClicked(SelectionEvent<Item> event) {

   }

   protected void removeButtonClicked(SelectEvent event) {

   }

   public void addSubmitButton() {
      addSubmitButton(BaseMessages.INSTANCE.submit());
   }

   public void addSubmitButton(String submit) {
      DwTextButton submitBtn = new DwTextButton(submit);
      submitBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            submitButtonClicked(event);
         }
      });
      addButton(submitBtn);
   }

   protected void submitButtonClicked(SelectEvent event) {

   }
}
