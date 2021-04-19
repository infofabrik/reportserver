package net.datenwerke.rs.teamspace.client.teamspace;

import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.Component;

/**
 * 
 *
 */
public interface TeamSpaceApp {

	public String getAppId();
	public String getName();
	public String getDescription();
	public ImageResource getIcon();
	public Component getAppComponent();
	public void displaySpace(TeamSpaceDto currentSpace);
	
}
