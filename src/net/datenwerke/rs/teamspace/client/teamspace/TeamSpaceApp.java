package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

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
