package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jxls.common.CellData;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.transform.poi.PoiUtil;

/**
 * Based on streaming-PoiTransformer. Deletes the comments in the original
 * template.
 */
public class JxlsStreamingTransformer extends PoiTransformer {

   public JxlsStreamingTransformer(Workbook workbook, int rowAccessWindowSize, boolean compressTmpFiles,
         boolean useSharedStringsTable) {
      super(workbook, true, rowAccessWindowSize, compressTmpFiles, useSharedStringsTable);
   }

   @Override
   public void transform(CellRef srcCellRef, CellRef targetCellRef, Context context, boolean updateRowHeightFlag) {

      CellData cellData = isTransformable(srcCellRef, targetCellRef);
      if (cellData == null) {
         return;
      }

      Sheet destSheet = getWorkbook().getSheet(targetCellRef.getSheetName());
      if (destSheet == null) {
         destSheet = getWorkbook().createSheet(targetCellRef.getSheetName());
         PoiUtil.copySheetProperties(getWorkbook().getSheet(srcCellRef.getSheetName()), destSheet);
      }

      Row destRow = destSheet.getRow(targetCellRef.getRow());

      if (destRow == null) {
         if (isStreaming()) {
            XSSFSheet _sh = ((SXSSFWorkbook) getWorkbook()).getXSSFWorkbook().getSheet(targetCellRef.getSheetName());
            if (_sh.getPhysicalNumberOfRows() > 0 && targetCellRef.getRow() <= _sh.getLastRowNum()) {
               destRow = _sh.getRow(targetCellRef.getRow());
               destSheet = _sh;
               if (destRow == null) {
                  destRow = destSheet.createRow(targetCellRef.getRow());
               }
            } else {
               destRow = destSheet.createRow(targetCellRef.getRow());
            }
         } else {
            destRow = destSheet.createRow(targetCellRef.getRow());
         }
      }
      transformCell(srcCellRef, targetCellRef, context, updateRowHeightFlag, cellData, destSheet, destRow);
   }

   @Override
   protected Row getRowForClearCell(Sheet sheet, CellRef cellRef) {
      Row row = super.getRowForClearCell(sheet, cellRef);
      // remove comments:
      if (row == null && isStreaming()) {
         XSSFSheet _sh = getXSSFWorkbook().getSheet(cellRef.getSheetName());
         if (_sh.getPhysicalNumberOfRows() > 0 && cellRef.getRow() <= _sh.getLastRowNum()) {
            row = _sh.getRow(cellRef.getRow());
         }
      }
      return row;
   }

   @Override
   public Workbook getWorkbook() {
      return super.getWorkbook();
   }
}
