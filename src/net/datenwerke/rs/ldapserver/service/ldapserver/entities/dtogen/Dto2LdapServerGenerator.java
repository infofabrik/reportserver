package net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen;

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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.LdapServer;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.dtogen.Dto2LdapServerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;

/**
 * Dto2PosoGenerator for LdapServer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2LdapServerGenerator implements Dto2PosoGenerator<LdapServerDto,LdapServer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2LdapServerGenerator(
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

	public LdapServer loadPoso(LdapServerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		LdapServer poso = entityManager.find(LdapServer.class, id);
		return poso;
	}

	public LdapServer instantiatePoso()  {
		LdapServer poso = new LdapServer();
		return poso;
	}

	public LdapServer createPoso(LdapServerDto dto)  throws ExpectedException {
		LdapServer poso = new LdapServer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public LdapServer createUnmanagedPoso(LdapServerDto dto)  throws ExpectedException {
		LdapServer poso = new LdapServer();

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

	public void mergePoso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		/*  set attrAdd */
		poso.setAttrAdd(dto.getAttrAdd() );

		/*  set attrGroupMember */
		poso.setAttrGroupMember(dto.getAttrGroupMember() );

		/*  set attrGroupName */
		poso.setAttrGroupName(dto.getAttrGroupName() );

		/*  set attrGroupObjClass */
		poso.setAttrGroupObjClass(dto.getAttrGroupObjClass() );

		/*  set attrGuid */
		poso.setAttrGuid(dto.getAttrGuid() );

		/*  set attrObjClass */
		poso.setAttrObjClass(dto.getAttrObjClass() );

		/*  set attrOrgUnitObjClass */
		poso.setAttrOrgUnitObjClass(dto.getAttrOrgUnitObjClass() );

		/*  set attrOrgUnitObjName */
		poso.setAttrOrgUnitObjName(dto.getAttrOrgUnitObjName() );

		/*  set attrUserFirstname */
		poso.setAttrUserFirstname(dto.getAttrUserFirstname() );

		/*  set attrUserLastname */
		poso.setAttrUserLastname(dto.getAttrUserLastname() );

		/*  set attrUserMail */
		poso.setAttrUserMail(dto.getAttrUserMail() );

		/*  set attrUserObjClass */
		poso.setAttrUserObjClass(dto.getAttrUserObjClass() );

		/*  set attrUserUsername */
		poso.setAttrUserUsername(dto.getAttrUserUsername() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set externalDir */
		OrganisationalUnitDto tmpDto_externalDir = dto.getExternalDir();
		if(null != tmpDto_externalDir && null != tmpDto_externalDir.getId()){
			if(null != poso.getExternalDir() && null != poso.getExternalDir().getId() && ! poso.getExternalDir().getId().equals(tmpDto_externalDir.getId())){
				OrganisationalUnit newPropertyValue = (OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getExternalDir(), newPropertyValue, "externalDir");
				poso.setExternalDir(newPropertyValue);
			} else if(null == poso.getExternalDir()){
				poso.setExternalDir((OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir));
			}
		} else if(null != tmpDto_externalDir && null == tmpDto_externalDir.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (externalDir)");
					poso.setExternalDir((OrganisationalUnit)refPoso);
				}
			});
		} else if(null == tmpDto_externalDir){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getExternalDir(), null, "externalDir");
			poso.setExternalDir(null);
		}

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set flattenTree */
		try{
			poso.setFlattenTree(dto.isFlattenTree() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set ldapBase */
		poso.setLdapBase(dto.getLdapBase() );

		/*  set ldapDisabled */
		try{
			poso.setLdapDisabled(dto.isLdapDisabled() );
		} catch(NullPointerException e){
		}

		/*  set ldapFilter */
		poso.setLdapFilter(dto.getLdapFilter() );

		/*  set logResultingTree */
		try{
			poso.setLogResultingTree(dto.isLogResultingTree() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set providerHost */
		poso.setProviderHost(dto.getProviderHost() );

		/*  set providerPort */
		try{
			poso.setProviderPort(dto.getProviderPort() );
		} catch(NullPointerException e){
		}

		/*  set securityCredentials */
		poso.setSecurityCredentials(dto.getSecurityCredentials() );

		/*  set securityEncryption */
		poso.setSecurityEncryption(dto.getSecurityEncryption() );

		/*  set securityPrincipal */
		poso.setSecurityPrincipal(dto.getSecurityPrincipal() );

		/*  set writeProtection */
		try{
			poso.setWriteProtection(dto.isWriteProtection() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		/*  set attrAdd */
		if(dto.isAttrAddModified()){
			poso.setAttrAdd(dto.getAttrAdd() );
		}

		/*  set attrGroupMember */
		if(dto.isAttrGroupMemberModified()){
			poso.setAttrGroupMember(dto.getAttrGroupMember() );
		}

		/*  set attrGroupName */
		if(dto.isAttrGroupNameModified()){
			poso.setAttrGroupName(dto.getAttrGroupName() );
		}

		/*  set attrGroupObjClass */
		if(dto.isAttrGroupObjClassModified()){
			poso.setAttrGroupObjClass(dto.getAttrGroupObjClass() );
		}

		/*  set attrGuid */
		if(dto.isAttrGuidModified()){
			poso.setAttrGuid(dto.getAttrGuid() );
		}

		/*  set attrObjClass */
		if(dto.isAttrObjClassModified()){
			poso.setAttrObjClass(dto.getAttrObjClass() );
		}

		/*  set attrOrgUnitObjClass */
		if(dto.isAttrOrgUnitObjClassModified()){
			poso.setAttrOrgUnitObjClass(dto.getAttrOrgUnitObjClass() );
		}

		/*  set attrOrgUnitObjName */
		if(dto.isAttrOrgUnitObjNameModified()){
			poso.setAttrOrgUnitObjName(dto.getAttrOrgUnitObjName() );
		}

		/*  set attrUserFirstname */
		if(dto.isAttrUserFirstnameModified()){
			poso.setAttrUserFirstname(dto.getAttrUserFirstname() );
		}

		/*  set attrUserLastname */
		if(dto.isAttrUserLastnameModified()){
			poso.setAttrUserLastname(dto.getAttrUserLastname() );
		}

		/*  set attrUserMail */
		if(dto.isAttrUserMailModified()){
			poso.setAttrUserMail(dto.getAttrUserMail() );
		}

		/*  set attrUserObjClass */
		if(dto.isAttrUserObjClassModified()){
			poso.setAttrUserObjClass(dto.getAttrUserObjClass() );
		}

		/*  set attrUserUsername */
		if(dto.isAttrUserUsernameModified()){
			poso.setAttrUserUsername(dto.getAttrUserUsername() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set externalDir */
		if(dto.isExternalDirModified()){
			OrganisationalUnitDto tmpDto_externalDir = dto.getExternalDir();
			if(null != tmpDto_externalDir && null != tmpDto_externalDir.getId()){
				if(null != poso.getExternalDir() && null != poso.getExternalDir().getId() && ! poso.getExternalDir().getId().equals(tmpDto_externalDir.getId())){
					OrganisationalUnit newPropertyValue = (OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getExternalDir(), newPropertyValue, "externalDir");
					poso.setExternalDir(newPropertyValue);
				} else if(null == poso.getExternalDir()){
					poso.setExternalDir((OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir));
				}
			} else if(null != tmpDto_externalDir && null == tmpDto_externalDir.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (externalDir)");
						poso.setExternalDir((OrganisationalUnit)refPoso);
					}
			});
			} else if(null == tmpDto_externalDir){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getExternalDir(), null, "externalDir");
				poso.setExternalDir(null);
			}
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set flattenTree */
		if(dto.isFlattenTreeModified()){
			try{
				poso.setFlattenTree(dto.isFlattenTree() );
			} catch(NullPointerException e){
			}
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set ldapBase */
		if(dto.isLdapBaseModified()){
			poso.setLdapBase(dto.getLdapBase() );
		}

		/*  set ldapDisabled */
		if(dto.isLdapDisabledModified()){
			try{
				poso.setLdapDisabled(dto.isLdapDisabled() );
			} catch(NullPointerException e){
			}
		}

		/*  set ldapFilter */
		if(dto.isLdapFilterModified()){
			poso.setLdapFilter(dto.getLdapFilter() );
		}

		/*  set logResultingTree */
		if(dto.isLogResultingTreeModified()){
			try{
				poso.setLogResultingTree(dto.isLogResultingTree() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set providerHost */
		if(dto.isProviderHostModified()){
			poso.setProviderHost(dto.getProviderHost() );
		}

		/*  set providerPort */
		if(dto.isProviderPortModified()){
			try{
				poso.setProviderPort(dto.getProviderPort() );
			} catch(NullPointerException e){
			}
		}

		/*  set securityCredentials */
		if(dto.isSecurityCredentialsModified()){
			poso.setSecurityCredentials(dto.getSecurityCredentials() );
		}

		/*  set securityEncryption */
		if(dto.isSecurityEncryptionModified()){
			poso.setSecurityEncryption(dto.getSecurityEncryption() );
		}

		/*  set securityPrincipal */
		if(dto.isSecurityPrincipalModified()){
			poso.setSecurityPrincipal(dto.getSecurityPrincipal() );
		}

		/*  set writeProtection */
		if(dto.isWriteProtectionModified()){
			try{
				poso.setWriteProtection(dto.isWriteProtection() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		/*  set attrAdd */
		poso.setAttrAdd(dto.getAttrAdd() );

		/*  set attrGroupMember */
		poso.setAttrGroupMember(dto.getAttrGroupMember() );

		/*  set attrGroupName */
		poso.setAttrGroupName(dto.getAttrGroupName() );

		/*  set attrGroupObjClass */
		poso.setAttrGroupObjClass(dto.getAttrGroupObjClass() );

		/*  set attrGuid */
		poso.setAttrGuid(dto.getAttrGuid() );

		/*  set attrObjClass */
		poso.setAttrObjClass(dto.getAttrObjClass() );

		/*  set attrOrgUnitObjClass */
		poso.setAttrOrgUnitObjClass(dto.getAttrOrgUnitObjClass() );

		/*  set attrOrgUnitObjName */
		poso.setAttrOrgUnitObjName(dto.getAttrOrgUnitObjName() );

		/*  set attrUserFirstname */
		poso.setAttrUserFirstname(dto.getAttrUserFirstname() );

		/*  set attrUserLastname */
		poso.setAttrUserLastname(dto.getAttrUserLastname() );

		/*  set attrUserMail */
		poso.setAttrUserMail(dto.getAttrUserMail() );

		/*  set attrUserObjClass */
		poso.setAttrUserObjClass(dto.getAttrUserObjClass() );

		/*  set attrUserUsername */
		poso.setAttrUserUsername(dto.getAttrUserUsername() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set externalDir */
		OrganisationalUnitDto tmpDto_externalDir = dto.getExternalDir();
		if(null != tmpDto_externalDir && null != tmpDto_externalDir.getId()){
			OrganisationalUnit newPropertyValue = (OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir);
			poso.setExternalDir(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setExternalDir((OrganisationalUnit)refPoso);
				}
			});
		} else if(null != tmpDto_externalDir && null == tmpDto_externalDir.getId()){
			final OrganisationalUnitDto tmpDto_externalDir_final = tmpDto_externalDir;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setExternalDir((OrganisationalUnit)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_externalDir_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setExternalDir((OrganisationalUnit)refPoso);
					}
				}
			});
		} else if(null == tmpDto_externalDir){
			poso.setExternalDir(null);
		}

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set flattenTree */
		try{
			poso.setFlattenTree(dto.isFlattenTree() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set ldapBase */
		poso.setLdapBase(dto.getLdapBase() );

		/*  set ldapDisabled */
		try{
			poso.setLdapDisabled(dto.isLdapDisabled() );
		} catch(NullPointerException e){
		}

		/*  set ldapFilter */
		poso.setLdapFilter(dto.getLdapFilter() );

		/*  set logResultingTree */
		try{
			poso.setLogResultingTree(dto.isLogResultingTree() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set providerHost */
		poso.setProviderHost(dto.getProviderHost() );

		/*  set providerPort */
		try{
			poso.setProviderPort(dto.getProviderPort() );
		} catch(NullPointerException e){
		}

		/*  set securityCredentials */
		poso.setSecurityCredentials(dto.getSecurityCredentials() );

		/*  set securityEncryption */
		poso.setSecurityEncryption(dto.getSecurityEncryption() );

		/*  set securityPrincipal */
		poso.setSecurityPrincipal(dto.getSecurityPrincipal() );

		/*  set writeProtection */
		try{
			poso.setWriteProtection(dto.isWriteProtection() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(LdapServerDto dto, final LdapServer poso)  throws ExpectedException {
		/*  set attrAdd */
		if(dto.isAttrAddModified()){
			poso.setAttrAdd(dto.getAttrAdd() );
		}

		/*  set attrGroupMember */
		if(dto.isAttrGroupMemberModified()){
			poso.setAttrGroupMember(dto.getAttrGroupMember() );
		}

		/*  set attrGroupName */
		if(dto.isAttrGroupNameModified()){
			poso.setAttrGroupName(dto.getAttrGroupName() );
		}

		/*  set attrGroupObjClass */
		if(dto.isAttrGroupObjClassModified()){
			poso.setAttrGroupObjClass(dto.getAttrGroupObjClass() );
		}

		/*  set attrGuid */
		if(dto.isAttrGuidModified()){
			poso.setAttrGuid(dto.getAttrGuid() );
		}

		/*  set attrObjClass */
		if(dto.isAttrObjClassModified()){
			poso.setAttrObjClass(dto.getAttrObjClass() );
		}

		/*  set attrOrgUnitObjClass */
		if(dto.isAttrOrgUnitObjClassModified()){
			poso.setAttrOrgUnitObjClass(dto.getAttrOrgUnitObjClass() );
		}

		/*  set attrOrgUnitObjName */
		if(dto.isAttrOrgUnitObjNameModified()){
			poso.setAttrOrgUnitObjName(dto.getAttrOrgUnitObjName() );
		}

		/*  set attrUserFirstname */
		if(dto.isAttrUserFirstnameModified()){
			poso.setAttrUserFirstname(dto.getAttrUserFirstname() );
		}

		/*  set attrUserLastname */
		if(dto.isAttrUserLastnameModified()){
			poso.setAttrUserLastname(dto.getAttrUserLastname() );
		}

		/*  set attrUserMail */
		if(dto.isAttrUserMailModified()){
			poso.setAttrUserMail(dto.getAttrUserMail() );
		}

		/*  set attrUserObjClass */
		if(dto.isAttrUserObjClassModified()){
			poso.setAttrUserObjClass(dto.getAttrUserObjClass() );
		}

		/*  set attrUserUsername */
		if(dto.isAttrUserUsernameModified()){
			poso.setAttrUserUsername(dto.getAttrUserUsername() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set externalDir */
		if(dto.isExternalDirModified()){
			OrganisationalUnitDto tmpDto_externalDir = dto.getExternalDir();
			if(null != tmpDto_externalDir && null != tmpDto_externalDir.getId()){
				OrganisationalUnit newPropertyValue = (OrganisationalUnit)dtoServiceProvider.get().loadPoso(tmpDto_externalDir);
				poso.setExternalDir(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setExternalDir((OrganisationalUnit)refPoso);
					}
			});
			} else if(null != tmpDto_externalDir && null == tmpDto_externalDir.getId()){
				final OrganisationalUnitDto tmpDto_externalDir_final = tmpDto_externalDir;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_externalDir, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setExternalDir((OrganisationalUnit)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_externalDir_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setExternalDir((OrganisationalUnit)refPoso);
						}
					}
			});
			} else if(null == tmpDto_externalDir){
				poso.setExternalDir(null);
			}
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set flattenTree */
		if(dto.isFlattenTreeModified()){
			try{
				poso.setFlattenTree(dto.isFlattenTree() );
			} catch(NullPointerException e){
			}
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set ldapBase */
		if(dto.isLdapBaseModified()){
			poso.setLdapBase(dto.getLdapBase() );
		}

		/*  set ldapDisabled */
		if(dto.isLdapDisabledModified()){
			try{
				poso.setLdapDisabled(dto.isLdapDisabled() );
			} catch(NullPointerException e){
			}
		}

		/*  set ldapFilter */
		if(dto.isLdapFilterModified()){
			poso.setLdapFilter(dto.getLdapFilter() );
		}

		/*  set logResultingTree */
		if(dto.isLogResultingTreeModified()){
			try{
				poso.setLogResultingTree(dto.isLogResultingTree() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set providerHost */
		if(dto.isProviderHostModified()){
			poso.setProviderHost(dto.getProviderHost() );
		}

		/*  set providerPort */
		if(dto.isProviderPortModified()){
			try{
				poso.setProviderPort(dto.getProviderPort() );
			} catch(NullPointerException e){
			}
		}

		/*  set securityCredentials */
		if(dto.isSecurityCredentialsModified()){
			poso.setSecurityCredentials(dto.getSecurityCredentials() );
		}

		/*  set securityEncryption */
		if(dto.isSecurityEncryptionModified()){
			poso.setSecurityEncryption(dto.getSecurityEncryption() );
		}

		/*  set securityPrincipal */
		if(dto.isSecurityPrincipalModified()){
			poso.setSecurityPrincipal(dto.getSecurityPrincipal() );
		}

		/*  set writeProtection */
		if(dto.isWriteProtectionModified()){
			try{
				poso.setWriteProtection(dto.isWriteProtection() );
			} catch(NullPointerException e){
			}
		}

	}

	public LdapServer loadAndMergePoso(LdapServerDto dto)  throws ExpectedException {
		LdapServer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(LdapServerDto dto, LdapServer poso)  {
	}


	public void postProcessCreateUnmanaged(LdapServerDto dto, LdapServer poso)  {
	}


	public void postProcessLoad(LdapServerDto dto, LdapServer poso)  {
	}


	public void postProcessMerge(LdapServerDto dto, LdapServer poso)  {
	}


	public void postProcessInstantiate(LdapServer poso)  {
	}


	public boolean validateKeyProperty(LdapServerDto dto, LdapServer poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]+$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
