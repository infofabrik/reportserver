package net.datenwerke.rs.dropbox.client.dropbox;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface DropboxUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
