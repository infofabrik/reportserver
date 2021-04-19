package net.sf.jxls.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellReference;

/**
 * Simple implementation of {@link net.sf.jxls.controller.WorkbookCellFinder} based on SheetCellFinder mapping to corresponding worksheets
 * @author Leonid Vysochyn
 */
public class WorkbookCellFinderImpl implements WorkbookCellFinder {

    Map sheetCellFinderMapping = new HashMap();

    public WorkbookCellFinderImpl() {
    }

    public WorkbookCellFinderImpl(Map sheetCellFinderMapping) {
        this.sheetCellFinderMapping = sheetCellFinderMapping;
    }

    public List findCell(String sheetName, String cellName) {
        CellReference cellReference = new CellReference( cellName );
        int colNum = cellReference.getCol();
        int rowNum = cellReference.getRow();
        return findCell( sheetName, rowNum, colNum );
    }

    public List findCell(String sheetName, int rowNum, int colNum) {
        if( !sheetCellFinderMapping.containsKey( sheetName ) ){
            throw new IllegalArgumentException("Can't find sheet with name " + sheetName + " used in formula cell reference");
        }
        return ((SheetCellFinder)sheetCellFinderMapping.get( sheetName )).findCell( rowNum, colNum );
    }

}
