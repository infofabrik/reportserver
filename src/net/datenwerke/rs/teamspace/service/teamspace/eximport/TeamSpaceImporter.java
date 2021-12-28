package net.datenwerke.rs.teamspace.service.teamspace.eximport;

import net.datenwerke.eximport.im.entity.GenericEntityImporter;

public class TeamSpaceImporter extends GenericEntityImporter {

   public static final String IMPORTER_ID = "TeamSpaceImporter";

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { TeamSpaceExporter.class };
   }
}
