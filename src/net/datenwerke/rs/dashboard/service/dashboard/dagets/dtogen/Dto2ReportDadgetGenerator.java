package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.ReportDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2ReportDadgetGenerator;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetContainer;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ReportDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportDadgetGenerator implements Dto2PosoGenerator<ReportDadgetDto,ReportDadget> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportDadgetGenerator(
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

	public ReportDadget loadPoso(ReportDadgetDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ReportDadget poso = entityManager.find(ReportDadget.class, id);
		return poso;
	}

	public ReportDadget instantiatePoso()  {
		ReportDadget poso = new ReportDadget();
		return poso;
	}

	public ReportDadget createPoso(ReportDadgetDto dto)  throws ExpectedException {
		ReportDadget poso = new ReportDadget();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportDadget createUnmanagedPoso(ReportDadgetDto dto)  throws ExpectedException {
		ReportDadget poso = new ReportDadget();

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

	public void mergePoso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		/*  set col */
		try{
			poso.setCol(dto.getCol() );
		} catch(NullPointerException e){
		}

		/*  set config */
		poso.setConfig(dto.getConfig() );

		/*  set container */
		DadgetContainerDto tmpDto_container = dto.getContainer();
		poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* load current data from poso */
		if(null != poso.getParameterInstances())
			col_parameterInstances.addAll(poso.getParameterInstances());

		/* remove non existing data */
		Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstance ref : col_parameterInstances){
			boolean found = false;
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_parameterInstances.add(ref);
		}
		for(ParameterInstance ref : remDto_parameterInstances)
			col_parameterInstances.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

		/* merge or add data */
		Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			boolean found = false;
			for(ParameterInstance ref : col_parameterInstances){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
		}
		poso.setParameterInstances(new_col_parameterInstances);

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

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

		/*  set reportReference */
		TsDiskReportReferenceDto tmpDto_reportReference = dto.getReportReference();
		if(null != tmpDto_reportReference && null != tmpDto_reportReference.getId()){
			if(null != poso.getReportReference() && null != poso.getReportReference().getId() && ! poso.getReportReference().getId().equals(tmpDto_reportReference.getId())){
				TsDiskReportReference newPropertyValue = (TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReportReference(), newPropertyValue, "reportReference");
				poso.setReportReference(newPropertyValue);
			} else if(null == poso.getReportReference()){
				poso.setReportReference((TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference));
			}
		} else if(null != tmpDto_reportReference && null == tmpDto_reportReference.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (reportReference)");
					poso.setReportReference((TsDiskReportReference)refPoso);
				}
			});
		} else if(null == tmpDto_reportReference){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReportReference(), null, "reportReference");
			poso.setReportReference(null);
		}

		/*  set showExecuteButton */
		try{
			poso.setShowExecuteButton(dto.isShowExecuteButton() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		/*  set col */
		if(dto.isColModified()){
			try{
				poso.setCol(dto.getCol() );
			} catch(NullPointerException e){
			}
		}

		/*  set config */
		if(dto.isConfigModified()){
			poso.setConfig(dto.getConfig() );
		}

		/*  set container */
		if(dto.isContainerModified()){
			DadgetContainerDto tmpDto_container = dto.getContainer();
			poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* load current data from poso */
			if(null != poso.getParameterInstances())
				col_parameterInstances.addAll(poso.getParameterInstances());

			/* remove non existing data */
			Set<ParameterInstance> remDto_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstance ref : col_parameterInstances){
				boolean found = false;
				for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_parameterInstances.add(ref);
			}
			for(ParameterInstance ref : remDto_parameterInstances)
				col_parameterInstances.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_parameterInstances, "parameterInstances");

			/* merge or add data */
			Set<ParameterInstance> new_col_parameterInstances = new HashSet<ParameterInstance>();
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				boolean found = false;
				for(ParameterInstance ref : col_parameterInstances){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(parameterInstances) ");
			}
			poso.setParameterInstances(new_col_parameterInstances);
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
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

		/*  set reportReference */
		if(dto.isReportReferenceModified()){
			TsDiskReportReferenceDto tmpDto_reportReference = dto.getReportReference();
			if(null != tmpDto_reportReference && null != tmpDto_reportReference.getId()){
				if(null != poso.getReportReference() && null != poso.getReportReference().getId() && ! poso.getReportReference().getId().equals(tmpDto_reportReference.getId())){
					TsDiskReportReference newPropertyValue = (TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReportReference(), newPropertyValue, "reportReference");
					poso.setReportReference(newPropertyValue);
				} else if(null == poso.getReportReference()){
					poso.setReportReference((TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference));
				}
			} else if(null != tmpDto_reportReference && null == tmpDto_reportReference.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (reportReference)");
						poso.setReportReference((TsDiskReportReference)refPoso);
					}
			});
			} else if(null == tmpDto_reportReference){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReportReference(), null, "reportReference");
				poso.setReportReference(null);
			}
		}

		/*  set showExecuteButton */
		if(dto.isShowExecuteButtonModified()){
			try{
				poso.setShowExecuteButton(dto.isShowExecuteButton() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		/*  set col */
		try{
			poso.setCol(dto.getCol() );
		} catch(NullPointerException e){
		}

		/*  set config */
		poso.setConfig(dto.getConfig() );

		/*  set container */
		DadgetContainerDto tmpDto_container = dto.getContainer();
		poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set parameterInstances */
		final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
		/* load new data from dto */
		Collection<ParameterInstanceDto> tmpCol_parameterInstances = dto.getParameterInstances();

		/* merge or add data */
		for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
			col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setParameterInstances(col_parameterInstances);

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

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

		/*  set reportReference */
		TsDiskReportReferenceDto tmpDto_reportReference = dto.getReportReference();
		if(null != tmpDto_reportReference && null != tmpDto_reportReference.getId()){
			TsDiskReportReference newPropertyValue = (TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference);
			poso.setReportReference(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setReportReference((TsDiskReportReference)refPoso);
				}
			});
		} else if(null != tmpDto_reportReference && null == tmpDto_reportReference.getId()){
			final TsDiskReportReferenceDto tmpDto_reportReference_final = tmpDto_reportReference;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setReportReference((TsDiskReportReference)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_reportReference_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setReportReference((TsDiskReportReference)refPoso);
					}
				}
			});
		} else if(null == tmpDto_reportReference){
			poso.setReportReference(null);
		}

		/*  set showExecuteButton */
		try{
			poso.setShowExecuteButton(dto.isShowExecuteButton() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(ReportDadgetDto dto, final ReportDadget poso)  throws ExpectedException {
		/*  set col */
		if(dto.isColModified()){
			try{
				poso.setCol(dto.getCol() );
			} catch(NullPointerException e){
			}
		}

		/*  set config */
		if(dto.isConfigModified()){
			poso.setConfig(dto.getConfig() );
		}

		/*  set container */
		if(dto.isContainerModified()){
			DadgetContainerDto tmpDto_container = dto.getContainer();
			poso.setContainer((DadgetContainer)dtoServiceProvider.get().createPoso(tmpDto_container));
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set parameterInstances */
		if(dto.isParameterInstancesModified()){
			final Set<ParameterInstance> col_parameterInstances = new HashSet<ParameterInstance>();
			/* load new data from dto */
			Collection<ParameterInstanceDto> tmpCol_parameterInstances = null;
			tmpCol_parameterInstances = dto.getParameterInstances();

			/* merge or add data */
			for(ParameterInstanceDto refDto : tmpCol_parameterInstances){
				col_parameterInstances.add((ParameterInstance) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setParameterInstances(col_parameterInstances);
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
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

		/*  set reportReference */
		if(dto.isReportReferenceModified()){
			TsDiskReportReferenceDto tmpDto_reportReference = dto.getReportReference();
			if(null != tmpDto_reportReference && null != tmpDto_reportReference.getId()){
				TsDiskReportReference newPropertyValue = (TsDiskReportReference)dtoServiceProvider.get().loadPoso(tmpDto_reportReference);
				poso.setReportReference(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setReportReference((TsDiskReportReference)refPoso);
					}
			});
			} else if(null != tmpDto_reportReference && null == tmpDto_reportReference.getId()){
				final TsDiskReportReferenceDto tmpDto_reportReference_final = tmpDto_reportReference;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_reportReference, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setReportReference((TsDiskReportReference)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_reportReference_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setReportReference((TsDiskReportReference)refPoso);
						}
					}
			});
			} else if(null == tmpDto_reportReference){
				poso.setReportReference(null);
			}
		}

		/*  set showExecuteButton */
		if(dto.isShowExecuteButtonModified()){
			try{
				poso.setShowExecuteButton(dto.isShowExecuteButton() );
			} catch(NullPointerException e){
			}
		}

	}

	public ReportDadget loadAndMergePoso(ReportDadgetDto dto)  throws ExpectedException {
		ReportDadget poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportDadgetDto dto, ReportDadget poso)  {
	}


	public void postProcessCreateUnmanaged(ReportDadgetDto dto, ReportDadget poso)  {
	}


	public void postProcessLoad(ReportDadgetDto dto, ReportDadget poso)  {
	}


	public void postProcessMerge(ReportDadgetDto dto, ReportDadget poso)  {
	}


	public void postProcessInstantiate(ReportDadget poso)  {
	}



}
