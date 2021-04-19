package net.datenwerke.security.service.authenticator;



public class CurrentUser {
	
	private Long currentUserId;
	private Long sudoParent;

	public Long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public Long getSudoParent() {
		return sudoParent;
	}

	public void setSudoParent(Long sudoParent) {
		this.sudoParent = sudoParent;
	}
	
	
	
}
