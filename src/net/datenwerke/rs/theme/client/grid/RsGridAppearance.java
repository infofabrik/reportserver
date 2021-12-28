package net.datenwerke.rs.theme.client.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.grid.Css3GridAppearance;

public class RsGridAppearance extends Css3GridAppearance {

   public interface GridTemplates extends XTemplates {
      @XTemplate(source = "Grid.html")
      SafeHtml render(GridStyle style);
   }

   protected final GridResources resources;
   protected final GridStyle style;
   private GridTemplates templates = GWT.create(GridTemplates.class);

   public RsGridAppearance() {
      this(GWT.<GridResources>create(GridResources.class));
   }

   public RsGridAppearance(GridResources resources) {
      this.resources = resources;
      this.style = this.resources.css();

      StyleInjectorHelper.ensureInjected(style, true);
   }

   @Override
   public void render(SafeHtmlBuilder sb) {
      sb.append(templates.render(style));
   }

   @Override
   public void onRowHighlight(Element row, boolean highlight) {
      super.onRowHighlight(row, highlight);
      row.<XElement>cast().setClassName("rs-grid-hl", highlight);
   }

   @Override
   public void onRowOver(Element row, boolean over) {
      super.onRowOver(row, over);
      row.<XElement>cast().setClassName("rs-grid-over", over);
   }
}
