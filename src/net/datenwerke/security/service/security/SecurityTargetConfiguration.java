package net.datenwerke.security.service.security;

import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;

/**
 * Configures a security target.
 * 
 * <p>
 * A security target is either a class or a specific entity object. The 
 * {@link SecurityTargetConfiguration} is used to define what securees
 * are interested in the target and what rights are defined.
 * </p>
 * 
 * <p>
 * Two types of security targets exist. A Generic Target is anything that
 * is not an entity and hence cannot store their own ACL. A special
 * entity object ({@link GenericSecurityTargetEntity}) is created that stores
 * the ACL for the generic target.
 * </p>
 * 
 *
 */
public class SecurityTargetConfiguration {

	/**
	 * Used for targets that do not maintain their own ACL
	 */
	private final Class<?> genericTarget;
	
	/**
	 * Used for targets that maintain their own ACL
	 */
	private final Class<? extends SecurityTarget> target;
	
	/**
	 * Stores the one responsible for this configuration
	 */
	private final Securee securee;
	
	/**
	 * Creates a new configuration for a target. 
	 * 
	 * <p>The submitted class implements SecurityTarget,
	 * then the object maintains it ACL directly. Otherwise
	 * SecurityService takes over.
	 * </p> 
	 * @param target
	 */
	public SecurityTargetConfiguration(Securee securee, Class<? extends SecurityTarget> target, Class<?> genericTarget){
		this.securee = securee;
		this.target = target;
		this.genericTarget = genericTarget;
	}
	
	public boolean isGenericTarget(){
		return genericTarget != null;
	}

	public Class<?> getGenericTarget() {
		return genericTarget;
	}

	public Class<? extends SecurityTarget> getTarget() {
		return target;
	}

	public Securee getSecuree() {
		return securee;
	}
}
