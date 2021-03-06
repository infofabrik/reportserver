package net.datenwerke.rs.incubator.service.scriptdatasource.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ScriptDatasourceMessages extends Messages {

   public final static ScriptDatasourceMessages INSTANCE = LocalizationServiceImpl
         .getMessages(ScriptDatasourceMessages.class);

   String scriptDatasourceTypeName();

}
