package net.datenwerke.rs.saiku.service.saiku.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.saiku.service.saiku.entities.dtogen.SaikuReportVariant2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for SaikuReportVariant
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SaikuReportVariant2DtoGenerator implements Poso2DtoGenerator<SaikuReportVariant,SaikuReportVariantDtoDec> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Report2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SaikuReportVariant2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Report2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public SaikuReportVariantDtoDec instantiateDto(SaikuReportVariant poso)  {
		SaikuReportVariantDtoDec dto = new SaikuReportVariantDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public SaikuReportVariantDtoDec createDto(SaikuReportVariant poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SaikuReportVariantDtoDec dto = new SaikuReportVariantDtoDec();
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

			/*  set uuid */
			dto.setUuid(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUuid(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set allowMdx */
			dto.setAllowMdx(poso.isAllowMdx() );

			/*  set createdFromPivotReport */
			dto.setCreatedFromPivotReport(poso.isCreatedFromPivotReport() );

			/*  set datasourceContainer */
			Object tmpDtoDatasourceContainerDtogetDatasourceContainer = dtoServiceProvider.get().createDto(poso.getDatasourceContainer(), here, referenced);
			dto.setDatasourceContainer((DatasourceContainerDto)tmpDtoDatasourceContainerDtogetDatasourceContainer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceContainerDtogetDatasourceContainer, poso.getDatasourceContainer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasourceContainer((DatasourceContainerDto)refDto);
				}
			});

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set hideParents */
			dto.setHideParents(poso.isHideParents() );

			/*  set originalPivotReportId */
			dto.setOriginalPivotReportId(poso.getOriginalPivotReportId() );

			/*  set parameterDefinitions */
			final List<ParameterDefinitionDto> col_parameterDefinitions = new ArrayList<ParameterDefinitionDto>();
			if( null != poso.getParameterDefinitions()){
				for(ParameterDefinition refPoso : poso.getParameterDefinitions()){
					final Object tmpDtoParameterDefinitionDtogetParameterDefinitions = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_parameterDefinitions.add((ParameterDefinitionDto) tmpDtoParameterDefinitionDtogetParameterDefinitions);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetParameterDefinitions, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (parameterDefinitions)");
							int tmp_index = col_parameterDefinitions.indexOf(tmpDtoParameterDefinitionDtogetParameterDefinitions);
							col_parameterDefinitions.set(tmp_index,(ParameterDefinitionDto) dto);
						}
					});
				}
				dto.setParameterDefinitions(col_parameterDefinitions);
			}

			/*  set parameterInstances */
			final Set<ParameterInstanceDto> col_parameterInstances = new HashSet<ParameterInstanceDto>();
			if( null != poso.getParameterInstances()){
				for(ParameterInstance refPoso : poso.getParameterInstances()){
					final Object tmpDtoParameterInstanceDtogetParameterInstances = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_parameterInstances.add((ParameterInstanceDto) tmpDtoParameterInstanceDtogetParameterInstances);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterInstanceDtogetParameterInstances, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (parameterInstances)");
							col_parameterInstances.remove(tmpDtoParameterInstanceDtogetParameterInstances);
							col_parameterInstances.add((ParameterInstanceDto) dto);
						}
					});
				}
				dto.setParameterInstances(col_parameterInstances);
			}

			/*  set reportMetadata */
			final Set<ReportMetadataDto> col_reportMetadata = new HashSet<ReportMetadataDto>();
			if( null != poso.getReportMetadata()){
				for(ReportMetadata refPoso : poso.getReportMetadata()){
					final Object tmpDtoReportMetadataDtogetReportMetadata = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_reportMetadata.add((ReportMetadataDto) tmpDtoReportMetadataDtogetReportMetadata);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoReportMetadataDtogetReportMetadata, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (reportMetadata)");
							col_reportMetadata.remove(tmpDtoReportMetadataDtogetReportMetadata);
							col_reportMetadata.add((ReportMetadataDto) dto);
						}
					});
				}
				dto.setReportMetadata(col_reportMetadata);
			}

			/*  set reportProperties */
			final Set<ReportPropertyDto> col_reportProperties = new HashSet<ReportPropertyDto>();
			if( null != poso.getReportProperties()){
				for(ReportProperty refPoso : poso.getReportProperties()){
					final Object tmpDtoReportPropertyDtogetReportProperties = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_reportProperties.add((ReportPropertyDto) tmpDtoReportPropertyDtogetReportProperties);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoReportPropertyDtogetReportProperties, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (reportProperties)");
							col_reportProperties.remove(tmpDtoReportPropertyDtogetReportProperties);
							col_reportProperties.add((ReportPropertyDto) dto);
						}
					});
				}
				dto.setReportProperties(col_reportProperties);
			}

			/*  set BaseReport */
			Object tmpDtoReportDtogetBaseReport = dtoServiceProvider.get().createDto(poso.getBaseReport(), referenced, referenced);
			dto.setBaseReport((ReportDto)tmpDtoReportDtogetBaseReport);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoReportDtogetBaseReport, poso.getBaseReport(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setBaseReport((ReportDto)refDto);
				}
			});

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
