package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;

/**
 * Dto Decorator for {@link ColumnReferenceDto}
 *
 */
public class ColumnReferenceDtoDec extends ColumnReferenceDto {


	private static final long serialVersionUID = 1L;

	public ColumnReferenceDtoDec() {
		super();
	}

	public ColumnDto cloneColumnForSelection() {
		ColumnReferenceDtoDec clone = new ColumnReferenceDtoDec();
		
		copyPropertiesTo(clone);
		
		return clone;
	}

	public void copyPropertiesTo(ColumnDto clone) {
		super.copyPropertiesTo(clone);
		((ColumnReferenceDtoDec)clone).setReference(getReference());
	}
	

	@Override
	public Integer getType() {
		if(null != getReference())
			return getReference().getType();
		return SqlTypes.VARCHAR;
	}
	
	@Override
	public String getName() {
		if(null == getReference())
			return "";
		return getReference().getName();
	}
	
	@Override
	public String getDescription() {
		if(null == getReference())
			return "";
		return getReference().getDescription();
	}
}
