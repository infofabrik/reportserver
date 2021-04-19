package net.sf.jxls.formula;

import net.sf.jxls.controller.WorkbookCellFinder;

/**
 * An interface to resolve coded formulas into real Excel formulas
 * @author Leonid Vysochyn
 */
public interface FormulaResolver {
    /**
     * This method resolves original formula coded in XLS template file into the real Excel formula
     * @param sourceFormula - {@link Formula} object representing coded formula found in XLS template file
     * @param cellFinder    - {@link net.sf.jxls.controller.WorkbookCellFinder}
     * @return Real Excel formula to be placed instead of the coded one
     */
    String resolve(Formula sourceFormula, WorkbookCellFinder cellFinder);
}
