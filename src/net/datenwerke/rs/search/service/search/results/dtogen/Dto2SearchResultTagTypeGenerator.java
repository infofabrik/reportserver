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
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.service.search.results.SearchResultTagType;
import net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultTagTypeGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SearchResultTagType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SearchResultTagTypeGenerator implements Dto2PosoGenerator<SearchResultTagTypeDto,SearchResultTagType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SearchResultTagTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public SearchResultTagType loadPoso(SearchResultTagTypeDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SearchResultTagType instantiatePoso()  {
		SearchResultTagType poso = new SearchResultTagType();
		return poso;
	}

	public SearchResultTagType createPoso(SearchResultTagTypeDto dto)  throws ExpectedException {
		SearchResultTagType poso = new SearchResultTagType();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SearchResultTagType createUnmanagedPoso(SearchResultTagTypeDto dto)  throws ExpectedException {
		SearchResultTagType poso = new SearchResultTagType();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		/*  set display */
		poso.setDisplay(dto.getDisplay() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2Poso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		/*  set display */
		if(dto.isDisplayModified()){
			poso.setDisplay(dto.getDisplay() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public void mergeUnmanagedPoso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		/*  set display */
		poso.setDisplay(dto.getDisplay() );

		/*  set type */
		poso.setType(dto.getType() );

	}

	protected void mergeProxy2UnmanagedPoso(SearchResultTagTypeDto dto, final SearchResultTagType poso)  throws ExpectedException {
		/*  set display */
		if(dto.isDisplayModified()){
			poso.setDisplay(dto.getDisplay() );
		}

		/*  set type */
		if(dto.isTypeModified()){
			poso.setType(dto.getType() );
		}

	}

	public SearchResultTagType loadAndMergePoso(SearchResultTagTypeDto dto)  throws ExpectedException {
		SearchResultTagType poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SearchResultTagTypeDto dto, SearchResultTagType poso)  {
	}


	public void postProcessCreateUnmanaged(SearchResultTagTypeDto dto, SearchResultTagType poso)  {
	}


	public void postProcessLoad(SearchResultTagTypeDto dto, SearchResultTagType poso)  {
	}


	public void postProcessMerge(SearchResultTagTypeDto dto, SearchResultTagType poso)  {
	}


	public void postProcessInstantiate(SearchResultTagType poso)  {
	}



}
