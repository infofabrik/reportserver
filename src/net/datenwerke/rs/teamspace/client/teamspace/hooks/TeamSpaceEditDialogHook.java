package net.datenwerke.rs.teamspace.client.teamspace.hooks;

import net.datenwerke.gxtdto.client.dialog.properties.RpcPropertiesDialogCard;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public interface TeamSpaceEditDialogHook extends Hook, RpcPropertiesDialogCard {

	public boolean applies(TeamSpaceDto teamSpace);
	public void setCurrentSpace(TeamSpaceDto teamSpace);
	
}
