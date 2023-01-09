package net.datenwerke.rs.adminutils.server.systemconsole.generalinfo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc.GeneralInfoRpcService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.adminutils.service.systemconsole.genrights.SystemConsoleSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class GeneralInfoRpcServiceImpl extends SecuredRemoteServiceServlet implements GeneralInfoRpcService {

   private static final long serialVersionUID = 1L;

   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;

   @Inject
   public GeneralInfoRpcServiceImpl(Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<SecurityService> securityServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @Override
   public GeneralInfoDto loadGeneralInfo() {
      securityServiceProvider.get().assertRights(SystemConsoleSecurityTarget.class, Read.class);
      return generalInfoServiceProvider.get().getGeneralInfo();
   }

}
