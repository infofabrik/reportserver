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
   
   String commandLdaptest_sub_organizationalUnits_description();
   
   String commandLdaptest_sub_organizationalUnits_flagS();
   
   String commandLdapschema_description();
   
   String commandLdapschema_sub_objectclassinfo_description();
   
   String commandLdapschema_sub_objectclassinfo_description_arg_objectclass();
   
   String commandLdapschema_sub_objectclasslist_description();
   
   String commandLdapinfo_description();
   
   String commandLdapfilter_description();
   
   String commandLdapfilter_sub_flagI();
   
   String commandLdapfilter_sub_flagN();
   
   String commandLdapguid_description();
   
   String commandLdapschema_sub_attributelist_description();
   
   String commandLdapschema_sub_attributeinfo_description();
   
   String commandLdapschema_sub_attributeinfo_description_arg_attribute();
   
   String commandLdapschema_sub_matchingrulelist_description();
   
   String commandLdapschema_sub_matchingruleinfo_description();
   
   String commandLdapschema_sub_matchingruleinfo_description_arg_matching_rule();
   
   String commandLdapschema_sub_syntaxrulelist_description();
   
   String commandLdapschema_sub_syntaxruleinfo_description();
   
   String commandLdapschema_sub_syntaxruleinfo_description_arg_oid();
   
   String commandLdapschema_sub_entry_description();
   
   String commandLdaptest_sub_filter_sub_flagA();
   
   String commandLdaptest_sub_users_sub_flagA();
   
   String commandLdaptest_sub_groups_sub_flagA();
   
   String commandLdaptest_sub_organizationalUnits_sub_flagA();
   
   String commandLdaptest_sub_guid_sub_flagA();
   
   String commandLdaptest_sub_orphans_sub_flagA();
   
   String commandLdaptest_sub_orphans_description();
}
