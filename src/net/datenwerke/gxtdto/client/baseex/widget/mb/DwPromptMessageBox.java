package net.datenwerke.gxtdto.client.baseex.widget.mb;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;

public class DwPromptMessageBox extends PromptMessageBox {

   public DwPromptMessageBox(String titleHtml, String messageHtml) {
      this(SafeHtmlUtils.fromString(titleHtml), SafeHtmlUtils.fromString(messageHtml));
   }

   public DwPromptMessageBox(SafeHtml titleHtml, SafeHtml messageHtml) {
      super(titleHtml.asString(), messageHtml.asString());
      setPredefinedButtons(PredefinedButton.CANCEL, PredefinedButton.OK);
      getTextField().setWidth(110);
      initCss();
   }

   public DwPromptMessageBox(String titleHtml, String messageHtml, boolean trustedStrings) {
      this(trustedStrings ? SafeHtmlUtils.fromTrustedString(titleHtml) : SafeHtmlUtils.fromString(titleHtml),
            trustedStrings ? SafeHtmlUtils.fromTrustedString(messageHtml) : SafeHtmlUtils.fromString(messageHtml));
   }

   protected void initCss() {
      getElement().addClassName(DwWindow.CSS_NAME);
      getAppearance().getHeaderElem(getElement()).addClassName(DwWindow.CSS_HEADER_NAME);
      getAppearance().getBodyWrap(getElement()).addClassName(DwWindow.CSS_BODY_NAME);
      getAppearance().getContentElem(getElement()).addClassName(DwWindow.CSS_CONTENT_NAME);

      getButtonBar().getElement().addClassName(DwWindow.CSS_BBAR);
   }

   @Override
   protected void createButtons() {
      Widget focusWidget = getFocusWidget();

      boolean focus = focusWidget == null || (focusWidget != null && getButtonBar().getWidgetIndex(focusWidget) != -1);

      getButtonBar().clear();

      SelectHandler handler = new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            onButtonPressed((TextButton) event.getSource());
         }
      };

      for (int i = 0; i < getPredefinedButtons().size(); i++) {
         PredefinedButton b = getPredefinedButtons().get(i);
         TextButton tb = new DwTextButton(getText(b)); // only line that needed changin
         tb.setItemId(b.name());
         tb.addSelectHandler(handler);
         if (i == 0 && focus) {
            setFocusWidget(tb);
         }
         addButton(tb);
      }
   }

}
