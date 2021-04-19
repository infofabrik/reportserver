package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.ReportDadget2DtoGenerator;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportDadget2DtoGenerator implements Poso2DtoGenerator<ReportDadget,ReportDadgetDtoDec> {

	private final net.datenwerke.rs.dashboard.service.dashboard.dagets.post.ReportDadget2DtoPost postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportDadget2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.dashboard.service.dashboard.dagets.post.ReportDadget2DtoPost postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public ReportDadgetDtoDec instantiateDto(ReportDadget poso)  {
		ReportDadgetDtoDec dto = new ReportDadgetDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public ReportDadgetDtoDec createDto(ReportDadget poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportDadgetDtoDec dto = new ReportDadgetDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set config */
			dto.setConfig(StringEscapeUtils.escapeXml(StringUtils.left(poso.getConfig(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set col */
			dto.setCol(poso.getCol() );

			/*  set container */
			Object tmpDtoDadgetContainerDtogetContainer = dtoServiceProvider.get().createDto(poso.getContainer(), referenced, referenced);
			dto.setContainer((DadgetContainerDto)tmpDtoDadgetContainerDtogetContainer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDadgetContainerDtogetContainer, poso.getContainer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setContainer((DadgetContainerDto)refDto);
				}
			});

			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set n */
			dto.setN(poso.getN() );

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

			/*  set reloadInterval */
			dto.setReloadInterval(poso.getReloadInterval() );

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

			/*  set reportReference */
			Object tmpDtoTsDiskReportReferenceDtogetReportReference = dtoServiceProvider.get().createDto(poso.getReportReference(), here, referenced);
			dto.setReportReference((TsDiskReportReferenceDto)tmpDtoTsDiskReportReferenceDtogetReportReference);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTsDiskReportReferenceDtogetReportReference, poso.getReportReference(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReportReference((TsDiskReportReferenceDto)refDto);
				}
			});

			/*  set showExecuteButton */
			dto.setShowExecuteButton(poso.isShowExecuteButton() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
