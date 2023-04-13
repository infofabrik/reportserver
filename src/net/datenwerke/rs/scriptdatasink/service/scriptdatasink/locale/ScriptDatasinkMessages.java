package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ScriptDatasinkMessages extends Messages {

   public final static ScriptDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(ScriptDatasinkMessages.class);

   String scriptDatasinkTypeName();

   String scriptDatasinks();

}