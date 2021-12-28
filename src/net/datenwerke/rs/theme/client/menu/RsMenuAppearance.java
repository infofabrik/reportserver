package net.datenwerke.rs.theme.client.menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.theme.neptune.client.base.menu.Css3MenuAppearance;

public class RsMenuAppearance extends Css3MenuAppearance {

   public interface RsBaseMenuTemplate extends BaseMenuTemplate {

      @XTemplate(source = "RsMenu.html")
      SafeHtml template(MenuStyle style, String ignoreClass);

   }

   public RsMenuAppearance() {
      this(GWT.<Css3MenuResources>create(Css3MenuResources.class),
            GWT.<BaseMenuTemplate>create(RsBaseMenuTemplate.class));
   }

   public RsMenuAppearance(Css3MenuResources resources, BaseMenuTemplate template) {
      super(resources, template);
   }
}
