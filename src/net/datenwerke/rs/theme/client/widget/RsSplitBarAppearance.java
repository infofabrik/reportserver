package net.datenwerke.rs.theme.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.widget.Css3SplitBarAppearance;

public class RsSplitBarAppearance extends Css3SplitBarAppearance {

   private final Css3SplitBarStyle style;

   public RsSplitBarAppearance() {
      this(GWT.<Css3SplitBarResources>create(Css3SplitBarResources.class));
   }

   public RsSplitBarAppearance(Css3SplitBarResources resources) {
      super(resources);
      this.style = resources.style();

      StyleInjectorHelper.ensureInjected(style, true);
   }

   @Override
   public void render(SafeHtmlBuilder sb, LayoutRegion region) {
      String cls = "";
      if (region == LayoutRegion.SOUTH || region == LayoutRegion.NORTH) {
         cls = style.horizontalBar();
      } else {
         cls = style.verticalBar();
      }
      sb.appendHtmlConstant("<div class='" + cls + " rs-splitbar'></div>");
   }
}
