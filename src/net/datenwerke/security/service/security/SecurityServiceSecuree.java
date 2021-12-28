package net.datenwerke.security.service.security;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.locale.SecurityMessages;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.security.rights.Write;

/**
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.security.client.security.dto")
public class SecurityServiceSecuree implements Securee {

   private final SecurityMessages messages = LocalizationServiceImpl.getMessages(SecurityMessages.class);

   public static final String SECUREE_ID = "SecurityService_DefaultSecuree"; //$NON-NLS-1$

   @Override
   public List<Right> getRights() {
      List<Right> rights = new ArrayList<Right>();

      rights.add(new Read());
      rights.add(new Write());
      rights.add(new Execute());
      rights.add(new Delete());
      rights.add(new GrantAccess());

      return rights;
   }

   @Override
   public String getSecureeId() {
      return SECUREE_ID;
   }

   @Override
   public String getName() {
      return messages.securitySecureeName();
   }

}
