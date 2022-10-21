package net.datenwerke.rs.theme.client.tab;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.theme.neptune.client.base.tabs.Css3TabPanelAppearance;

import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class RsTabPanelAppearance extends Css3TabPanelAppearance {

   @Override
   protected void setItemIcon(XElement item, ImageResource icon) {
      XElement node = item.selectNode("." + style.tabImage());
      if (node != null) {
         node.removeFromParent();
      }
      if (icon != null) {
         Element e = null;
         if (icon instanceof CssIconImageResource) {
            e = ((CssIconImageResource) icon).getCssElement();
            XElement text = item.selectNode("." + style.tabStripText());
            if (null != text)
               item.insertBefore(e, text);
            else
               item.appendChild(e);
            e.addClassName(style.tabImage());
         } else {
            e = IconHelper.getElement(icon);
            e.setClassName(style.tabImage());
         }

      }
      item.setClassName(style.tabWithIcon(), icon != null);
   }

   @Override
   public void createScrollers(XElement parent) {
      int h = getStripWrap(parent).getOffsetHeight();
      SafeHtml html = SafeHtmlUtils
            .fromTrustedString("<div class='" + style.tabScrollerLeft() + " rs-tp-scroll-l'></div>");
      XElement scrollLeft = getBar(parent).insertFirst(html);
      scrollLeft.setId(XDOM.getUniqueId());
      scrollLeft.setHeight(h);

      html = SafeHtmlUtils.fromTrustedString("<div class='" + style.tabScrollerRight() + " rs-tp-scroll-r'></div>");
      XElement scrollRight = getBar(parent).insertFirst(html);
      scrollRight.setId(XDOM.getUniqueId());
      scrollRight.setHeight(h);
   }

}
