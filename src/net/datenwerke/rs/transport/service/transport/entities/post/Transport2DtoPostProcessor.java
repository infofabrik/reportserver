package net.datenwerke.rs.transport.service.transport.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.service.transport.entities.Transport;

public class Transport2DtoPostProcessor implements Poso2DtoPostProcessor<Transport, TransportDto> {

   @Override
   public void dtoCreated(Transport transport, TransportDto transportDto) {
      transportDto.setShortKey(transport.getShortKey());
      transportDto.setCreatedOnStr(transport.getCreatedOnStr());
   }

   @Override
   public void dtoInstantiated(Transport arg0, TransportDto arg1) {

   }

}