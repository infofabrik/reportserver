package net.datenwerke.rs.oauth.client.oauth.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.oauth.client.oauth.OAuthAuthenticationUriInfo;

public interface OAuthRpcServiceAsync {
   void generateAuthenticationUrl(DatasinkDefinitionDto oAuthDatasinkDto,
         AsyncCallback<OAuthAuthenticationUriInfo> callback);
}