package net.datenwerke.security.service.authenticator;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

public class RequestUserCacheImpl implements RequestUserCache {

	private Map<Long, User> userMap = new HashMap<Long, User>();
	
	private final UserManagerService userManagerService;
	
	@Inject
	public RequestUserCacheImpl(
		UserManagerService userManagerService
		){
		
		this.userManagerService = userManagerService;
	}
	
	@Override
	public User getUser(Long id) {
		if(! userMap.containsKey(id)){
			AbstractUserManagerNode userNode = userManagerService.getNodeById(id);
			if(!(userNode instanceof User))
				throw new AuthenticatorRuntimeException("Something went terribly wrong. UserId (" + id + ") does not point at user."); //$NON-NLS-1$ //$NON-NLS-2$

			userMap.put(id, (User) userNode);
			return (User) userNode;	
		}
		return userMap.get(id);
	}

}
