package net.datenwerke.gxtdto.client.objectinformation.hooks;

import java.util.Date;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import com.google.gwt.resources.client.ImageResource;

public interface ObjectInfoKeyInfoProvider extends Hook {

	public boolean consumes(Object object);
	
	String getName(Object object);
	String getDescription(Object object);
	String getType(Object object);
	Date getCreatedOn(Object object);
	Date getLastUpdatedOn(Object object);

	public ImageResource getIconSmall(Object object);
	
	
	
}
