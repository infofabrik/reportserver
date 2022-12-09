package net.datenwerke.rs.ldap.service.ldap.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface LdapMessages extends Messages {

   public final static LdapMessages INSTANCE = LocalizationServiceImpl.getMessages(LdapMessages.class);

   String commandLdapimport_description();
   
   String commandLdaptest_description();
   
   String commandLdaptest_sub_filter_description();
   
   String commandLdaptest_sub_users_description();
   
   String commandLdaptest_sub_users_flagS();
   
   String commandLdaptest_sub_groups_description();
   
   String commandLdaptest_sub_groups_flagS();
   
   String commandLdaptest_sub_guid_description();
   
   String commandLdaptest_sub_organisationalUnits_description();
   
   String commandLdaptest_sub_organisationalUnits_flagS();
}
