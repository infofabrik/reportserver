package net.datenwerke.rs.emaildatasink.client.emaildatasink;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface EmailDatasinkUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
