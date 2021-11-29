package net.datenwerke.rs.amazons3.service.amazons3;

import java.util.Optional;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyAmazonS3ServiceImpl implements AmazonS3Service {

   @Override
   public void exportIntoDatasink(Object report, AmazonS3Datasink amazonS3Datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
   }

   @Override
   public void testDatasink(AmazonS3Datasink amazonS3Datasink) throws DatasinkExportException {
   }

   @Override
   public Optional<AmazonS3Datasink> getDefaultDatasink() {
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