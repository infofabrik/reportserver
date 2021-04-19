package net.datenwerke.security.service.security.action;

import java.util.Collection;

import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.rights.Right;

public interface SecurityAction {

	public Collection<Right> getRights();
	
	public Class<? extends Securee> getSecuree();
	
	public boolean requireInheritance();
}
