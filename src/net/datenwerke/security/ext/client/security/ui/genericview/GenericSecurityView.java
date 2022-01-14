package net.datenwerke.security.ext.client.security.ui.genericview;

import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;

import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;

public class GenericSecurityView extends DwBorderContainer {

   private final GenericSecurityMainPanel mainPanel;
   private final GenericSecurityNavigationPanel navigationPanel;

   @Inject
   public GenericSecurityView(GenericSecurityMainPanel mainPanel, GenericSecurityNavigationPanel navigationPanel) {

      /* store objects */
      this.mainPanel = mainPanel;
      this.navigationPanel = navigationPanel;

      initializeUI();
   }

   private void initializeUI() {
      /* set layout */
      BorderLayoutData westData = new BorderLayoutData(250);
      westData.setSplit(true);
      westData.setMargins(new Margins(0, 1, 0, 0));

      setWestWidget(navigationPanel, westData);
      setCenterWidget(mainPanel);
   }

}
