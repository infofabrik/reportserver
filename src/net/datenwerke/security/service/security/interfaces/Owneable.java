package net.datenwerke.security.service.security.interfaces;

import net.datenwerke.security.service.usermanager.entities.User;


public interface Owneable {

	public void setOwner(User owner);
	
	public User getOwner();
}
