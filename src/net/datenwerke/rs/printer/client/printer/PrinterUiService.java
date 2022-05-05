package net.datenwerke.rs.printer.client.printer;

import java.util.Map;

import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface PrinterUiService {
   
   Map<StorageType, Boolean> getStorageEnabledConfigs();

}
