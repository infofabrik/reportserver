package net.datenwerke.rs.keyutils.service.keyutils.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface GeneralGenerateDefaultKeyHook extends Hook {
   
   String generateDefaultKey();
}
