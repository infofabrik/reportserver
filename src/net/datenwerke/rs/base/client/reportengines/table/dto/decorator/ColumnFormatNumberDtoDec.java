package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;

/**
 * Dto Decorator for {@link ColumnFormatNumberDto}
 *
 */
public class ColumnFormatNumberDtoDec extends ColumnFormatNumberDto {


	private static final long serialVersionUID = 1L;

	public ColumnFormatNumberDtoDec() {
		super();
		init();
	}

	private void init() {
		setNumberOfDecimalPlaces(2);
		setType(NumberTypeDto.DEFAULT);
	}

	@Override
	public ColumnFormatDto cloneFormat() {
		ColumnFormatNumberDtoDec clone = new ColumnFormatNumberDtoDec();
		
		clone.setThousandSeparator(isThousandSeparator());
		clone.setNumberOfDecimalPlaces(getNumberOfDecimalPlaces());
		clone.setType(getType());
		
		return clone;
	}


}
