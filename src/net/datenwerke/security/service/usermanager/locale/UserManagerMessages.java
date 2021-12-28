package net.datenwerke.security.service.usermanager.locale;

import net.datenwerke.rs.utils.localization.Messages;

public interface UserManagerMessages extends Messages {

   String commandUsermod_description();

   String commandUsermod_sub_setProperty_description();

   String commandUsermod_sub_setProperty_arg1();

   String commandUsermod_sub_setProperty_arg2();

   String commandUsermod_sub_setProperty_arg3();

   String commandGroupmod_description();

   String commandGroupmod_sub_memberadd_description();

   String commandGroupmod_sub_memberadd_cflag();

   String commandGroupmod_sub_memberadd_arg_groupid();

   String commandGroupmod_sub_memberadd_arg_members();

   String userTypeName();

   String groupTypeName();

   String ouTypeName();
}
