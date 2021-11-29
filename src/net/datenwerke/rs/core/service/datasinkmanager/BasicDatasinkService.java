package net.datenwerke.rs.core.service.datasinkmanager;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface BasicDatasinkService {

   String getDatasinkPropertyName();

   StorageType getStorageType();

   StorageType getSchedulingStorageType();

   void doExportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition, DatasinkConfiguration config)
         throws DatasinkExportException;
}
