package net.datenwerke.rs.tabletemplate.client.engines.jxls.hookers;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.tabletemplate.client.engines.jxls.JXlsTemplateUiModule;
import net.datenwerke.rs.tabletemplate.client.engines.jxls.locale.JXlsTemplateMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class JXlsTemplateClientProvider implements TableTemplateClientProviderHook {

   @Override
   public String getType() {
      return JXlsTemplateUiModule.TEMPLATE_TYPE;
   }

   @Override
   public String getName() {
      return "JXLS";
   }

   @Override
   public String getDescription() {
      return JXlsTemplateMessages.INSTANCE.templateTypeDescription();
   }

   @Override
   public ImageResource getIconLarge() {
      return BaseIcon.fromFileExtension("xls").toImageResource(1);
   }

   @Override
   public ImageResource getIconSmall() {
      return BaseIcon.fromFileExtension("xls").toImageResource();
   }

   @Override
   public ContentTypeConfig getContentTypeConfig() {
      return new ContentTypeConfig() {
         @Override
         public boolean isDisplay() {
            return false;
         }

         @Override
         public String getDefaultContentType() {
            return null;
         }

         @Override
         public String getDefaultFileExtension() {
            return null;
         }
      };
   }

}
