package net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen;

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
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen.Dto2RemoteRsServerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RemoteRsServer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RemoteRsServerGenerator implements Dto2PosoGenerator<RemoteRsServerDto,RemoteRsServer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RemoteRsServerGenerator(
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

	public RemoteRsServer loadPoso(RemoteRsServerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		RemoteRsServer poso = entityManager.find(RemoteRsServer.class, id);
		return poso;
	}

	public RemoteRsServer instantiatePoso()  {
		RemoteRsServer poso = new RemoteRsServer();
		return poso;
	}

	public RemoteRsServer createPoso(RemoteRsServerDto dto)  throws ExpectedException {
		RemoteRsServer poso = new RemoteRsServer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RemoteRsServer createUnmanagedPoso(RemoteRsServerDto dto)  throws ExpectedException {
		RemoteRsServer poso = new RemoteRsServer();

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

	public void mergePoso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		/*  set apikey */
		poso.setApikey(dto.getApikey() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		/*  set apikey */
		if(dto.isApikeyModified()){
			poso.setApikey(dto.getApikey() );
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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		/*  set apikey */
		poso.setApikey(dto.getApikey() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(RemoteRsServerDto dto, final RemoteRsServer poso)  throws ExpectedException {
		/*  set apikey */
		if(dto.isApikeyModified()){
			poso.setApikey(dto.getApikey() );
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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public RemoteRsServer loadAndMergePoso(RemoteRsServerDto dto)  throws ExpectedException {
		RemoteRsServer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RemoteRsServerDto dto, RemoteRsServer poso)  {
	}


	public void postProcessCreateUnmanaged(RemoteRsServerDto dto, RemoteRsServer poso)  {
	}


	public void postProcessLoad(RemoteRsServerDto dto, RemoteRsServer poso)  {
	}


	public void postProcessMerge(RemoteRsServerDto dto, RemoteRsServer poso)  {
	}


	public void postProcessInstantiate(RemoteRsServer poso)  {
	}


	public boolean validateKeyProperty(RemoteRsServerDto dto, RemoteRsServer poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]*$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
