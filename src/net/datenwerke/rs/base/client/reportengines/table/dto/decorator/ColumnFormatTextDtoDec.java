package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto;

/**
 * Dto Decorator for {@link ColumnFormatTextDto}
 *
 */
public class ColumnFormatTextDtoDec extends ColumnFormatTextDto {


	private static final long serialVersionUID = 1L;

	public ColumnFormatTextDtoDec() {
		super();
	}

	@Override
	public ColumnFormatDto cloneFormat() {
		ColumnFormatTextDtoDec clone = new ColumnFormatTextDtoDec();
		return clone;
	}


}
