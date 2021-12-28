package net.datenwerke.rs.oauth.client.oauth;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.oauth.client.oauth.rpc.OAuthRpcServiceAsync;

public class OAuthDao extends Dao {

   private final OAuthRpcServiceAsync rpcService;

   @Inject
   public OAuthDao(OAuthRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void generateAuthenticationUrl(DatasinkDefinitionDto oAuth2Datasink,
         AsyncCallback<OAuthAuthenticationUriInfo> callback) {
      rpcService.generateAuthenticationUrl(oAuth2Datasink, transformAndKeepCallback(callback));
   }
}
