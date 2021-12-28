package net.datenwerke.rs.core.service.reportmanager.metadata;

import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;

/**
 * 
 *
 */
public class PlainMetadata implements CompiledReportMetadata {

   private final String data;

   public PlainMetadata(String data) {
      this.data = data;
   }

   @Override
   public String getMetadata() {
      return data;
   }

}
