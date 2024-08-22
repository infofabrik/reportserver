package net.datenwerke.rs.ftp.client.ftp;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface FtpUiService {

   Map<StorageType, Boolean> getStorageEnabledConfigs();
}
