package net.datenwerke.security.service.authenticator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.security.client.login.AuthenticateResultInfo;
import net.datenwerke.security.service.usermanager.entities.User;

public class AuthenticationResult {

   private boolean allowed;
   private final User user;

   private List<AuthenticateResultInfo> infos = new ArrayList<AuthenticateResultInfo>();

   @Deprecated
   public AuthenticationResult(boolean valid, User user, boolean authoritative) {
      this(valid || !authoritative, user);
   }

   public AuthenticationResult(boolean allowed, User user) {
      this.allowed = allowed;
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public boolean isAllowed() {
      return allowed;
   }

   public void setInfos(List<AuthenticateResultInfo> infos) {
      this.infos = infos;
   }

   public List<AuthenticateResultInfo> getInfos() {
      return infos;
   }

   public void addInfo(AuthenticateResultInfo info) {
      if (null == infos)
         infos = new ArrayList<AuthenticateResultInfo>();

      infos.add(info);
   }

   public void setAllowed(boolean allowed) {
      this.allowed = allowed;
   }

   /**
    * Denies access
    * 
    * @return an AuthenticationResult denying access
    */
   public static AuthenticationResult denyAccess() {
      return new AuthenticationResult(false, null);
   }

   /**
    * Denies access
    * 
    * @param user the user
    * 
    * @return an AuthenticationResult denying access
    */
   public static AuthenticationResult denyAccess(User user) {
      return new AuthenticationResult(false, user);
   }

   /**
    * Grants access to the given user
    * 
    * @param user the user
    * @return an AuthenticationResult granting access to the given user
    */
   public static AuthenticationResult grantAccess(User user) {
      return new AuthenticationResult(true, user);
   }

   /**
    * Creates an AuthenticationResult specifying don't care, somebody else should
    * decide
    * 
    * @return an AuthenticationResult specifying don't care, somebody else should
    *         decide
    */
   public static AuthenticationResult dontCareAccess() {
      return new AuthenticationResult(true, null);
   }

   /**
    * If authoritative is true, creates an AuthenticationResult denying access.
    * Else, creates an AuthenticationResult specifying don't care, somebody else
    * should decide
    * 
    * @param authoritative
    * @return
    */
   public static AuthenticationResult cannotAuthenticate(boolean authoritative) {
      if (authoritative)
         return denyAccess();
      else
         return dontCareAccess();
   }

   /**
    * If authoritative is true, creates an AuthenticationResult denying access to
    * the respective user. Else, creates an AuthenticationResult specifying don't
    * care, somebody else should decide
    * 
    * @param authoritative
    * @param user          the user
    * @return
    */
   public static AuthenticationResult cannotAuthenticate(boolean authoritative, User user) {
      if (authoritative)
         return denyAccess(user);
      else
         return dontCareAccess();
   }

}
