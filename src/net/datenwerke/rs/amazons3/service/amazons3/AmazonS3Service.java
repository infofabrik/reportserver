package net.datenwerke.rs.amazons3.service.amazons3;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyAmazonS3ServiceImpl.class)
public interface AmazonS3Service extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<AmazonS3Datasink> getDefaultDatasink();

}
