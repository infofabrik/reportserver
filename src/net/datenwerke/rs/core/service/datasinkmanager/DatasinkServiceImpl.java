package net.datenwerke.rs.core.service.datasinkmanager;

public class DatasinkServiceImpl implements DatasinkService {
   
   @Override
   public String getDefaultFolder(FolderedDatasink datasink) {
      return datasink.getFolder();
   }
   
}
