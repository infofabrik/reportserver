package net.datenwerke.rs.base.client.reportengines.table.columnfilter;



public class FilterServiceImpl implements FilterService{

	public String getStringValue(Object value, Integer sqlTargetType){
		return String.valueOf(value);
	}
	
	public Object valueOf(String value, Integer sqlTargetType) throws FilterServiceRuntimeException {
		return value;
	}


}
