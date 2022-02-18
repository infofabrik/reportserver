package net.datenwerke.rs.onedrive.service.onedrive.terminal.helper

import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.oauth.OAuth20Service

import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink
import net.datenwerke.rs.terminal.service.terminal.TerminalSession
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException
import net.datenwerke.security.service.security.rights.Read

abstract class OnedriveTerminalMethods {
   
   def getOneDriveDatasink(String query, TerminalSession session) {
      Collection<Object> resolvedDatasource = session.getObjectResolver().getObjects(query, Read)
      if (1 != resolvedDatasource.size())
         throw new IllegalArgumentException("OneDriveDatasink must be resolved to exactly one object: ' $query '")
      def datasinkAsObject = resolvedDatasource.iterator().next()
      if(!(datasinkAsObject instanceof OneDriveDatasink))
         throw new IllegalArgumentException("not a OneDriveDatasink: ' $query '")
      return (OneDriveDatasink) datasinkAsObject
   }

   def validateDatasink(OneDriveDatasink oneDriveDatasink) {
      assert oneDriveDatasink : 'Datasink is null'
      assert oneDriveDatasink.tenantId : 'Tenant is null'
      assert oneDriveDatasink.getRefreshToken() : "OneDrive - SharePoint (O365) authentication not configured, please setup by using the 'Datasink OAuth2 Authentication Setup' button."
   }

   def getAndValidateOneDriveDatasink(String query, TerminalSession session) {
      def oneDriveDatasink = getOneDriveDatasink(query, session)
      validateDatasink(oneDriveDatasink)
      return oneDriveDatasink
   }

   def getAccessToken(OAuth20Service oauthService, String refreshToken) {
      try {
         return oauthService.refreshAccessToken(refreshToken)
      } catch (Exception e) {
         throw new TerminalException("Could not get access token: $e.message", e)
      }
   }
   
   def executeRequest(OAuth20Service oauthService, OAuthRequest request, def accessToken) {
      try {
         oauthService.signRequest(accessToken, request)
         oauthService.execute(request).withCloseable { response ->
            def responseCode = response.getCode()
            if (200 != responseCode && 201 != responseCode)
               throw new IOException("Could not execute. Response code = ' $responseCode ', response body = [' $response.body ']")
            return response
         }
      } catch (Exception e) {
         return e
      }
   }
}
