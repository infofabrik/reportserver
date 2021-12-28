package net.datenwerke.rs.tabletemplate.client.engines.velocity.hookers;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.tabletemplate.client.engines.velocity.VelocityTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.velocity.locale.VelocityTemplateMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class VelocityTemplateClientProvider implements TableTemplateClientProviderHook {

   @Override
   public String getType() {
      return VelocityTemplateUIModule.TEMPLATE_TYPE;
   }

   @Override
   public String getName() {
      return "Velocity";
   }

   @Override
   public String getDescription() {
      return VelocityTemplateMessages.INSTANCE.templateTypeDescription();
   }

   @Override
   public ImageResource getIconLarge() {
      return BaseIcon.fromFileExtension("txt").toImageResource(1);
   }

   @Override
   public ImageResource getIconSmall() {
      return BaseIcon.fromFileExtension("txt").toImageResource();
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
            return "plain/text";
         }

         @Override
         public String getDefaultFileExtension() {
            return "txt";
         }
      };
   }
}
