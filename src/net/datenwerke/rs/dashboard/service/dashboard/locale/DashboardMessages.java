package net.datenwerke.rs.dashboard.service.dashboard.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface DashboardMessages extends Messages{

	public final static DashboardMessages INSTANCE = LocalizationServiceImpl.getMessages(DashboardMessages.class);

	String folderTypeName();
	
	String removedDashboard(String string);
}

