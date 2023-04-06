package net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerContainer;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.dtogen.RemoteServerContainer2DtoGenerator;

/**
 * Poso2DtoGenerator for RemoteServerContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RemoteServerContainer2DtoGenerator implements Poso2DtoGenerator<RemoteServerContainer,RemoteServerContainerDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RemoteServerContainer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RemoteServerContainerDto instantiateDto(RemoteServerContainer poso)  {
		RemoteServerContainerDto dto = new RemoteServerContainerDto();
		return dto;
	}

	public RemoteServerContainerDto createDto(RemoteServerContainer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RemoteServerContainerDto dto = new RemoteServerContainerDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set remoteServer */
			Object tmpDtoRemoteServerDefinitionDtogetRemoteServer = dtoServiceProvider.get().createDto(poso.getRemoteServer(), referenced, referenced);
			dto.setRemoteServer((RemoteServerDefinitionDto)tmpDtoRemoteServerDefinitionDtogetRemoteServer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoRemoteServerDefinitionDtogetRemoteServer, poso.getRemoteServer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setRemoteServer((RemoteServerDefinitionDto)refDto);
				}
			});

		}

		return dto;
	}


}
