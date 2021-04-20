package net.sf.jxls.tag;

import org.apache.poi.ss.util.CellReference;

/**
 * Represents a single cell
 *
 * @author Leonid Vysochyn
 */
public class Point {
    int row;
    short col;

    public Point(int row, short col) {
        this.row = row;
        this.col = col;
    }

    public Point(String refCell) {
        CellReference cellReference = new CellReference(refCell);
        row = cellReference.getRow();
        col = cellReference.getCol();
    }

    public Point shift(int rowOffset, int colOffset) {
        return new Point(row + rowOffset, (short) (col + colOffset));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public short getCol() {
        return col;
    }

    public void setCol(short col) {
        this.col = col;
    }

    public String getCellRef() {
        CellReference cellRef = new CellReference(row, col, false, false);
        return cellRef.formatAsString();
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public String toString(String sheetName) {
        String cellname;
        CellReference cellRef = new CellReference(row, col, false, false);
        if (sheetName != null) {
            cellname = sheetName + "!" + cellRef.formatAsString();
        } else {
            cellname = cellRef.formatAsString();
        }
        return cellname;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Point point = (Point) o;

        if (col != point.col) {
            return false;
        }
        if (row != point.row) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = row;
        result = 29 * result + col;
        return result;
    }
}
