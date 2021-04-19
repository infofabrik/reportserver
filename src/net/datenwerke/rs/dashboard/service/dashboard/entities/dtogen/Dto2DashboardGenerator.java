package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.LayoutType;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for Dashboard
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DashboardGenerator implements Dto2PosoGenerator<DashboardDto,Dashboard> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DashboardGenerator(
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

	public Dashboard loadPoso(DashboardDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		Dashboard poso = entityManager.find(Dashboard.class, id);
		return poso;
	}

	public Dashboard instantiatePoso()  {
		Dashboard poso = new Dashboard();
		return poso;
	}

	public Dashboard createPoso(DashboardDto dto)  throws ExpectedException {
		Dashboard poso = new Dashboard();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Dashboard createUnmanagedPoso(DashboardDto dto)  throws ExpectedException {
		Dashboard poso = new Dashboard();

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

	public void mergePoso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		/*  set dadgets */
		final List<Dadget> col_dadgets = new ArrayList<Dadget>();
		/* load new data from dto */
		Collection<DadgetDto> tmpCol_dadgets = dto.getDadgets();

		/* load current data from poso */
		if(null != poso.getDadgets())
			col_dadgets.addAll(poso.getDadgets());

		/* remove non existing data */
		List<Dadget> remDto_dadgets = new ArrayList<Dadget>();
		for(Dadget ref : col_dadgets){
			boolean found = false;
			for(DadgetDto refDto : tmpCol_dadgets){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_dadgets.add(ref);
		}
		for(Dadget ref : remDto_dadgets)
			col_dadgets.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_dadgets, "dadgets");

		/* merge or add data */
		List<Dadget> new_col_dadgets = new ArrayList<Dadget>();
		for(DadgetDto refDto : tmpCol_dadgets){
			boolean found = false;
			for(Dadget ref : col_dadgets){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_dadgets.add((Dadget) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_dadgets.add((Dadget) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(dadgets) ");
		}
		poso.setDadgets(new_col_dadgets);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set layout */
		LayoutTypeDto tmpDto_layout = dto.getLayout();
		poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set primary */
		try{
			poso.setPrimary(dto.isPrimary() );
		} catch(NullPointerException e){
		}

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

		/*  set singlePage */
		try{
			poso.setSinglePage(dto.isSinglePage() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		/*  set dadgets */
		if(dto.isDadgetsModified()){
			final List<Dadget> col_dadgets = new ArrayList<Dadget>();
			/* load new data from dto */
			Collection<DadgetDto> tmpCol_dadgets = null;
			tmpCol_dadgets = dto.getDadgets();

			/* load current data from poso */
			if(null != poso.getDadgets())
				col_dadgets.addAll(poso.getDadgets());

			/* remove non existing data */
			List<Dadget> remDto_dadgets = new ArrayList<Dadget>();
			for(Dadget ref : col_dadgets){
				boolean found = false;
				for(DadgetDto refDto : tmpCol_dadgets){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_dadgets.add(ref);
			}
			for(Dadget ref : remDto_dadgets)
				col_dadgets.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_dadgets, "dadgets");

			/* merge or add data */
			List<Dadget> new_col_dadgets = new ArrayList<Dadget>();
			for(DadgetDto refDto : tmpCol_dadgets){
				boolean found = false;
				for(Dadget ref : col_dadgets){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_dadgets.add((Dadget) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_dadgets.add((Dadget) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(dadgets) ");
			}
			poso.setDadgets(new_col_dadgets);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set layout */
		if(dto.isLayoutModified()){
			LayoutTypeDto tmpDto_layout = dto.getLayout();
			poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set primary */
		if(dto.isPrimaryModified()){
			try{
				poso.setPrimary(dto.isPrimary() );
			} catch(NullPointerException e){
			}
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

		/*  set singlePage */
		if(dto.isSinglePageModified()){
			try{
				poso.setSinglePage(dto.isSinglePage() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		/*  set dadgets */
		final List<Dadget> col_dadgets = new ArrayList<Dadget>();
		/* load new data from dto */
		Collection<DadgetDto> tmpCol_dadgets = dto.getDadgets();

		/* merge or add data */
		for(DadgetDto refDto : tmpCol_dadgets){
			col_dadgets.add((Dadget) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setDadgets(col_dadgets);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set layout */
		LayoutTypeDto tmpDto_layout = dto.getLayout();
		poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set primary */
		try{
			poso.setPrimary(dto.isPrimary() );
		} catch(NullPointerException e){
		}

		/*  set reloadInterval */
		try{
			poso.setReloadInterval(dto.getReloadInterval() );
		} catch(NullPointerException e){
		}

		/*  set singlePage */
		try{
			poso.setSinglePage(dto.isSinglePage() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(DashboardDto dto, final Dashboard poso)  throws ExpectedException {
		/*  set dadgets */
		if(dto.isDadgetsModified()){
			final List<Dadget> col_dadgets = new ArrayList<Dadget>();
			/* load new data from dto */
			Collection<DadgetDto> tmpCol_dadgets = null;
			tmpCol_dadgets = dto.getDadgets();

			/* merge or add data */
			for(DadgetDto refDto : tmpCol_dadgets){
				col_dadgets.add((Dadget) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setDadgets(col_dadgets);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set layout */
		if(dto.isLayoutModified()){
			LayoutTypeDto tmpDto_layout = dto.getLayout();
			poso.setLayout((LayoutType)dtoServiceProvider.get().createPoso(tmpDto_layout));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set primary */
		if(dto.isPrimaryModified()){
			try{
				poso.setPrimary(dto.isPrimary() );
			} catch(NullPointerException e){
			}
		}

		/*  set reloadInterval */
		if(dto.isReloadIntervalModified()){
			try{
				poso.setReloadInterval(dto.getReloadInterval() );
			} catch(NullPointerException e){
			}
		}

		/*  set singlePage */
		if(dto.isSinglePageModified()){
			try{
				poso.setSinglePage(dto.isSinglePage() );
			} catch(NullPointerException e){
			}
		}

	}

	public Dashboard loadAndMergePoso(DashboardDto dto)  throws ExpectedException {
		Dashboard poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DashboardDto dto, Dashboard poso)  {
	}


	public void postProcessCreateUnmanaged(DashboardDto dto, Dashboard poso)  {
	}


	public void postProcessLoad(DashboardDto dto, Dashboard poso)  {
	}


	public void postProcessMerge(DashboardDto dto, Dashboard poso)  {
	}


	public void postProcessInstantiate(Dashboard poso)  {
	}



}
