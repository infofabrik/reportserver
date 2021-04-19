package net.datenwerke.rs.incubator.service.versioning.locale;

import net.datenwerke.rs.utils.localization.Messages;

public interface VersioningMessages extends Messages{
	
	String commandRev_description();
	
	String commandRev_sub_list_description();
	String commandRev_sub_list_arg1();
	String commandRev_sub_list_flagN();
	String commandRev_sub_list_flagA();
	
	String commandRev_sub_restore_description();
	String commandRev_sub_restore_arg1();
	String commandRev_sub_restore_arg2();
	String commandRev_sub_restore_arg3();

}
