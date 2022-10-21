package net.datenwerke.rs.theme.client.listview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.theme.neptune.client.base.listview.Css3ListViewAppearance;

public class RsListViewAppearance<M> extends Css3ListViewAppearance<M> {

   public interface Css3ListViewTemplates extends XTemplates {
      @XTemplate("<div class='{view} rs-lview'></div>")
      SafeHtml renderBody(Css3ListViewStyle style);

      @XTemplate("<div class='{style.item} rs-lview-item'>{content}</div>")
      SafeHtml renderItem(Css3ListViewStyle style, SafeHtml content);
   }

   private final Css3ListViewTemplates template;
   private final Css3ListViewStyle style;

   public RsListViewAppearance() {
      this(GWT.<Css3ListViewResources>create(Css3ListViewResources.class));
   }

   public RsListViewAppearance(Css3ListViewResources resources) {
      super(resources);
      this.style = resources.css();
      this.style.ensureInjected();
      this.template = GWT.create(Css3ListViewTemplates.class);
   }

   @Override
   public void render(SafeHtmlBuilder builder) {
      builder.append(template.renderBody(style));
   }

   @Override
   public void renderItem(SafeHtmlBuilder sb, SafeHtml content) {
      sb.append(template.renderItem(style, content));
   }

   @Override
   public void onOver(XElement item, boolean over) {
      item.setClassName(style.over(), over);
      item.setClassName("rs-lview-over", over);
   }
}
