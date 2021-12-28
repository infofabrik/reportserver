package net.datenwerke.gf.client.homepage.modules.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.homepage.modules.ClientModule;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;

/**
 * 
 *
 */
@Singleton
public class ModuleManagerPanel extends DwContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-body";

   private CardLayoutContainer container;

   private Map<ClientModule, Widget> moduleWidgetMap = new HashMap<ClientModule, Widget>();

   private Widget lastActiveNonRecycleable;
   private ClientModule lastModule;

   @Inject
   public ModuleManagerPanel() {

      initializeUI();
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   /**
    * builds the basic UI
    */
   private void initializeUI() {
      setHeaderVisible(false);
      setBorders(false);
      setBodyBorder(false);

      container = new CardLayoutContainer();
      add(container, new MarginData(15, 10, 10, 10));
   }

   public void displayModule(ClientModule module) {
      if (module == lastModule)
         return;

      /* remove last non recycleable */
      if (null != lastActiveNonRecycleable) {
         lastActiveNonRecycleable.removeFromParent();
         lastActiveNonRecycleable = null;
      }

      if (module.isRecyclable()) {
         if (!moduleWidgetMap.containsKey(module)) {
            Widget widget = module.getMainWidget();
            moduleWidgetMap.put(module, widget);

            container.add(widget);
         }

         Widget widget = moduleWidgetMap.get(module);

         /* set active */
         container.setActiveWidget(widget);
      } else {
         Widget widget = module.getMainWidget();
         container.add(widget);
         container.setActiveWidget(widget);

         lastActiveNonRecycleable = widget;
      }

      lastModule = module;
   }

   public void removeModule(ClientTempModule module) {
      if (moduleWidgetMap.containsKey(module)) {
         moduleWidgetMap.get(module).removeFromParent();
         moduleWidgetMap.remove(module);
      }
   }

}
