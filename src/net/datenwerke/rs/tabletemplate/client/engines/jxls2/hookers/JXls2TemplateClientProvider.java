package net.datenwerke.rs.tabletemplate.client.engines.jxls2.hookers;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.tabletemplate.client.engines.jxls2.JXls2TemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.jxls2.locale.JXls2TemplateMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class JXls2TemplateClientProvider implements TableTemplateClientProviderHook {

    @Override
    public String getType() {
        return JXls2TemplateUIModule.TEMPLATE_TYPE;
    }

    @Override
    public String getName() {
        return "JXLS";
    }

    @Override
    public String getDescription() {
        return JXls2TemplateMessages.INSTANCE.templateTypeDescription();
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
