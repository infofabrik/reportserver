package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;

/**
 * Dto Decorator for {@link ColumnFormatCurrencyDto}
 *
 */
public class ColumnFormatCurrencyDtoDec extends ColumnFormatCurrencyDto {


	private static final long serialVersionUID = 1L;

	public ColumnFormatCurrencyDtoDec() {
		super();
		init();
	}

	private void init() {
		setNumberOfDecimalPlaces(2);
		setType(NumberTypeDto.DEFAULT);
		setCurrencyType(CurrencyTypeDto.EURO);
	}

	@Override
	public ColumnFormatDto cloneFormat() {
		ColumnFormatCurrencyDtoDec clone = new ColumnFormatCurrencyDtoDec();
		
		clone.setThousandSeparator(isThousandSeparator());
		clone.setNumberOfDecimalPlaces(clone.getNumberOfDecimalPlaces());
		clone.setCurrencyType(getCurrencyType());
		
		return clone;
	}
}
