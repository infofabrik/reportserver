package net.datenwerke.security.client.login.resultinfos;

import java.util.Date;

import net.datenwerke.security.client.login.AuthenticateResultInfo;

public class AccountLockedAuthenticateResultInfo extends AuthenticateResultInfo {

   private static final long serialVersionUID = 44454867455548906L;

   private Date lockedUntil;

   public AccountLockedAuthenticateResultInfo() {
      super();
   }

   public AccountLockedAuthenticateResultInfo(Date lockedUntil) {
      super();
      setLockedUntil(lockedUntil);
   }

   public Date getLockedUntil() {
      return lockedUntil;
   }

   public void setLockedUntil(Date lockedUntil) {
      this.lockedUntil = lockedUntil;
   }

}
