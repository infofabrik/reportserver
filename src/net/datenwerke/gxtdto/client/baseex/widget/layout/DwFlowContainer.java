package net.datenwerke.gxtdto.client.baseex.widget.layout;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class DwFlowContainer extends FlowLayoutContainer {

   private boolean inline;

   public void setDisplayInline(boolean inline) {
      this.inline = inline;
   }

   @Override
   protected void onInsert(int index, Widget child) {
      super.onInsert(index, child);

      if (inline) {
         child.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
      }
   }
}
