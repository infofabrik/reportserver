package net.datenwerke.rs.dashboard.client.dashboard.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;

/**
 * Dto Decorator for {@link DadgetDto}
 *
 */
abstract public class DadgetDtoDec extends DadgetDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public DadgetDtoDec() {
		super();
		this.setContainer(DadgetContainerDto.CENTER);
	}
	
	@Override
	public DadgetContainerDto getContainer() {
		DadgetContainerDto cont = super.getContainer();
		return null != cont ? cont : DadgetContainerDto.CENTER;
	}

	

}
