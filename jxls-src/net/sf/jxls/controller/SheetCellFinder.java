package net.sf.jxls.controller;

import java.util.List;

/**
 * This interface defines methods for searching transformed Cells inside a single sheet
 * @author Leonid Vysochyn
 */
public interface SheetCellFinder {
    
    List findCell(String cellName);

    List findCell(int rowNum, int colNum);

}
