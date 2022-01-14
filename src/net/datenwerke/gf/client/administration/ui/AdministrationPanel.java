package net.datenwerke.gf.client.administration.ui;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.util.Margins;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;

@Singleton
public class AdministrationPanel extends DwBorderContainer {

   @CssClassConstant
   public static final String CSS_NAME = "rs-admin";

   @CssClassConstant
   public static final String CSS_MAIN_NAME = "rs-admin-main";

   final private AdministrationNavPanel navigationPanel;

   @Inject
   public AdministrationPanel(AdministrationNavPanel navigationPanel) {
      super();

      this.navigationPanel = navigationPanel;

      initializeUI();

      navigationPanel.setAdministrationPanel(this);
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   public String getCssMainName() {
      return CSS_MAIN_NAME;
   }

   private void initializeUI() {
      /* set layout */
      BorderLayoutData westData = new BorderLayoutData(200);
      westData.setSplit(true);
      westData.setCollapsible(true);
      westData.setMargins(new Margins(0, 15, 0, 0));

      setWestWidget(navigationPanel, westData);
      setCenterWidget(DwContentPanel.newInlineInstance());
   }

   public void showAdminModule(AdminModule adminModule) {
      adminModule.notifyOfSelection();

      /* add module's widget */
      Widget widget = adminModule.getMainWidget();
      widget.getElement().addClassName(getCssMainName());
      setCenterWidget(widget);

      forceLayout();
   }
}
