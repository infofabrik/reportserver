package net.datenwerke.rs.samba.client.samba;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface SambaUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
