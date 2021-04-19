package net.sf.jxls.controller;

import net.sf.jxls.tag.Block;
import net.sf.jxls.transformer.RowCollection;
import net.sf.jxls.transformer.Sheet;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * Interface for controlling all excel sheet transformations
 * @author Leonid Vysochyn
 */
public interface SheetTransformationController {
    /**
     * @return {@link net.sf.jxls.transformer.Sheet} corresponding worksheet object
     */
    Sheet getSheet();

    /**
     * This method duplicates given block to the right
     * @param block - {@link Block} to process
     * @param n - number of times to duplicate given block
     * @return shift number based on number of affected rows
     */
    int duplicateRight(Block block, int n);

    /**
     * This method duplicates given block down
     * @param block - {@link Block} to process
     * @param n - number of times to duplicate given block
     * @return shift number based on number of affected rows
     */
    int duplicateDown( Block block, int n );

    /**
     * This method removes borders around given block shifting all other rows
     * @param block - {@link Block} to process
     */
    void removeBorders(Block block);

    /**
     * This method removes left and right borders for the block
     * @param block - {@link Block} to process
     */
    void removeLeftRightBorders(Block block);

    /**
     * Clears row cells in a given range
     * @param row {@link net.sf.jxls.transformer.Row} to process
     * @param startCellNum - start cell number to clear
     * @param endCellNum   - end cell number to clear
     */
    void removeRowCells(Row row, int startCellNum, int endCellNum);

    /**
     * Deletes the body of the block
     * @param block {@link Block} to process
     */
    void removeBodyRows(Block block);

    /**
     * Duplicates given row cells according to passed {@link RowCollection} object
     * @param rowCollection - {@link RowCollection} object defining duplicate numbers and cell ranges
     */
    void duplicateRow(RowCollection rowCollection);

    /**
     * @return list of all transformation applied by this controller
     */
    List getTransformations();

}
