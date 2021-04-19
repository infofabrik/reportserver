package net.datenwerke.rs.authenticator.cr.service.pam;

import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseService;
import net.datenwerke.security.service.usermanager.UserManagerService;

import com.google.inject.Inject;

public class ChallengeResponseServerPAM extends ChallengeResponsePAM {

	@Inject
	public ChallengeResponseServerPAM(UserManagerService userManagerService,
			ChallengeResponseService challengeResponseService) {
		super(userManagerService, challengeResponseService);
	}

	
	@Override
	public String getClientModuleName() {
		return null;
	}
}
