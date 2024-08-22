package net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen;


import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.LdapServer;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;

public class LdapServer2DtoPostProcessor implements Poso2DtoPostProcessor<LdapServer, LdapServerDto> {

   @Override
   public void dtoCreated(LdapServer poso, LdapServerDto dto) {
      if (null != poso.getSecurityCredentials() && !"".equals(poso.getSecurityCredentials().trim()))
         dto.setHasSecurityCredentials(true);
   }

   @Override
   public void dtoInstantiated(LdapServer poso, LdapServerDto dto) {
      // TODO Auto-generated method stub
      
   }

}
