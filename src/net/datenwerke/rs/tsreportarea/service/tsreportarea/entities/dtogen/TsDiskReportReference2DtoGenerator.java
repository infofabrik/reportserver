package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskReportReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.TsDiskReportReference2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TsDiskReportReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TsDiskReportReference2DtoGenerator implements Poso2DtoGenerator<TsDiskReportReference,TsDiskReportReferenceDtoDec> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.AbstractTsDiskNode2DtoPost postProcessor_2;
	private final net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.GeneralReference2DtoPost postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TsDiskReportReference2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.AbstractTsDiskNode2DtoPost postProcessor_2,
		net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.GeneralReference2DtoPost postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public TsDiskReportReferenceDtoDec instantiateDto(TsDiskReportReference poso)  {
		TsDiskReportReferenceDtoDec dto = new TsDiskReportReferenceDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public TsDiskReportReferenceDtoDec createDto(TsDiskReportReference poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TsDiskReportReferenceDtoDec dto = new TsDiskReportReferenceDtoDec();
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

			/*  set hardlink */
			dto.setHardlink(poso.isHardlink() );

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

			/*  set report */
			Object tmpDtoReportDtogetReport = dtoServiceProvider.get().createDto(poso.getReport(), here, referenced);
			dto.setReport((ReportDto)tmpDtoReportDtogetReport);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoReportDtogetReport, poso.getReport(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReport((ReportDto)refDto);
				}
			});

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set flags */
			dto.setFlags(poso.getFlags() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
