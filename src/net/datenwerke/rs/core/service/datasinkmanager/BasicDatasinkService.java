package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.service.usermanager.entities.User;

public interface BasicDatasinkService {

   String getDatasinkPropertyName();

   StorageType getStorageType();

   StorageType getSchedulingStorageType();

   void doExportIntoDatasink(Object data, User user, DatasinkDefinition datasinkDefinition,
         DatasinkConfiguration config) throws DatasinkExportException;

   Optional<? extends DatasinkDefinition> getDefaultDatasink();
}
