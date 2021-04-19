package net.datenwerke.security.service.usermanager.entities.dtogen;

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
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.security.service.usermanager.entities.dtogen.Dto2UserGenerator;

/**
 * Dto2PosoGenerator for User
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2UserGenerator implements Dto2PosoGenerator<UserDto,User> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2UserGenerator(
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

	public User loadPoso(UserDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		User poso = entityManager.find(User.class, id);
		return poso;
	}

	public User instantiatePoso()  {
		User poso = new User();
		return poso;
	}

	public User createPoso(UserDto dto)  throws ExpectedException {
		User poso = new User();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public User createUnmanagedPoso(UserDto dto)  throws ExpectedException {
		User poso = new User();

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

	public void mergePoso(UserDto dto, final User poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(UserDto dto, final User poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set email */
		poso.setEmail(dto.getEmail() );

		/*  set firstname */
		poso.setFirstname(dto.getFirstname() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set groups */
		final Set<Group> col_groups = new HashSet<Group>();
		/* load new data from dto */
		Collection<GroupDto> tmpCol_groups = dto.getGroups();

		for(GroupDto refDto : tmpCol_groups){
			if(null != refDto && null != refDto.getId())
				col_groups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (groups)");
						col_groups.add((Group) poso);
					}
					});
			}
		}
		poso.setGroups(col_groups);

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set lastname */
		poso.setLastname(dto.getLastname() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

		/*  set properties */
		final Set<UserProperty> col_properties = new HashSet<UserProperty>();
		/* load new data from dto */
		Collection<UserPropertyDto> tmpCol_properties = dto.getProperties();

		/* load current data from poso */
		if(null != poso.getProperties())
			col_properties.addAll(poso.getProperties());

		/* remove non existing data */
		Set<UserProperty> remDto_properties = new HashSet<UserProperty>();
		for(UserProperty ref : col_properties){
			boolean found = false;
			for(UserPropertyDto refDto : tmpCol_properties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_properties.add(ref);
		}
		for(UserProperty ref : remDto_properties)
			col_properties.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_properties, "properties");

		/* merge or add data */
		Set<UserProperty> new_col_properties = new HashSet<UserProperty>();
		for(UserPropertyDto refDto : tmpCol_properties){
			boolean found = false;
			for(UserProperty ref : col_properties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_properties.add((UserProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_properties.add((UserProperty) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(properties) ");
		}
		poso.setProperties(new_col_properties);

		/*  set sex */
		SexDto tmpDto_sex = dto.getSex();
		poso.setSex((Sex)dtoServiceProvider.get().createPoso(tmpDto_sex));

		/*  set superUser */
		poso.setSuperUser(dto.isSuperUser() );

		/*  set title */
		poso.setTitle(dto.getTitle() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(UserDto dto, final User poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set email */
		if(dto.isEmailModified()){
			poso.setEmail(dto.getEmail() );
		}

		/*  set firstname */
		if(dto.isFirstnameModified()){
			poso.setFirstname(dto.getFirstname() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set groups */
		if(dto.isGroupsModified()){
			final Set<Group> col_groups = new HashSet<Group>();
			/* load new data from dto */
			Collection<GroupDto> tmpCol_groups = null;
			tmpCol_groups = dto.getGroups();

			for(GroupDto refDto : tmpCol_groups){
				if(null != refDto && null != refDto.getId())
					col_groups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (groups)");
							col_groups.add((Group) poso);
						}
					});
				}
			}
			poso.setGroups(col_groups);
		}

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set lastname */
		if(dto.isLastnameModified()){
			poso.setLastname(dto.getLastname() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

		/*  set properties */
		if(dto.isPropertiesModified()){
			final Set<UserProperty> col_properties = new HashSet<UserProperty>();
			/* load new data from dto */
			Collection<UserPropertyDto> tmpCol_properties = null;
			tmpCol_properties = dto.getProperties();

			/* load current data from poso */
			if(null != poso.getProperties())
				col_properties.addAll(poso.getProperties());

			/* remove non existing data */
			Set<UserProperty> remDto_properties = new HashSet<UserProperty>();
			for(UserProperty ref : col_properties){
				boolean found = false;
				for(UserPropertyDto refDto : tmpCol_properties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_properties.add(ref);
			}
			for(UserProperty ref : remDto_properties)
				col_properties.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_properties, "properties");

			/* merge or add data */
			Set<UserProperty> new_col_properties = new HashSet<UserProperty>();
			for(UserPropertyDto refDto : tmpCol_properties){
				boolean found = false;
				for(UserProperty ref : col_properties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_properties.add((UserProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_properties.add((UserProperty) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(properties) ");
			}
			poso.setProperties(new_col_properties);
		}

		/*  set sex */
		if(dto.isSexModified()){
			SexDto tmpDto_sex = dto.getSex();
			poso.setSex((Sex)dtoServiceProvider.get().createPoso(tmpDto_sex));
		}

		/*  set superUser */
		if(dto.isSuperUserModified()){
			poso.setSuperUser(dto.isSuperUser() );
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(UserDto dto, final User poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(UserDto dto, final User poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set email */
		poso.setEmail(dto.getEmail() );

		/*  set firstname */
		poso.setFirstname(dto.getFirstname() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set groups */
		final Set<Group> col_groups = new HashSet<Group>();
		/* load new data from dto */
		Collection<GroupDto> tmpCol_groups = dto.getGroups();

		for(GroupDto refDto : tmpCol_groups){
			if(null != refDto && null != refDto.getId())
				col_groups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (groups)");
						col_groups.add((Group) poso);
					}
					});
			}
		}
		poso.setGroups(col_groups);

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set lastname */
		poso.setLastname(dto.getLastname() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

		/*  set properties */
		final Set<UserProperty> col_properties = new HashSet<UserProperty>();
		/* load new data from dto */
		Collection<UserPropertyDto> tmpCol_properties = dto.getProperties();

		/* merge or add data */
		for(UserPropertyDto refDto : tmpCol_properties){
			col_properties.add((UserProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setProperties(col_properties);

		/*  set sex */
		SexDto tmpDto_sex = dto.getSex();
		poso.setSex((Sex)dtoServiceProvider.get().createPoso(tmpDto_sex));

		/*  set superUser */
		poso.setSuperUser(dto.isSuperUser() );

		/*  set title */
		poso.setTitle(dto.getTitle() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(UserDto dto, final User poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set email */
		if(dto.isEmailModified()){
			poso.setEmail(dto.getEmail() );
		}

		/*  set firstname */
		if(dto.isFirstnameModified()){
			poso.setFirstname(dto.getFirstname() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set groups */
		if(dto.isGroupsModified()){
			final Set<Group> col_groups = new HashSet<Group>();
			/* load new data from dto */
			Collection<GroupDto> tmpCol_groups = null;
			tmpCol_groups = dto.getGroups();

			for(GroupDto refDto : tmpCol_groups){
				if(null != refDto && null != refDto.getId())
					col_groups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (groups)");
							col_groups.add((Group) poso);
						}
					});
				}
			}
			poso.setGroups(col_groups);
		}

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set lastname */
		if(dto.isLastnameModified()){
			poso.setLastname(dto.getLastname() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

		/*  set properties */
		if(dto.isPropertiesModified()){
			final Set<UserProperty> col_properties = new HashSet<UserProperty>();
			/* load new data from dto */
			Collection<UserPropertyDto> tmpCol_properties = null;
			tmpCol_properties = dto.getProperties();

			/* merge or add data */
			for(UserPropertyDto refDto : tmpCol_properties){
				col_properties.add((UserProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setProperties(col_properties);
		}

		/*  set sex */
		if(dto.isSexModified()){
			SexDto tmpDto_sex = dto.getSex();
			poso.setSex((Sex)dtoServiceProvider.get().createPoso(tmpDto_sex));
		}

		/*  set superUser */
		if(dto.isSuperUserModified()){
			poso.setSuperUser(dto.isSuperUser() );
		}

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public User loadAndMergePoso(UserDto dto)  throws ExpectedException {
		User poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(UserDto dto, User poso)  {
	}


	public void postProcessCreateUnmanaged(UserDto dto, User poso)  {
	}


	public void postProcessLoad(UserDto dto, User poso)  {
	}


	public void postProcessMerge(UserDto dto, User poso)  {
	}


	public void postProcessInstantiate(User poso)  {
	}



}
