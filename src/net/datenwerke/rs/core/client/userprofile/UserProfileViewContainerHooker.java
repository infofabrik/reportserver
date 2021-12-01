package net.datenwerke.rs.core.client.userprofile;

import java.util.Iterator;
import java.util.List;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitCompleteCallback;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerService;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHookImpl;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class UserProfileViewContainerHooker extends UserProfileCardProviderHookImpl {
	
	@Inject
	private LoginService loginService;
	
	@Inject
	private HookHandlerService hookHandler;
	
	@Inject
	private SubmitTrackerService submitTracker;

	private List<UserProfileViewContentHook> viewHooker;
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.EYE.toImageResource(1);
	}

	@Override
	public String getName() {
		return UsermanagerMessages.INSTANCE.userProfileViewContainerName();
	}

	@Override
	public Widget getCard() {
		UserDto user = loginService.getCurrentUser();
		
		DwFlowContainer container = new DwFlowContainer();
		
		viewHooker = hookHandler.getHookers(UserProfileViewContentHook.class);
		for(UserProfileViewContentHook hooker : viewHooker){
			Widget comp = hooker.getComponent(user);
			if(null != comp)
				container.add(comp);
		}
		
		return container;
	}
	
	protected void submitConsecutively(UserProfileViewContentHook next, final Iterator<UserProfileViewContentHook> hookerIterator, final SubmitTrackerToken outerToken) {
		SubmitTrackerToken token = submitTracker.createToken();
		token.setSubmitCompleteCallback(new SubmitCompleteCallback() {
			@Override
			public void onSuccess() {
				if(hookerIterator.hasNext())
					submitConsecutively(hookerIterator.next(), hookerIterator, outerToken);
				else
					outerToken.setCompleted();
			}
			
			@Override
			public void onFailure(Throwable t) {
				outerToken.failure(t);
			}
		});
		
		next.submitPressed(token);
	}
	

	@Override
	public void submitPressed(final SubmitTrackerToken token) {
		Iterator<UserProfileViewContentHook> iterator = viewHooker.iterator();
		submitConsecutively(iterator.next(), iterator, token);
	}



}
