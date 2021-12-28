package net.datenwerke.security.service.usermanager.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;

public class OU2DtoPostProcessor implements Poso2DtoPostProcessor<OrganisationalUnit, OrganisationalUnitDto> {

   @Override
   public void dtoCreated(OrganisationalUnit ou, OrganisationalUnitDto ouDto) {
      ouDto.setIsUserRoot(null == ou.getParent());
   }

   @Override
   public void dtoInstantiated(OrganisationalUnit arg0, OrganisationalUnitDto arg1) {

   }

}
