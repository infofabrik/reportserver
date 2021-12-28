package net.datenwerke.gxtdto.client.ui.helper.misc;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.menu.Menu;

public class FloatingWrapper extends Menu {

   public FloatingWrapper() {
      super();
      addStyleName("rs-float-wrap");
   }

   public FloatingWrapper(Widget widget) {
      this();
      add(widget);
   }

}
