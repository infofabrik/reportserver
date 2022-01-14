package net.datenwerke.gxtdto.client.ui.helper.grid;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.KeyNav;

public class ExtendedKeyNav extends KeyNav {

   public ExtendedKeyNav() {
      super();
   }

   public ExtendedKeyNav(Widget target) {
      super(target);
   }

   public void onKeyPress(com.google.gwt.dom.client.NativeEvent evt) {
      if (evt.getCtrlKey() && 65 == evt.getKeyCode()) {
         onSelectAll();
      }
   }

   protected void onSelectAll() {
      // implement
   };
}
