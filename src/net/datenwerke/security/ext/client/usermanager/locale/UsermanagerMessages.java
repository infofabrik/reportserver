package net.datenwerke.security.ext.client.usermanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UsermanagerMessages extends Messages {

   public final static UsermanagerMessages INSTANCE = GWT.create(UsermanagerMessages.class);

   String assignedRoles();

   String assignedUsers();

   String memberOus();

   String changePassword();

   String changePasswordDescription();

   String editOU();

   String editRole();

   String editUser();

   String email();

   String exportGroup();

   String firstname();

   String gender();

   String genderFemale();

   String genderMale();

   String importConfigFailureNoParent();

   String importerName();

   String importMainPropertiesDescription();

   String importMainPropertiesHeadline();

   String importWhereTo();

   String lastname();

   String ou();

   String password();

   String passwordChangeSuccess();

   String personDetails();

   String quickExportText();

   String role();

   String security();

   String user();

   String usermanagement();

   String usermanagementDescription();

   String userManagernavtext();

   String username();

   String userProfileUserDataName();

   String userProfileViewContainerName();

   String noRecipientSelected();

   String noUserSelectedTitle();

   String noUserSelectedMsg();

   String userNotInGroupMessages();

   String organisationalUnit();

   String userDetails();

   String groups();

}
