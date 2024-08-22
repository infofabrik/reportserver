package net.datenwerke.rs.remotersrestserver.server.remotersrestserver

import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_MESSAGE
import static net.datenwerke.rs.remotersrestserver.service.remotersrestserver.RemoteRsRestServerModule.TEST_RS_MESSAGE_CONTENT

import javax.inject.Singleton

import org.apache.commons.lang3.exception.ExceptionUtils

import com.google.inject.Inject

import groovy.json.JsonSlurper
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException
import net.datenwerke.gxtdto.server.dtomanager.DtoService
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.rpc.RemoteRsRestServerRpcService
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer
import net.datenwerke.security.server.SecuredRemoteServiceServlet

@Singleton
public class RemoteRsRestServerRpcServiceImpl extends SecuredRemoteServiceServlet implements RemoteRsRestServerRpcService {

   /**
    *
    */
   private static final long serialVersionUID = -2851245535282732136L

   private final DtoService dtoService
   
   @Inject
   public RemoteRsRestServerRpcServiceImpl(
      DtoService dtoService
         ) {
      this.dtoService = dtoService
   }

   @Override
   public boolean test(RemoteRsRestServerDto remoteServerDto) throws ServerCallFailedException {
      if (!(remoteServerDto instanceof RemoteRsRestServerDto))
         throw new ServerCallFailedException('Not a remote RS REST server')
         
      try {
         RemoteRsRestServer remoteServer = dtoService.loadPoso(remoteServerDto)
         def protocol = ''
         if (!remoteServer.url)
            throw new ServerCallFailedException("Remote RS REST server has no REST URL")
            
         if (!remoteServer.url.trim().startsWith('http://') && !remoteServer.url.trim().startsWith('https://'))
            throw new ServerCallFailedException("URL contains no protocol:  '$remoteServer.url'")
            
         def restUrl = new URL("${remoteServer.url.trim()}/test?user=${remoteServer.username}&apikey=${remoteServer.apikey}")
         def conn = restUrl.openConnection()
         if (conn.responseCode != HttpURLConnection.HTTP_OK)
            throw new ServerCallFailedException("Server returned response code $conn.responseCode")
            
         def msg = new JsonSlurper().parseText(conn.inputStream.text)[TEST_RS_MESSAGE]
         if (msg != TEST_RS_MESSAGE_CONTENT)
            throw new ServerCallFailedException("Incorrect message received: '$msg'")
      } catch (Exception e) {
         throw new ServerCallFailedException(ExceptionUtils.getRootCauseMessage(e), e)
      }
         
      return true
      
   }

}
