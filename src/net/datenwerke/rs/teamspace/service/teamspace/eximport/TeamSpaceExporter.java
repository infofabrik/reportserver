package net.datenwerke.rs.teamspace.service.teamspace.eximport;

import net.datenwerke.eximport.ex.entity.GenericEntityExporter;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;

public class TeamSpaceExporter extends GenericEntityExporter {

	public static final String EXPORTER_ID = "TeamSpaceExporter";
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}

	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{TeamSpace.class};
	}


}
