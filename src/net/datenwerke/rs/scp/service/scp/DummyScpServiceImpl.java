package net.datenwerke.rs.scp.service.scp;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class DummyScpServiceImpl implements ScpService {

   @Override
   public void exportIntoDatasink(Object report, ScpDatasink scpDatasink, String filename, String folder)
         throws DatasinkExportException {

   }

   @Override
   public void testScpDatasink(ScpDatasink scpDatasink) throws DatasinkExportException {

   }

   @Override
   public Optional<ScpDatasink> getDefaultDatasink() {
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