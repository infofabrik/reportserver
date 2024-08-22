package net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto;

import java.io.Serializable;

public class ConnectionPoolDatasourceDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -3941724813655907606L;

   private long dsId;
   private String dsName;

   public ConnectionPoolDatasourceDto() {
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

}
