package net.datenwerke.gf.client.homepage.modules;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public interface ClientModule {

   String getModuleName();

   ImageResource getIcon();

   Widget getMainWidget();

   String getToolTip();

   /**
    * Tells the module that it was selected.
    */
   void selected();

   boolean isRecyclable();

}
