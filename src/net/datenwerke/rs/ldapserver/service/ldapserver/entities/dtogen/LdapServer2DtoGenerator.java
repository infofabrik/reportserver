package net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.LdapServer;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen.LdapServer2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for LdapServer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class LdapServer2DtoGenerator implements Poso2DtoGenerator<LdapServer,LdapServerDto> {

	private final net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1;
	private final net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2;
	private final net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen.LdapServer2DtoPostProcessor postProcessor_3;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public LdapServer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.treedb.service.treedb.dtogen.AbstractNode2DtoPostProcessor postProcessor_1,
		net.datenwerke.security.service.treedb.entities.SecuredAbstractNode2DtoPostProcessor postProcessor_2,
		net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen.LdapServer2DtoPostProcessor postProcessor_3	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.postProcessor_2 = postProcessor_2;
		this.postProcessor_3 = postProcessor_3;
	}

	public LdapServerDto instantiateDto(LdapServer poso)  {
		LdapServerDto dto = new LdapServerDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		this.postProcessor_2.dtoInstantiated(poso, dto);
		this.postProcessor_3.dtoInstantiated(poso, dto);
		return dto;
	}

	public LdapServerDto createDto(LdapServer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final LdapServerDto dto = new LdapServerDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set position */
			dto.setPosition(poso.getPosition() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set createdOn */
			dto.setCreatedOn(poso.getCreatedOn() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set lastUpdated */
			dto.setLastUpdated(poso.getLastUpdated() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set attrAdd */
			dto.setAttrAdd(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrAdd(),8192)));

			/*  set attrGroupMember */
			dto.setAttrGroupMember(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrGroupMember(),8192)));

			/*  set attrGroupName */
			dto.setAttrGroupName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrGroupName(),8192)));

			/*  set attrGroupObjClass */
			dto.setAttrGroupObjClass(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrGroupObjClass(),8192)));

			/*  set attrGuid */
			dto.setAttrGuid(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrGuid(),8192)));

			/*  set attrObjClass */
			dto.setAttrObjClass(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrObjClass(),8192)));

			/*  set attrOrgUnitObjClass */
			dto.setAttrOrgUnitObjClass(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrOrgUnitObjClass(),8192)));

			/*  set attrOrgUnitObjName */
			dto.setAttrOrgUnitObjName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrOrgUnitObjName(),8192)));

			/*  set attrUserFirstname */
			dto.setAttrUserFirstname(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrUserFirstname(),8192)));

			/*  set attrUserLastname */
			dto.setAttrUserLastname(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrUserLastname(),8192)));

			/*  set attrUserMail */
			dto.setAttrUserMail(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrUserMail(),8192)));

			/*  set attrUserObjClass */
			dto.setAttrUserObjClass(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrUserObjClass(),8192)));

			/*  set attrUserUsername */
			dto.setAttrUserUsername(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAttrUserUsername(),8192)));

			/*  set externalDir */
			Object tmpDtoOrganisationalUnitDtogetExternalDir = dtoServiceProvider.get().createDto(poso.getExternalDir(), referenced, referenced);
			dto.setExternalDir((OrganisationalUnitDto)tmpDtoOrganisationalUnitDtogetExternalDir);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrganisationalUnitDtogetExternalDir, poso.getExternalDir(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setExternalDir((OrganisationalUnitDto)refDto);
				}
			});

			/*  set flags */
			dto.setFlags(poso.getFlags() );

			/*  set flattenTree */
			dto.setFlattenTree(poso.isFlattenTree() );

			/*  set ldapBase */
			dto.setLdapBase(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLdapBase(),8192)));

			/*  set ldapDisabled */
			dto.setLdapDisabled(poso.isLdapDisabled() );

			/*  set ldapFilter */
			dto.setLdapFilter(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLdapFilter(),8192)));

			/*  set logResultingTree */
			dto.setLogResultingTree(poso.isLogResultingTree() );

			/*  set providerHost */
			dto.setProviderHost(StringEscapeUtils.escapeXml(StringUtils.left(poso.getProviderHost(),8192)));

			/*  set providerPort */
			dto.setProviderPort(poso.getProviderPort() );

			/*  set securityCredentials */
			dto.setSecurityCredentials(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSecurityCredentials(),8192)));

			/*  set securityEncryption */
			dto.setSecurityEncryption(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSecurityEncryption(),8192)));

			/*  set securityPrincipal */
			dto.setSecurityPrincipal(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSecurityPrincipal(),8192)));

			/*  set writeProtection */
			dto.setWriteProtection(poso.isWriteProtection() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);
		this.postProcessor_2.dtoCreated(poso, dto);
		this.postProcessor_3.dtoCreated(poso, dto);

		return dto;
	}


}
