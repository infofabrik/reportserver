package net.sf.jxls.formula;

import net.sf.jxls.controller.WorkbookCellFinder;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link FormulaResolver} interface resolving formulas containing list range and label cell references
 * like $[SUM(@employees.payment@)] and also formulas with direct cell references like $[SUM(E5)]
 * @author Leonid Vysochyn
 */
public class CommonFormulaResolver extends BaseFormulaResolver {

    /**
     * This implementation first checks are there any list ranges in the source formula.
     * If source formula contains any list ranges then resolve original formula by replacing all list range names with corresponding cells.
     * If there is no list ranges in the source formula then replace all transformed cells with their transformation results
     * also trying to detect and put corresponding cell ranges (like a10:a20 or a10:h10 for example)
     * @param sourceFormula
     * @param cellFinder
     * @return Adjusted formula string
     */
    public String resolve(Formula sourceFormula, WorkbookCellFinder cellFinder) {
        String resolvedFormula;
        if( sourceFormula.containsListRanges() ){
            resolvedFormula = replaceListRanges( sourceFormula );
        }
        else{
            resolvedFormula = sourceFormula.getActualFormula();
//            resolvedFormula = replaceTransformedCells( sourceFormula, cellFinder );
        }
        return resolvedFormula;
    }


    /**
     * This implementation finds all transformation result cells
     * corresponding to the original cells in source formula and replaces original cells with result cells.
     * Also it detects 'row' or 'column' range if transformed cells are adjacent row or columns cells
     * (like a10:a20 or a10:h10 for example). Non adjacent result cells are returned in a comma separated list.
     * @param sourceFormula
     * @param cellFinder
     * @return Adjusted formula string
     */
    String replaceTransformedCells(Formula sourceFormula, WorkbookCellFinder cellFinder) {
        Set refCells = sourceFormula.findRefCells();
        String adjustedFormula = sourceFormula.getFormula();
        for (Iterator iterator = refCells.iterator(); iterator.hasNext();) {
            String refCell = (String) iterator.next();
            String newCell = "";
            String refSheetName = extractRefSheetName( refCell );
            String cellName = extractCellName( refCell );
            String sheetName = refSheetName == null?sourceFormula.getSheet().getSheetName() : refSheetName;
            if( sheetName.startsWith("'") && sheetName.endsWith("'")){
                sheetName = sheetName.substring(1, sheetName.length() - 1);
            }
            List resultCells = cellFinder.findCell( sheetName, cellName );
            if( resultCells != null && !resultCells.isEmpty() ){
                if( resultCells.size() == 1 ){
                    newCell = (String) resultCells.get( 0 );
                    newCell = getRefCellName( refSheetName, newCell );
                }else{
                    newCell = detectCellRange( refSheetName, resultCells );
                }
            }
            // formula is replaced with result cell enclosed with replacement markers to be able not to replace
            // already replaced cells
            String formulaPart = adjustedFormula;
            adjustedFormula = replaceFormulaPart(formulaPart, refCell, newCell);
        }
        // remove replacement markers
        adjustedFormula = adjustedFormula.replaceAll( "\\" + leftReplacementMarker, "" );
        adjustedFormula = adjustedFormula.replaceAll( "\\" + rightReplacementMarker, "" );
        return adjustedFormula;
    }

    public static String replaceFormulaPart(String formulaPart, String refCell, String newCell) {
        String replacedFormulaPart = "";
        String[] parts = formulaPart.split(regexReplacementMarker, 2);
        for(; parts.length == 2; parts = formulaPart.split(regexReplacementMarker, 2) ){
            replacedFormulaPart += parts[0].replaceAll( refCell, leftReplacementMarker + newCell + rightReplacementMarker );
            int secondPartIndex = formulaPart.indexOf(parts[1], parts[0].length());
            replacedFormulaPart += formulaPart.substring( parts[0].length(), secondPartIndex );
            formulaPart = parts[1];
        }
        replacedFormulaPart += parts[0].replaceAll( refCell, leftReplacementMarker + newCell + rightReplacementMarker );
        return replacedFormulaPart;
    }

    private String extractCellName(String refCell) {
        if( refCell != null ){
            if( refCell.indexOf("!") < 0 ){
                return refCell;
            }
            return refCell.substring( refCell.indexOf("!") + 1 );
        }
        return null;
    }

    private String extractRefSheetName(String refCell) {
        if( refCell != null ){
            if( refCell.indexOf("!") < 0 ){
                // no sheet reference found
                return null;
            }
            return refCell.substring(0, refCell.indexOf("!") );
        }
        return null;
    }


    static String leftReplacementMarker = "{";
    static String rightReplacementMarker = "}";

    static String regexReplacementMarker = "\\" + leftReplacementMarker + "[(),a-zA-Z0-9_ :*+/.-]+" + "\\" + rightReplacementMarker;

}
