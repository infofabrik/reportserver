package net.datenwerke.rs.base.service.reportengines.table.columnfilter;

import java.util.Map;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

public interface FilterService {

   Map<String, Map<String, Object>> getFilterMap(TableReport report);
   
}
