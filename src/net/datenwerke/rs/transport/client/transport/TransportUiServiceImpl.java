package net.datenwerke.rs.transport.client.transport;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.wrapper.ToolbarWrapperPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.pa.TransportCheckEntryDtoPA;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;

public class TransportUiServiceImpl implements TransportUiService {

   @Override
   public void displayAnalysisResult(List<TransportCheckEntryDto> result) {
      final DwWindow window = new DwWindow();
      window.setHeading(TransportMessages.INSTANCE.analysisResults());
      window.setSize(960, 680);
      window.setMaximizable(true);

      Component gridComponent = createGrid(result);
      window.add(gridComponent);

      DwTextButton closeBtn = new DwTextButton(BaseMessages.INSTANCE.close());
      closeBtn.addSelectHandler(event -> window.hide());
      window.addButton(closeBtn);

      window.show();
      
   }
   
   private Component createGrid(List<TransportCheckEntryDto> result) {
      final TransportCheckEntryDtoPA props = TransportCheckEntryDtoPA.INSTANCE;
      final ListStore<TransportCheckEntryDto> store = new ListStore<>(TransportCheckEntryDto::getKey);
      
      /* configure columns */
      List<ColumnConfig<TransportCheckEntryDto, ?>> columns = new ArrayList<>();
      
      /* key column */
      ColumnConfig<TransportCheckEntryDto, String> keyConfig = new ColumnConfig<>(
            props.key(), 250, BaseMessages.INSTANCE.key());
      keyConfig.setMenuDisabled(true);
      columns.add(keyConfig);
      
      /* result column */
      ColumnConfig<TransportCheckEntryDto, String> resultConfig = new ColumnConfig<>(
            props.result(), 80, TransportMessages.INSTANCE.result());
      resultConfig.setCell(new AbstractCell<String>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, String value,
               SafeHtmlBuilder sb) {
            if ("OK".equals(value)) sb.append(BaseIcon.CHECK.toSafeHtml());
            else sb.append(BaseIcon.ERROR.toSafeHtml());
         }
      });
      
      resultConfig.setMenuDisabled(true);
      columns.add(resultConfig);
      
      /* error message column */
      ColumnConfig<TransportCheckEntryDto, String> errorMsgConfig = new ColumnConfig<>(
            props.errorMsg(), 280, TransportMessages.INSTANCE.errorMessage());
      errorMsgConfig.setMenuDisabled(true);
      columns.add(errorMsgConfig);
      
      /* create grid */
      final Grid<TransportCheckEntryDto> grid = new Grid<>(store,
            new ColumnModel<TransportCheckEntryDto>(columns));
      grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      grid.getView().setAutoExpandColumn(errorMsgConfig);
      grid.getView().setShowDirtyCells(false);
      
      ToolbarWrapperPanel wrapper = new ToolbarWrapperPanel(grid) {};
      
      store.clear();
      store.addAll(result);

      
      return wrapper;
   }
}
