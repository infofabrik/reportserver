package net.datenwerke.rs.search.client.search.module;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.rs.search.client.search.locale.SearchMessages;

public class SearchAreaMainWidget extends DwContentPanel {

   private SearchAreaModule module;

   public SearchAreaMainWidget() {
      setHeading(SearchMessages.INSTANCE.searchAreaModule());

      ToolButton toolButton = new ToolButton(ToolButton.CLOSE);
      addTool(toolButton);
      toolButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            close();
         }
      });
   }

   public void addSearchComponent(String search, Component displayComponent) {
      setWidget(displayComponent);
      forceLayoutCommand.execute();
   }

   public void close() {
      hide();
      module.removeModule();
   }

   public void setModule(SearchAreaModule reportExecuteAreaModule) {
      this.module = reportExecuteAreaModule;
   }
}
