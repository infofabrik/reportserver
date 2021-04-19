package net.datenwerke.gf.base.client.dtogenerator;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

/**
 * Dto base class for all ReportServer Dtos.
 *
 */
abstract public class RsDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159136471076859296L;

	public RsDto() {
		super();
	}
	
	@Override
	public boolean isModified() {
		return false;
	}
	
}
