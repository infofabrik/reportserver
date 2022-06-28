package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFileReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.TsDiskFileReference2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TsDiskFileReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TsDiskFileReference2DtoGenerator implements Poso2DtoGenerator<TsDiskFileReference,TsDiskFileReferenceDtoDec> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.AbstractTsDiskNode2DtoPost postProcessor_2;
	private final net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.GeneralReference2DtoPost postProcessor_3;
	private final net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.TsDiskFileReference2DtoPost postProcessor_4;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TsDiskFileReference2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.AbstractTsDiskNode2DtoPost postProcessor_2,
		net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.GeneralReference2DtoPost postProcessor_3,
		net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.TsDiskFileReference2DtoPost postProcessor_4	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
		this.postProcessor_4 = postProcessor_4;
	}

	public TsDiskFileReferenceDtoDec instantiateDto(TsDiskFileReference poso)  {
		TsDiskFileReferenceDtoDec dto = new TsDiskFileReferenceDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		this.postProcessor_4.dtoInstantiated(poso, dto);
		return dto;
	}

	public TsDiskFileReferenceDtoDec createDto(TsDiskFileReference poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TsDiskFileReferenceDtoDec dto = new TsDiskFileReferenceDtoDec();
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
			/*  set flags */
			dto.setFlags(poso.getFlags() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);
		this.postProcessor_4.dtoCreated(poso, dto);

		return dto;
	}


}
