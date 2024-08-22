package net.datenwerke.rs.base.service.reportengines.table.columnfilter;

import java.util.Map;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;

public interface FilterService {

   Map<String, Map<String, Object>> getFilterMap(TableReport report);

   Map<BlockType, Object> getPrefilterMap(TableReport report);

   int calculatePrefilterDepth(TableReport report);

}
