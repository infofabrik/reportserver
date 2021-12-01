package net.datenwerke.rs.tabletemplate.client.engines.xdoc.hookers;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.tabletemplate.client.engines.xdoc.XdocTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.locale.XdocTemplateMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class XdocTemplateClientProvider implements
		TableTemplateClientProviderHook {

	@Override
	public String getType() {
		return XdocTemplateUIModule.TEMPLATE_TYPE;
	}

	@Override
	public String getName() {
		return "XDOC";
	}

	@Override
	public String getDescription() {
		return XdocTemplateMessages.INSTANCE.templateTypeDescription();
	}

	@Override
	public ImageResource getIconLarge() {
		return BaseIcon.fromFileExtension("doc").toImageResource(1);
	}

	@Override
	public ImageResource getIconSmall() {
		return BaseIcon.fromFileExtension("doc").toImageResource();
	}
	
	@Override
	public ContentTypeConfig getContentTypeConfig() {
		return new ContentTypeConfig(){
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
