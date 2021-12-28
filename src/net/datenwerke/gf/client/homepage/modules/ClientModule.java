package net.datenwerke.gf.client.homepage.modules;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public interface ClientModule {

   public String getModuleName();

   public ImageResource getIcon();

   public Widget getMainWidget();

   public String getToolTip();

   /**
    * Tells the module, that it was selected.
    */
   public void selected();

   public boolean isRecyclable();

}
