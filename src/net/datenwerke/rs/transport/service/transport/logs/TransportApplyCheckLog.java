package net.datenwerke.rs.transport.service.transport.logs;

import javax.annotation.Nullable;

public class TransportApplyCheckLog extends TransportApplyLog {

   private final String checkKey;
   private final String checkValue;

   public TransportApplyCheckLog(@Nullable Long timestamp, String checkKey, String checkValue,
         @Nullable String errorMessage) {
      super(timestamp, errorMessage);
      this.checkKey = checkKey;
      this.checkValue = checkValue;
   }

   public String getKey() {
      return checkKey;
   }

   public String getValue() {
      return checkValue;
   }

}
