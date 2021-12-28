package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.List;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;

import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;

/**
 * 
 *
 * @param <M>
 */
public abstract class TextView<M> extends DwNorthSouthContainer {

   @Inject
   protected static FilterService filterService;

   final protected ListStore<M> store;
   final protected SelectionPanel<M> selectionPanel;
   final protected ColumnDto column;

   protected TextArea textArea;

   public TextView(ListStore<M> store, SelectionPanel<M> selectionPanel, ColumnDto column, final TabPanel tPanel) {
      this.store = store;
      this.selectionPanel = selectionPanel;
      this.column = column;

      this.textArea = new TextArea();

      setBorders(false);

      setWidget(textArea);

      new DropTarget(textArea) {
         @Override
         protected void onDragDrop(DndDropEvent e) {
            super.onDragDrop(e);
            Object data = e.getData();
            List<Object> models = prepareDropData(data, true);

            handleDroppedData(models, e);
         }
      };

      tPanel.addBeforeSelectionHandler(new BeforeSelectionHandler<Widget>() {
         @Override
         public void onBeforeSelection(BeforeSelectionEvent<Widget> event) {
            if (tPanel.getContainer().getActiveWidget() == TextView.this) {
               try {
                  List<M> newDtos = tryParseText();
                  TextView.this.store.clear();
                  TextView.this.store.addAll(newDtos);
               } catch (RuntimeException e) {
                  new DwAlertMessageBox(FilterMessages.INSTANCE.textViewInvalidMessage(), e.getMessage()).show();
                  event.cancel();
               }
            }
         }
      });
   }

   abstract protected void handleDroppedData(List<Object> models, DndDropEvent e);

   abstract protected List<M> tryParseText() throws RuntimeException;

}
