/**
 * 
 */
package net.datenwerke.rs.eximport.client.eximport.security;

import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(ExportSecurityTarget.class) 
public class ExportGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -39543517049960233L;


	
}