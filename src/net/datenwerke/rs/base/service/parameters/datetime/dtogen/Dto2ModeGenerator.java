package net.datenwerke.rs.base.service.parameters.datetime.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.RuntimeException;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto;
import net.datenwerke.rs.base.service.parameters.datetime.Mode;
import net.datenwerke.rs.base.service.parameters.datetime.dtogen.Dto2ModeGenerator;

/**
 * Dto2PosoGenerator for Mode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ModeGenerator implements Dto2PosoGenerator<ModeDto,Mode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2ModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public Mode loadPoso(ModeDto dto)  {
		return createPoso(dto);
	}

	public Mode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public Mode createPoso(ModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Date:
				return Mode.Date;
			case Time:
				return Mode.Time;
			case DateTime:
				return Mode.DateTime;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public Mode createUnmanagedPoso(ModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(ModeDto dto, Mode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(ModeDto dto, Mode poso)  {
		/* no merging for enums */
	}

	public Mode loadAndMergePoso(ModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(ModeDto dto, Mode poso)  {
	}


	public void postProcessCreateUnmanaged(ModeDto dto, Mode poso)  {
	}


	public void postProcessLoad(ModeDto dto, Mode poso)  {
	}


	public void postProcessMerge(ModeDto dto, Mode poso)  {
	}


	public void postProcessInstantiate(Mode poso)  {
	}



}
