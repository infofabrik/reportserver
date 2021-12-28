package net.datenwerke.rs.dashboard.service.dashboard.dagets.post;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

public class LibraryDadget2DtoPost
		implements
		Poso2DtoPostProcessor<LibraryDadget, LibraryDadgetDto> {

	private final SecurityService securityService;
	
	@Inject
	public LibraryDadget2DtoPost(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public void dtoCreated(LibraryDadget poso, LibraryDadgetDto dto) {
		DadgetNode dadgetNode = poso.getDadgetNode();
		if(null != dadgetNode && ! securityService.checkRights(dadgetNode, Read.class))
			dto.setDadgetNode(null);
	}

	@Override
	public void dtoInstantiated(LibraryDadget arg0, LibraryDadgetDto arg1) {
		// TODO Auto-generated method stub
		
	}


}
