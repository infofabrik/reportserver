package net.datenwerke.rs.amazons3.client.amazons3;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface AmazonS3UiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
