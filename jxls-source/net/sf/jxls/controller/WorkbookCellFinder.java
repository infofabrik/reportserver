package net.sf.jxls.controller;

import java.util.List;

/**
 * Defines interface to find transformed cells in a workbook
 * @author Leonid Vysochyn
 */
public interface WorkbookCellFinder {

    List findCell(String sheetName, int rowNum, int colNum);

    List findCell(String sheetName, String cellName);

}
