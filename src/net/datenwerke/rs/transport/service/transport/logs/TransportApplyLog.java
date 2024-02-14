package net.datenwerke.rs.transport.service.transport.logs;

import javax.annotation.Nullable;

public class TransportApplyLog {
   @Nullable
   private final Long timestamp;
   @Nullable
   private final String message;
   
   public TransportApplyLog(@Nullable Long timestamp, @Nullable String message) {
      this.timestamp = timestamp;
      this.message = message;
   }
   
   @Nullable
   public long getTimestamp() {
      return timestamp;
   }
   
   @Nullable
   public String getMessage() {
      return message;
   }
}
