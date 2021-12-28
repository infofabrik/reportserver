package net.datenwerke.rs.globalconstants.service.globalconstants.eximport;

import net.datenwerke.eximport.ex.entity.GenericEntityExporter;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;

public class GlobalConstantExporter extends GenericEntityExporter {

   private static final String EXPORTER_ID = "GlobalConstantExporter";

   @Override
   public String getExporterId() {
      return EXPORTER_ID;
   }

   @Override
   protected Class<?>[] getExportableTypes() {
      return new Class<?>[] { GlobalConstant.class };
   }

}
