package net.datenwerke.rs.oauth.server.oauth;

import javax.inject.Singleton;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.oauth.client.oauth.OAuthAuthenticationUriInfo;
import net.datenwerke.rs.oauth.client.oauth.rpc.OAuthRpcService;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticationService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class OAuthRpcServiceImpl extends SecuredRemoteServiceServlet implements OAuthRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 2861287980553611185L;

   private final SecurityService securityService;
   private final DtoService dtoService;
   private final OAuthAuthenticationService oAuthAuthenticationService;

   @Inject
   public OAuthRpcServiceImpl(SecurityService securityService, DtoService dtoService,
         OAuthAuthenticationService oAuthAuthenticationService) {
      this.securityService = securityService;
      this.dtoService = dtoService;
      this.oAuthAuthenticationService = oAuthAuthenticationService;
   }

   @Override
   public OAuthAuthenticationUriInfo generateAuthenticationUrl(DatasinkDefinitionDto oAuthDatasinkDto)
         throws ServerCallFailedException {

      DatasinkDefinition oAuthDatasink = (DatasinkDefinition) dtoService.loadPoso(oAuthDatasinkDto);

      if (!(oAuthDatasink instanceof OAuthAuthenticatable))
         throw new IllegalStateException("Has to be OAuthAuthenticatable");

      /* check rights */
      securityService.assertRights(oAuthDatasink, Read.class, Execute.class);

      OAuthAuthenticationUriInfo uriInfo = new OAuthAuthenticationUriInfo();
      uriInfo.setAuthenticationUri(
            oAuthAuthenticationService.generateAuthenticationUrl((OAuthAuthenticatable) oAuthDatasink));
      uriInfo.setRedirectUri(oAuthAuthenticationService.getRedirectUri());

      return uriInfo;

   }

}
