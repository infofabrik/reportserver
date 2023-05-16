package net.datenwerke.rs.fileserver.service.fileserver.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface FileserverMessages extends Messages {

   public final static FileserverMessages INSTANCE = LocalizationServiceImpl
         .getMessages(FileserverMessages.class);
   
   String fileTypeName();

   String folderTypeName();

   String commandZip_description();

   String commandZip_outputfile();

   String commandZip_inputlist();

   String commandUnzip_description();

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();

   String commandDirMod_description();

   String commandWebAccess_description();

   String commandDirMod_description_arg_dirpath();

   String commandDirMod_description_arg_webaccess();
   
   String commandEditTextFile_description();
   
   String commandEditTextFile_file();
   
   String commandCreateTextFile_description();
   
   String commandCreateTextFile_file();
   
   String usageStatistics();
   
   String noFileEnding();
   
   String emptyName();

   String totalFiles();
   
   
}
