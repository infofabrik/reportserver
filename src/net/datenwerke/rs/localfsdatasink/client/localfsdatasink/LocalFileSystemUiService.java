package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface LocalFileSystemUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
