package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;

import com.google.inject.Inject;

public class AbstractTsDiskNode2DtoPost implements Poso2DtoPostProcessor<AbstractTsDiskNode, AbstractTsDiskNodeDto> {

	private final TsDiskService diskService;
	
	@Inject
	public AbstractTsDiskNode2DtoPost(TsDiskService diskService) {
		this.diskService = diskService;
	}

	@Override
	public void dtoCreated(AbstractTsDiskNode poso, AbstractTsDiskNodeDto dto) {
		TeamSpace ts = diskService.getTeamSpaceFor(poso);
		if(null != ts)
			((AbstractTsDiskNodeDtoDec)dto).setTeamSpaceId(ts.getId());
	}

	@Override
	public void dtoInstantiated(AbstractTsDiskNode arg0,
			AbstractTsDiskNodeDto arg1) {
		
	}

	


}
