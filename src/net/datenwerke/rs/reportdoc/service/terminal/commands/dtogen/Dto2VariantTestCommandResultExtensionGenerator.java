package net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.reportdoc.client.dto.VariantTestCommandResultExtensionDto;
import net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommandResultExtension;
import net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.Dto2VariantTestCommandResultExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for VariantTestCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2VariantTestCommandResultExtensionGenerator implements Dto2PosoGenerator<VariantTestCommandResultExtensionDto,VariantTestCommandResultExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2VariantTestCommandResultExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public VariantTestCommandResultExtension loadPoso(VariantTestCommandResultExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public VariantTestCommandResultExtension instantiatePoso()  {
		VariantTestCommandResultExtension poso = new VariantTestCommandResultExtension();
		return poso;
	}

	public VariantTestCommandResultExtension createPoso(VariantTestCommandResultExtensionDto dto)  throws ExpectedException {
		VariantTestCommandResultExtension poso = new VariantTestCommandResultExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public VariantTestCommandResultExtension createUnmanagedPoso(VariantTestCommandResultExtensionDto dto)  throws ExpectedException {
		VariantTestCommandResultExtension poso = new VariantTestCommandResultExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		/*  set datasources */
		final List<DatasourceDefinition> col_datasources = new ArrayList<DatasourceDefinition>();
		/* load new data from dto */
		Collection<DatasourceDefinitionDto> tmpCol_datasources = dto.getDatasources();

		for(DatasourceDefinitionDto refDto : tmpCol_datasources){
			if(null != refDto && null != refDto.getId())
				col_datasources.add((DatasourceDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (datasources)");
						col_datasources.add((DatasourceDefinition) poso);
					}
					});
			}
		}
		poso.setDatasources(col_datasources);

		/*  set report */
		ReportDto tmpDto_report = dto.getReport();
		if(null != tmpDto_report && null != tmpDto_report.getId()){
			if(null != poso.getReport() && null != poso.getReport().getId() && ! poso.getReport().getId().equals(tmpDto_report.getId())){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_report);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), newPropertyValue, "report");
				poso.setReport(newPropertyValue);
			} else if(null == poso.getReport()){
				poso.setReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_report));
			}
		} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (report)");
					poso.setReport((Report)refPoso);
				}
			});
		} else if(null == tmpDto_report){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), null, "report");
			poso.setReport(null);
		}

	}

	protected void mergeProxy2Poso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		/*  set datasources */
		if(dto.isDatasourcesModified()){
			final List<DatasourceDefinition> col_datasources = new ArrayList<DatasourceDefinition>();
			/* load new data from dto */
			Collection<DatasourceDefinitionDto> tmpCol_datasources = null;
			tmpCol_datasources = dto.getDatasources();

			for(DatasourceDefinitionDto refDto : tmpCol_datasources){
				if(null != refDto && null != refDto.getId())
					col_datasources.add((DatasourceDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (datasources)");
							col_datasources.add((DatasourceDefinition) poso);
						}
					});
				}
			}
			poso.setDatasources(col_datasources);
		}

		/*  set report */
		if(dto.isReportModified()){
			ReportDto tmpDto_report = dto.getReport();
			if(null != tmpDto_report && null != tmpDto_report.getId()){
				if(null != poso.getReport() && null != poso.getReport().getId() && ! poso.getReport().getId().equals(tmpDto_report.getId())){
					Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_report);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), newPropertyValue, "report");
					poso.setReport(newPropertyValue);
				} else if(null == poso.getReport()){
					poso.setReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_report));
				}
			} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (report)");
						poso.setReport((Report)refPoso);
					}
			});
			} else if(null == tmpDto_report){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), null, "report");
				poso.setReport(null);
			}
		}

	}

	public void mergeUnmanagedPoso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		/*  set datasources */
		final List<DatasourceDefinition> col_datasources = new ArrayList<DatasourceDefinition>();
		/* load new data from dto */
		Collection<DatasourceDefinitionDto> tmpCol_datasources = dto.getDatasources();

		for(DatasourceDefinitionDto refDto : tmpCol_datasources){
			if(null != refDto && null != refDto.getId())
				col_datasources.add((DatasourceDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (datasources)");
						col_datasources.add((DatasourceDefinition) poso);
					}
					});
			}
		}
		poso.setDatasources(col_datasources);

		/*  set report */
		ReportDto tmpDto_report = dto.getReport();
		if(null != tmpDto_report && null != tmpDto_report.getId()){
			Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_report);
			poso.setReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setReport((Report)refPoso);
				}
			});
		} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			final ReportDto tmpDto_report_final = tmpDto_report;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_report_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setReport((Report)refPoso);
					}
				}
			});
		} else if(null == tmpDto_report){
			poso.setReport(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(VariantTestCommandResultExtensionDto dto, final VariantTestCommandResultExtension poso)  throws ExpectedException {
		/*  set datasources */
		if(dto.isDatasourcesModified()){
			final List<DatasourceDefinition> col_datasources = new ArrayList<DatasourceDefinition>();
			/* load new data from dto */
			Collection<DatasourceDefinitionDto> tmpCol_datasources = null;
			tmpCol_datasources = dto.getDatasources();

			for(DatasourceDefinitionDto refDto : tmpCol_datasources){
				if(null != refDto && null != refDto.getId())
					col_datasources.add((DatasourceDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (datasources)");
							col_datasources.add((DatasourceDefinition) poso);
						}
					});
				}
			}
			poso.setDatasources(col_datasources);
		}

		/*  set report */
		if(dto.isReportModified()){
			ReportDto tmpDto_report = dto.getReport();
			if(null != tmpDto_report && null != tmpDto_report.getId()){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_report);
				poso.setReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setReport((Report)refPoso);
					}
			});
			} else if(null != tmpDto_report && null == tmpDto_report.getId()){
				final ReportDto tmpDto_report_final = tmpDto_report;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_report_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setReport((Report)refPoso);
						}
					}
			});
			} else if(null == tmpDto_report){
				poso.setReport(null);
			}
		}

	}

	public VariantTestCommandResultExtension loadAndMergePoso(VariantTestCommandResultExtensionDto dto)  throws ExpectedException {
		VariantTestCommandResultExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(VariantTestCommandResultExtensionDto dto, VariantTestCommandResultExtension poso)  {
	}


	public void postProcessCreateUnmanaged(VariantTestCommandResultExtensionDto dto, VariantTestCommandResultExtension poso)  {
	}


	public void postProcessLoad(VariantTestCommandResultExtensionDto dto, VariantTestCommandResultExtension poso)  {
	}


	public void postProcessMerge(VariantTestCommandResultExtensionDto dto, VariantTestCommandResultExtension poso)  {
	}


	public void postProcessInstantiate(VariantTestCommandResultExtension poso)  {
	}



}
