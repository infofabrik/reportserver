package net.datenwerke.rs.base.service.reportengines.table.columnfilter;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

public class FilterServiceImpl implements FilterService {

   @Override
   public Map<String, Map<String, Object>> getFilterMap(TableReport report) {
      Map<String, Map<String, Object>> filterMap = new HashMap<>();

      List<Column> columns = report.getColumns(); // get all columns

      List<Column> colsHavingFilter = 
            columns
            .stream()
            .filter(col -> null != col.getFilter())
            .collect(toList());

      colsHavingFilter.sort((col1, col2) -> col1.getName().compareTo(col2.getName()));

      if (!colsHavingFilter.isEmpty()) {
         colsHavingFilter.forEach(col -> {
            boolean hasFilter = false;

            if (null != col.getFilter()) {

               // include filters
               if (!col.getFilter().getIncludeValues().isEmpty()) {
                  hasFilter = true;
                  addFilterToMap(filterMap, col, "include", col.getFilter().getIncludeValues());
               }

               // include range
               if (!col.getFilter().getIncludeRanges().isEmpty()) {
                  hasFilter = true;
                  Object val = 
                        col.getFilter().getIncludeRanges()
                        .stream()
                        .map(filterRange -> Arrays.asList(filterRange.getRangeFrom(), filterRange.getRangeTo()))
                        .collect(toList());
                  addFilterToMap(filterMap, col, "include_range", val);
               }

               // exclude filters
               if (!col.getFilter().getExcludeValues().isEmpty()) {
                  hasFilter = true;
                  addFilterToMap(filterMap, col, "exclude", col.getFilter().getExcludeValues());
               }

               // exclude range
               if (!col.getFilter().getExcludeRanges().isEmpty()) {
                  hasFilter = true;
                  Object val = 
                        col.getFilter().getExcludeRanges()
                        .stream()
                        .map(filterRange -> Arrays.asList(filterRange.getRangeFrom(), filterRange.getRangeTo()))
                        .collect(toList());
                  addFilterToMap(filterMap, col, "exclude_range", val);
               }

               if (hasFilter) {
                  // null handling
                  String nullHandlingKey = "null_handling";
                  if (null == col.getNullHandling())
                     addFilterToMap(filterMap, col, nullHandlingKey, "--");
                  else
                     addFilterToMap(filterMap, col, nullHandlingKey, col.getNullHandling());

                  // case sensitive
                  addFilterToMap(filterMap, col, "case_sensitive", col.getFilter().isCaseSensitive());
               }

            }
         });
      }

      return filterMap;
   }

   private void addFilterToMap(Map<String, Map<String, Object>> filterMap, Column column, String type, Object value) {
      if (!filterMap.containsKey(column.getName()))
         filterMap.put(column.getName(), new HashMap<>());

      Map<String, Object> filterVals = filterMap.get(column.getName());
      filterVals.put(type, value);
   }

}
