package net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen;

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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.Dto2EmailDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for EmailDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2EmailDatasinkGenerator implements Dto2PosoGenerator<EmailDatasinkDto,EmailDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2EmailDatasinkGenerator(
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

	public EmailDatasink loadPoso(EmailDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		EmailDatasink poso = entityManager.find(EmailDatasink.class, id);
		return poso;
	}

	public EmailDatasink instantiatePoso()  {
		EmailDatasink poso = new EmailDatasink();
		return poso;
	}

	public EmailDatasink createPoso(EmailDatasinkDto dto)  throws ExpectedException {
		EmailDatasink poso = new EmailDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public EmailDatasink createUnmanagedPoso(EmailDatasinkDto dto)  throws ExpectedException {
		EmailDatasink poso = new EmailDatasink();

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

	public void mergePoso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set encryptionPolicy */
		poso.setEncryptionPolicy(dto.getEncryptionPolicy() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set forceSender */
		try{
			poso.setForceSender(dto.isForceSender() );
		} catch(NullPointerException e){
		}

		/*  set host */
		poso.setHost(dto.getHost() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set port */
		try{
			poso.setPort(dto.getPort() );
		} catch(NullPointerException e){
		}

		/*  set sender */
		poso.setSender(dto.getSender() );

		/*  set senderName */
		poso.setSenderName(dto.getSenderName() );

		/*  set sslEnable */
		try{
			poso.setSslEnable(dto.isSslEnable() );
		} catch(NullPointerException e){
		}

		/*  set tlsEnable */
		try{
			poso.setTlsEnable(dto.isTlsEnable() );
		} catch(NullPointerException e){
		}

		/*  set tlsRequire */
		try{
			poso.setTlsRequire(dto.isTlsRequire() );
		} catch(NullPointerException e){
		}

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set encryptionPolicy */
		if(dto.isEncryptionPolicyModified()){
			poso.setEncryptionPolicy(dto.getEncryptionPolicy() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set forceSender */
		if(dto.isForceSenderModified()){
			try{
				poso.setForceSender(dto.isForceSender() );
			} catch(NullPointerException e){
			}
		}

		/*  set host */
		if(dto.isHostModified()){
			poso.setHost(dto.getHost() );
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

		/*  set sender */
		if(dto.isSenderModified()){
			poso.setSender(dto.getSender() );
		}

		/*  set senderName */
		if(dto.isSenderNameModified()){
			poso.setSenderName(dto.getSenderName() );
		}

		/*  set sslEnable */
		if(dto.isSslEnableModified()){
			try{
				poso.setSslEnable(dto.isSslEnable() );
			} catch(NullPointerException e){
			}
		}

		/*  set tlsEnable */
		if(dto.isTlsEnableModified()){
			try{
				poso.setTlsEnable(dto.isTlsEnable() );
			} catch(NullPointerException e){
			}
		}

		/*  set tlsRequire */
		if(dto.isTlsRequireModified()){
			try{
				poso.setTlsRequire(dto.isTlsRequire() );
			} catch(NullPointerException e){
			}
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set encryptionPolicy */
		poso.setEncryptionPolicy(dto.getEncryptionPolicy() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set forceSender */
		try{
			poso.setForceSender(dto.isForceSender() );
		} catch(NullPointerException e){
		}

		/*  set host */
		poso.setHost(dto.getHost() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set port */
		try{
			poso.setPort(dto.getPort() );
		} catch(NullPointerException e){
		}

		/*  set sender */
		poso.setSender(dto.getSender() );

		/*  set senderName */
		poso.setSenderName(dto.getSenderName() );

		/*  set sslEnable */
		try{
			poso.setSslEnable(dto.isSslEnable() );
		} catch(NullPointerException e){
		}

		/*  set tlsEnable */
		try{
			poso.setTlsEnable(dto.isTlsEnable() );
		} catch(NullPointerException e){
		}

		/*  set tlsRequire */
		try{
			poso.setTlsRequire(dto.isTlsRequire() );
		} catch(NullPointerException e){
		}

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(EmailDatasinkDto dto, final EmailDatasink poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set encryptionPolicy */
		if(dto.isEncryptionPolicyModified()){
			poso.setEncryptionPolicy(dto.getEncryptionPolicy() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set forceSender */
		if(dto.isForceSenderModified()){
			try{
				poso.setForceSender(dto.isForceSender() );
			} catch(NullPointerException e){
			}
		}

		/*  set host */
		if(dto.isHostModified()){
			poso.setHost(dto.getHost() );
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

		/*  set sender */
		if(dto.isSenderModified()){
			poso.setSender(dto.getSender() );
		}

		/*  set senderName */
		if(dto.isSenderNameModified()){
			poso.setSenderName(dto.getSenderName() );
		}

		/*  set sslEnable */
		if(dto.isSslEnableModified()){
			try{
				poso.setSslEnable(dto.isSslEnable() );
			} catch(NullPointerException e){
			}
		}

		/*  set tlsEnable */
		if(dto.isTlsEnableModified()){
			try{
				poso.setTlsEnable(dto.isTlsEnable() );
			} catch(NullPointerException e){
			}
		}

		/*  set tlsRequire */
		if(dto.isTlsRequireModified()){
			try{
				poso.setTlsRequire(dto.isTlsRequire() );
			} catch(NullPointerException e){
			}
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public EmailDatasink loadAndMergePoso(EmailDatasinkDto dto)  throws ExpectedException {
		EmailDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(EmailDatasinkDto dto, EmailDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(EmailDatasinkDto dto, EmailDatasink poso)  {
	}


	public void postProcessLoad(EmailDatasinkDto dto, EmailDatasink poso)  {
	}


	public void postProcessMerge(EmailDatasinkDto dto, EmailDatasink poso)  {
	}


	public void postProcessInstantiate(EmailDatasink poso)  {
	}


	public boolean validateKeyProperty(EmailDatasinkDto dto, EmailDatasink poso)  throws ExpectedException {
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
