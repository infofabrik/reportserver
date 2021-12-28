package net.datenwerke.rs.base.client.reportengines.table.columnfilter;

public interface FilterService {

   public String getStringValue(Object value, Integer sqlTargetType);

   public Object valueOf(String value, Integer sqlTargetType) throws FilterServiceRuntimeException;
}
