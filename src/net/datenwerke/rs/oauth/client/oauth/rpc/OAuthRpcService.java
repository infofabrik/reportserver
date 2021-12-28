package net.datenwerke.rs.oauth.client.oauth.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.oauth.client.oauth.OAuthAuthenticationUriInfo;

@RemoteServiceRelativePath("oauthrpc")
public interface OAuthRpcService extends RemoteService {

   OAuthAuthenticationUriInfo generateAuthenticationUrl(DatasinkDefinitionDto oAuthDatasinkDto)
         throws ServerCallFailedException;
}