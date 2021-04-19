package net.sf.jxls.util;

import net.sf.jxls.tag.Block;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Leonid Vysochyn
 */
public class TagBodyHelper {
    protected static final Log log = LogFactory.getLog(TagBodyHelper.class);

    public static int duplicateDown(Sheet sheet, Block block, int n) {
        if (n > 0) {
            int startRow = block.getEndRowNum() + 1;
            int endRow = Math.max( sheet.getLastRowNum(), sheet.getPhysicalNumberOfRows());
            int numberOfRows = block.getNumberOfRows() * n;
            Util.shiftRows(sheet, startRow, endRow, numberOfRows);
            for (int i = 0; i < n; i++) {
                for (int j = 0, c = block.getNumberOfRows(); j < c; j++) {
                    Row row = sheet.getRow(block.getStartRowNum() + j);
                    Row newRow = sheet.getRow(block.getEndRowNum() + block.getNumberOfRows() * i + 1 + j);
                    if (row != null) {
                        if (newRow == null) {
                            newRow = sheet.createRow(block.getEndRowNum() + block.getNumberOfRows() * i + 1 + j);
                        }
                        Util.copyRow(sheet, row, newRow);
                    }
                }
            }
            return block.getNumberOfRows() * n;
        }
        return 0;
    }

    public static int duplicateDown(Sheet sheet, Block block, int n, Map formulaCellsToUpdate) {
        if (n > 0) {
            Util.shiftRows(sheet, block.getEndRowNum() + 1, sheet.getLastRowNum(), block.getNumberOfRows() * n);
            for (int i = 0; i < n; i++) {
                for (int j = 0, c = block.getNumberOfRows(); j < c; j++) {
                    Row row = sheet.getRow(block.getStartRowNum() + j);
                    Row newRow = sheet.getRow(block.getEndRowNum() + block.getNumberOfRows() * i + 1 + j);
                    if (row != null) {
                        if (newRow == null) {
                            newRow = sheet.createRow(block.getEndRowNum() + block.getNumberOfRows() * i + 1 + j);
                        }
                        Util.copyRow(sheet, row, newRow);
                    }
                }
            }
            return block.getNumberOfRows() * n;
        }
        return 0;
    }

    public static int duplicateRight(Sheet sheet, Block block, int n) {
        if (n > 0) {
            Set mergedRegions = new HashSet();
            Util.shiftCellsRight(sheet, block.getStartRowNum(), block.getEndRowNum(),  (block.getEndCellNum() + 1),  (block.getNumberOfColumns() * n), true);
            for (int rowNum = block.getStartRowNum(), c = block.getEndRowNum(); rowNum <= c; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    for (int k = 0; k < n; k++) {
                        for (int cellNum = block.getStartCellNum(), c2 = block.getEndCellNum(); cellNum <= c2; cellNum++) {
                            int destCellNum =  (block.getEndCellNum() + k * block.getNumberOfColumns() + cellNum - block.getStartCellNum() + 1);
                            Cell destCell = row.getCell(destCellNum);
                            Cell cell = row.getCell(cellNum);
                            if (destCell == null) {
                                destCell = row.createCell(destCellNum);
                            }
                            Util.copyCell(cell, destCell, true);
                            Util.updateMergedRegionInRow(sheet, mergedRegions, rowNum, cellNum, destCellNum, false);
                            sheet.setColumnWidth(destCellNum, Util.getWidth(sheet, cellNum));
                        }
                    }
                }
            }
            return block.getNumberOfColumns() * n;
        }
        return 0;
    }

//    private static void updateMergedRegionInRow(Sheet sheet, Set mergedRegions, int rowNum, int cellNum) {
//        CellRangeAddress mergedRegion = Util.getMergedRegion(sheet, rowNum, cellNum);
//        if (mergedRegion != null) {
//            CellRangeAddress newMergedRegion = new CellRangeAddress(
//                    rowNum, rowNum + mergedRegion.getLastRow() - mergedRegion.getFirstRow(),
//                    mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
//            if (Util.isNewMergedRegion(newMergedRegion, mergedRegions)) {
//                mergedRegions.add(newMergedRegion);
//                sheet.addMergedRegion(newMergedRegion);
//            }
//        }
//    }

    public static void replaceProperty(Sheet sheet, Block block, String oldProperty, String newProperty) {
        for (int i = block.getStartRowNum(), c = block.getEndRowNum(); i <= c; i++) {
            Row row = sheet.getRow(i);
            replacePropertyInRow(row, oldProperty, newProperty);
        }
    }

    private static void replacePropertyInRow(Row row, String oldProperty, String newProperty) {
         if (row.getFirstCellNum() >= 0 && row.getLastCellNum() >= 0) {
              for (int j = row.getFirstCellNum(), c = row.getLastCellNum(); j <= c; j++) {
                    Cell cell = row.getCell(j);
                    replacePropertyInCell(cell, oldProperty, newProperty);
              }
         }
    }

    private static void replacePropertyInCell(Cell cell, String oldProperty, String newProperty) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getRichStringCellValue().getString();
            String newValue = cellValue.replaceAll(oldProperty, newProperty);
            cell.setCellValue(cell.getSheet().getWorkbook().getCreationHelper().createRichTextString(newValue));
        }
    }

    public static void removeBorders(Sheet sheet, Block block) {
        Row rowToDelete = sheet.getRow(block.getStartRowNum());
        deleteRow(sheet, rowToDelete);
        block.setStartRowNum(block.getStartRowNum() + 1);
        deleteRow(sheet, sheet.getRow(block.getEndRowNum()));
        block.setEndRowNum(block.getEndRowNum() - 1);
        shift(sheet, block, -1);
        if (block.getEndRowNum() + 2 < sheet.getLastRowNum()) {
            Util.shiftRows(sheet, block.getEndRowNum() + 3, sheet.getLastRowNum(), -2);
        }
    }

    private static void deleteRow(Sheet sheet, Row rowToDelete) {
        if (rowToDelete != null) {
            sheet.removeRow(rowToDelete);
        }
    }

    public static void removeLeftRightBorders(Sheet sheet, Block block) {
        Row row = sheet.getRow(block.getStartRowNum());
        if (row != null) {
            Util.shiftCellsLeft(sheet, block.getStartRowNum(),  (block.getStartCellNum() + 1),
                    block.getEndRowNum(), row.getLastCellNum(), 1, true);
            Cell cellToRemove = row.getCell(row.getLastCellNum());
            clearAndRemoveCell(row, cellToRemove);
            Util.shiftCellsLeft(sheet, block.getStartRowNum(), block.getEndCellNum(), block.getEndRowNum(), row.getLastCellNum(), 1, true);
            Cell cell = cellToRemove;
            clearAndRemoveCell(row, cell);
            block.setEndCellNum((int) (block.getEndCellNum() - 2));
        }
    }

    private static void clearAndRemoveCell(Row row, Cell cellToRemove) {
        clearCell(cellToRemove);
        if (cellToRemove != null) {
            row.removeCell(cellToRemove);
        }
    }

    public static void shift(Sheet sheet, Block block, int n) {
        Util.shiftRows(sheet, block.getStartRowNum(), block.getEndRowNum(), n);
        block.setStartRowNum(block.getStartRowNum() + n);
        block.setEndRowNum(block.getEndRowNum() + n);

    }


    public static void removeRowCells(Sheet sheet, Row row, int startCellNum, int endCellNum) {
        clearRowCells(row, startCellNum, endCellNum);
        Util.shiftCellsLeft(sheet, row.getRowNum(), (int) (endCellNum + 1), row.getRowNum(), row.getLastCellNum(), (int) (endCellNum - startCellNum + 1), true);
        clearRowCells(row, (int) (row.getLastCellNum() - (endCellNum - startCellNum)), row.getLastCellNum());
    }

    public static void removeBodyRows(Sheet sheet, Block block) {
        for (int i = 0, c = block.getNumberOfRows(); i < c; i++) {
            Row row = sheet.getRow(block.getStartRowNum() + i);
            removeMergedRegions(sheet, row);
            deleteRow(sheet, row);
        }
        Util.shiftRows(sheet, block.getEndRowNum() + 1, sheet.getLastRowNum(), -block.getNumberOfRows());
    }

    private static void removeMergedRegions(Sheet sheet, Row row) {
        if (row != null && row.getFirstCellNum() >= 0 && row.getLastCellNum() >= 0) {
            int i = row.getRowNum();
            for (int j = row.getFirstCellNum(), c = row.getLastCellNum(); j <= c; j++) {
                Util.removeMergedRegion(sheet, i, j);
            }
        }
    }

    static void clearRow(Row row) {
        if (row != null && row.getFirstCellNum() >= 0 && row.getLastCellNum() >= 0) {
            for (int i = row.getFirstCellNum(), c = row.getLastCellNum(); i <= c; i++) {
                Cell cell = row.getCell(i);
                clearCell(cell);
            }
        }
    }

    static void clearRowCells(Row row, int startCell, int endCell) {
        if (row != null && startCell >= 0 && endCell >= 0) {
            for (int i = startCell; i <= endCell; i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    row.removeCell(cell);
                }
                row.createCell(i);
            }
        }
    }

    static void clearCell(Cell cell) {
        if (cell != null) {
            cell.setCellValue(cell.getSheet().getWorkbook().getCreationHelper().createRichTextString(""));
            cell.setCellType(CellType.BLANK);
        }
    }

    public static void adjustFormulas(Workbook hssfWorkbook, Sheet hssfSheet, Block body) {
        for (int i = body.getStartRowNum(), c = body.getEndRowNum(); i <= c; i++) {
            Row row = hssfSheet.getRow(i);
            adjustFormulas(row);
        }
    }

    private static void adjustFormulas(Row row) {
        if (row != null && row.getFirstCellNum() >= 0 && row.getLastCellNum() >= 0) {
            for (int i = row.getFirstCellNum(), c = row.getLastCellNum(); i <= c; i++) {
                Cell cell = row.getCell(i);
                if (cell != null && cell.getCellType() == CellType.STRING && cell.getRichStringCellValue().getString().matches("\\$\\[.*?\\]")) {
                    String cellValue = cell.getRichStringCellValue().getString();
                    String[] parts = cellValue.split("\\$\\[.*?\\]");
                    String newCellValue = parts[0];
                    newCellValue = newCellValue.replaceAll("#", Integer.toString(row.getRowNum() + 1));

                    cell.setCellValue(cell.getSheet().getWorkbook().getCreationHelper().createRichTextString(newCellValue));
                }
            }
        }
    }
}
