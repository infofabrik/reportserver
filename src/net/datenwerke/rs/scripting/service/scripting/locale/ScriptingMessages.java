package net.datenwerke.rs.scripting.service.scripting.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface ScriptingMessages extends Messages{

	public final static ScriptingMessages INSTANCE = LocalizationServiceImpl.getMessages(ScriptingMessages.class);
	
	String commandPs_description();
	String commandKill_description();
	String commandKill_description_forceFlag();
	String commandKill_description_id();
	
	String commandExec_description();
	String commandExec_cFlag();
	String commandExec_sFlag();
	String commandExec_nFlag();
	String commandExec_tFlag();	
	String commandExec_eFlag();
	String commandExec_execArgument();
	
	String commandScheduleScript_description();
	
	String commandScheduleScript_sub_list_description();
	
	String commandScheduleScript_sub_execute_description();
	String commandScheduleScript_sub_setProperty_arg1();
	String commandScheduleScript_sub_setProperty_arg2();
	
}

