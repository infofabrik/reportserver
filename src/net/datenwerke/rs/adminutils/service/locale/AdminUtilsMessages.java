package net.datenwerke.rs.adminutils.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface AdminUtilsMessages extends Messages {

   public final static AdminUtilsMessages INSTANCE = LocalizationServiceImpl.getMessages(AdminUtilsMessages.class);

   String commandListlogfiles_description();

   String commandListlogfiles_sub_flagS();

   String commandListlogfiles_sub_flagF();

   String commandListlogfiles_sub_flagE();
   
   String commandListlogfiles_sub_flagD();

   String commandViewLogFile_description();

   String commandViewLogFile_logFile();

   String emailLogFilesSubject();

   String emailLogFilesSalutation();

   String emailLogFilesIntro();

   String emailLogFilesEnd();
}
