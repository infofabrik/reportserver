package net.datenwerke.rs.theme.client.field;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.base.client.field.FileUploadDefaultAppearance;

public class RsUploadFieldAppearance extends FileUploadDefaultAppearance {

   public interface FileUploadTemplate extends XTemplates {
      @XTemplate("<div class='{style.wrap} rs-upl-field'></div>")
      SafeHtml render(FileUploadStyle style);
   }

   private final FileUploadResources resources;
   private final FileUploadStyle style;
   private final FileUploadTemplate template;

   public RsUploadFieldAppearance() {
      this(GWT.<FileUploadResources>create(FileUploadResources.class));
   }

   public RsUploadFieldAppearance(FileUploadResources resources) {
      this.resources = resources;
      this.style = this.resources.css();

      StyleInjectorHelper.ensureInjected(this.style, true);

      this.template = GWT.create(FileUploadTemplate.class);
   }

   @Override
   public void render(SafeHtmlBuilder sb) {
      sb.append(template.render(style));
   }
}
