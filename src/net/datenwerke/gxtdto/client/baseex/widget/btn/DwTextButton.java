package net.datenwerke.gxtdto.client.baseex.widget.btn;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwTextButton extends TextButton {

   @CssClassConstant
   public static final String CSS_NAME = "rs-btn";

   @CssClassConstant
   public static final String CSS_BODY_NAME = "rs-btn-body";

   public DwTextButton() {
      super();
      initCss();
   }

   public DwTextButton(String label) {
      super(label);
      initCss();
   }

   public DwTextButton(String label, ImageResource icon) {
      super(label, icon);
      initCss();
   }

   public DwTextButton(String label, SelectHandler selectHandler) {
      super(label, selectHandler);
      initCss();
   }

   public DwTextButton(BaseIcon icon) {
      super();
      initCss();
      setIcon(icon);
   }

   public DwTextButton(String label, BaseIcon icon) {
      super(label, null == icon ? null : icon.toImageResource());
      initCss();
   }

   private void initCss() {
      getElement().addClassName(getCssName());
      getCell().getAppearance().getButtonElement(getElement()).addClassName(getCssBodyName());
   }

   public String getCssName() {
      return CSS_NAME;
   }

   public String getCssBodyName() {
      return CSS_BODY_NAME;
   }

   public void setIcon(BaseIcon icon) {
      super.setIcon(icon.toImageResource());
   }
}
