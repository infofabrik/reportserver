package net.datenwerke.rs.terminal.service.terminal.vfs.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface VfsMessages extends Messages{

	public final static VfsMessages INSTANCE = LocalizationServiceImpl.getMessages(VfsMessages.class);
	
	String commandLs_description();
	String commandLs_lArgument();
	String commandLs_dirArgument();
	
	String commandPwd_description();
	String commandPwd_argument();
	
	String commandListpath_description();
	String commandListpath_iflag();
	String commandListpath_objectarg();

	String commandMkdir_description();
	String commandMkdir_argument();
	
	String commandCp_description();
	String commandCp_source();
	String commandCp_target();
	
	String commandMv_description();
	String commandMv_source();
	String commandMv_target();
	
	String commandRm_description();
	String commandRm_rArgument();
	String commandRm_fArgument();
	String commandRm_dirArgument();
	
	String fileNotFound();
	String notSupported();
}

