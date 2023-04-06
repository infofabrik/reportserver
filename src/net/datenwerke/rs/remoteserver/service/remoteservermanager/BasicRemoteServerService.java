package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface BasicRemoteServerService {

   String getRemoteServerPropertyName();

   StorageType getStorageType();

   StorageType getSchedulingStorageType();
}
