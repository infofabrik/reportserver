package net.datenwerke.gf.client.homepage.ui;

import com.google.gwt.user.client.ui.Widget;

public abstract class DwMainViewportTopBarWidget implements DwMainViewportTopBarElement {

   public String getName() {
      return null;
   }

   public void onClick() {

   }

   public abstract Widget getComponent();

   public int getSize() {
      return 150;
   }
}
