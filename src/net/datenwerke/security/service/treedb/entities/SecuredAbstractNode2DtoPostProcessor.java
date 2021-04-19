package net.datenwerke.security.service.treedb.entities;

import java.util.HashSet;
import java.util.Set;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class SecuredAbstractNode2DtoPostProcessor implements Poso2DtoPostProcessor<SecuredAbstractNode, SecuredAbstractNodeDtoDec> {

	private final SecurityService securityService;
	
	@Inject
	public SecuredAbstractNode2DtoPostProcessor(
		SecurityService securityService	
		){
		
		this.securityService = securityService;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void dtoCreated(SecuredAbstractNode poso,
			SecuredAbstractNodeDtoDec dto) {
		if(dto.getDtoView() == DtoView.LIST_FTO || dto.getDtoView() == DtoView.FTO)
			return;
		
		Set<RightDto> availableRights = new HashSet<RightDto>();
		Set<RightDto> availableInheritedRights = new HashSet<RightDto>();
		
		if( securityService.checkRights(poso, SecurityServiceSecuree.class, Read.class))
			availableRights.add(new ReadDto());
		if( securityService.checkRights(poso, SecurityServiceSecuree.class, Write.class))
			availableRights.add(new WriteDto());
		if(securityService.checkRights(poso, true, SecurityServiceSecuree.class, Write.class))
			availableInheritedRights.add(new WriteDto());
		if( securityService.checkRights(poso, SecurityServiceSecuree.class, Delete.class))
			availableRights.add(new DeleteDto());
		if( securityService.checkRights(poso, SecurityServiceSecuree.class, Execute.class))
			availableRights.add(new ExecuteDto());
		if( securityService.checkRights(poso, SecurityServiceSecuree.class, GrantAccess.class))
			availableRights.add(new GrantAccessDto());
		
		dto.setAvailableAccessRights(availableRights);
		dto.setAvailableInheritedAccessRights(availableInheritedRights);
		
		dto.setAvailableAccessRightsSet(true);
	}

	@Override
	public void dtoInstantiated(SecuredAbstractNode poso,
			SecuredAbstractNodeDtoDec dto) {
	}

}
