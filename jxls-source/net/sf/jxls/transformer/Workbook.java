package net.sf.jxls.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.formula.FormulaController;
import net.sf.jxls.formula.FormulaControllerImpl;
import net.sf.jxls.util.SheetHelper;

/**
 * Represents excel workbook
 * @author Leonid Vysochyn
 */
public class Workbook {
    List sheets = new ArrayList();
    /**
     * POI Excel workbook object
     */
    org.apache.poi.ss.usermodel.Workbook hssfWorkbook;

    FormulaController formulaController;

    Configuration configuration = new Configuration();

    public Workbook(org.apache.poi.ss.usermodel.Workbook hssfWorkbook) {
        this.hssfWorkbook = hssfWorkbook;
    }

    public Workbook(org.apache.poi.ss.usermodel.Workbook hssfWorkbook, Configuration configuration) {
        this.hssfWorkbook = hssfWorkbook;
        this.configuration = configuration;
    }

    public Workbook(org.apache.poi.ss.usermodel.Workbook hssfWorkbook, List sheets) {
        this.hssfWorkbook = hssfWorkbook;
        this.sheets = sheets;
    }

    public Workbook(org.apache.poi.ss.usermodel.Workbook hssfWorkbook, List sheets, Configuration configuration) {
        this.hssfWorkbook = hssfWorkbook;
        this.sheets = sheets;
        this.configuration = configuration;
    }

    public org.apache.poi.ss.usermodel.Workbook getPoiWorkbook() {
        return hssfWorkbook;
    }

    public void setPoiWorkbook(org.apache.poi.ss.usermodel.Workbook hssfWorkbook) {
        this.hssfWorkbook = hssfWorkbook;
    }

    public void addSheet(Sheet sheet){
        sheets.add( sheet );
        sheet.setWorkbook( this );
    }

    public void initSheetNames(){
        for (int i = 0, c = sheets.size(); i < c; i++) {
            Sheet sheet = (Sheet) sheets.get(i);
            sheet.initSheetName();
        }
    }

    public Map getListRanges(){
        Map listRanges = new HashMap();
        for (int i = 0, c = sheets.size(); i < c; i++) {
            Sheet sheet = (Sheet) sheets.get(i);
            listRanges.putAll( sheet.getListRanges() );
        }
        return listRanges;
    }

    public List findFormulas(){
        List formulas = new ArrayList();
        for (int i = 0, c = sheets.size(); i < c; i++) {
            Sheet sheet = (Sheet) sheets.get(i);
            formulas.addAll( SheetHelper.findFormulas( sheet ) );
        }
        return formulas;
    }

    public Map createFormulaSheetMap(){
        Map formulas = new HashMap();
        for (int i = 0, c = sheets.size(); i < c; i++) {
            Sheet sheet = (Sheet) sheets.get(i);
            formulas.put( sheet.getSheetName(), SheetHelper.findFormulas( sheet ) );
        }
        return formulas;
    }

    public FormulaController createFormulaController(){
        formulaController = new FormulaControllerImpl( this );
        return formulaController;
    }

    public FormulaController getFormulaController() {
        return formulaController;
    }


    public List getSheets() {
        return sheets;
    }

    public int getNumberOfSheets(){
        return sheets.size();
    }

    public Sheet getSheetAt(int sheetNo){
        return (Sheet) sheets.get( sheetNo );
    }

    public void removeSheetAt(int sheetNo){
        hssfWorkbook.removeSheetAt(sheetNo);
        sheets.remove( sheetNo );
    }

}
