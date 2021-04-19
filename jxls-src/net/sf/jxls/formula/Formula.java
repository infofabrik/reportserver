package net.sf.jxls.formula;

import net.sf.jxls.parser.Cell;
import net.sf.jxls.transformer.Sheet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Represents formula cell
 * @author Leonid Vysochyn
 */
public class Formula {
    protected static final Log log = LogFactory.getLog(Formula.class);
    private static final ThreadLocal<Map<String, FormulaInfo>> cache = new ThreadLocal<Map<String, FormulaInfo>>();

    private String formula;
    private Integer rowNum;
    private Integer cellNum;
    static final String inlineFormulaToken = "#";
    static final String formulaListRangeToken = "@";

    private Sheet sheet;

    private Set cellRefs = new HashSet();

    private List formulaParts = new ArrayList();

    public static void clearCache(){
        cache.remove();
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Formula(String formula, Sheet sheet) {
        this.formula = formula;
        this.sheet = sheet;
        String cacheKey = (sheet!=null ? sheet.getSheetName() : "") + "!" + formula;
        Map<String, FormulaInfo> cacheMap = cache.get();
        if(cacheMap == null){
            cacheMap = new HashMap<String, FormulaInfo>();
            cache.set(cacheMap);
        }
        FormulaInfo fi = cacheMap.get(cacheKey);
        if (fi == null) {
            parseFormula();
            updateCellRefs();
            cacheMap.put(cacheKey, new FormulaInfo(this));
        } else {
            for (int i = 0, c = fi.formulaParts.size(); i < c; i++) {
              FormulaPart formulaPart = (FormulaPart) fi.formulaParts.get(i);
              formulaParts.add( new FormulaPart( formulaPart, this ) );
            }
            updateCellRefs();
        }
    }

    public Formula() {
    }

    public Formula(Formula f){
        this.formula = f.formula;
        this.sheet = f.getSheet();
        for (int i = 0, c = f.formulaParts.size(); i < c; i++) {
            FormulaPart formulaPart = (FormulaPart) f.formulaParts.get(i);
            formulaParts.add( new FormulaPart( formulaPart ) );
        }
        updateCellRefs();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }


    public Set getCellRefs() {
        return cellRefs;
    }

    public List getFormulaParts() {
        return formulaParts;
    }

    public boolean isInline() {
        return formula.indexOf(inlineFormulaToken) >= 0;
    }

    public String getInlineFormula(int n) {
        if (isInline()) {
            return formula.replaceAll(inlineFormulaToken, Integer.toString(n));
        }
        return formula;
    }

    /**
     * @return Formula string that should be set into Excel cell using POI
     */
    public String getAppliedFormula(Map listRanges, Map namedCells) {
        String codedFormula = formula;
        StringBuilder appliedFormulaBuilder = new StringBuilder();
        String delimiter = formulaListRangeToken;
        int index = codedFormula.indexOf(delimiter);
        boolean isExpression = false;
        while (index >= 0) {
            String token = codedFormula.substring(0, index);
            if (isExpression) {
                // this is formula coded expression variable
                // look into the listRanges to see do we have cell range for it
                if (listRanges.containsKey(token)) {
                    appliedFormulaBuilder.append(((ListRange) listRanges.get(token)).toExcelCellRange());
                } else if (namedCells.containsKey(token)) {
                    appliedFormulaBuilder.append( ((Cell) namedCells.get(token)).toCellName() );
                } else {
                    log.warn("can't find list range or named cell for " + token);
                    // returning null if we don't have given list range or named cell so we don't need to set formula to avoid error
                    return null;
                }
            } else {
                appliedFormulaBuilder.append( token );
            }
            codedFormula = codedFormula.substring(index + 1);
            index = codedFormula.indexOf(delimiter);
            isExpression = !isExpression;
        }
        appliedFormulaBuilder.append(codedFormula);
        return appliedFormulaBuilder.toString();
    }

    private static final String regexFormulaPart = "[a-zA-Z]+[0-9]*\\([^@()]+\\)@[0-9]+";

    private static final Pattern regexFormulaPartPattern = Pattern.compile( regexFormulaPart );

    public String getActualFormula(){
        FormulaPart formulaPart;
        StringBuilder actualFormulaBuilder = new StringBuilder();
        for (Iterator iterator = formulaParts.iterator(); iterator.hasNext();) {
            formulaPart = (FormulaPart) iterator.next();
            actualFormulaBuilder.append(formulaPart.getActualFormula());
        }
        return actualFormulaBuilder.toString();
    }

    public Set findRefCells() {
        Set refCells = new HashSet();
        for (Iterator iterator = formulaParts.iterator(); iterator.hasNext();) {
            FormulaPart formulaPart = (FormulaPart) iterator.next();
            refCells.addAll(formulaPart.getRefCells());
        }
        return refCells;
    }

    private void parseFormula(){
//        formulaParts.clear();
        Matcher formulaPartMatcher = regexFormulaPartPattern.matcher( formula );
        int end = 0;
        while( formulaPartMatcher.find() ){
            String formulaPartString = formula.substring(end, formulaPartMatcher.start());
            if( formulaPartString.length() > 0){
                formulaParts.add( new FormulaPart( formulaPartString, this) );
            }
            formulaParts.add( new FormulaPart(formulaPartMatcher.group(), this));
            end = formulaPartMatcher.end();
        }

        String endPart = formula.substring(end);
        if( endPart.length() > 0 ){
            formulaParts.add( new FormulaPart(endPart, this ));
        }
        updateCellRefs();
    }

    void updateCellRefs(){
        cellRefs = findRefCells();
    }

    public String toString() {
        return "Formula{" +
                "formula='" + formula + "'" +
                ", rowNum=" + rowNum +
                ", cellNum=" + cellNum +
                "}";
    }

    public boolean containsListRanges() {
        return formula.matches("[^)]*@.*");
    }

    public void removeCellRefs( Set cellRefsToRemove ){
        for (int i = 0, c = formulaParts.size(); i < c; i++) {
            FormulaPart formulaPart = (FormulaPart) formulaParts.get(i);
            formulaPart.removeCellRefs( cellRefsToRemove );
        }
        updateCellRefs();
    }

    public boolean updateReplacedRefCellsCollection(){
        boolean ret = false;
        for (Iterator iterator = formulaParts.iterator(); iterator.hasNext();) {
            FormulaPart formulaPart = (FormulaPart) iterator.next();
            if (formulaPart.updateReplacedRefCellsCollection( )) ret = true;
        }
        return ret;
    }

    private static class FormulaInfo {
        private FormulaInfo(final Formula formula) {
            this.formulaParts = new LinkedList();
            this.cellRefs = new HashSet();
            for (int i=0,c=formula.formulaParts.size(); i<c; i++) {
                FormulaPart newpart = new FormulaPart((FormulaPart)formula.formulaParts.get(i));
                this.formulaParts.add(newpart);
                this.cellRefs.addAll(newpart.cellRefs);
            }
        }

        private List formulaParts;
        private Set cellRefs;
    }
}
