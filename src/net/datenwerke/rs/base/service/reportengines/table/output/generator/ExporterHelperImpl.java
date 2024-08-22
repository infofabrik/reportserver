package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;

public class ExporterHelperImpl implements ExporterHelper {

   @Override
   public List<Column> getExportedColumns(final TableReport report, TableDefinition td) {
      List<Column> columns;
      if (report.isSelectAllColumns()) {
         columns = new ArrayList<Column>();
         for (Object[] c : td.getColumns()) {
            Column col = new Column();
            col.setName((String) c[0]);
            col.setType(((Integer) c[3]));
            columns.add(col);
         }
      } else {
         columns = report.getVisibleColumns();
      }
      return columns;
   }

   @Override
   public ImmutablePair<String[], Boolean[]> getNullFormats(List<Column> columns, CellFormatter[] cellFormatters,
         final TableDefinition td) {
      int columnCount = td.getColumns().size();
      String[] nullReplacements = new String[columnCount];
      Boolean[] exportNullAsString = new Boolean[columnCount];
      IntStream.range(0, columnCount).forEach(
            i -> prepareNullFormat(nullReplacements, exportNullAsString, cellFormatters[i], columns.get(i), i, td));
      return new ImmutablePair<>(nullReplacements, exportNullAsString);
   }

   private void prepareNullFormat(String[] nullReplacements, Boolean[] exportNullAsString, CellFormatter cellFormatter,
         Column column, int col, TableDefinition td) {
      if (null == cellFormatter) {
         nullReplacements[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat() : "NULL";
         exportNullAsString[col] = column.isExportNullAsString();
         return;
      }

      if (Integer.class.equals(td.getColumnType(col)) || Long.class.equals(td.getColumnType(col))
            || Byte.class.equals(td.getColumnType(col)) || Short.class.equals(td.getColumnType(col))) {
         nullReplacements[col] = cellFormatter.format(null);
      } else if (Double.class.equals(td.getColumnType(col)) || Float.class.equals(td.getColumnType(col))
            || BigDecimal.class.equals(td.getColumnType(col))) {

         nullReplacements[col] = cellFormatter.format(null);
      }
      exportNullAsString[col] = column.isExportNullAsString();
      if (null == nullReplacements[col])
         nullReplacements[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat() : "NULL";
   }

   @Override
   public ImmutablePair<String[], Boolean[]> getNullFormats(TableReport report, TableDefinition td,
         CellFormatter[] cellFormatters) {
      List<Column> columns = getExportedColumns(report, td);
      return getNullFormats(columns, cellFormatters, td);
   }
}
