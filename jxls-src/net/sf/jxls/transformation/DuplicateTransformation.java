package net.sf.jxls.transformation;

import net.sf.jxls.formula.CellRef;
import net.sf.jxls.tag.Block;
import net.sf.jxls.tag.Point;
import org.apache.poi.ss.util.CellReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines duplicate transformation for {@link Block}
 *
 * @author Leonid Vysochyn
 */
public class DuplicateTransformation extends BlockTransformation {

    int rowNum;
    int duplicateNumber;
    List cells = new ArrayList();

    public DuplicateTransformation(Block block, int duplicateNumber) {
        super(block);
        this.duplicateNumber = duplicateNumber;
    }

    public Block getBlockAfterTransformation() {
        return null;
    }

    public List transformCell(Point p) {
        List resultCells;
        if (block.contains(p)) {
            resultCells = new ArrayList();
            Point rp = p;
            resultCells.add(p);
            for (int i = 0; i < duplicateNumber; i++) {
                resultCells.add(rp = rp.shift(block.getNumberOfRows(), 0));
            }
        } else {
            resultCells = new ArrayList();
            resultCells.add(p);
        }
        return resultCells;
    }

    public String getDuplicatedCellRef(String sheetName, String cell, int duplicateBlock) {
        CellReference cellRef = new CellReference(cell);
        int row = cellRef.getRow();
        short col = cellRef.getCol();
        String refSheetName = cellRef.getSheetName();
        String resultCellRef = cell;
        if (block.getSheet().getSheetName().equalsIgnoreCase(refSheetName) || (refSheetName == null && block.getSheet().getSheetName().equalsIgnoreCase(sheetName))) {
            // sheet check passed
            if (block.contains(row, col) && duplicateNumber >= 1 && duplicateNumber >= duplicateBlock) {
                row += block.getNumberOfRows() * duplicateBlock;
                resultCellRef = cellToString(row, col, refSheetName);
            }
        }
        return resultCellRef;
    }

    public List transformCell(String sheetName, CellRef cellRef) {
        String refSheetName = cellRef.getSheetName();
        cells.clear();
        if (block.getSheet().getSheetName().equalsIgnoreCase(refSheetName) || (refSheetName == null && block.getSheet().getSheetName().equalsIgnoreCase(sheetName))) {
            // sheet check passed
            if (block.contains(cellRef.getRowNum(), cellRef.getColNum())) {
                rowNum = cellRef.getRowNum();
                if (cellRef.getCellIndex() == null) {
                    // transformation result is a set of cells
                    cells.add(cellToString(rowNum, cellRef.getColNum(), refSheetName));
                    for (int i = 0; i < duplicateNumber; i++) {
                        rowNum += block.getNumberOfRows();
                        cells.add(cellToString(rowNum, cellRef.getColNum(), refSheetName));
                    }
                } else {
                    // transformation result is a single cell according to index number
                    rowNum += block.getNumberOfRows() * (cellRef.getCellIndex().intValue());
                    cells.add(cellToString(rowNum, cellRef.getColNum(), refSheetName));
                }
            }
        }
        return cells;
    }

    public String cellToString(int row, int col, String sheetName) {
        String cellname;
        CellReference cellReference = new CellReference(row, col, false, false);
        if (sheetName != null) {
            cellname = sheetName + "!" + cellReference.formatAsString();
        } else {
            cellname = cellReference.formatAsString();
        }
        return cellname;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DuplicateTransformation) {
            DuplicateTransformation dt = (DuplicateTransformation) obj;
            return (super.equals(obj) && dt.duplicateNumber == duplicateNumber);
        }
        return false;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + duplicateNumber;
        return result;
    }

    public String toString() {
        return "DuplicateTransformation: {" + super.toString() + ", duplicateNumber=" + duplicateNumber + "}";
    }
}
