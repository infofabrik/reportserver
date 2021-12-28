package net.datenwerke.rs.passwordpolicy.client.accountinhibition;

import java.util.Date;

import net.datenwerke.gxtdto.client.model.DwModel;

public class AccountInhibitionConfiguration implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 1551519273324174420L;

   private boolean inhibitionState;
   private boolean blockedTemporarily;
   private Date expirationDate;
   private Long userId;

   public boolean isInhibitionState() {
      return inhibitionState;
   }

   public void setInhibitionState(boolean inhibitionState) {
      this.inhibitionState = inhibitionState;
   }

   public Date getExpirationDate() {
      return expirationDate;
   }

   public void setExpirationDate(Date expirationDate) {
      this.expirationDate = expirationDate;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public boolean isBlockedTemporarily() {
      return blockedTemporarily;
   }

   public void setBlockedTemporarily(boolean blockedTemporarily) {
      this.blockedTemporarily = blockedTemporarily;
   }

}