package net.datenwerke.rs.ftp.service.ftp.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface FtpMessages extends Messages{

	public final static FtpMessages INSTANCE = LocalizationServiceImpl.getMessages(FtpMessages.class);
	
	String ftpDatasinkTypeName();
	String sftpDatasinkTypeName();
	String ftpsDatasinkTypeName();
	
}

