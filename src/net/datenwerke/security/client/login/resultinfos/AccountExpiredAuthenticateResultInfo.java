package net.datenwerke.security.client.login.resultinfos;

import java.util.Date;

import net.datenwerke.security.client.login.AuthenticateResultInfo;

public class AccountExpiredAuthenticateResultInfo extends AuthenticateResultInfo {

   /**
    * 
    */
   private static final long serialVersionUID = -3828289383438268043L;

   private Date expiredSince;

   public AccountExpiredAuthenticateResultInfo() {
      super();
   }

   public AccountExpiredAuthenticateResultInfo(Date expiredSince) {
      super();
      setExpiredSince(expiredSince);
   }

   public Date getExpiredSince() {
      return expiredSince;
   }

   public void setExpiredSince(Date expiredSince) {
      this.expiredSince = expiredSince;
   }

}
