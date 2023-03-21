package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks;

import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface GeneralInfoCategoryProviderHook extends Hook {

   Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory();
}
