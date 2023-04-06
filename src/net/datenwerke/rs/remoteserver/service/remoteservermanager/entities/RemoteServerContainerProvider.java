package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.remoteserver.client.remoteservermanager.dto")
public interface RemoteServerContainerProvider {

   @ExposeMethodToClient
   @EnclosedEntity
   public RemoteServerContainer getRemoteServerContainer();

   @ExposeMethodToClient
   @EnclosedEntity
   public void setRemoteServerContainer(RemoteServerContainer remoteserverContainer);

}
