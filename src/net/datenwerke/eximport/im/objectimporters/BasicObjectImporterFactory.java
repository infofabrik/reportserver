package net.datenwerke.eximport.im.objectimporters;

import net.datenwerke.eximport.im.ImportSupervisor;

public interface BasicObjectImporterFactory {

   public BasicObjectImporter create(ImportSupervisor importSupervisor, ImportableElement element);

}
