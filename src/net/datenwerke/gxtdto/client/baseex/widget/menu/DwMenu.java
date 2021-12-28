package net.datenwerke.gxtdto.client.baseex.widget.menu;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;

public class DwMenu extends Menu {

   @CssClassConstant
   public static final String CSS_NAME = "rs-menu";

   public DwMenu() {
      super();
      initCss();
   }

   private void initCss() {
      getElement().addClassName(getCssName());
   }

   public String getCssName() {
      return CSS_NAME;
   }
}
