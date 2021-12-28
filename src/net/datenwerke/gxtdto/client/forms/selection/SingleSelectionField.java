package net.datenwerke.gxtdto.client.forms.selection;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwTwinTriggerField;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwTwinTriggerFieldCell;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup.ItemsSelectedCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class SingleSelectionField<M> extends DwTwinTriggerField<M> {

   private final ListStore<M> allItemsStore;
   private final Map<ValueProvider<M, String>, String> displayProperties;
   private final SelectionPopup<M> popup;

   private boolean dataLoaded = false;

   public SingleSelectionField(ListStore<M> allItemsStore, Map<ValueProvider<M, String>, String> displayProperties) {
      super(new DwTwinTriggerFieldCell<M>(), new PropertyEditor<M>() {
         @Override
         public String render(M object) {
            if (null == object)
               return null;

            if (object instanceof Dto)
               return ((Dto) object).toDisplayTitle();
            return String.valueOf(object);
         }

         @Override
         public M parse(CharSequence text) throws ParseException {
            return null;
         }
      });
      setHideTwinTrigger(true);
      setTriggerIcon(BaseIcon.SEARCH);

      this.allItemsStore = allItemsStore;
      this.displayProperties = displayProperties;
      this.popup = null;

      /* build ui */
      initializeUI();
   }

   public SingleSelectionField(SelectionPopup<M> popup) {
      super(new DwTwinTriggerFieldCell<M>());
      this.allItemsStore = null;
      this.displayProperties = null;
      this.popup = popup;

      setHideTwinTrigger(true);
      setTriggerIcon(BaseIcon.SEARCH);

      /* build ui */
      initializeUI();
   }

   private void initializeUI() {
      /* configure triggerField */
      setEditable(false);

      setPropertyEditor(new PropertyEditor<M>() {
         @Override
         public String render(M object) {
            ValueProvider<M, String> vp = null;
            if (null != popup)
               vp = popup.getDisplayProperties().keySet().iterator().next();
            else
               vp = displayProperties.keySet().iterator().next();
            return vp.getValue(object);
         }

         @Override
         public M parse(CharSequence text) throws ParseException {
            return null;
         }
      });

      /* init context menu */
      initContextMenu();

      /* listener */
      addTriggerClickHandler(new TriggerClickHandler() {

         @Override
         public void onTriggerClick(TriggerClickEvent event) {
            triggerClicked(event);
         }
      });
   }

   protected void initContextMenu() {
      Menu menu = new DwMenu();

      MenuItem delete = new DwMenuItem();
      delete.setText(BaseMessages.INSTANCE.remove());
      menu.add(delete);

      delete.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            setValue(null, true);
         }
      });

      setContextMenu(menu);
   }

   /**
    * 
    */
   protected void triggerClicked(TriggerClickEvent e) {
      displaySelectionPopup();
   }

   public void displaySelectionPopup() {
      SelectionPopup<M> popup = this.popup;
      if (null == popup)
         popup = new SelectionPopup<M>(allItemsStore, displayProperties);

      popup.setSelectionCallback(new ItemsSelectedCallback<M>() {
         @Override
         public void itemsSelected(List<M> selectedItems) {
            if (selectedItems.size() > 0) {
               SingleSelectionField.this.setValue(selectedItems.get(0), true);
            } else {
               setValue(null, true);
            }
         }
      });

      popup.setSelectionMode(SelectionMode.SINGLE);

      if (!dataLoaded) {
         dataLoaded = true;
         popup.loadData();
      }
      if (null != getValue())
         popup.setSelectedItem(getValue());
      popup.show();
   }

}
