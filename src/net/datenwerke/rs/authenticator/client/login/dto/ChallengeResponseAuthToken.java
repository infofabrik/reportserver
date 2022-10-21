package net.datenwerke.rs.authenticator.client.login.dto;

public class ChallengeResponseAuthToken implements NamedUserAuthToken {

   private static final long serialVersionUID = -2055789296583255766L;

   private String username;
   private ChallengeResponseContainer challengeResponseContainer;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public ChallengeResponseContainer getChallengeResponse() {
      return challengeResponseContainer;
   }

   public void setChallengeResponseContainer(ChallengeResponseContainer container) {
      this.challengeResponseContainer = container;
   }
}
