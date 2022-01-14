package net.datenwerke.usermanager.ext.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface UserManagerMessages extends Messages {

   public final static UserManagerMessages INSTANCE = LocalizationServiceImpl.getMessages(UserManagerMessages.class);

   String historyUrlBuilderName();

   String gender();

   String genderFemale();

   String genderMale();

   String commandId_description();

   String commandId_arg_username();

   String title();

   String id();

   String username();

   String firstName();

   String lastName();

   String email();

   String accountInhibitionLabel();

   String accountExpirationDate();

}
