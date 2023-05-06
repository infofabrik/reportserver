package net.datenwerke.rs.base.service.reportengines.table.dot;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.service.reportengines.table.dot.http.HttpExportService;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.security.service.usermanager.entities.User;

public class PrefilterDotServiceImpl implements PrefilterDotService {

   private final Provider<HttpExportService> httpExportServiceProvider;
   private final ReportDtoService reportDtoService;
   private final Provider<FilterService> filterServiceProvider;

   private StringBuilder digraph;
   private int currentOperatorNumber = 0;

   @Inject
   public PrefilterDotServiceImpl(
         Provider<HttpExportService> httpExportServiceProvider,
         ReportDtoService reportDtoService, 
         Provider<FilterService> filterServiceProvider
         ) {
      this.httpExportServiceProvider = httpExportServiceProvider;
      this.reportDtoService = reportDtoService;
      this.filterServiceProvider = filterServiceProvider;
   }

   @Override
   public String createDotFile(User user, TableReportDto reportDto, String executeToken) {
      String content = extractDotPreFilterSchema(user, reportDto, executeToken);
      /* store export */
      httpExportServiceProvider.get().storeExport(content, reportDto.getName());

      /* export report */
      return content;
   }

   protected String extractDotPreFilterSchema(User user, TableReportDto reportDto, String executeToken) {
      TableReport tableReport = (TableReport) reportDtoService.getReport(reportDto);

      digraph = new StringBuilder("digraph D {\r\n");

      String parentBlock = "";

      if (null != tableReport.getPreFilter() && null != tableReport.getPreFilter().getRootBlock())
         parentBlock = defineNewBlock(tableReport.getPreFilter().getRootBlock().getBlockType());

      Map<BlockType, Object> prefilterMap = filterServiceProvider.get().getPrefilterMap(tableReport);

      for (Entry<?, ?> entry : prefilterMap.entrySet()) {
         printPrefilterBlock((Set<?>) entry.getValue(), parentBlock);
      }

      return digraph.append("\r\n").append("}").toString();
   }

   private void printPrefilterBlock(Set<?> childElements, String parentBlock) {
      childElements.forEach(child -> {
         if (child instanceof Map) {
            Map<?, ?> childMap = (Map<?, ?>) child;
            childMap.forEach((childKey, childVal) -> {
               if (childKey instanceof BlockType && childVal instanceof Set) {
                  // FilterBlock                  
                  String childBlock = defineNewBlock((BlockType) childKey);
                  if (null != parentBlock) {
                     digraph
                        .append("\r\n")
                        .append(parentBlock)
                        .append(" -> ")
                        .append(childBlock)
                        .append(";\r\n");
                  }
                  printPrefilterBlock((Set<?>) childVal, childBlock);
               } else if (childKey instanceof String && childVal instanceof List) {
                  // BinaryColumnFilter
                  printBinaryColumnFilter((String) childKey, (List<?>) childVal, parentBlock);
               } else if (childKey instanceof String && childVal instanceof Map) {
                  // ColumnFilter
                  printColumnFilter((String) childKey, (Map<?, ?>) childVal, parentBlock);
               }
            });
         }
      });
   }

   private void printBinaryColumnFilter(String operator, List<?> values, String parentBlock) {
      if (2 != values.size())
         throw new IllegalArgumentException("Values of binary column filter must have size 2");
      
      currentOperatorNumber++;

      digraph.append("\r\n")
         .append("    e").append(currentOperatorNumber).append("[\r\n")
         .append("      color=\"#65c6db\"; \r\n")
         .append("      fontcolor=\"black\"; \r\n")
         .append("      style=filled;\r\n")
         .append("      fontsize=\"15pt\";\r\n")
         .append("      label=\"")
         .append(values.get(0).toString()) 
         .append(" ")
         .append(operator)
         .append(" ")
         .append(values.get(1).toString())
         .append("\";\r\n")
         .append("    ];\r\n");
      
      if (null != parentBlock) {
         digraph
            .append("    \r\n")
            .append(parentBlock)
            .append(" -> ")
            .append("e")
            .append(currentOperatorNumber)
            .append(";\r\n");
      }
   }

   private void printColumnFilter(String columnName, Map<?, ?> columnFilters, String parentBlock) {
      currentOperatorNumber++;
      
      String property = "";
      for (Entry<?, ?> entry : columnFilters.entrySet()) {
         property = property + "\r\n" + entry.getKey().toString() + " " + entry.getValue().toString();
      }

      digraph.append("\r\n")
         .append("    i")
         .append(currentOperatorNumber)
         .append("[\r\n")
         .append("      color=\"#328a3f\"; \r\n")
         .append("      fontcolor=\"black\"; \r\n")
         .append("      style=filled;\r\n")
         .append("      fontsize=\"15pt\";\r\n")
         .append("      label=\"")
         .append(columnName)
         .append(property)
         .append("\";\r\n")
         .append("    ];\r\n");
      
      if (null != parentBlock) {
         digraph
            .append("    \r\n")
            .append(parentBlock)
            .append(" -> ")
            .append("i")
            .append(currentOperatorNumber)
            .append(";\r\n");
      }
   }

   private String defineNewBlock(final BlockType blockType) {
      currentOperatorNumber++;

      switch (blockType) {
         case OR:
            digraph
               .append("    \r\n")
               .append("    or")
               .append(currentOperatorNumber)
               .append("[\r\n")
               .append("      color=\"#dec5a4\"; \r\n")
               .append("      fontcolor=\"black\"; \r\n")
               .append("      style=filled;\r\n")
               .append("      fontsize=\"15pt\";\r\n")
               .append("      label = \"OR\";\r\n")
               .append("    ];\r\n")
               .append("    \r\n");
            
            return "or" + currentOperatorNumber;
         case AND:
            digraph
               .append("    \r\n")
               .append("    and")
               .append(currentOperatorNumber)
               .append("[\r\n")
               .append("      color=\"#c48f68\"; \r\n")
               .append("      fontcolor=\"black\"; \r\n")
               .append("      style=filled;\r\n")
               .append("      fontsize=\"15pt\";\r\n")
               .append("      label = \"AND\";\r\n")
               .append("    ];\r\n")
               .append("    \r\n");
            return "and" + currentOperatorNumber;
      }
      
      return null;
   }

}
