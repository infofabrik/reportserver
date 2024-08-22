package net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public class EmailDatasink2DtoPostProcessor implements Poso2DtoPostProcessor<EmailDatasink, EmailDatasinkDto> {

   @Override
   public void dtoCreated(EmailDatasink poso, EmailDatasinkDto dto) {
      if (null != poso.getPassword() && !"".equals(poso.getPassword().trim()))
         dto.setHasPassword(true);
   }

   @Override
   public void dtoInstantiated(EmailDatasink poso, EmailDatasinkDto dto) {
      // TODO Auto-generated method stub
   }

}
