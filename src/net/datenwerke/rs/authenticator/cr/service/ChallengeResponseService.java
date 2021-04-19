package net.datenwerke.rs.authenticator.cr.service;

import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;




public interface ChallengeResponseService {
	
	public ChallengeResponseContainer requestChallenge();
	public boolean validateResponse(ChallengeResponseContainer container, String expectedSecret);
	
}
