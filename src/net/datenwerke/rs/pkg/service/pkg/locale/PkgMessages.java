package net.datenwerke.rs.pkg.service.pkg.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface PkgMessages extends Messages {

   public final static PkgMessages INSTANCE = LocalizationServiceImpl.getMessages(PkgMessages.class);

   String pkg_description();

   String pkg_sub_list_description();

   String pkg_sub_install_description();

   String commandPkg_sub_install_flagD();

   String commandPkg_sub_install_packageName();
}
