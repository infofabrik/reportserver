package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.SearchResultTagType;
import net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultTagGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SearchResultTag
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SearchResultTagGenerator implements Dto2PosoGenerator<SearchResultTagDto,SearchResultTag> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SearchResultTagGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public SearchResultTag loadPoso(SearchResultTagDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SearchResultTag instantiatePoso()  {
		SearchResultTag poso = new SearchResultTag();
		return poso;
	}

	public SearchResultTag createPoso(SearchResultTagDto dto)  throws ExpectedException {
		SearchResultTag poso = new SearchResultTag();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SearchResultTag createUnmanagedPoso(SearchResultTagDto dto)  throws ExpectedException {
		SearchResultTag poso = new SearchResultTag();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		/*  set display */
		poso.setDisplay(dto.getDisplay() );

		/*  set type */
		SearchResultTagTypeDto tmpDto_type = dto.getType();
		poso.setType((SearchResultTagType)dtoServiceProvider.get().createPoso(tmpDto_type));

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		/*  set display */
		if(dto.isDisplayModified()){
			poso.setDisplay(dto.getDisplay() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			SearchResultTagTypeDto tmpDto_type = dto.getType();
			poso.setType((SearchResultTagType)dtoServiceProvider.get().createPoso(tmpDto_type));
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		/*  set display */
		poso.setDisplay(dto.getDisplay() );

		/*  set type */
		SearchResultTagTypeDto tmpDto_type = dto.getType();
		poso.setType((SearchResultTagType)dtoServiceProvider.get().createPoso(tmpDto_type));

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(SearchResultTagDto dto, final SearchResultTag poso)  throws ExpectedException {
		/*  set display */
		if(dto.isDisplayModified()){
			poso.setDisplay(dto.getDisplay() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			SearchResultTagTypeDto tmpDto_type = dto.getType();
			poso.setType((SearchResultTagType)dtoServiceProvider.get().createPoso(tmpDto_type));
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public SearchResultTag loadAndMergePoso(SearchResultTagDto dto)  throws ExpectedException {
		SearchResultTag poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SearchResultTagDto dto, SearchResultTag poso)  {
	}


	public void postProcessCreateUnmanaged(SearchResultTagDto dto, SearchResultTag poso)  {
	}


	public void postProcessLoad(SearchResultTagDto dto, SearchResultTag poso)  {
	}


	public void postProcessMerge(SearchResultTagDto dto, SearchResultTag poso)  {
	}


	public void postProcessInstantiate(SearchResultTag poso)  {
	}



}
