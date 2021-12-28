package net.datenwerke.rs.tabletemplate.client.engines.xsl.hookers;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.tabletemplate.client.engines.xsl.XslTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xsl.locale.XslTemplateMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class XslTemplateClientProvider implements TableTemplateClientProviderHook {

   @Override
   public String getType() {
      return XslTemplateUIModule.TEMPLATE_TYPE;
   }

   @Override
   public String getName() {
      return "XSLT";
   }

   @Override
   public String getDescription() {
      return XslTemplateMessages.INSTANCE.templateTypeDescription();
   }

   @Override
   public ImageResource getIconLarge() {
      return BaseIcon.WRENCH.toImageResource(1);
   }

   @Override
   public ImageResource getIconSmall() {
      return BaseIcon.WRENCH.toImageResource();
   }

   @Override
   public ContentTypeConfig getContentTypeConfig() {
      return new ContentTypeConfig() {
         @Override
         public boolean isDisplay() {
            return true;
         }

         @Override
         public String getDefaultContentType() {
            return "plain/xml";
         }

         @Override
         public String getDefaultFileExtension() {
            return "xml";
         }
      };
   }
}
