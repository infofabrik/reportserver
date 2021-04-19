package net.sf.jxls.controller;

import java.util.List;

import net.sf.jxls.transformer.Workbook;

/**
 * Defines interface to control workbook transformations
 * @author Leonid Vysochyn
 */
public interface WorkbookTransformationController {
    List getSheetTransformationControllers();
    void setSheetTransformationControllers(List sheetTransformationControllers);
    void addSheetTransformationController(SheetTransformationController sheetTransformationController);
    void removeSheetTransformationController(SheetTransformationController sheetTransformationController);
    Workbook getWorkbook();
}
