package net.datenwerke.security.service.security.rights;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.locale.SecurityMessages;

/**
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.security.client.security.dto")
public class Read implements Right {

   private final SecurityMessages messages = LocalizationServiceImpl.getMessages(SecurityMessages.class);

   @Override
   public String getDescription() {
      return messages.readRightName();
   }

   @Override
   public long getBitField() {
      return SecurityServiceRightsDefinition.RIGHT_READ;
   }

   @Override
   public String getAbbreviation() {
      return messages.readRightAbbreviation();
   }
}
