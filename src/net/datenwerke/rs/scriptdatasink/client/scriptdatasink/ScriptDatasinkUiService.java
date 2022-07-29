package net.datenwerke.rs.scriptdatasink.client.scriptdatasink;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface ScriptDatasinkUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
