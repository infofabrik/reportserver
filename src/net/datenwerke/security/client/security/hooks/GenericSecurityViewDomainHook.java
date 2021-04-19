package net.datenwerke.security.client.security.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.client.security.GenericTargetIdentifier;

import com.google.gwt.resources.client.ImageResource;

/**
 * Defines a hook for attaching generic security targets to the generic security target view.
 * 
 *
 */
public interface GenericSecurityViewDomainHook extends Hook {

	/**
	 * The target's name, which is used as link in the navigation panel.
	 */
	public String genericSecurityViewDomainHook_getName();
	
	/**
	 * A longer description which describes the target. 
	 */
	public String genericSecurityViewDomainHook_getDescription();
	
	/**
	 * The icon used in the navigation panel
	 */
	public ImageResource genericSecurityViewDomainHook_getIcon();
	
	/**
	 * A mapping object which is used to identify the marker class on the server side.
	 * 
	 * <p>This is used to be typesafe and not to have strings.</p>
	 * 
	 */
	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId();
}
