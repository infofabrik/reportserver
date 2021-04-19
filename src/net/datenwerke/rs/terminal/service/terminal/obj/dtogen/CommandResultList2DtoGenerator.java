package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultListDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultList2DtoGenerator;

/**
 * Poso2DtoGenerator for CommandResultList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResultList2DtoGenerator implements Poso2DtoGenerator<CommandResultList,CommandResultListDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResultList2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultListDtoDec instantiateDto(CommandResultList poso)  {
		CommandResultListDtoDec dto = new CommandResultListDtoDec();
		return dto;
	}

	public CommandResultListDtoDec createDto(CommandResultList poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultListDtoDec dto = new CommandResultListDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set denyBreakUp */
			dto.setDenyBreakUp(poso.isDenyBreakUp() );

			/*  set list */
			List<String> col_list = new ArrayList<String>();
			if( null != poso.getList()){
				for(String obj : poso.getList())
					col_list.add((String) obj);
				dto.setList(col_list);
			}

		}

		return dto;
	}


}
