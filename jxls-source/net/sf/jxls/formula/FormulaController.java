package net.sf.jxls.formula;

import java.util.Map;

import net.sf.jxls.transformation.BlockTransformation;

/**
 * @author Leonid Vysochyn
 */
public interface FormulaController {
    public void updateWorkbookFormulas(BlockTransformation transformation);
    public Map getSheetFormulasMap();

    void writeFormulas(FormulaResolver formulaResolver);
}
