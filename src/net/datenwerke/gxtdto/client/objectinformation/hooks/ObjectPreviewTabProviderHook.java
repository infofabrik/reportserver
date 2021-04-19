package net.datenwerke.gxtdto.client.objectinformation.hooks;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import com.sencha.gxt.widget.core.client.Component;

public interface ObjectPreviewTabProviderHook extends Hook {

	public boolean consumes(Object object);
	
	public List<PreviewComponent> getInfoComponents(Object object);

	interface PreviewComponent{
		public Component getInfoComponent(Object object);
		
		public String getInfoName();
	}
	
}
