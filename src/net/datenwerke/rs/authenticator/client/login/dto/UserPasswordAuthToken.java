package net.datenwerke.rs.authenticator.client.login.dto;

public class UserPasswordAuthToken implements NamedUserAuthToken {

   private static final long serialVersionUID = 4549245677333691398L;

   private String username;
   private String password;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
