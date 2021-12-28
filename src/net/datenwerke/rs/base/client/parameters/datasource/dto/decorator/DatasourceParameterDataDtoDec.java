package net.datenwerke.rs.base.client.parameters.datasource.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;

/**
 * Dto Decorator for {@link DatasourceParameterDataDto}
 *
 */
public class DatasourceParameterDataDtoDec extends DatasourceParameterDataDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public DatasourceParameterDataDtoDec() {
		super();
	}
	
	public static DatasourceParameterDataDto cloneDataObject(
			DatasourceParameterDataDto dataObject) {
		DatasourceParameterDataDtoDec clone = new DatasourceParameterDataDtoDec();
		clone.setKey(dataObject.getKey());
		clone.setValue(dataObject.getValue());
		return clone;
	}
	
	@Override
	public int hashCode() {
		if(null == getKey())
			return super.hashCode();
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof DatasourceParameterDataDto))
			return false;

		
		DatasourceParameterDataDto data = (DatasourceParameterDataDto) obj;
		
		if(null == getKey() && null != data.getKey())
			return false;
			
		if(null == getValue() && null != data.getValue())
			return false;
		
		if(null != getKey() && ! getKey().equals(data.getKey()))
			return false;
			
		if(null != getValue() && ! getValue().equals(data.getValue()))
			return false;
		
		return true;
	}



}
