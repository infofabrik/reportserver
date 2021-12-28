package net.datenwerke.rs.scheduler.service.scheduler.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SchedulerMessages extends Messages {

   public final static SchedulerMessages INSTANCE = LocalizationServiceImpl.getMessages(SchedulerMessages.class);

   String commandScheduler_description();

   String commandScheduler_sub_list_description();

   String commandScheduler_sub_unschedule_description();

   String commandScheduler_sub_unschedule_arg1();

   String commandScheduler_sub_remove_description();

   String commandScheduler_sub_remove_arg1();

   String commandScheduler_sub_listFireTimes_description();

   String commandScheduler_sub_listFireTimes_arg1();

   String errorNoRecipients();

   String errorNoSubject();

   String errorNoEmail();

   String errorNotValidDate();

   String errorExecutorNotOwner();

   String errorOwnersMissingRights();

   String jobId();

   String reportId();

   String lastexec();

}
