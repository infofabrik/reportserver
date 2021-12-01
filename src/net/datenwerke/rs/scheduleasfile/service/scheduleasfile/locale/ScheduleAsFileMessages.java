package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ScheduleAsFileMessages extends Messages {

   public final static ScheduleAsFileMessages INSTANCE = LocalizationServiceImpl
         .getMessages(ScheduleAsFileMessages.class);

   String reportTypeName();

}
