package net.datenwerke.rs.core.client.urlview.module;

import net.datenwerke.gf.client.homepage.modules.ClientMainModuleImpl;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewSpecialViewHandler;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class UrlViewClientMainModule extends ClientMainModuleImpl {

	private final HookHandlerService hookHandler;
	
	private String name;
	private String url;

	public UrlViewClientMainModule(HookHandlerService hookHandler, String name, String url){
		this.hookHandler = hookHandler;
		this.name = name;
		this.url = url;
	}
	
	@Override
	public String getModuleName() {
		return name;
	}

	@Override
	public ImageResource getIcon() {
		return null;
	}

	@Override
	public Widget getMainWidget() {
		DwContentPanel panel = new DwContentPanel();
		panel.setHeaderVisible(false);
		
		boolean consumed = false;
		for(UrlViewSpecialViewHandler handler : hookHandler.getHookers(UrlViewSpecialViewHandler.class)){
			
			if(handler.consumes(url)){
				Widget widget = handler.getWidget(url);
				
				SimpleContainer container = new SimpleContainer();
				container.add(widget);
				panel.add(container);
				
				consumed = true;
				break;
			}
		}
		
		if(! consumed)
			panel.setUrl(url);
		
		return panel;
	}

	@Override
	public boolean isRecyclable() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isRecyclable() ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlViewClientMainModule other = (UrlViewClientMainModule) obj;
		if (!isRecyclable())
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	
	
	
}
