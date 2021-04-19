package net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.Dto2BirtReportDatasourceConfigGenerator;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BirtReportDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BirtReportDatasourceConfigGenerator implements Dto2PosoGenerator<BirtReportDatasourceConfigDto,BirtReportDatasourceConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BirtReportDatasourceConfigGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public BirtReportDatasourceConfig loadPoso(BirtReportDatasourceConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		BirtReportDatasourceConfig poso = entityManager.find(BirtReportDatasourceConfig.class, id);
		return poso;
	}

	public BirtReportDatasourceConfig instantiatePoso()  {
		BirtReportDatasourceConfig poso = new BirtReportDatasourceConfig();
		return poso;
	}

	public BirtReportDatasourceConfig createPoso(BirtReportDatasourceConfigDto dto)  throws ExpectedException {
		BirtReportDatasourceConfig poso = new BirtReportDatasourceConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BirtReportDatasourceConfig createUnmanagedPoso(BirtReportDatasourceConfigDto dto)  throws ExpectedException {
		BirtReportDatasourceConfig poso = new BirtReportDatasourceConfig();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

		/*  set report */
		BirtReportDto tmpDto_report = dto.getReport();
		if(null != tmpDto_report && null != tmpDto_report.getId()){
			if(null != poso.getReport() && null != poso.getReport().getId() && ! poso.getReport().getId().equals(tmpDto_report.getId())){
				BirtReport newPropertyValue = (BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), newPropertyValue, "report");
				poso.setReport(newPropertyValue);
			} else if(null == poso.getReport()){
				poso.setReport((BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report));
			}
		} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (report)");
					poso.setReport((BirtReport)refPoso);
				}
			});
		} else if(null == tmpDto_report){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), null, "report");
			poso.setReport(null);
		}

		/*  set target */
		poso.setTarget(dto.getTarget() );

		/*  set targetType */
		BirtReportDatasourceTargetTypeDto tmpDto_targetType = dto.getTargetType();
		poso.setTargetType((BirtReportDatasourceTargetType)dtoServiceProvider.get().createPoso(tmpDto_targetType));

	}

	protected void mergeProxy2Poso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

		/*  set report */
		if(dto.isReportModified()){
			BirtReportDto tmpDto_report = dto.getReport();
			if(null != tmpDto_report && null != tmpDto_report.getId()){
				if(null != poso.getReport() && null != poso.getReport().getId() && ! poso.getReport().getId().equals(tmpDto_report.getId())){
					BirtReport newPropertyValue = (BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), newPropertyValue, "report");
					poso.setReport(newPropertyValue);
				} else if(null == poso.getReport()){
					poso.setReport((BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report));
				}
			} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (report)");
						poso.setReport((BirtReport)refPoso);
					}
			});
			} else if(null == tmpDto_report){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReport(), null, "report");
				poso.setReport(null);
			}
		}

		/*  set target */
		if(dto.isTargetModified()){
			poso.setTarget(dto.getTarget() );
		}

		/*  set targetType */
		if(dto.isTargetTypeModified()){
			BirtReportDatasourceTargetTypeDto tmpDto_targetType = dto.getTargetType();
			poso.setTargetType((BirtReportDatasourceTargetType)dtoServiceProvider.get().createPoso(tmpDto_targetType));
		}

	}

	public void mergeUnmanagedPoso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		/*  set queryWrapper */
		poso.setQueryWrapper(dto.getQueryWrapper() );

		/*  set report */
		BirtReportDto tmpDto_report = dto.getReport();
		if(null != tmpDto_report && null != tmpDto_report.getId()){
			BirtReport newPropertyValue = (BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report);
			poso.setReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setReport((BirtReport)refPoso);
				}
			});
		} else if(null != tmpDto_report && null == tmpDto_report.getId()){
			final BirtReportDto tmpDto_report_final = tmpDto_report;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setReport((BirtReport)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_report_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setReport((BirtReport)refPoso);
					}
				}
			});
		} else if(null == tmpDto_report){
			poso.setReport(null);
		}

		/*  set target */
		poso.setTarget(dto.getTarget() );

		/*  set targetType */
		BirtReportDatasourceTargetTypeDto tmpDto_targetType = dto.getTargetType();
		poso.setTargetType((BirtReportDatasourceTargetType)dtoServiceProvider.get().createPoso(tmpDto_targetType));

	}

	protected void mergeProxy2UnmanagedPoso(BirtReportDatasourceConfigDto dto, final BirtReportDatasourceConfig poso)  throws ExpectedException {
		/*  set queryWrapper */
		if(dto.isQueryWrapperModified()){
			poso.setQueryWrapper(dto.getQueryWrapper() );
		}

		/*  set report */
		if(dto.isReportModified()){
			BirtReportDto tmpDto_report = dto.getReport();
			if(null != tmpDto_report && null != tmpDto_report.getId()){
				BirtReport newPropertyValue = (BirtReport)dtoServiceProvider.get().loadPoso(tmpDto_report);
				poso.setReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setReport((BirtReport)refPoso);
					}
			});
			} else if(null != tmpDto_report && null == tmpDto_report.getId()){
				final BirtReportDto tmpDto_report_final = tmpDto_report;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_report, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setReport((BirtReport)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_report_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setReport((BirtReport)refPoso);
						}
					}
			});
			} else if(null == tmpDto_report){
				poso.setReport(null);
			}
		}

		/*  set target */
		if(dto.isTargetModified()){
			poso.setTarget(dto.getTarget() );
		}

		/*  set targetType */
		if(dto.isTargetTypeModified()){
			BirtReportDatasourceTargetTypeDto tmpDto_targetType = dto.getTargetType();
			poso.setTargetType((BirtReportDatasourceTargetType)dtoServiceProvider.get().createPoso(tmpDto_targetType));
		}

	}

	public BirtReportDatasourceConfig loadAndMergePoso(BirtReportDatasourceConfigDto dto)  throws ExpectedException {
		BirtReportDatasourceConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BirtReportDatasourceConfigDto dto, BirtReportDatasourceConfig poso)  {
	}


	public void postProcessCreateUnmanaged(BirtReportDatasourceConfigDto dto, BirtReportDatasourceConfig poso)  {
	}


	public void postProcessLoad(BirtReportDatasourceConfigDto dto, BirtReportDatasourceConfig poso)  {
	}


	public void postProcessMerge(BirtReportDatasourceConfigDto dto, BirtReportDatasourceConfig poso)  {
	}


	public void postProcessInstantiate(BirtReportDatasourceConfig poso)  {
	}



}
