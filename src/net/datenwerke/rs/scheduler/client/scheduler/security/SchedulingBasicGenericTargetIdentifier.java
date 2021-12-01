/**
 * 
 */
package net.datenwerke.rs.scheduler.client.scheduler.security;

import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingBasicSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(SchedulingBasicSecurityTarget.class) 
public class SchedulingBasicGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5630802826139164129L;


	
}