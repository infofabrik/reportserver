package net.datenwerke.rs.core.service.datasinkmanager;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface BasicDatasinkService {

   String getDatasinkPropertyName();

   StorageType getStorageType();

   StorageType getSchedulingStorageType();
}
