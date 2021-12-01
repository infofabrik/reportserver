package net.datenwerke.usermanager.ext.client.hooks;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.ui.Widget;

public interface UserProfileViewContentHook extends Hook {

	public void submitPressed(SubmitTrackerToken token);

	public Widget getComponent(UserDto user);
}
