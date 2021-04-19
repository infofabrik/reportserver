package net.sf.jxls.util;

import net.sf.jxls.formula.Formula;
import net.sf.jxls.parser.CellParser;
import net.sf.jxls.tag.Block;
import net.sf.jxls.transformer.Row;
import net.sf.jxls.transformer.Sheet;
import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonid Vysochyn
 */
public class SheetHelper {



    public static List findFormulas(Sheet sheet){
        return findFormulas( sheet, new Block(null, 0, sheet.getPoiSheet().getLastRowNum() ) );
    }

    public static List findFormulas(Sheet sheet, Block block){
        List formulas = new ArrayList();
        for(int i = block.getStartRowNum(); i <= block.getEndRowNum(); i++){
            org.apache.poi.ss.usermodel.Row hssfRow = sheet.getPoiSheet().getRow( i );
            if( block.isRowBlock() ){
                formulas.addAll( findFormulasInRow(sheet, hssfRow) );
            }else{
                formulas.addAll( findFormulasInRow(sheet, hssfRow, block.getStartCellNum(), block.getEndCellNum() ));
            }
        }
        return formulas;
    }


    private static List findFormulasInRow(Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow, int startCellNum, int endCellNum) {
        List formulas = new ArrayList();
        if( hssfRow!=null ){
            Row row = new Row(sheet, hssfRow);
            int endNum = (int)Math.min( hssfRow.getLastCellNum(), endCellNum);
            for(int i = (int)Math.max(hssfRow.getFirstCellNum(), startCellNum); i <= endNum; i++){
                Cell hssfCell = i<0?null:hssfRow.getCell( i );
                if( hssfCell!=null ){
                    CellParser cellParser = new CellParser(hssfCell, row, sheet.getConfiguration());
                    if( cellParser.parseCellFormula() != null && !cellParser.getCell().getFormula().isInline() ){
                        Formula formula = cellParser.getCell().getFormula();
                        formula.setSheet( sheet );
                        formulas.add( formula );
                    }
                }
            }
        }
        return formulas;
    }

    private static List findFormulasInRow(Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow) {
        List formulas = new ArrayList();
        if( hssfRow!=null ){
            Row row = new Row(sheet, hssfRow);
            CellParser cellParser;
            Formula formula;
            Cell hssfCell;
            for(int i = hssfRow.getFirstCellNum(); i <= hssfRow.getLastCellNum() && i > -1; i++){
                hssfCell = i<0?null:hssfRow.getCell( i );
                if( hssfCell!=null ){
                    cellParser = new CellParser(hssfCell, row, sheet.getConfiguration());
                    if( cellParser.parseCellFormula() != null && !cellParser.getCell().getFormula().isInline() ){
                        formula = cellParser.getCell().getFormula();
                        formula.setSheet( sheet );
                        formulas.add( formula );
                    }
                }
            }
        }
        return formulas;
    }
}
