package net.datenwerke.rs.authenticator.cr.service;

import com.google.inject.servlet.SessionScoped;

import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;

@SessionScoped
public class SessionChallengeContainer {

	private ChallengeResponseContainer container;

	public ChallengeResponseContainer getContainer() {
		return container;
	}

	public void setContainer(ChallengeResponseContainer container) {
		this.container = container;
	}

	public void clear() {
		this.container = null;
	}
}
