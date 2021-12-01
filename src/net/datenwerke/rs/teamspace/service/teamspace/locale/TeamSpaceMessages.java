package net.datenwerke.rs.teamspace.service.teamspace.locale;

import net.datenwerke.rs.utils.localization.Messages;


public interface TeamSpaceMessages extends Messages{

	String teamSpaceSecureeName();

	String rightTeamSpaceAdminAbbreviation();

	String rightTeamSpaceAdminDescription();

	String commandTeamspacemod_description();
	
	String commandTeamspacemod_sub_memberadd_description();
	String commandTeamspacemod_sub_memberadd_cflag();
	String commandTeamspacemod_sub_memberadd_arg_teamspaceid();
	String commandTeamspacemod_sub_memberadd_arg_members();
	
	String commandTeamspacemod_sub_setrole_description();
	String commandTeamspacemod_sub_setrole_arg1();
	String commandTeamspacemod_sub_setrole_arg2();
	String commandTeamspacemod_sub_setrole_arg3();
}

