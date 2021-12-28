package net.datenwerke.rs.core.client.userprofile;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public interface UserProfileViewContentHook extends Hook {

   public void submitPressed(SubmitTrackerToken token);

   public Widget getComponent(UserDto user);
}
