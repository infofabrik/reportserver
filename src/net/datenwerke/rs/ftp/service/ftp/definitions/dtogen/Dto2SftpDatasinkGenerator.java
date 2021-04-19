package net.datenwerke.rs.ftp.service.ftp.definitions.dtogen;

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
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.Dto2SftpDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SftpDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SftpDatasinkGenerator implements Dto2PosoGenerator<SftpDatasinkDto,SftpDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SftpDatasinkGenerator(
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

	public SftpDatasink loadPoso(SftpDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		SftpDatasink poso = entityManager.find(SftpDatasink.class, id);
		return poso;
	}

	public SftpDatasink instantiatePoso()  {
		SftpDatasink poso = new SftpDatasink();
		return poso;
	}

	public SftpDatasink createPoso(SftpDatasinkDto dto)  throws ExpectedException {
		SftpDatasink poso = new SftpDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SftpDatasink createUnmanagedPoso(SftpDatasinkDto dto)  throws ExpectedException {
		SftpDatasink poso = new SftpDatasink();

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

	public void mergePoso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		/*  set authenticationType */
		poso.setAuthenticationType(dto.getAuthenticationType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set host */
		poso.setHost(dto.getHost() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set port */
		try{
			poso.setPort(dto.getPort() );
		} catch(NullPointerException e){
		}

		/*  set privateKeyPassphrase */
		poso.setPrivateKeyPassphrase(dto.getPrivateKeyPassphrase() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		/*  set authenticationType */
		if(dto.isAuthenticationTypeModified()){
			poso.setAuthenticationType(dto.getAuthenticationType() );
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

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
		}

		/*  set host */
		if(dto.isHostModified()){
			poso.setHost(dto.getHost() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set password */
		if(dto.isPasswordModified()){
			poso.setPassword(dto.getPassword() );
		}

		/*  set port */
		if(dto.isPortModified()){
			try{
				poso.setPort(dto.getPort() );
			} catch(NullPointerException e){
			}
		}

		/*  set privateKeyPassphrase */
		if(dto.isPrivateKeyPassphraseModified()){
			poso.setPrivateKeyPassphrase(dto.getPrivateKeyPassphrase() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		/*  set authenticationType */
		poso.setAuthenticationType(dto.getAuthenticationType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set host */
		poso.setHost(dto.getHost() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set port */
		try{
			poso.setPort(dto.getPort() );
		} catch(NullPointerException e){
		}

		/*  set privateKeyPassphrase */
		poso.setPrivateKeyPassphrase(dto.getPrivateKeyPassphrase() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(SftpDatasinkDto dto, final SftpDatasink poso)  throws ExpectedException {
		/*  set authenticationType */
		if(dto.isAuthenticationTypeModified()){
			poso.setAuthenticationType(dto.getAuthenticationType() );
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

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
		}

		/*  set host */
		if(dto.isHostModified()){
			poso.setHost(dto.getHost() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set password */
		if(dto.isPasswordModified()){
			poso.setPassword(dto.getPassword() );
		}

		/*  set port */
		if(dto.isPortModified()){
			try{
				poso.setPort(dto.getPort() );
			} catch(NullPointerException e){
			}
		}

		/*  set privateKeyPassphrase */
		if(dto.isPrivateKeyPassphraseModified()){
			poso.setPrivateKeyPassphrase(dto.getPrivateKeyPassphrase() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public SftpDatasink loadAndMergePoso(SftpDatasinkDto dto)  throws ExpectedException {
		SftpDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SftpDatasinkDto dto, SftpDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(SftpDatasinkDto dto, SftpDatasink poso)  {
	}


	public void postProcessLoad(SftpDatasinkDto dto, SftpDatasink poso)  {
	}


	public void postProcessMerge(SftpDatasinkDto dto, SftpDatasink poso)  {
	}


	public void postProcessInstantiate(SftpDatasink poso)  {
	}



}
