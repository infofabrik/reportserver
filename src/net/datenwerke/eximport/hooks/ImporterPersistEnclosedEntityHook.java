package net.datenwerke.eximport.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;


/**
 * 
 *
 */
public interface ImporterPersistEnclosedEntityHook extends Hook {

	public boolean consumes(Object object);
	
	public void persist(Object object);
}
