package net.datenwerke.rs.tabledatasink.client.tabledatasink;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface TableDatasinkUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();

}
