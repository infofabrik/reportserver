package net.datenwerke.rs.transport.service.transport.entities.dtogen;

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
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.dtogen.Dto2TransportGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for Transport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TransportGenerator implements Dto2PosoGenerator<TransportDto,Transport> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TransportGenerator(
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

	public Transport loadPoso(TransportDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		Transport poso = entityManager.find(Transport.class, id);
		return poso;
	}

	public Transport instantiatePoso()  {
		Transport poso = new Transport();
		return poso;
	}

	public Transport createPoso(TransportDto dto)  throws ExpectedException {
		Transport poso = new Transport();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Transport createUnmanagedPoso(TransportDto dto)  throws ExpectedException {
		Transport poso = new Transport();

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

	public void mergePoso(TransportDto dto, final Transport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TransportDto dto, final Transport poso)  throws ExpectedException {
		/*  set closed */
		try{
			poso.setClosed(dto.isClosed() );
		} catch(NullPointerException e){
		}

		/*  set creatorEmail */
		poso.setCreatorEmail(dto.getCreatorEmail() );

		/*  set creatorFirstname */
		poso.setCreatorFirstname(dto.getCreatorFirstname() );

		/*  set creatorLastname */
		poso.setCreatorLastname(dto.getCreatorLastname() );

		/*  set creatorUsername */
		poso.setCreatorUsername(dto.getCreatorUsername() );

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

		/*  set rsSchemaVersion */
		poso.setRsSchemaVersion(dto.getRsSchemaVersion() );

		/*  set rsVersion */
		poso.setRsVersion(dto.getRsVersion() );

		/*  set serverId */
		poso.setServerId(dto.getServerId() );

		/*  set xml */
		poso.setXml(dto.getXml() );

	}

	protected void mergeProxy2Poso(TransportDto dto, final Transport poso)  throws ExpectedException {
		/*  set closed */
		if(dto.isClosedModified()){
			try{
				poso.setClosed(dto.isClosed() );
			} catch(NullPointerException e){
			}
		}

		/*  set creatorEmail */
		if(dto.isCreatorEmailModified()){
			poso.setCreatorEmail(dto.getCreatorEmail() );
		}

		/*  set creatorFirstname */
		if(dto.isCreatorFirstnameModified()){
			poso.setCreatorFirstname(dto.getCreatorFirstname() );
		}

		/*  set creatorLastname */
		if(dto.isCreatorLastnameModified()){
			poso.setCreatorLastname(dto.getCreatorLastname() );
		}

		/*  set creatorUsername */
		if(dto.isCreatorUsernameModified()){
			poso.setCreatorUsername(dto.getCreatorUsername() );
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

		/*  set rsSchemaVersion */
		if(dto.isRsSchemaVersionModified()){
			poso.setRsSchemaVersion(dto.getRsSchemaVersion() );
		}

		/*  set rsVersion */
		if(dto.isRsVersionModified()){
			poso.setRsVersion(dto.getRsVersion() );
		}

		/*  set serverId */
		if(dto.isServerIdModified()){
			poso.setServerId(dto.getServerId() );
		}

		/*  set xml */
		if(dto.isXmlModified()){
			poso.setXml(dto.getXml() );
		}

	}

	public void mergeUnmanagedPoso(TransportDto dto, final Transport poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TransportDto dto, final Transport poso)  throws ExpectedException {
		/*  set closed */
		try{
			poso.setClosed(dto.isClosed() );
		} catch(NullPointerException e){
		}

		/*  set creatorEmail */
		poso.setCreatorEmail(dto.getCreatorEmail() );

		/*  set creatorFirstname */
		poso.setCreatorFirstname(dto.getCreatorFirstname() );

		/*  set creatorLastname */
		poso.setCreatorLastname(dto.getCreatorLastname() );

		/*  set creatorUsername */
		poso.setCreatorUsername(dto.getCreatorUsername() );

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

		/*  set rsSchemaVersion */
		poso.setRsSchemaVersion(dto.getRsSchemaVersion() );

		/*  set rsVersion */
		poso.setRsVersion(dto.getRsVersion() );

		/*  set serverId */
		poso.setServerId(dto.getServerId() );

		/*  set xml */
		poso.setXml(dto.getXml() );

	}

	protected void mergeProxy2UnmanagedPoso(TransportDto dto, final Transport poso)  throws ExpectedException {
		/*  set closed */
		if(dto.isClosedModified()){
			try{
				poso.setClosed(dto.isClosed() );
			} catch(NullPointerException e){
			}
		}

		/*  set creatorEmail */
		if(dto.isCreatorEmailModified()){
			poso.setCreatorEmail(dto.getCreatorEmail() );
		}

		/*  set creatorFirstname */
		if(dto.isCreatorFirstnameModified()){
			poso.setCreatorFirstname(dto.getCreatorFirstname() );
		}

		/*  set creatorLastname */
		if(dto.isCreatorLastnameModified()){
			poso.setCreatorLastname(dto.getCreatorLastname() );
		}

		/*  set creatorUsername */
		if(dto.isCreatorUsernameModified()){
			poso.setCreatorUsername(dto.getCreatorUsername() );
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

		/*  set rsSchemaVersion */
		if(dto.isRsSchemaVersionModified()){
			poso.setRsSchemaVersion(dto.getRsSchemaVersion() );
		}

		/*  set rsVersion */
		if(dto.isRsVersionModified()){
			poso.setRsVersion(dto.getRsVersion() );
		}

		/*  set serverId */
		if(dto.isServerIdModified()){
			poso.setServerId(dto.getServerId() );
		}

		/*  set xml */
		if(dto.isXmlModified()){
			poso.setXml(dto.getXml() );
		}

	}

	public Transport loadAndMergePoso(TransportDto dto)  throws ExpectedException {
		Transport poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TransportDto dto, Transport poso)  {
	}


	public void postProcessCreateUnmanaged(TransportDto dto, Transport poso)  {
	}


	public void postProcessLoad(TransportDto dto, Transport poso)  {
	}


	public void postProcessMerge(TransportDto dto, Transport poso)  {
	}


	public void postProcessInstantiate(Transport poso)  {
	}


	public boolean validateKeyProperty(TransportDto dto, Transport poso)  throws ExpectedException {
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
