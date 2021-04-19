package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
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
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.AppProperty;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceAppGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TeamSpaceApp
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TeamSpaceAppGenerator implements Dto2PosoGenerator<TeamSpaceAppDto,TeamSpaceApp> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TeamSpaceAppGenerator(
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

	public TeamSpaceApp loadPoso(TeamSpaceAppDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TeamSpaceApp poso = entityManager.find(TeamSpaceApp.class, id);
		return poso;
	}

	public TeamSpaceApp instantiatePoso()  {
		TeamSpaceApp poso = new TeamSpaceApp();
		return poso;
	}

	public TeamSpaceApp createPoso(TeamSpaceAppDto dto)  throws ExpectedException {
		TeamSpaceApp poso = new TeamSpaceApp();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TeamSpaceApp createUnmanagedPoso(TeamSpaceAppDto dto)  throws ExpectedException {
		TeamSpaceApp poso = new TeamSpaceApp();

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

	public void mergePoso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		/*  set appProperties */
		final Set<AppProperty> col_appProperties = new HashSet<AppProperty>();
		/* load new data from dto */
		Collection<AppPropertyDto> tmpCol_appProperties = dto.getAppProperties();

		/* load current data from poso */
		if(null != poso.getAppProperties())
			col_appProperties.addAll(poso.getAppProperties());

		/* remove non existing data */
		Set<AppProperty> remDto_appProperties = new HashSet<AppProperty>();
		for(AppProperty ref : col_appProperties){
			boolean found = false;
			for(AppPropertyDto refDto : tmpCol_appProperties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_appProperties.add(ref);
		}
		for(AppProperty ref : remDto_appProperties)
			col_appProperties.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_appProperties, "appProperties");

		/* merge or add data */
		Set<AppProperty> new_col_appProperties = new HashSet<AppProperty>();
		for(AppPropertyDto refDto : tmpCol_appProperties){
			boolean found = false;
			for(AppProperty ref : col_appProperties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_appProperties.add((AppProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_appProperties.add((AppProperty) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(appProperties) ");
		}
		poso.setAppProperties(new_col_appProperties);

		/*  set installed */
		poso.setInstalled(dto.isInstalled() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2Poso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		/*  set appProperties */
		if(dto.isAppPropertiesModified()){
			final Set<AppProperty> col_appProperties = new HashSet<AppProperty>();
			/* load new data from dto */
			Collection<AppPropertyDto> tmpCol_appProperties = null;
			tmpCol_appProperties = dto.getAppProperties();

			/* load current data from poso */
			if(null != poso.getAppProperties())
				col_appProperties.addAll(poso.getAppProperties());

			/* remove non existing data */
			Set<AppProperty> remDto_appProperties = new HashSet<AppProperty>();
			for(AppProperty ref : col_appProperties){
				boolean found = false;
				for(AppPropertyDto refDto : tmpCol_appProperties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_appProperties.add(ref);
			}
			for(AppProperty ref : remDto_appProperties)
				col_appProperties.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_appProperties, "appProperties");

			/* merge or add data */
			Set<AppProperty> new_col_appProperties = new HashSet<AppProperty>();
			for(AppPropertyDto refDto : tmpCol_appProperties){
				boolean found = false;
				for(AppProperty ref : col_appProperties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_appProperties.add((AppProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_appProperties.add((AppProperty) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(appProperties) ");
			}
			poso.setAppProperties(new_col_appProperties);
		}

		/*  set installed */
		if(dto.isInstalledModified()){
			poso.setInstalled(dto.isInstalled() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public void mergeUnmanagedPoso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		/*  set appProperties */
		final Set<AppProperty> col_appProperties = new HashSet<AppProperty>();
		/* load new data from dto */
		Collection<AppPropertyDto> tmpCol_appProperties = dto.getAppProperties();

		/* merge or add data */
		for(AppPropertyDto refDto : tmpCol_appProperties){
			col_appProperties.add((AppProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setAppProperties(col_appProperties);

		/*  set installed */
		poso.setInstalled(dto.isInstalled() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2UnmanagedPoso(TeamSpaceAppDto dto, final TeamSpaceApp poso)  throws ExpectedException {
		/*  set appProperties */
		if(dto.isAppPropertiesModified()){
			final Set<AppProperty> col_appProperties = new HashSet<AppProperty>();
			/* load new data from dto */
			Collection<AppPropertyDto> tmpCol_appProperties = null;
			tmpCol_appProperties = dto.getAppProperties();

			/* merge or add data */
			for(AppPropertyDto refDto : tmpCol_appProperties){
				col_appProperties.add((AppProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setAppProperties(col_appProperties);
		}

		/*  set installed */
		if(dto.isInstalledModified()){
			poso.setInstalled(dto.isInstalled() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public TeamSpaceApp loadAndMergePoso(TeamSpaceAppDto dto)  throws ExpectedException {
		TeamSpaceApp poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TeamSpaceAppDto dto, TeamSpaceApp poso)  {
	}


	public void postProcessCreateUnmanaged(TeamSpaceAppDto dto, TeamSpaceApp poso)  {
	}


	public void postProcessLoad(TeamSpaceAppDto dto, TeamSpaceApp poso)  {
	}


	public void postProcessMerge(TeamSpaceAppDto dto, TeamSpaceApp poso)  {
	}


	public void postProcessInstantiate(TeamSpaceApp poso)  {
	}



}
