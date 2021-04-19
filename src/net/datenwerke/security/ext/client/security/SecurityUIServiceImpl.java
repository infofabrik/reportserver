package net.datenwerke.security.ext.client.security;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.RightDto;

import com.google.inject.Inject;

public class SecurityUIServiceImpl implements SecurityUIService {

	private final LoginService loginService;
	
	private GenericSecurityTargetContainer genericSecurityContainer;
	
	@Inject
	public SecurityUIServiceImpl(
		LoginService loginService	
		){
		
		/* store objects */
		this.loginService = loginService;
	}
	
	@Override
	public void setGenericSecurityContainer(GenericSecurityTargetContainer genericSecurityContainer){
		this.genericSecurityContainer = genericSecurityContainer;
	}

	@Override
	public boolean hasRight(Class<? extends GenericTargetIdentifier> identifier, Class<? extends RightDto> toCheck) {
		if(null == toCheck)
			return false;
		for(RightDto right : genericSecurityContainer.getRights(identifier))
			if(right.getClass().equals(toCheck))
				return true;
		return false;
	}

	@Override
	public boolean isSuperUser() {
		return loginService.getCurrentUser().isSuperUser();
	}


}
