package net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen.RemoteRsServer2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for RemoteRsServer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RemoteRsServer2DtoGenerator implements Poso2DtoGenerator<RemoteRsServer,RemoteRsServerDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen.RemoteRsServer2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RemoteRsServer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen.RemoteRsServer2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public RemoteRsServerDto instantiateDto(RemoteRsServer poso)  {
		RemoteRsServerDto dto = new RemoteRsServerDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public RemoteRsServerDto createDto(RemoteRsServer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RemoteRsServerDto dto = new RemoteRsServerDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set url */
			dto.setUrl(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUrl(),8192)));

			/*  set username */
			dto.setUsername(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUsername(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
