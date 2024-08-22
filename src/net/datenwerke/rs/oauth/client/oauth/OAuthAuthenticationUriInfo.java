package net.datenwerke.rs.oauth.client.oauth;

import java.io.Serializable;

public class OAuthAuthenticationUriInfo implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -4623732267255306841L;

   private String authenticationUri;
   private String redirectUri;

   public String getAuthenticationUri() {
      return authenticationUri;
   }

   public void setAuthenticationUri(String authenticationUri) {
      this.authenticationUri = authenticationUri;
   }

   public String getRedirectUri() {
      return redirectUri;
   }

   public void setRedirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
   }

}
