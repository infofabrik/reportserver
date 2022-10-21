package net.datenwerke.rs.theme.client.icon;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.shared.event.GroupingHandlerRegistration;
import com.sencha.gxt.widget.core.client.WidgetComponent;

public class BaseIconComponent extends WidgetComponent implements HasClickHandlers {

   private GroupingHandlerRegistration handlers = new GroupingHandlerRegistration();

   public BaseIconComponent(SafeHtml safeHtml) {
      super(new HTML(safeHtml));
   }

   @Override
   public HandlerRegistration addClickHandler(ClickHandler handler) {
      HandlerRegistration clickHandler = ((HTML) getWidget()).addClickHandler(handler);
      handlers.add(clickHandler);
      return clickHandler;
   }

   public void release() {
      handlers.removeHandler();
      handlers = null;
   }
}
