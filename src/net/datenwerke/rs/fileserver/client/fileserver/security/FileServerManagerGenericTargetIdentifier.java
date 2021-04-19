/**
 * 
 */
package net.datenwerke.rs.fileserver.client.fileserver.security;

import net.datenwerke.rs.fileserver.service.fileserver.genrights.FileServerManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(FileServerManagerAdminViewSecurityTarget.class) 
public class FileServerManagerGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2257028052488924969L;



	
}