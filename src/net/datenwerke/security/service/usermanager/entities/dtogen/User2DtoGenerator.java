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
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.security.service.usermanager.entities.dtogen.User2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for User
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class User2DtoGenerator implements Poso2DtoGenerator<User,UserDtoDec> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.security.service.usermanager.entities.post.User2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public User2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.security.service.usermanager.entities.post.User2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public UserDtoDec instantiateDto(User poso)  {
		UserDtoDec dto = new UserDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public UserDtoDec createDto(User poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final UserDtoDec dto = new UserDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set active */
			dto.setActive(poso.isActive() );

			/*  set firstname */
			dto.setFirstname(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFirstname(),8192)));

			/*  set guid */
			dto.setGuid(StringEscapeUtils.escapeXml(StringUtils.left(poso.getGuid(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set lastname */
			dto.setLastname(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLastname(),8192)));

			/*  set origin */
			dto.setOrigin(StringEscapeUtils.escapeXml(StringUtils.left(poso.getOrigin(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

			/*  set sex */
			Object tmpDtoSexDtogetSex = dtoServiceProvider.get().createDto(poso.getSex(), referenced, referenced);
			dto.setSex((SexDto)tmpDtoSexDtogetSex);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSexDtogetSex, poso.getSex(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setSex((SexDto)refDto);
				}
			});

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set email */
			dto.setEmail(StringEscapeUtils.escapeXml(StringUtils.left(poso.getEmail(),8192)));

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set groups */
			final Set<GroupDto> col_groups = new HashSet<GroupDto>();
			if( null != poso.getGroups()){
				for(Group refPoso : poso.getGroups()){
					final Object tmpDtoGroupDtogetGroups = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_groups.add((GroupDto) tmpDtoGroupDtogetGroups);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGroupDtogetGroups, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (groups)");
							col_groups.remove(tmpDtoGroupDtogetGroups);
							col_groups.add((GroupDto) dto);
						}
					});
				}
				dto.setGroups(col_groups);
			}

			/*  set properties */
			final Set<UserPropertyDto> col_properties = new HashSet<UserPropertyDto>();
			if( null != poso.getProperties()){
				for(UserProperty refPoso : poso.getProperties()){
					final Object tmpDtoUserPropertyDtogetProperties = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_properties.add((UserPropertyDto) tmpDtoUserPropertyDtogetProperties);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserPropertyDtogetProperties, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (properties)");
							col_properties.remove(tmpDtoUserPropertyDtogetProperties);
							col_properties.add((UserPropertyDto) dto);
						}
					});
				}
				dto.setProperties(col_properties);
			}

			/*  set superUser */
			dto.setSuperUser(poso.isSuperUser() );

			/*  set title */
			dto.setTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTitle(),8192)));

			/*  set username */
			dto.setUsername(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUsername(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
