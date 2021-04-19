package net.datenwerke.security.service.usermanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.dtogen.Group2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for Group
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Group2DtoGenerator implements Poso2DtoGenerator<Group,GroupDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Group2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
	}

	public GroupDto instantiateDto(Group poso)  {
		GroupDto dto = new GroupDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		return dto;
	}

	public GroupDto createDto(Group poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GroupDto dto = new GroupDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set guid */
			dto.setGuid(StringEscapeUtils.escapeXml(StringUtils.left(poso.getGuid(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set origin */
			dto.setOrigin(StringEscapeUtils.escapeXml(StringUtils.left(poso.getOrigin(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set ous */
			final Set<OrganisationalUnitDto> col_ous = new HashSet<OrganisationalUnitDto>();
			if( null != poso.getOus()){
				for(OrganisationalUnit refPoso : poso.getOus()){
					final Object tmpDtoOrganisationalUnitDtogetOus = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_ous.add((OrganisationalUnitDto) tmpDtoOrganisationalUnitDtogetOus);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrganisationalUnitDtogetOus, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (ous)");
							col_ous.remove(tmpDtoOrganisationalUnitDtogetOus);
							col_ous.add((OrganisationalUnitDto) dto);
						}
					});
				}
				dto.setOus(col_ous);
			}

			/*  set referencedGroups */
			final Set<GroupDto> col_referencedGroups = new HashSet<GroupDto>();
			if( null != poso.getReferencedGroups()){
				for(Group refPoso : poso.getReferencedGroups()){
					final Object tmpDtoGroupDtogetReferencedGroups = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_referencedGroups.add((GroupDto) tmpDtoGroupDtogetReferencedGroups);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGroupDtogetReferencedGroups, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (referencedGroups)");
							col_referencedGroups.remove(tmpDtoGroupDtogetReferencedGroups);
							col_referencedGroups.add((GroupDto) dto);
						}
					});
				}
				dto.setReferencedGroups(col_referencedGroups);
			}

			/*  set users */
			final Set<UserDto> col_users = new HashSet<UserDto>();
			if( null != poso.getUsers()){
				for(User refPoso : poso.getUsers()){
					final Object tmpDtoUserDtogetUsers = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_users.add((UserDto) tmpDtoUserDtogetUsers);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetUsers, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (users)");
							col_users.remove(tmpDtoUserDtogetUsers);
							col_users.add((UserDto) dto);
						}
					});
				}
				dto.setUsers(col_users);
			}

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);

		return dto;
	}


}
