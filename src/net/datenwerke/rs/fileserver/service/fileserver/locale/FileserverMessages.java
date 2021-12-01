package net.datenwerke.rs.fileserver.service.fileserver.locale;

import net.datenwerke.rs.utils.localization.Messages;

public interface FileserverMessages extends Messages {

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
}
