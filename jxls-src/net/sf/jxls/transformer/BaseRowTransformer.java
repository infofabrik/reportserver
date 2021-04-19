package net.sf.jxls.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.formula.ListRange;
import net.sf.jxls.parser.Cell;
import net.sf.jxls.processor.CellProcessor;
import net.sf.jxls.tag.Block;
import net.sf.jxls.util.FormulaUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for {@link RowTransformer} impelementations
 * @author Leonid Vysochyn
 */
public abstract class BaseRowTransformer implements RowTransformer{

    protected static final Log log = LogFactory.getLog(BaseRowTransformer.class);

    /**
     * This list is used to store formula cells information while processing template file
     */
    private List formulas = new ArrayList();
    /**
     * This variable stores all list ranges found while processing template file
     */
    private Map listRanges = new HashMap();
    /**
     * Stores all named Cell objects
     */
    private Map namedCells = new HashMap();

    private List cellProcessors = new ArrayList();

    Row row;

    public Row getRow() {
        return row;
    }


    /**
     * Adds new {@link net.sf.jxls.formula.ListRange} to the map of ranges and updates formulas if there is range with the same name already
     *
     * @param sheet     Sheet to process
     * @param rangeName The name of {@link net.sf.jxls.formula.ListRange} to add
     * @param range     actual {@link net.sf.jxls.formula.ListRange} to add
     * @return true if a range with such name already exists or false if not
     */
    protected boolean addListRange(Sheet sheet, String rangeName, ListRange range) {
        if (listRanges.containsKey(rangeName)) {
            // update all formulas that can be updated and remove them from formulas list ( ignore all others )
            FormulaUtil.updateFormulas(sheet.getPoiSheet(), formulas, listRanges, namedCells, true);
            listRanges.put(rangeName, range);
            return true;
        }
        listRanges.put(rangeName, range);
        return false;
    }


    /**
     * Applies all registered CellProcessors to a cell
     *
     * @param cell - {@link net.sf.jxls.parser.Cell} object with cell information
     */
    protected void applyCellProcessors(Cell cell) {
        for (int i = 0, c = cellProcessors.size(); i < c; i++) {
            CellProcessor cellProcessor = (CellProcessor) cellProcessors.get(i);
            cellProcessor.processCell(cell, namedCells);
        }
    }

    public Block getTransformationBlock(){
        return null;
    }

    public void setTransformationBlock(Block block) {
    }
}
