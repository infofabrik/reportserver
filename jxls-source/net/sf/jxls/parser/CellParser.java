package net.sf.jxls.parser;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.formula.Formula;
import net.sf.jxls.tag.Block;
import net.sf.jxls.tag.Tag;
import net.sf.jxls.tag.TagContext;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.Row;
import net.sf.jxls.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 * Class for parsing excel cell
 * @author Leonid Vysochyn
 */
public class CellParser {
    protected static final Log log = LogFactory.getLog(CellParser.class);

    private final Cell cell;

    private Configuration configuration;

    public CellParser(org.apache.poi.ss.usermodel.Cell hssfCell, Row row, Configuration configuration) {
        this.cell = new Cell( hssfCell, row );
        if( configuration!=null ){
            this.configuration = configuration;
        }else{
            this.configuration = new Configuration();
        }
    }



    public CellParser(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public Cell parseCell(Map beans){
        org.apache.poi.ss.usermodel.Cell c = cell.getPoiCell();
        if (c != null) {
            try {
                if( c.getCellType() == CellType.STRING ){
                    cell.setPoiCellValue(c.getRichStringCellValue().getString());
                    parseCellValue( beans);
                }
            } catch (ParsePropertyException e) {
                log.error("Can't get value for property=" + cell.getCollectionProperty().getProperty(), e);
                throw new RuntimeException(e);
            }
            updateMergedRegions();
        }
        return cell;
    }

    public Formula parseCellFormula(){
        if( cell.getPoiCell() != null && (cell.getPoiCell().getCellType() == CellType.STRING)) {
            cell.setPoiCellValue( cell.getPoiCell().getRichStringCellValue().getString() );
            if( cell.getPoiCellValue().startsWith(configuration.getStartFormulaToken()) && cell.getPoiCellValue().lastIndexOf(configuration.getEndFormulaToken()) > 0 ){
                parseFormula();
            }
        }
        return cell.getFormula();
    }

    private void parseFormula() {
        // process formula cell
        final String poiCellValue = cell.getPoiCellValue();
        int i = poiCellValue.lastIndexOf(configuration.getEndFormulaToken());
        String expr = poiCellValue.substring(2, i);
        cell.setFormula(new Formula(expr, cell.getRow().getSheet()));
        cell.getFormula().setRowNum(cell.getRow().getPoiRow().getRowNum());
        cell.getFormula().setCellNum(cell.getPoiCell().getColumnIndex());
        if (i + 1 < poiCellValue.length()) {
            String tail = poiCellValue.substring(i + 1);
            int j = tail.indexOf(configuration.getMetaInfoToken());
            if( j >= 0 ){
                cell.setMetaInfo(tail.substring(j));
                if( j > 0 ){
                    cell.setLabel(tail.substring(0, j));
                }
                cell.setCollectionName(tail.substring(j + 2));
            }else{
                cell.setLabel(tail);
            }
        }
        cell.setStringCellValue(poiCellValue.substring(0, i + 1));
    }

    private void parseCellExpression(Map beans) {
        cell.setCollectionProperty(null);
        String curValue = cell.getPoiCellValue();
        String cv = curValue;
        int depRowNum = 0;
        int j = curValue.lastIndexOf(configuration.getMetaInfoToken());
        if( j>=0 ){
            cell.setStringCellValue(cv.substring(0, j));
            cell.setMetaInfo(cv.substring(j + 2));
            String tail = curValue.substring(j + 2);
            // processing additional parameters
                // check if there is collection property name specified
                int k = tail.indexOf(":");
                if( k >= 0 ){
                    try {
                        depRowNum = Integer.parseInt( tail.substring(k+1) );
                    } catch (NumberFormatException e) {
                        // ignore it if not an integer
                    }
                    cell.setCollectionName(tail.substring(0, k));
                }else{
                    cell.setCollectionName(tail);
                }
                curValue = curValue.substring(0, j);
        }else{
            cell.setStringCellValue(cv);
        }

        try {
            while( curValue.length()>0 ){
                int i = curValue.indexOf(configuration.getStartExpressionToken());
                if( i>=0 ) {
                    int k = curValue.indexOf(configuration.getEndExpressionToken(), i+2);
                    if( k>=0 ){
                        // new bean property found
                        String expr = curValue.substring(i+2, k);
                        if( i>0 ){
                            String before = curValue.substring(0, i);
                            cell.getExpressions().add( new Expression( before, configuration ) );
                        }
                        Expression expression = new Expression(expr, beans, configuration);
                        if( expression.getCollectionProperty() != null ){
                            if( cell.getCollectionProperty() == null ){
                                cell.setCollectionName(expression.getCollectionProperty().getFullCollectionName());
                                cell.setCollectionProperty(expression.getCollectionProperty());
                                cell.setDependentRowNumber(depRowNum);
                            }else{
                                if( log.isInfoEnabled() ){
                                    log.info("Only the same collection property in a cell is allowed.");
                                }
                            }
                        }
                        cell.getExpressions().add( expression );
                        curValue = curValue.substring(k+1, curValue.length());
                    }else{
                        cell.getExpressions().add( new Expression(curValue, configuration) );
                        curValue = "";
                    }
                }else{
                    if( curValue.length()!=cv.length() ){
                        cell.getExpressions().add( new Expression( curValue, configuration ));
                    }
                    curValue = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Can't parse expression", e);
        }
    }

    private void parseCellValue(Map beans) throws ParsePropertyException {
        String cv = cell.getPoiCellValue();
        if( cv !=null ){
            if( cv.startsWith(configuration.getStartFormulaToken()) && cv.lastIndexOf(configuration.getEndFormulaToken()) > 0 ){
                parseFormula();
            }
            else if(cv.startsWith( configuration.getTagPrefixWithBrace() )){
//                String tagName = cell.getPoiCellValue().split("(?<=<" + configuration.getTagPrefix() + ")\\w+", 2)[0];
                String tagName = getTagName( cv );
                if( tagName!=null ){
                    final Row row = cell.getRow();
                    final org.apache.poi.ss.usermodel.Row poiRow = row.getPoiRow();
                    final org.apache.poi.ss.usermodel.Cell poiCell = cell.getPoiCell();
                    final int rowNum = poiRow.getRowNum();
                    final int columnIndex = poiCell.getColumnIndex();
                    if (cv.endsWith("/>")) {
                        Block tagBody = new Block(rowNum, columnIndex, rowNum, columnIndex);
                        parseTag( tagName, tagBody, beans, false);
                    } else {
                        org.apache.poi.ss.usermodel.Cell hssfCell = findMatchingPairInRow(poiRow, tagName );
                        if( hssfCell!=null ){
                            // closing tag is in the same row
                            Block tagBody = new Block(rowNum, columnIndex, rowNum, hssfCell.getColumnIndex());
                            parseTag( tagName, tagBody, beans, true);
                        }else{
                            org.apache.poi.ss.usermodel.Row hssfRow = findMatchingPair( tagName );
                            if( hssfRow!=null ){
                                // closing tag is in hssfRow
                                int lastTagBodyRowNum = hssfRow.getRowNum() ;
                                Block tagBody = new Block(null, rowNum, lastTagBodyRowNum);
                                parseTag( tagName, tagBody, beans , true);
                            }else{
                                log.error("Can't find matching tag pair for " + cv);
                            }
                        }
                    }
                }
            }else{
                parseCellExpression(beans);
            }
        }
    }

    private org.apache.poi.ss.usermodel.Cell findMatchingPairInRow(org.apache.poi.ss.usermodel.Row hssfRow, String tagName) {
        int count = 0;
        if( hssfRow!=null ){
            for(int j = (cell.getPoiCell().getColumnIndex() + 1); j <= hssfRow.getLastCellNum(); j++){
                org.apache.poi.ss.usermodel.Cell hssfCell = hssfRow.getCell( j );
                if( hssfCell != null && hssfCell.getCellType() == CellType.STRING ){
                    String cellValue = hssfCell.getRichStringCellValue().getString();
                    if( cellValue.matches("<" + configuration.getTagPrefix() + tagName + "\\b.*")){
                        count++;
                    }else{
                        if( cellValue.matches("</" + configuration.getTagPrefix() + tagName + ">" )){
                            if( count == 0 ){
                                return hssfCell;
                            }
                            count--;
                        }
                    }
                }
            }
        }
        return null;
    }

    private String getTagName(String xmlTag){
        int i = configuration.getTagPrefix().length() + 1;
        int j = i;
        while( j < xmlTag.length() && Character.isLetterOrDigit( xmlTag.charAt( j ) ) ){
            j++;
        }
        if( j == xmlTag.length() ){
            log.warn("can't determine tag name");
            return null;
        }
        return xmlTag.substring(i, j);
    }

    private org.apache.poi.ss.usermodel.Row findMatchingPair(String tagName) {
        Sheet hssfSheet = cell.getRow().getSheet().getPoiSheet();
        int count = 0;

        for( int i = cell.getRow().getPoiRow().getRowNum() + 1; i <= hssfSheet.getLastRowNum(); i++ ){
            org.apache.poi.ss.usermodel.Row hssfRow = hssfSheet.getRow( i );
            if( hssfRow!=null && hssfRow.getFirstCellNum() >= 0 && hssfRow.getLastCellNum() >= 0 ){
                for(short j = hssfRow.getFirstCellNum(); j <= hssfRow.getLastCellNum(); j++){
                    org.apache.poi.ss.usermodel.Cell hssfCell = hssfRow.getCell( (int)j );
                    if( hssfCell != null && hssfCell.getCellType() == CellType.STRING ){
                        String cellValue = hssfCell.getRichStringCellValue().getString();
                        if( cellValue.matches("<" + configuration.getTagPrefix() + tagName + "\\b.*")){
                            count++;
                        }else{
                            if( cellValue.matches("</" + configuration.getTagPrefix() + tagName + ">" )){
                                if( count == 0 ){
                                    return hssfRow;
                                }
                                count--;
                            }
                        }
                    }
                }
            }

        }

        return null;
    }

    private void parseTag(String tagName, Block tagBody, Map beans, boolean appendCloseTag){

        String xml = null;

        try {
            if (appendCloseTag) {
                xml = configuration.getJXLSRoot() + cell.getPoiCellValue() + "</" + configuration.getTagPrefix() + tagName + ">" + configuration.getJXLSRootEnd();
            } else {
                xml = configuration.getJXLSRoot() + cell.getPoiCellValue() + configuration.getJXLSRootEnd();
            }
            if (configuration.getEncodeXMLAttributes()) {
                xml = Util.escapeAttributes( xml );
            }
            Tag tag = (Tag) configuration.getDigester().parse(new StringReader( xml ) );
            if (tag == null) {
                throw new RuntimeException("Invalid tag: " + tagName);
            }
            cell.setTag( tag );
            TagContext tagContext = new TagContext( cell.getRow().getSheet(), tagBody, beans );
            tag.init( tagContext );
        } catch (IOException e) {
            log.warn( "Can't parse cell tag " + cell.getPoiCellValue() + ": fullXML: " + xml, e);
            throw new RuntimeException("Can't parse cell tag " + cell.getPoiCellValue() + ": fullXML: " + xml, e);
        } catch (SAXException e) {
            log.warn( "Can't parse cell tag " + cell.getPoiCellValue() + ": fullXML: " + xml, e);
            throw new RuntimeException("Can't parse cell tag " + cell.getPoiCellValue() + ": fullXML: " + xml, e);
        }
    }

    private void updateMergedRegions() {
        Row row = cell.getRow();
        cell.setMergedRegion(
                Util.getMergedRegion( row.getSheet().getPoiSheet(), row.getPoiRow().getRowNum(), cell.getPoiCell().getColumnIndex())
        );
    }
}
