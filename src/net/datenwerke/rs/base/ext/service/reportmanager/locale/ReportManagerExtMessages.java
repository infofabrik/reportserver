package net.datenwerke.rs.base.ext.service.reportmanager.locale;

import net.datenwerke.rs.utils.localization.Messages;


public interface ReportManagerExtMessages extends Messages{
	String commandReportmod_description();
	
	String commandReportmod_sub_setUUID_description();
	String commandReportmod_sub_setUUID_arg1();
	
	String commandReportmod_sub_setProperty_description();
	String commandReportmod_sub_setProperty_arg1();
	String commandReportmod_sub_setProperty_arg2();
	String commandReportmod_sub_setProperty_arg3();
	
	String commandReportmod_sub_removeProperty_description();
	String commandReportmod_sub_removeProperty_arg1();
	String commandReportmod_sub_removeProperty_arg2();
	
	String commandReportmod_sub_listProperty_description();
	String commandReportmod_sub_listProperty_arg1();
	String commandReportmod_sub_listProperty_arg2();
}

