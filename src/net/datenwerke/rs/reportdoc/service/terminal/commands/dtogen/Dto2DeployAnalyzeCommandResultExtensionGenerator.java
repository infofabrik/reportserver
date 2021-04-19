package net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto;
import net.datenwerke.rs.reportdoc.service.terminal.commands.DeployAnalyzeCommandResultExtension;
import net.datenwerke.rs.reportdoc.service.terminal.commands.dtogen.Dto2DeployAnalyzeCommandResultExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DeployAnalyzeCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DeployAnalyzeCommandResultExtensionGenerator implements Dto2PosoGenerator<DeployAnalyzeCommandResultExtensionDto,DeployAnalyzeCommandResultExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DeployAnalyzeCommandResultExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public DeployAnalyzeCommandResultExtension loadPoso(DeployAnalyzeCommandResultExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public DeployAnalyzeCommandResultExtension instantiatePoso()  {
		DeployAnalyzeCommandResultExtension poso = new DeployAnalyzeCommandResultExtension();
		return poso;
	}

	public DeployAnalyzeCommandResultExtension createPoso(DeployAnalyzeCommandResultExtensionDto dto)  throws ExpectedException {
		DeployAnalyzeCommandResultExtension poso = new DeployAnalyzeCommandResultExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DeployAnalyzeCommandResultExtension createUnmanagedPoso(DeployAnalyzeCommandResultExtensionDto dto)  throws ExpectedException {
		DeployAnalyzeCommandResultExtension poso = new DeployAnalyzeCommandResultExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		/*  set ignoreCase */
		try{
			poso.setIgnoreCase(dto.isIgnoreCase() );
		} catch(NullPointerException e){
		}

		/*  set leftReport */
		ReportDto tmpDto_leftReport = dto.getLeftReport();
		if(null != tmpDto_leftReport && null != tmpDto_leftReport.getId()){
			if(null != poso.getLeftReport() && null != poso.getLeftReport().getId() && ! poso.getLeftReport().getId().equals(tmpDto_leftReport.getId())){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getLeftReport(), newPropertyValue, "leftReport");
				poso.setLeftReport(newPropertyValue);
			} else if(null == poso.getLeftReport()){
				poso.setLeftReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport));
			}
		} else if(null != tmpDto_leftReport && null == tmpDto_leftReport.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (leftReport)");
					poso.setLeftReport((Report)refPoso);
				}
			});
		} else if(null == tmpDto_leftReport){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getLeftReport(), null, "leftReport");
			poso.setLeftReport(null);
		}

		/*  set rightReport */
		ReportDto tmpDto_rightReport = dto.getRightReport();
		if(null != tmpDto_rightReport && null != tmpDto_rightReport.getId()){
			if(null != poso.getRightReport() && null != poso.getRightReport().getId() && ! poso.getRightReport().getId().equals(tmpDto_rightReport.getId())){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRightReport(), newPropertyValue, "rightReport");
				poso.setRightReport(newPropertyValue);
			} else if(null == poso.getRightReport()){
				poso.setRightReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport));
			}
		} else if(null != tmpDto_rightReport && null == tmpDto_rightReport.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (rightReport)");
					poso.setRightReport((Report)refPoso);
				}
			});
		} else if(null == tmpDto_rightReport){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRightReport(), null, "rightReport");
			poso.setRightReport(null);
		}

	}

	protected void mergeProxy2Poso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		/*  set ignoreCase */
		if(dto.isIgnoreCaseModified()){
			try{
				poso.setIgnoreCase(dto.isIgnoreCase() );
			} catch(NullPointerException e){
			}
		}

		/*  set leftReport */
		if(dto.isLeftReportModified()){
			ReportDto tmpDto_leftReport = dto.getLeftReport();
			if(null != tmpDto_leftReport && null != tmpDto_leftReport.getId()){
				if(null != poso.getLeftReport() && null != poso.getLeftReport().getId() && ! poso.getLeftReport().getId().equals(tmpDto_leftReport.getId())){
					Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getLeftReport(), newPropertyValue, "leftReport");
					poso.setLeftReport(newPropertyValue);
				} else if(null == poso.getLeftReport()){
					poso.setLeftReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport));
				}
			} else if(null != tmpDto_leftReport && null == tmpDto_leftReport.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (leftReport)");
						poso.setLeftReport((Report)refPoso);
					}
			});
			} else if(null == tmpDto_leftReport){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getLeftReport(), null, "leftReport");
				poso.setLeftReport(null);
			}
		}

		/*  set rightReport */
		if(dto.isRightReportModified()){
			ReportDto tmpDto_rightReport = dto.getRightReport();
			if(null != tmpDto_rightReport && null != tmpDto_rightReport.getId()){
				if(null != poso.getRightReport() && null != poso.getRightReport().getId() && ! poso.getRightReport().getId().equals(tmpDto_rightReport.getId())){
					Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRightReport(), newPropertyValue, "rightReport");
					poso.setRightReport(newPropertyValue);
				} else if(null == poso.getRightReport()){
					poso.setRightReport((Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport));
				}
			} else if(null != tmpDto_rightReport && null == tmpDto_rightReport.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (rightReport)");
						poso.setRightReport((Report)refPoso);
					}
			});
			} else if(null == tmpDto_rightReport){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getRightReport(), null, "rightReport");
				poso.setRightReport(null);
			}
		}

	}

	public void mergeUnmanagedPoso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		/*  set ignoreCase */
		try{
			poso.setIgnoreCase(dto.isIgnoreCase() );
		} catch(NullPointerException e){
		}

		/*  set leftReport */
		ReportDto tmpDto_leftReport = dto.getLeftReport();
		if(null != tmpDto_leftReport && null != tmpDto_leftReport.getId()){
			Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport);
			poso.setLeftReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setLeftReport((Report)refPoso);
				}
			});
		} else if(null != tmpDto_leftReport && null == tmpDto_leftReport.getId()){
			final ReportDto tmpDto_leftReport_final = tmpDto_leftReport;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setLeftReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_leftReport_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setLeftReport((Report)refPoso);
					}
				}
			});
		} else if(null == tmpDto_leftReport){
			poso.setLeftReport(null);
		}

		/*  set rightReport */
		ReportDto tmpDto_rightReport = dto.getRightReport();
		if(null != tmpDto_rightReport && null != tmpDto_rightReport.getId()){
			Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport);
			poso.setRightReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setRightReport((Report)refPoso);
				}
			});
		} else if(null != tmpDto_rightReport && null == tmpDto_rightReport.getId()){
			final ReportDto tmpDto_rightReport_final = tmpDto_rightReport;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setRightReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_rightReport_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setRightReport((Report)refPoso);
					}
				}
			});
		} else if(null == tmpDto_rightReport){
			poso.setRightReport(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(DeployAnalyzeCommandResultExtensionDto dto, final DeployAnalyzeCommandResultExtension poso)  throws ExpectedException {
		/*  set ignoreCase */
		if(dto.isIgnoreCaseModified()){
			try{
				poso.setIgnoreCase(dto.isIgnoreCase() );
			} catch(NullPointerException e){
			}
		}

		/*  set leftReport */
		if(dto.isLeftReportModified()){
			ReportDto tmpDto_leftReport = dto.getLeftReport();
			if(null != tmpDto_leftReport && null != tmpDto_leftReport.getId()){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_leftReport);
				poso.setLeftReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setLeftReport((Report)refPoso);
					}
			});
			} else if(null != tmpDto_leftReport && null == tmpDto_leftReport.getId()){
				final ReportDto tmpDto_leftReport_final = tmpDto_leftReport;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_leftReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setLeftReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_leftReport_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setLeftReport((Report)refPoso);
						}
					}
			});
			} else if(null == tmpDto_leftReport){
				poso.setLeftReport(null);
			}
		}

		/*  set rightReport */
		if(dto.isRightReportModified()){
			ReportDto tmpDto_rightReport = dto.getRightReport();
			if(null != tmpDto_rightReport && null != tmpDto_rightReport.getId()){
				Report newPropertyValue = (Report)dtoServiceProvider.get().loadPoso(tmpDto_rightReport);
				poso.setRightReport(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setRightReport((Report)refPoso);
					}
			});
			} else if(null != tmpDto_rightReport && null == tmpDto_rightReport.getId()){
				final ReportDto tmpDto_rightReport_final = tmpDto_rightReport;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_rightReport, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setRightReport((Report)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_rightReport_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setRightReport((Report)refPoso);
						}
					}
			});
			} else if(null == tmpDto_rightReport){
				poso.setRightReport(null);
			}
		}

	}

	public DeployAnalyzeCommandResultExtension loadAndMergePoso(DeployAnalyzeCommandResultExtensionDto dto)  throws ExpectedException {
		DeployAnalyzeCommandResultExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DeployAnalyzeCommandResultExtensionDto dto, DeployAnalyzeCommandResultExtension poso)  {
	}


	public void postProcessCreateUnmanaged(DeployAnalyzeCommandResultExtensionDto dto, DeployAnalyzeCommandResultExtension poso)  {
	}


	public void postProcessLoad(DeployAnalyzeCommandResultExtensionDto dto, DeployAnalyzeCommandResultExtension poso)  {
	}


	public void postProcessMerge(DeployAnalyzeCommandResultExtensionDto dto, DeployAnalyzeCommandResultExtension poso)  {
	}


	public void postProcessInstantiate(DeployAnalyzeCommandResultExtension poso)  {
	}



}
