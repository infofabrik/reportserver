package net.datenwerke.rs.theme.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.theme.neptune.client.base.panel.Css3HeaderAppearance;

import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class RsHeaderAppearance extends Css3HeaderAppearance {

   private Css3HeaderStyle style;

   public RsHeaderAppearance() {
      this(GWT.<Css3HeaderResources>create(Css3HeaderResources.class));
   }

   public RsHeaderAppearance(Css3HeaderResources resources) {
      super(resources);

      this.style = resources.style();
   }

   @Override
   public void setIcon(XElement parent, ImageResource icon) {
      XElement iconWrap = parent.getFirstChildElement().cast();
      iconWrap.removeChildren();
      if (icon != null) {
         if (icon instanceof CssIconImageResource)
            iconWrap.appendChild(((CssIconImageResource) icon).getCssElement());
         else
            iconWrap.appendChild(IconHelper.getElement(icon));
      }
      parent.setClassName(style.headerHasIcon(), icon != null);
   }
}
