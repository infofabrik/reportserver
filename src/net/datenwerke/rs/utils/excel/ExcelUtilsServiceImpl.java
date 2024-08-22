package net.datenwerke.rs.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ExcelUtilsServiceImpl implements ExcelUtilsService {
   private static final int MAX_COUNT_COLUMNS_FOR_COMMENT = 33;
   private static final int MIN_COLUMN_WIDTH_FOR_ONE_COLUMN = 80;
   private static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;

   @Inject
   public ExcelUtilsServiceImpl() {
   }

   @Override
   public void setCellComment(Workbook workbook, int cellsCount, Row row, String text) {
      Cell cell = row.getCell(0);

      int col1 = 1;
      int row1 = cell.getRowIndex();
      int col2 = calculateWidth(cell, 1); // count columns
      int row2 = 4; // count rows

      int dx1 = workbook instanceof HSSFWorkbook ? 18 : 18 * Units.EMU_PER_PIXEL,
            dy1 = workbook instanceof HSSFWorkbook ? 30 : 30 * Units.EMU_PER_PIXEL,
            dx2 = workbook instanceof HSSFWorkbook ? 500 : 500 * Units.EMU_PER_PIXEL, dy2 = 0;

      CreationHelper createHelper = cell.getSheet().getWorkbook().getCreationHelper();
      RichTextString richTextString = createHelper.createRichTextString(text);
      Drawing<?> drawing = cell.getSheet().createDrawingPatriarch();
      ClientAnchor anchor = drawing.createAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
      Comment comment = drawing.createCellComment(anchor);
      comment.setString(richTextString);
      cell.setCellComment(comment);
   }

   private int calculateWidth(Cell cell, int col2) {
      int columnIndex = cell.getColumnIndex();
      int width = cell.getSheet().getColumnWidth(columnIndex);
      if (width / EXCEL_COLUMN_WIDTH_FACTOR < MIN_COLUMN_WIDTH_FOR_ONE_COLUMN) {
         for (int i = 0; i < MAX_COUNT_COLUMNS_FOR_COMMENT; i++) {
            width = width + cell.getSheet().getColumnWidth(i);
            if (width / EXCEL_COLUMN_WIDTH_FACTOR < MIN_COLUMN_WIDTH_FOR_ONE_COLUMN) {
               col2++;
               continue;
            }
            break;
         }
      }
      return col2;
   }
}
