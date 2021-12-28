package net.datenwerke.gxtdto.client.clipboard;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.event.HeaderClickEvent;
import com.sencha.gxt.widget.core.client.event.HeaderClickEvent.HeaderClickHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

public abstract class ClipboardHandler implements KeyDownHandler {

   protected Widget component;

   public ClipboardHandler(Widget target) {
      bind(target);
   }

   /**
    * Binds the key nav to the component.
    * 
    * @param target the target component
    */
   public void bind(final Widget target) {
      if (target != null) {
         target.addDomHandler(this, KeyDownEvent.getType());

         if (target instanceof Grid) {
            ((Grid) target).addHeaderClickHandler(new HeaderClickHandler() {

               @Override
               public void onHeaderClick(HeaderClickEvent event) {
                  ((Component) target).focus();
               }
            });
         }
      }
      this.component = target;
   }

}
