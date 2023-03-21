package net.datenwerke.rs.remoteaccess.service.sftp.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SftpMessages extends Messages {

   public final static SftpMessages INSTANCE = LocalizationServiceImpl.getMessages(SftpMessages.class);

   public String disabled();

   public String sftpPort();

   public String sftpKey();

   public String sftpServer();

}
