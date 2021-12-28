package net.datenwerke.rs.base.service.reportengines.table.columnfilter;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.core.client.helper.ObjectHolder;

public class FilterServiceImpl implements FilterService {

   @Override
   public Map<String, Map<String, Object>> getFilterMap(TableReport report) {
      Map<String, Map<String, Object>> filterMap = new HashMap<>();

      List<Column> columns = report.getColumns();
      List<Column> colsHavingFilter = columns.stream().filter(col -> col.hasFilters()).collect(toList());
      colsHavingFilter.sort(comparing(Column::getName));

      if (!colsHavingFilter.isEmpty())
         colsHavingFilter.forEach(col -> filterMap.put(col.getName(), col.getFilterAsMap()));

      return filterMap;
   }

   @Override
   public Map<BlockType, Object> getPrefilterMap(TableReport report) {
      Map<BlockType, Object> prefilterMap = new HashMap<>();

      if (!report.hasPrefilters())
         return prefilterMap;

      prefilterMap = report.getPreFilter().getRootBlock().asMap();

      return prefilterMap;
   }

   @Override
   public int calculatePrefilterDepth(TableReport report) {
      PreFilter pf = report.getPreFilter();
      FilterBlock rootBlock = pf.getRootBlock();
      
      return doCalculatePrefilterDepth(rootBlock, 1);
   }

   private int doCalculatePrefilterDepth(FilterBlock block, int currentBlockDepth) {
      Set<FilterBlock> childBlocks = block.getChildBlocks();

      if (childBlocks.isEmpty())
         return currentBlockDepth;

      ObjectHolder<Integer> max = new ObjectHolder<>();
      max.set(0);
      childBlocks.forEach(childBlock -> {
         int depth = doCalculatePrefilterDepth(childBlock, currentBlockDepth + 1);
         if (depth > max.get())
            max.set(depth);
      });
      return max.get();
   }
}
