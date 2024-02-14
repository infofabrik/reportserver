package net.datenwerke.rs.transport.service.transport.logs;

import javax.annotation.Nullable;

import net.datenwerke.eximport.im.ImportResult.ImportResultExtraData.ImportStrategy;

public class TransportApplyNodeLog extends TransportApplyLog {

   private final String key;
   private final String value;
   private final ImportStrategy strategy;
   private final boolean skip;
   
   public TransportApplyNodeLog(@Nullable Long timestamp, String key, String value, ImportStrategy strategy, boolean skip) {
      super(timestamp, key + ": " + value);
      this.key = key;
      this.value = value;

      this.strategy = strategy;
      this.skip = skip;
   }
   
   public String getKey() {
      return key;
   }
   
   public String getValue() {
      return value;
   }
   
   public ImportStrategy getImportStrategy() {
      return strategy;
   }
   
   public boolean hasSkipped() {
      return skip;
   }
}
