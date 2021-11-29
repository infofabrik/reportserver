package net.datenwerke.rs.onedrive.service.onedrive;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyOneDriveServiceImpl implements OneDriveService {

   @Override
   public void exportIntoDatasink(Object report, OneDriveDatasink oneDriveDatasink, DatasinkConfiguration config)
         throws DatasinkExportException {
   }

   @Override
   public void testDatasink(OneDriveDatasink OneDriveDatasink) throws DatasinkExportException {
   }

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
      return null;
   }

   @Override
   public String getDatasinkPropertyName() {
      return null;
   }

   @Override
   public StorageType getStorageType() {
      return null;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return null;
   }

}