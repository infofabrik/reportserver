package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;

/**
 * Dto Decorator for {@link ColumnFormatTemplateDto}
 *
 */
public class ColumnFormatTemplateDtoDec extends ColumnFormatTemplateDto {


	private static final long serialVersionUID = 1L;

	public ColumnFormatTemplateDtoDec() {
		super();
	}

	@Override
	public ColumnFormatDto cloneFormat() {
		ColumnFormatTemplateDtoDec clone = new ColumnFormatTemplateDtoDec();
		
		clone.setTemplate(getTemplate());
		
		return clone;
	}


}
