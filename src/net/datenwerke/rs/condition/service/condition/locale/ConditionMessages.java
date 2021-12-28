package net.datenwerke.rs.condition.service.condition.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ConditionMessages extends Messages {

   public final static ConditionMessages INSTANCE = LocalizationServiceImpl.getMessages(ConditionMessages.class);

   String commandRcondition_description();

   String commandRcondition_sub_list_desc();

   String commandRcondition_sub_create_desc();

   String commandRcondition_sub_create_par_reportId();

   String commandRcondition_sub_create_par_key();

   String commandRcondition_sub_create_par_name();

   String commandRcondition_sub_create_par_desc();

   String commandRcondition_sub_remove_par_condition();

   String commandRcondition_sub_remove_desc();

   String skipMsg(String name);

   String retryMsg(String name);

   String notIsEmptyCondition();

   String notIsEmptyConditionDesc();
}
