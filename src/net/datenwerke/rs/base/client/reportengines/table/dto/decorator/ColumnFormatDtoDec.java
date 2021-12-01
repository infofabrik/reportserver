package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;

/**
 * Dto Decorator for {@link ColumnFormatDto}
 *
 */
public abstract class ColumnFormatDtoDec extends ColumnFormatDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public ColumnFormatDtoDec() {
		super();
	}

	public static ColumnFormatDto clone(ColumnFormatDto format) {
		if(null == format)
			return null;
		
		return ((ColumnFormatDtoDec)format).cloneFormat();
	}

	public abstract ColumnFormatDto cloneFormat();

}
