package net.datenwerke.security.service.security.rights.dtogen;

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
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.security.rights.dtogen.Dto2WriteGenerator;

/**
 * Dto2PosoGenerator for Write
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2WriteGenerator implements Dto2PosoGenerator<WriteDto,Write> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2WriteGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public Write loadPoso(WriteDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public Write instantiatePoso()  {
		Write poso = new Write();
		return poso;
	}

	public Write createPoso(WriteDto dto)  throws ExpectedException {
		Write poso = new Write();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Write createUnmanagedPoso(WriteDto dto)  throws ExpectedException {
		Write poso = new Write();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(WriteDto dto, final Write poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(WriteDto dto, final Write poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(WriteDto dto, final Write poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(WriteDto dto, final Write poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(WriteDto dto, final Write poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(WriteDto dto, final Write poso)  throws ExpectedException {
	}

	public Write loadAndMergePoso(WriteDto dto)  throws ExpectedException {
		Write poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(WriteDto dto, Write poso)  {
	}


	public void postProcessCreateUnmanaged(WriteDto dto, Write poso)  {
	}


	public void postProcessLoad(WriteDto dto, Write poso)  {
	}


	public void postProcessMerge(WriteDto dto, Write poso)  {
	}


	public void postProcessInstantiate(Write poso)  {
	}



}
