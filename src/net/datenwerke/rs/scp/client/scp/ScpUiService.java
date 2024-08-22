package net.datenwerke.rs.scp.client.scp;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface ScpUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
