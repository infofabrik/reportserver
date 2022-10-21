package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.dtogen.Dto2TsDiskReportReferenceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TsDiskReportReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TsDiskReportReferenceGenerator implements Dto2PosoGenerator<TsDiskReportReferenceDto,TsDiskReportReference> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TsDiskReportReferenceGenerator(
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

	public TsDiskReportReference loadPoso(TsDiskReportReferenceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TsDiskReportReference poso = entityManager.find(TsDiskReportReference.class, id);
		return poso;
	}

	public TsDiskReportReference instantiatePoso()  {
		TsDiskReportReference poso = new TsDiskReportReference();
		return poso;
	}

	public TsDiskReportReference createPoso(TsDiskReportReferenceDto dto)  throws ExpectedException {
		TsDiskReportReference poso = new TsDiskReportReference();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TsDiskReportReference createUnmanagedPoso(TsDiskReportReferenceDto dto)  throws ExpectedException {
		TsDiskReportReference poso = new TsDiskReportReference();

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

	public void mergePoso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set hardlink */
		poso.setHardlink(dto.isHardlink() );

		/*  set name */
		poso.setName(dto.getName() );

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

	protected void mergeProxy2Poso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set hardlink */
		if(dto.isHardlinkModified()){
			poso.setHardlink(dto.isHardlink() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
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

	public void mergeUnmanagedPoso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set hardlink */
		poso.setHardlink(dto.isHardlink() );

		/*  set name */
		poso.setName(dto.getName() );

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

	protected void mergeProxy2UnmanagedPoso(TsDiskReportReferenceDto dto, final TsDiskReportReference poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set hardlink */
		if(dto.isHardlinkModified()){
			poso.setHardlink(dto.isHardlink() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
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

	public TsDiskReportReference loadAndMergePoso(TsDiskReportReferenceDto dto)  throws ExpectedException {
		TsDiskReportReference poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TsDiskReportReferenceDto dto, TsDiskReportReference poso)  {
	}


	public void postProcessCreateUnmanaged(TsDiskReportReferenceDto dto, TsDiskReportReference poso)  {
	}


	public void postProcessLoad(TsDiskReportReferenceDto dto, TsDiskReportReference poso)  {
	}


	public void postProcessMerge(TsDiskReportReferenceDto dto, TsDiskReportReference poso)  {
	}


	public void postProcessInstantiate(TsDiskReportReference poso)  {
	}



}
