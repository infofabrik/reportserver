package net.datenwerke.rs.amazons3.service.amazons3;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyAmazonS3ServiceImpl implements AmazonS3Service {

   @Override
   public void exportIntoAmazonS3(Object report, AmazonS3Datasink amazonS3Datasink, String filename, String folder)
         throws IOException {

   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isAmazonS3Enabled() {
      return false;
   }

   @Override
   public boolean isAmazonS3SchedulingEnabled() {
      return false;
   }

   @Override
   public void testAmazonS3Datasink(AmazonS3Datasink amazonS3Datasink) throws IOException {
   }

   @Override
   public Optional<AmazonS3Datasink> getDefaultDatasink() {
      return null;
   }

}