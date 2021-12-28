package net.datenwerke.gxtdto.client.baseex.widget.drag;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.fx.client.Draggable;

public class DwDraggable extends Draggable {

   public DwDraggable(Widget dragComponent) {
      super(dragComponent);
   }

   public DwDraggable(Widget dragComponent, DraggableAppearance appearance) {
      super(dragComponent, appearance);
   }

   public DwDraggable(Widget dragComponent, Widget handle, DraggableAppearance appearance) {
      super(dragComponent, handle, appearance);
   }

   public DwDraggable(Widget dragComponent, Widget handle) {
      super(dragComponent, handle);
   }

   @Override
   protected XElement createProxy() {
      XElement proxy = super.createProxy();
      proxy.addClassName("rs-draggable");
      return proxy;
   }
}
