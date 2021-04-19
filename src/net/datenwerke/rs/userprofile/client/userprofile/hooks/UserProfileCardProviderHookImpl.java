package net.datenwerke.rs.userprofile.client.userprofile.hooks;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;

public abstract class UserProfileCardProviderHookImpl implements UserProfileCardProviderHook {

	public int getHeight(){
		return 400;
	}
	
	public void cancelPressed(){
	}
	
	public final void submitPressed(){
	}

	public void submitPressed(SubmitTrackerToken token){
		token.setCompleted();
	}
	
	@Override
	public String isValid() {
		return null;
	}

}
