package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardNodeGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DashboardNode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DashboardNodeGenerator implements Dto2PosoGenerator<DashboardNodeDto,DashboardNode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DashboardNodeGenerator(
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

	public DashboardNode loadPoso(DashboardNodeDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DashboardNode poso = entityManager.find(DashboardNode.class, id);
		return poso;
	}

	public DashboardNode instantiatePoso()  {
		DashboardNode poso = new DashboardNode();
		return poso;
	}

	public DashboardNode createPoso(DashboardNodeDto dto)  throws ExpectedException {
		DashboardNode poso = new DashboardNode();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DashboardNode createUnmanagedPoso(DashboardNodeDto dto)  throws ExpectedException {
		DashboardNode poso = new DashboardNode();

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

	public void mergePoso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		/*  set dashboard */
		DashboardDto tmpDto_dashboard = dto.getDashboard();
		if(null != tmpDto_dashboard && null != tmpDto_dashboard.getId()){
			if(null != poso.getDashboard() && null != poso.getDashboard().getId() && poso.getDashboard().getId().equals(tmpDto_dashboard.getId()))
				poso.setDashboard((Dashboard)dtoServiceProvider.get().loadAndMergePoso(tmpDto_dashboard));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (dashboard)");
		} else if(null != poso.getDashboard()){
			Dashboard newPropertyValue = (Dashboard)dtoServiceProvider.get().createPoso(tmpDto_dashboard);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDashboard(), newPropertyValue, "dashboard");
			poso.setDashboard(newPropertyValue);
		} else {
			poso.setDashboard((Dashboard)dtoServiceProvider.get().createPoso(tmpDto_dashboard));
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		/*  set dashboard */
		if(dto.isDashboardModified()){
			DashboardDto tmpDto_dashboard = dto.getDashboard();
			if(null != tmpDto_dashboard && null != tmpDto_dashboard.getId()){
				if(null != poso.getDashboard() && null != poso.getDashboard().getId() && poso.getDashboard().getId().equals(tmpDto_dashboard.getId()))
					poso.setDashboard((Dashboard)dtoServiceProvider.get().loadAndMergePoso(tmpDto_dashboard));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (dashboard)");
			} else if(null != poso.getDashboard()){
				Dashboard newPropertyValue = (Dashboard)dtoServiceProvider.get().createPoso(tmpDto_dashboard);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDashboard(), newPropertyValue, "dashboard");
				poso.setDashboard(newPropertyValue);
			} else {
				poso.setDashboard((Dashboard)dtoServiceProvider.get().createPoso(tmpDto_dashboard));
			}
		}

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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		/*  set dashboard */
		DashboardDto tmpDto_dashboard = dto.getDashboard();
		poso.setDashboard((Dashboard)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dashboard));

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(DashboardNodeDto dto, final DashboardNode poso)  throws ExpectedException {
		/*  set dashboard */
		if(dto.isDashboardModified()){
			DashboardDto tmpDto_dashboard = dto.getDashboard();
			poso.setDashboard((Dashboard)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dashboard));
		}

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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public DashboardNode loadAndMergePoso(DashboardNodeDto dto)  throws ExpectedException {
		DashboardNode poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DashboardNodeDto dto, DashboardNode poso)  {
	}


	public void postProcessCreateUnmanaged(DashboardNodeDto dto, DashboardNode poso)  {
	}


	public void postProcessLoad(DashboardNodeDto dto, DashboardNode poso)  {
	}


	public void postProcessMerge(DashboardNodeDto dto, DashboardNode poso)  {
	}


	public void postProcessInstantiate(DashboardNode poso)  {
	}



}
