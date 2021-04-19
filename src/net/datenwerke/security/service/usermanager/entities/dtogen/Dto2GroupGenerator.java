package net.datenwerke.security.service.usermanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
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
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.dtogen.Dto2GroupGenerator;

/**
 * Dto2PosoGenerator for Group
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2GroupGenerator implements Dto2PosoGenerator<GroupDto,Group> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2GroupGenerator(
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

	public Group loadPoso(GroupDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		Group poso = entityManager.find(Group.class, id);
		return poso;
	}

	public Group instantiatePoso()  {
		Group poso = new Group();
		return poso;
	}

	public Group createPoso(GroupDto dto)  throws ExpectedException {
		Group poso = new Group();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Group createUnmanagedPoso(GroupDto dto)  throws ExpectedException {
		Group poso = new Group();

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

	public void mergePoso(GroupDto dto, final Group poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(GroupDto dto, final Group poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

		/*  set ous */
		final Set<OrganisationalUnit> col_ous = new HashSet<OrganisationalUnit>();
		/* load new data from dto */
		Collection<OrganisationalUnitDto> tmpCol_ous = dto.getOus();

		for(OrganisationalUnitDto refDto : tmpCol_ous){
			if(null != refDto && null != refDto.getId())
				col_ous.add((OrganisationalUnit) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (ous)");
						col_ous.add((OrganisationalUnit) poso);
					}
					});
			}
		}
		poso.setOus(col_ous);

		/*  set referencedGroups */
		final Set<Group> col_referencedGroups = new HashSet<Group>();
		/* load new data from dto */
		Collection<GroupDto> tmpCol_referencedGroups = dto.getReferencedGroups();

		for(GroupDto refDto : tmpCol_referencedGroups){
			if(null != refDto && null != refDto.getId())
				col_referencedGroups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (referencedGroups)");
						col_referencedGroups.add((Group) poso);
					}
					});
			}
		}
		poso.setReferencedGroups(col_referencedGroups);

		/*  set users */
		final Set<User> col_users = new HashSet<User>();
		/* load new data from dto */
		Collection<UserDto> tmpCol_users = dto.getUsers();

		for(UserDto refDto : tmpCol_users){
			if(null != refDto && null != refDto.getId())
				col_users.add((User) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (users)");
						col_users.add((User) poso);
					}
					});
			}
		}
		poso.setUsers(col_users);

	}

	protected void mergeProxy2Poso(GroupDto dto, final Group poso)  throws ExpectedException {
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

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

		/*  set ous */
		if(dto.isOusModified()){
			final Set<OrganisationalUnit> col_ous = new HashSet<OrganisationalUnit>();
			/* load new data from dto */
			Collection<OrganisationalUnitDto> tmpCol_ous = null;
			tmpCol_ous = dto.getOus();

			for(OrganisationalUnitDto refDto : tmpCol_ous){
				if(null != refDto && null != refDto.getId())
					col_ous.add((OrganisationalUnit) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (ous)");
							col_ous.add((OrganisationalUnit) poso);
						}
					});
				}
			}
			poso.setOus(col_ous);
		}

		/*  set referencedGroups */
		if(dto.isReferencedGroupsModified()){
			final Set<Group> col_referencedGroups = new HashSet<Group>();
			/* load new data from dto */
			Collection<GroupDto> tmpCol_referencedGroups = null;
			tmpCol_referencedGroups = dto.getReferencedGroups();

			for(GroupDto refDto : tmpCol_referencedGroups){
				if(null != refDto && null != refDto.getId())
					col_referencedGroups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (referencedGroups)");
							col_referencedGroups.add((Group) poso);
						}
					});
				}
			}
			poso.setReferencedGroups(col_referencedGroups);
		}

		/*  set users */
		if(dto.isUsersModified()){
			final Set<User> col_users = new HashSet<User>();
			/* load new data from dto */
			Collection<UserDto> tmpCol_users = null;
			tmpCol_users = dto.getUsers();

			for(UserDto refDto : tmpCol_users){
				if(null != refDto && null != refDto.getId())
					col_users.add((User) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (users)");
							col_users.add((User) poso);
						}
					});
				}
			}
			poso.setUsers(col_users);
		}

	}

	public void mergeUnmanagedPoso(GroupDto dto, final Group poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(GroupDto dto, final Group poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

		/*  set ous */
		final Set<OrganisationalUnit> col_ous = new HashSet<OrganisationalUnit>();
		/* load new data from dto */
		Collection<OrganisationalUnitDto> tmpCol_ous = dto.getOus();

		for(OrganisationalUnitDto refDto : tmpCol_ous){
			if(null != refDto && null != refDto.getId())
				col_ous.add((OrganisationalUnit) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (ous)");
						col_ous.add((OrganisationalUnit) poso);
					}
					});
			}
		}
		poso.setOus(col_ous);

		/*  set referencedGroups */
		final Set<Group> col_referencedGroups = new HashSet<Group>();
		/* load new data from dto */
		Collection<GroupDto> tmpCol_referencedGroups = dto.getReferencedGroups();

		for(GroupDto refDto : tmpCol_referencedGroups){
			if(null != refDto && null != refDto.getId())
				col_referencedGroups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (referencedGroups)");
						col_referencedGroups.add((Group) poso);
					}
					});
			}
		}
		poso.setReferencedGroups(col_referencedGroups);

		/*  set users */
		final Set<User> col_users = new HashSet<User>();
		/* load new data from dto */
		Collection<UserDto> tmpCol_users = dto.getUsers();

		for(UserDto refDto : tmpCol_users){
			if(null != refDto && null != refDto.getId())
				col_users.add((User) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (users)");
						col_users.add((User) poso);
					}
					});
			}
		}
		poso.setUsers(col_users);

	}

	protected void mergeProxy2UnmanagedPoso(GroupDto dto, final Group poso)  throws ExpectedException {
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

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

		/*  set ous */
		if(dto.isOusModified()){
			final Set<OrganisationalUnit> col_ous = new HashSet<OrganisationalUnit>();
			/* load new data from dto */
			Collection<OrganisationalUnitDto> tmpCol_ous = null;
			tmpCol_ous = dto.getOus();

			for(OrganisationalUnitDto refDto : tmpCol_ous){
				if(null != refDto && null != refDto.getId())
					col_ous.add((OrganisationalUnit) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (ous)");
							col_ous.add((OrganisationalUnit) poso);
						}
					});
				}
			}
			poso.setOus(col_ous);
		}

		/*  set referencedGroups */
		if(dto.isReferencedGroupsModified()){
			final Set<Group> col_referencedGroups = new HashSet<Group>();
			/* load new data from dto */
			Collection<GroupDto> tmpCol_referencedGroups = null;
			tmpCol_referencedGroups = dto.getReferencedGroups();

			for(GroupDto refDto : tmpCol_referencedGroups){
				if(null != refDto && null != refDto.getId())
					col_referencedGroups.add((Group) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (referencedGroups)");
							col_referencedGroups.add((Group) poso);
						}
					});
				}
			}
			poso.setReferencedGroups(col_referencedGroups);
		}

		/*  set users */
		if(dto.isUsersModified()){
			final Set<User> col_users = new HashSet<User>();
			/* load new data from dto */
			Collection<UserDto> tmpCol_users = null;
			tmpCol_users = dto.getUsers();

			for(UserDto refDto : tmpCol_users){
				if(null != refDto && null != refDto.getId())
					col_users.add((User) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (users)");
							col_users.add((User) poso);
						}
					});
				}
			}
			poso.setUsers(col_users);
		}

	}

	public Group loadAndMergePoso(GroupDto dto)  throws ExpectedException {
		Group poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(GroupDto dto, Group poso)  {
	}


	public void postProcessCreateUnmanaged(GroupDto dto, Group poso)  {
	}


	public void postProcessLoad(GroupDto dto, Group poso)  {
	}


	public void postProcessMerge(GroupDto dto, Group poso)  {
	}


	public void postProcessInstantiate(Group poso)  {
	}



}
