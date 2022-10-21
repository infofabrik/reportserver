package net.datenwerke.rs.amazons3.service.amazons3;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;

@ImplementedBy(DummyAmazonS3ServiceImpl.class)
public interface AmazonS3Service extends BasicDatasinkService {

}
