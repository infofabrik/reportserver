package net.datenwerke.rs.passwordpolicy.client;

import net.datenwerke.security.client.login.AuthenticateResultInfo;

public class PasswordExpiredAuthenticationResultInfo extends AuthenticateResultInfo {

   private static final long serialVersionUID = -19532144352144733L;

   private Integer expiresIn;
   private String username;

   public PasswordExpiredAuthenticationResultInfo() {
      super();
   }

   public PasswordExpiredAuthenticationResultInfo(String username, int expiresIn) {
      super();
      setExpiresIn(expiresIn);
      setUsername(username);
   }

   public Integer getExpiresIn() {
      return expiresIn;
   }

   public void setExpiresIn(Integer expiresIn) {
      this.expiresIn = expiresIn;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

}
