package net.datenwerke.rs.googledrive.client.googledrive;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface GoogleDriveUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
