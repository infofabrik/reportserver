package net.datenwerke.rs.box.client.box;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface BoxUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
