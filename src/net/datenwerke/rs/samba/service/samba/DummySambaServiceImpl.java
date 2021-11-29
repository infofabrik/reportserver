package net.datenwerke.rs.samba.service.samba;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummySambaServiceImpl implements SambaService {

   @Override
   public void exportIntoDatasink(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws DatasinkExportException {

   }

   @Override
   public void testDatasink(SambaDatasink sambaDatasink) throws DatasinkExportException {

   }

   @Override
   public Optional<SambaDatasink> getDefaultDatasink() {
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