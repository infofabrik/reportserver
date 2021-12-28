package net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface TableTemplateClientProviderHook extends Hook {

	public interface ContentTypeConfig{
		public boolean isDisplay();
		public String getDefaultContentType();
		public String getDefaultFileExtension();
	}
	
	public String getType();
	
	public String getName();
	
	public String getDescription();
	
	public ImageResource getIconLarge();
	
	public ImageResource getIconSmall();

	public ContentTypeConfig getContentTypeConfig();
}
