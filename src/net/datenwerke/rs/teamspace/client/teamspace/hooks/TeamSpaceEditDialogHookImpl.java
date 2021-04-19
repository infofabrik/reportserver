package net.datenwerke.rs.teamspace.client.teamspace.hooks;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public abstract class TeamSpaceEditDialogHookImpl implements
		TeamSpaceEditDialogHook {

	protected TeamSpaceDto teamSpace;
	
	@Override
	public void setCurrentSpace(TeamSpaceDto teamSpace) {
		this.teamSpace = teamSpace;
	}

	@Override
	public int getHeight() {
		return 480;
	}

	@Override
	public void cancelPressed() {
	}

	@Override
	public final void submitPressed() {
	}
	
	@Override
	public void submitPressed(SubmitTrackerToken submitTrackerToken) {
		submitTrackerToken.setCompleted();
	}
	
	@Override
	public boolean applies(TeamSpaceDto teamSpace) {
		return true;
	}
	
	@Override
	public String isValid() {
		return null;
	}

}
