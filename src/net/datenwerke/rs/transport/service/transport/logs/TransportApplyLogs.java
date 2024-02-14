package net.datenwerke.rs.transport.service.transport.logs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportApplyLogs {
   private List<TransportApplyCheckLog> checkLogs = new ArrayList<>();
   private List<TransportApplyErrorLog> errorLogs = new ArrayList<>();
   private List<TransportApplyNodeLog> nodeLogs = new ArrayList<>();
   
   private long startTime = -1l;
   private long endTime = -1l;
   private long duration = -1l;
   
   private String user = "";
   
   public TransportApplyLogs(long startTime, String user) {
      this.startTime = startTime;
      this.user = user;
   }
   
   public void setEndTime(long endTime) {
      this.endTime = endTime;
      this.duration = this.endTime - this.startTime;
   }
   
   public void addCheckLog(TransportApplyCheckLog log) {
      checkLogs.add(log);
   }
   
   public void addCheckLogs(List<TransportApplyCheckLog> logs) {
      checkLogs.addAll(logs);
   }
   
   public void addErrorLog(TransportApplyErrorLog log) {
      errorLogs.add(log);
   }
   
   public void addNodeLog(TransportApplyNodeLog log) {
      nodeLogs.add(log);
   }
   
   public List<TransportApplyCheckLog> getCheckLogs() {
      return Collections.unmodifiableList(checkLogs);
   }
   
   public List<TransportApplyErrorLog> getErrorLogs() {
      return Collections.unmodifiableList(errorLogs);
   }
   
   public List<TransportApplyNodeLog> getNodeLogs() {
      return Collections.unmodifiableList(nodeLogs);
   }
   
   public long getStartTime() {
      return startTime;
   }
   
   public long getEndTime() {
      return endTime;
   }
   
   public long getDuration() {
      return duration;
   }
   
   public String getUser() {
      return user;
   }
}
