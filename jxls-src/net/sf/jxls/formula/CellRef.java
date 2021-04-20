package net.sf.jxls.formula;

import org.apache.poi.ss.util.CellReference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonid Vysochyn
 */
public class CellRef {
    private static String leftReplacementMarker = "{";
    private static String rightReplacementMarker = "}";
    private static String regexReplacementMarker = "\\" + leftReplacementMarker + "[(),a-zA-Z0-9_ :*+/.-]+" + "\\" + rightReplacementMarker;
    protected static final String regexCellCharPart = "[0-9]+";
    protected static final String regexCellDigitPart = "[a-zA-Z]+";

    String cellRef;
    FormulaPart parentFormula;
    int rowNum;
    short colNum;
    String sheetName;

    Integer cellIndex;

    List rangeFormulaParts = new ArrayList();

    private CellRef(String cellRef) {
        this.cellRef = cellRef;
        CellReference cellReference = new CellReference(cellRef);
        rowNum = cellReference.getRow();
        colNum = cellReference.getCol();
        sheetName = cellReference.getSheetName();
    }

    public CellRef(String cellRef, FormulaPart parentFormula) {
        this(cellRef);
        this.parentFormula = parentFormula;
    }

    public CellRef(CellRef ref, FormulaPart parentFormula) {
        this.cellRef = ref.cellRef;
        this.parentFormula = parentFormula;
        this.rowNum = ref.rowNum;
        this.colNum = ref.colNum;
        this.sheetName = ref.sheetName;
        this.cellIndex = ref.cellIndex;
    }

    public String getSheetName() {
        String name = sheetName;
        if (sheetName != null && sheetName.indexOf(' ') >= 0) {
            name = "'" + sheetName + "'";
        }
        return name;
    }

    public int getRowNum() {
        return rowNum;
    }

    public short getColNum() {
        return colNum;
    }


    public Integer getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(Integer cellIndex) {
        this.cellIndex = cellIndex;
    }

    public void update(String newCellRef) {
        cellRef = newCellRef;
        CellReference cellReference = new CellReference(cellRef);
        rowNum = cellReference.getRow();
        colNum = cellReference.getCol();
        sheetName = cellReference.getSheetName();
    }

    public void update(List newCellRefs) {
        String refSheetName = extractRefSheetName(cellRef);
        String newCell = detectCellRange(refSheetName, newCellRefs);
        if (!rangeFormulaParts.isEmpty()) {
            parentFormula.replaceCellRef(this, rangeFormulaParts);
        } else {
            update(newCell);
        }
    }

    boolean containsSheetRef() {
        return (cellRef != null && cellRef.indexOf("!") >= 0);
    }

    protected static String cellRangeSeparator = ":";

    String detectCellRange(String refSheetName, List cells) {
        rangeFormulaParts.clear();
        cutSheetRefFromCells(cells);
        String firstCell = (String) cells.get(0);
        String range = firstCell;
        if (firstCell != null && firstCell.length() > 0) {
            if (isRowRange(cells) || isColumnRange(cells)) {
                String lastCell = (String) cells.get(cells.size() - 1);
                String refCellName = getRefCellName(refSheetName, firstCell);
                range = refCellName + cellRangeSeparator + lastCell.toUpperCase();
                rangeFormulaParts.add(new CellRef(refCellName, parentFormula));
                rangeFormulaParts.add(cellRangeSeparator);
                CellRef lastCellRef = new CellRef(lastCell.toUpperCase(), parentFormula);
                lastCellRef.sheetName = refSheetName;
                rangeFormulaParts.add(lastCellRef);
            } else {
                range = buildCommaSeparatedListOfCells(refSheetName, cells);
            }
        }
        return range;
    }

    private void cutSheetRefFromCells(List cells) {
        for (int i = 0, c = cells.size(); i < c; i++) {
            String cell = (String) cells.get(i);
            cells.set(i, extractCellName(cell));
        }
    }

    String buildCommaSeparatedListOfCells(String refSheetName, List cells) {
        StringBuilder listOfCellsBuilder = new StringBuilder();
        for (int i = 0, c = cells.size() - 1; i < c; i++) {
            String cell = (String) cells.get(i);
            String refCellName = getRefCellName(refSheetName, cell);
            listOfCellsBuilder.append(refCellName);
            listOfCellsBuilder.append(",");
            rangeFormulaParts.add(new CellRef(refCellName, parentFormula));
            rangeFormulaParts.add(",");
        }
        String refCellName = getRefCellName(refSheetName, (String) cells.get(cells.size() - 1));
        listOfCellsBuilder.append(refCellName);
        rangeFormulaParts.add(new CellRef(refCellName, parentFormula));
        return listOfCellsBuilder.toString();
    }


    String getRefCellName(String refSheetName, String cellName) {
        if (refSheetName == null) {
            return cellName.toUpperCase();
        }
        return refSheetName + "!" + cellName.toUpperCase();
    }

    boolean isColumnRange(List cells) {
        String firstCell = (String) cells.get(0);
        boolean isColumnRange = true;
        if (firstCell != null && firstCell.length() > 0) {
            String firstCellCharPart = firstCell.split(CellRef.regexCellCharPart)[0];
            String firstCellDigitPart = firstCell.split(CellRef.regexCellDigitPart)[1];
            int cellNumber = Integer.parseInt(firstCellDigitPart);
            String nextCell, cellCharPart, cellDigitPart;
            for (int i = 1, c = cells.size(); i < c && isColumnRange; i++) {
                nextCell = (String) cells.get(i);
                cellCharPart = nextCell.split(CellRef.regexCellCharPart)[0];
                cellDigitPart = nextCell.split(CellRef.regexCellDigitPart)[1];
                if (!firstCellCharPart.equalsIgnoreCase(cellCharPart) || Integer.parseInt(cellDigitPart) != ++cellNumber) {
                    isColumnRange = false;
                }
            }
        }
        return isColumnRange;
    }

    boolean isRowRange(List cells) {
        String firstCell = (String) cells.get(0);
        boolean isRowRange = true;
        if (firstCell != null && firstCell.length() > 0) {
            String firstCellDigitPart = firstCell.split(CellRef.regexCellDigitPart)[1];
            String nextCell, cellDigitPart;
            CellReference cellReference = new CellReference(firstCell);
            int cellNumber = cellReference.getCol();
            for (int i = 1, c = cells.size(); i < c && isRowRange; i++) {
                nextCell = (String) cells.get(i);
                cellDigitPart = nextCell.split(CellRef.regexCellDigitPart)[1];
                cellReference = new CellReference(nextCell);
                if (!firstCellDigitPart.equalsIgnoreCase(cellDigitPart) || cellReference.getCol() != ++cellNumber) {
                    isRowRange = false;
                }
            }
        }
        return isRowRange;
    }

    private String extractRefSheetName(String refCell) {
        if (refCell != null) {
            if (refCell.indexOf("!") < 0) {
                return null;
            }
            return refCell.substring(0, refCell.indexOf("!"));
        }
        return null;
    }

    private String extractCellName(String refCell) {
        if (refCell != null) {
            if (refCell.indexOf("!") < 0) {
                return refCell;
            }
            return refCell.substring(refCell.indexOf("!") + 1);
        }
        return null;
    }

    /**
     * Ref cell in a formula string is replaced with result cell enclosed with replacement markers to be able not to replace
     * already replaced cells
     *
     * @param formulaPart - Part of the formula to replace
     * @param refCell     - Cell name to replace
     * @param newCell     - New cell name after replacement
     * @return updated formula string
     */
    public static String replaceFormulaPart(String formulaPart, String refCell, String newCell) {
        String replacedFormulaPart = "";
        String[] parts = formulaPart.split(regexReplacementMarker, 2);
        for (; parts.length == 2; parts = formulaPart.split(regexReplacementMarker, 2)) {
            replacedFormulaPart += parts[0].replaceAll(refCell, leftReplacementMarker + newCell + rightReplacementMarker);
            int secondPartIndex;
            if (parts[1].length() != 0) {
                secondPartIndex = formulaPart.indexOf(parts[1], parts[0].length());
            } else {
                secondPartIndex = formulaPart.length();
            }
            replacedFormulaPart += formulaPart.substring(parts[0].length(), secondPartIndex);
            formulaPart = parts[1];
        }
        replacedFormulaPart += parts[0].replaceAll(refCell, leftReplacementMarker + newCell + rightReplacementMarker);
        return replacedFormulaPart;
    }

    public String toString() {
        return cellRef;
    }

    private static class CellRefInfo {
        private CellRefInfo(final short colNum, final int rowNum, final String sheetName) {
            this.colNum = colNum;
            this.rowNum = rowNum;
            this.sheetName = sheetName;
        }

        private int rowNum;
        private short colNum;
        private String sheetName;
    }
}
