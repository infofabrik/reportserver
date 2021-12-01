package net.datenwerke.eximport.im;

import java.util.List;

public interface ImportSupervisorFactory {

	public ImportSupervisor create(ImportConfig config, List<Importer> importers);
	
}
