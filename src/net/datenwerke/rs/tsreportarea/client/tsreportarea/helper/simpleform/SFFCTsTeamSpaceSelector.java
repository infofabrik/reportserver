package net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public interface SFFCTsTeamSpaceSelector extends SimpleFormFieldConfiguration {
	
	public TeamSpaceDto getTeamSpace();

}
