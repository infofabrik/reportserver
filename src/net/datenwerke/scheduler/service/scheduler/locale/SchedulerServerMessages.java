package net.datenwerke.scheduler.service.scheduler.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SchedulerServerMessages extends Messages {

   public final static SchedulerServerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(SchedulerServerMessages.class);

   public String triggerConfigException();

}
