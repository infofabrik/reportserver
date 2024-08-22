package net.datenwerke.rs.onedrive.client.onedrive;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface OneDriveUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
