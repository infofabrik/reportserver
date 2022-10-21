package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sencha.gxt.core.client.util.Util;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * Dto Decorator for {@link TableReportDto}
 *
 */
public class TableReportDtoDec extends TableReportDto {

   private static final long serialVersionUID = 1L;

   public TableReportDtoDec() {
      super();
   }

   public ColumnDto getColumnByName(String columnName) {
      if (null == columnName)
         return null;
      for (ColumnDto column : getColumns()) {
         if (!Util.isEmptyString(column.getAlias()) && columnName.equals(column.getAlias()))
            return column;
         if (Util.isEmptyString(column.getAlias()) && !Util.isEmptyString(column.getDefaultAlias())
               && columnName.equals(column.getDefaultAlias()))
            return column;
         if (Util.isEmptyString(column.getAlias()) && Util.isEmptyString(column.getDefaultAlias())
               && columnName.equals(column.getName()))
            return column;
      }
      return null;
   }

   public int getVisibleColumnPositionByName(String columnName) {
      ColumnDto col = getVisibleColumnByName(columnName);
      if (null == col)
         return -1;

      for (int i = 0; i <= getVisibleColumnCount() - 1; i++)
         if (col == getVisibleColumnByPos(i))
            return i;

      return -1;
   }

   public ColumnDto getVisibleColumnByName(String columnName) {
      ColumnDto col = getColumnByName(columnName);

      if (null == col)
         return null;

      if (col.isHidden())
         return null;

      return col;
   }

   public ColumnDto getVisibleColumnByPos(int pos) {
      int vis = 0;
      for (int i = 0; i < getColumns().size(); i++) {
         ColumnDto col = getColumns().get(i);
         if (null == col.isHidden() || !col.isHidden()) {
            if (vis == pos)
               return col;
            vis++;
         }
      }
      return null;
   }

   public int getVisibleColumnCount() {
      int vis = 0;
      for (int i = 0; i < getColumns().size(); i++) {
         ColumnDto col = getColumns().get(i);
         if (null == col.isHidden() || !col.isHidden()) {
            vis++;
         }
      }
      return vis;
   }

   public void moveColumnToPos(ColumnDto col, int index) {
      List<ColumnDto> cols = new ArrayList<ColumnDto>(getColumns());

      boolean found = false;
      int insertIndex = 0;
      for (Iterator iterator = cols.iterator(); iterator.hasNext();) {
         ColumnDto c = (ColumnDto) iterator.next();
         if (!found && c.equals(col)) {
            iterator.remove();
            found = true;
         }

         if (index > 0) {
            insertIndex++;
            if (null == c.isHidden() || !c.isHidden())
               index--;
         }
      }

      if (!found)
         return;

      if (insertIndex >= cols.size())
         cols.add(col);
      else
         cols.add(insertIndex, col);

      setColumns(cols);
   }

   public boolean hasAggregateColumn() {
      for (ColumnDto col : getColumns())
         if (null != col.getAggregateFunction())
            return true;
      return false;
   }

   public boolean hasSubtotalGroupColumn() {
      for (ColumnDto col : getColumns())
         if (col.isSubtotalGroup())
            return true;
      return false;
   }

   public boolean isBaseReportExecutable() {
      return false;
   }

   public void setPreviewColumnWidth(ColumnDto col, int cwidth) {
      col.setPreviewWidth(cwidth);
   }

}
