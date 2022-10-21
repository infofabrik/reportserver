package net.datenwerke.rs.theme.client.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.grid.Css3ColumnHeaderAppearance;
import com.sencha.gxt.widget.core.client.grid.ColumnHeader.ColumnHeaderStyles;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RsColumnHeaderAppearance extends Css3ColumnHeaderAppearance {

   public interface ColumnHeaderTemplate extends XTemplates {
      @XTemplate("<div class=\"{style.header} rs-grid-head\"><div class=\"{style.headerInner}\"></div></div>")
      SafeHtml render(ColumnHeaderStyles style);
   }

   public interface RsColumnHeaderAppearanceResources extends Css3ColumnHeaderResources {
      @Source("RsColumnHeader.gss")
      Styles style();
   }

   private final RsColumnHeaderAppearanceResources resources;
   private final ColumnHeaderStyles style;
   private ColumnHeaderTemplate templates = GWT.create(ColumnHeaderTemplate.class);

   public RsColumnHeaderAppearance() {
      this(GWT.<RsColumnHeaderAppearanceResources>create(RsColumnHeaderAppearanceResources.class));
   }

   public RsColumnHeaderAppearance(RsColumnHeaderAppearanceResources resources) {
      this.resources = resources;
      this.style = this.resources.style();

      StyleInjectorHelper.ensureInjected(style, true);
   }

   @Override
   public void render(SafeHtmlBuilder sb) {
      sb.append(templates.render(style));
   }

   @Override
   public ImageResource sortAscendingIcon() {
      return BaseIcon.SORT_ALPHA_ASC.toImageResource();
   }

   @Override
   public ImageResource sortDescendingIcon() {
      return BaseIcon.SORT_ALPHA_DESC.toImageResource();
   }

}
