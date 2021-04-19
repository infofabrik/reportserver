package net.sf.jxls.transformation;

import java.util.ArrayList;
import java.util.List;

import net.sf.jxls.formula.CellRef;
import net.sf.jxls.tag.Block;
import net.sf.jxls.tag.Point;

import org.apache.poi.ss.util.CellReference;

/**
 * Defines simple shift transformation
 *
 * @author Leonid Vysochyn
 */
public class ShiftTransformation extends BlockTransformation {
    int rowShift, colShift;
    int rowNum;
    int colNum;
    private CellReference cellReference;
    private List cells = new ArrayList();
    private List points = new ArrayList();

    public ShiftTransformation(Block block, int rowShift, int colShift) {
        super(block);
        this.rowShift = rowShift;
        this.colShift = colShift;
    }

    public Block getBlockAfterTransformation() {
        return null;
    }

    public List transformCell(Point p) {
        points.clear();
        if (block.contains(p) || (rowShift != 0 && block.isAbove(p)) || (colShift != 0 && block.isToLeft(p))) {
            Point newPoint = p.shift(rowShift, colShift);
            points.add(newPoint);
        } else {
            points.add(p);
        }
        return points;
    }

    public List transformCell(String sheetName, CellRef cellRef) {
        cells.clear();
        String refSheetName = cellRef.getSheetName();
        if (block.contains(cellRef.getRowNum(), cellRef.getColNum()) || (rowShift != 0 && block.getEndRowNum() < cellRef.getRowNum())
             || (colShift != 0 && block.getEndCellNum() < cellRef.getColNum())) {
            if (block.getSheet().getSheetName().equalsIgnoreCase(refSheetName) || (cellRef.getSheetName() == null && block.getSheet().getSheetName().equalsIgnoreCase(sheetName))) {
                rowNum = cellRef.getRowNum() + rowShift;
                colNum = cellRef.getColNum() + colShift;
                // todo: remove this check
                if (colNum < 0) {
                    colNum = 0;
                }
                cellReference = new CellReference(rowNum, colNum, false, false);
                if (cellRef.getSheetName() != null) {
                    cells.add(cellRef.getSheetName() + "!" + cellReference.formatAsString());
                } else {
                    cells.add(cellReference.formatAsString());
                }
            }
        }
        return cells;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ShiftTransformation) {
            ShiftTransformation st = (ShiftTransformation) obj;
            return (super.equals(obj) && rowShift == st.rowShift && colShift == st.colShift);
        }
        return false;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + rowShift;
        result = 29 * result + colShift;
        return result;
    }

    public String toString() {
        return "ShiftTransformation: {" + super.toString() + ", shift=(" + rowShift + ", " + colShift + ")}";
    }
}
