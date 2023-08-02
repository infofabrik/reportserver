package net.datenwerke.rs.core.client.reportexecutor.dto;

import java.io.Serializable;

public class ExportThresholdDto implements Serializable {

   private static final long serialVersionUID = -995317223072341397L;
   
   private int warnThreshold;
   private int maxThreshold;
   
   public ExportThresholdDto() {}
   
   public int getWarnThreshold() {
      return warnThreshold;
   }
   public void setWarnThreshold(int warnThreshold) {
      this.warnThreshold = warnThreshold;
   }
   public int getMaxThreshold() {
      return maxThreshold;
   }
   public void setMaxThreshold(int maxThreshold) {
      this.maxThreshold = maxThreshold;
   }
   
   
}
