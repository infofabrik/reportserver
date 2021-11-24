package net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.AmazonS3Datasink2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AmazonS3Datasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AmazonS3Datasink2DtoGenerator implements Poso2DtoGenerator<AmazonS3Datasink,AmazonS3DatasinkDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.AmazonS3Datasink2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AmazonS3Datasink2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.AmazonS3Datasink2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public AmazonS3DatasinkDto instantiateDto(AmazonS3Datasink poso)  {
		AmazonS3DatasinkDto dto = new AmazonS3DatasinkDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public AmazonS3DatasinkDto createDto(AmazonS3Datasink poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AmazonS3DatasinkDto dto = new AmazonS3DatasinkDto();
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

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set appKey */
			dto.setAppKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAppKey(),8192)));

			/*  set bucketName */
			dto.setBucketName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getBucketName(),8192)));

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set folder */
			dto.setFolder(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFolder(),8192)));

			/*  set regionName */
			dto.setRegionName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getRegionName(),8192)));

			/*  set storageType */
			dto.setStorageType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getStorageType(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
