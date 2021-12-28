package net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto;

import java.io.Serializable;

public class ConnectionPoolInfoDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -3941724813655907606L;

   private long dsId;
   private String dsName;
   private int maxPoolSize;
   private int noOfConnections;

   private int busyConnections;
   private int idleConnections;
   private int threadsAwaitingConnection;

   private int unclosedOrphanedConnections;

   private Long timestamp;

   public ConnectionPoolInfoDto() {
   }

   public ConnectionPoolInfoDto(long timestamp) {
      this.timestamp = timestamp;
   }

   public long getTimestamp() {
      return timestamp;
   }

   public void setSampleDate(long timestamp) {
      this.timestamp = timestamp;
   }

   public long getDsId() {
      return dsId;
   }

   public void setDsId(long dsId) {
      this.dsId = dsId;
   }

   public String getDsName() {
      return dsName;
   }

   public void setDsName(String dsName) {
      this.dsName = dsName;
   }

   public int getMaxPoolSize() {
      return maxPoolSize;
   }

   public void setMaxPoolSize(int maxPoolSize) {
      this.maxPoolSize = maxPoolSize;
   }

   public int getNoOfConnections() {
      return noOfConnections;
   }

   public void setNoOfConnections(int noOfConnections) {
      this.noOfConnections = noOfConnections;
   }

   public int getBusyConnections() {
      return busyConnections;
   }

   public void setBusyConnections(int busyConnections) {
      this.busyConnections = busyConnections;
   }

   public int getIdleConnections() {
      return idleConnections;
   }

   public void setIdleConnections(int idleConnections) {
      this.idleConnections = idleConnections;
   }

   public int getThreadsAwaitingConnection() {
      return threadsAwaitingConnection;
   }

   public void setThreadsAwaitingConnection(int threadsAwaitingConnection) {
      this.threadsAwaitingConnection = threadsAwaitingConnection;
   }

   public int getUnclosedOrphanedConnections() {
      return unclosedOrphanedConnections;
   }

   public void setUnclosedOrphanedConnections(int unclosedOrphanedConnections) {
      this.unclosedOrphanedConnections = unclosedOrphanedConnections;
   }

}
