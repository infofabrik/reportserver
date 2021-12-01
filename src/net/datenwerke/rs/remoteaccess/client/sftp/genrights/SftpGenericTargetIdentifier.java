package net.datenwerke.rs.remoteaccess.client.sftp.genrights;

import net.datenwerke.rs.remoteaccess.service.sftp.genrights.SftpSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

@GenericTargetIdentifierMapper(SftpSecurityTarget.class)
public class SftpGenericTargetIdentifier implements GenericTargetIdentifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7140134460185977474L;

}
