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
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.Dto2FtpDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FtpDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FtpDatasinkGenerator implements Dto2PosoGenerator<FtpDatasinkDto,FtpDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FtpDatasinkGenerator(
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

	public FtpDatasink loadPoso(FtpDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FtpDatasink poso = entityManager.find(FtpDatasink.class, id);
		return poso;
	}

	public FtpDatasink instantiatePoso()  {
		FtpDatasink poso = new FtpDatasink();
		return poso;
	}

	public FtpDatasink createPoso(FtpDatasinkDto dto)  throws ExpectedException {
		FtpDatasink poso = new FtpDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FtpDatasink createUnmanagedPoso(FtpDatasinkDto dto)  throws ExpectedException {
		FtpDatasink poso = new FtpDatasink();

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

	public void mergePoso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
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

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
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

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
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

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(FtpDatasinkDto dto, final FtpDatasink poso)  throws ExpectedException {
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

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public FtpDatasink loadAndMergePoso(FtpDatasinkDto dto)  throws ExpectedException {
		FtpDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FtpDatasinkDto dto, FtpDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(FtpDatasinkDto dto, FtpDatasink poso)  {
	}


	public void postProcessLoad(FtpDatasinkDto dto, FtpDatasink poso)  {
	}


	public void postProcessMerge(FtpDatasinkDto dto, FtpDatasink poso)  {
	}


	public void postProcessInstantiate(FtpDatasink poso)  {
	}



}
