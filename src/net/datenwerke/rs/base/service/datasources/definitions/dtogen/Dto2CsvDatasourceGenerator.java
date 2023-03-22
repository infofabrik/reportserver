package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

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
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2CsvDatasourceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CsvDatasource
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CsvDatasourceGenerator implements Dto2PosoGenerator<CsvDatasourceDto,CsvDatasource> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CsvDatasourceGenerator(
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

	public CsvDatasource loadPoso(CsvDatasourceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		CsvDatasource poso = entityManager.find(CsvDatasource.class, id);
		return poso;
	}

	public CsvDatasource instantiatePoso()  {
		CsvDatasource poso = new CsvDatasource();
		return poso;
	}

	public CsvDatasource createPoso(CsvDatasourceDto dto)  throws ExpectedException {
		CsvDatasource poso = new CsvDatasource();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CsvDatasource createUnmanagedPoso(CsvDatasourceDto dto)  throws ExpectedException {
		CsvDatasource poso = new CsvDatasource();

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

	public void mergePoso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		/*  set connector */
		DatasourceConnectorDto tmpDto_connector = dto.getConnector();
		if(null != tmpDto_connector && null != tmpDto_connector.getId()){
			if(null != poso.getConnector() && null != poso.getConnector().getId() && poso.getConnector().getId().equals(tmpDto_connector.getId()))
				poso.setConnector((DatasourceConnector)dtoServiceProvider.get().loadAndMergePoso(tmpDto_connector));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (connector)");
		} else if(null != poso.getConnector()){
			DatasourceConnector newPropertyValue = (DatasourceConnector)dtoServiceProvider.get().createPoso(tmpDto_connector);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getConnector(), newPropertyValue, "connector");
			poso.setConnector(newPropertyValue);
		} else {
			poso.setConnector((DatasourceConnector)dtoServiceProvider.get().createPoso(tmpDto_connector));
		}

		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

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

		/*  set quote */
		poso.setQuote(dto.getQuote() );

		/*  set separator */
		poso.setSeparator(dto.getSeparator() );

	}

	protected void mergeProxy2Poso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		/*  set connector */
		if(dto.isConnectorModified()){
			DatasourceConnectorDto tmpDto_connector = dto.getConnector();
			if(null != tmpDto_connector && null != tmpDto_connector.getId()){
				if(null != poso.getConnector() && null != poso.getConnector().getId() && poso.getConnector().getId().equals(tmpDto_connector.getId()))
					poso.setConnector((DatasourceConnector)dtoServiceProvider.get().loadAndMergePoso(tmpDto_connector));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (connector)");
			} else if(null != poso.getConnector()){
				DatasourceConnector newPropertyValue = (DatasourceConnector)dtoServiceProvider.get().createPoso(tmpDto_connector);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getConnector(), newPropertyValue, "connector");
				poso.setConnector(newPropertyValue);
			} else {
				poso.setConnector((DatasourceConnector)dtoServiceProvider.get().createPoso(tmpDto_connector));
			}
		}

		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
			}
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

		/*  set quote */
		if(dto.isQuoteModified()){
			poso.setQuote(dto.getQuote() );
		}

		/*  set separator */
		if(dto.isSeparatorModified()){
			poso.setSeparator(dto.getSeparator() );
		}

	}

	public void mergeUnmanagedPoso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		/*  set connector */
		DatasourceConnectorDto tmpDto_connector = dto.getConnector();
		poso.setConnector((DatasourceConnector)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_connector));

		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

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

		/*  set quote */
		poso.setQuote(dto.getQuote() );

		/*  set separator */
		poso.setSeparator(dto.getSeparator() );

	}

	protected void mergeProxy2UnmanagedPoso(CsvDatasourceDto dto, final CsvDatasource poso)  throws ExpectedException {
		/*  set connector */
		if(dto.isConnectorModified()){
			DatasourceConnectorDto tmpDto_connector = dto.getConnector();
			poso.setConnector((DatasourceConnector)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_connector));
		}

		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
			}
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

		/*  set quote */
		if(dto.isQuoteModified()){
			poso.setQuote(dto.getQuote() );
		}

		/*  set separator */
		if(dto.isSeparatorModified()){
			poso.setSeparator(dto.getSeparator() );
		}

	}

	public CsvDatasource loadAndMergePoso(CsvDatasourceDto dto)  throws ExpectedException {
		CsvDatasource poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CsvDatasourceDto dto, CsvDatasource poso)  {
	}


	public void postProcessCreateUnmanaged(CsvDatasourceDto dto, CsvDatasource poso)  {
	}


	public void postProcessLoad(CsvDatasourceDto dto, CsvDatasource poso)  {
	}


	public void postProcessMerge(CsvDatasourceDto dto, CsvDatasource poso)  {
	}


	public void postProcessInstantiate(CsvDatasource poso)  {
	}


	public boolean validateKeyProperty(CsvDatasourceDto dto, CsvDatasource poso)  throws ExpectedException {
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
