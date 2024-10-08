package net.datenwerke.rs.ftp.service.ftp.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpDatasink2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for FtpDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FtpDatasink2DtoGenerator implements Poso2DtoGenerator<FtpDatasink,FtpDatasinkDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpDatasink2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FtpDatasink2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpDatasink2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public FtpDatasinkDto instantiateDto(FtpDatasink poso)  {
		FtpDatasinkDto dto = new FtpDatasinkDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public FtpDatasinkDto createDto(FtpDatasink poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FtpDatasinkDto dto = new FtpDatasinkDto();
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

			/*  set folder */
			dto.setFolder(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFolder(),8192)));

			/*  set ftpMode */
			dto.setFtpMode(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFtpMode(),8192)));

			/*  set host */
			dto.setHost(StringEscapeUtils.escapeXml(StringUtils.left(poso.getHost(),8192)));

			/*  set port */
			dto.setPort(poso.getPort() );

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
