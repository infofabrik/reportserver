package net.sf.jxls.formula;

import org.apache.poi.ss.util.CellReference;


/**
 * Represents named list range (usually used in formulas resolving)
 *
 * @author Leonid Vysochyn
 */
public class ListRange {
    private int firstRowNum;
    private int lastRowNum;
    private String listName;
    private String listAlias;

    public ListRange(int firstRowNum, int lastRowNum, int cellNum) {
        this.firstRowNum = firstRowNum;
        this.lastRowNum = lastRowNum;
        this.cellNum = cellNum;
    }

    private int cellNum;

    public ListRange() {
    }

    public String toExcelCellRange() {
        CellReference firstCellRef = new CellReference(firstRowNum, cellNum, false, false);
        CellReference lastCellRef = new CellReference(lastRowNum, cellNum, false, false);
        return firstCellRef.formatAsString() + ":" + lastCellRef.formatAsString();
    }

    public int getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(int firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public int getLastRowNum() {
        return lastRowNum;
    }

    public void setLastRowNum(int lastRowNum) {
        this.lastRowNum = lastRowNum;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListAlias() {
        return listAlias;
    }

    public void setListAlias(String listAlias) {
        this.listAlias = listAlias;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }


    public String toString() {
        return "ListRange{" +
                "firstRowNum=" + firstRowNum +
                ", lastRowNum=" + lastRowNum +
                ", listName='" + listName + "'" +
                ", cellNum=" + cellNum +
                "}";
    }
}
