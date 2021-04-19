package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;

/**
 * Dto Decorator for {@link AdditionalColumnSpecDto}
 *
 */
public class AdditionalColumnSpecDtoDec extends AdditionalColumnSpecDto {


	private static final long serialVersionUID = 1L;

	public AdditionalColumnSpecDtoDec() {
		super();
	}

	public ColumnDto cloneColumnForSelection() {
		ColumnReferenceDtoDec clone = new ColumnReferenceDtoDec();
		
		copyPropertiesTo(clone);
		((ColumnReferenceDtoDec)clone).setReference(this);
		
		return clone;
	}

}
