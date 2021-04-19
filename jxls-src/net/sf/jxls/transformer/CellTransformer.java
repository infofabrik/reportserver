package net.sf.jxls.transformer;

import net.sf.jxls.formula.Formula;
import net.sf.jxls.parser.Cell;
import net.sf.jxls.parser.Expression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

/**
 * Cell transformation class
 * @author Leonid Vysochyn
 */
public class CellTransformer {
    protected static final Log log = LogFactory.getLog(CellTransformer.class);


    private Configuration configuration;

    public CellTransformer(Configuration configuration) {
        if( configuration!=null ){
            this.configuration = configuration;
        }else{
            this.configuration = new Configuration();
        }
    }

    void transform( Cell cell ){
        try {
            if (cell.getPoiCell() != null && cell.getPoiCell().getCellType() == CellType.STRING) {
                if (cell.getCollectionProperty() == null) {
                    if (cell.getFormula() == null) {
                            if( cell.getExpressions().size() == 0 ){
                                if( cell.getMetaInfo() !=null ){
                                    cell.getPoiCell().setCellValue(cell.getPoiCell().getSheet().getWorkbook().getCreationHelper().createRichTextString(cell.getStringCellValue()));
                                }
                            }else if (cell.getExpressions().size() == 1) {
                                Object value = ((Expression) cell.getExpressions().get(0)).evaluate();
                                if (value == null) {
                                    cell.getPoiCell().setCellValue(cell.getPoiCell().getSheet().getWorkbook().getCreationHelper().createRichTextString(""));
                                    cell.getPoiCell().setCellType( CellType.BLANK );
                                } else if (value instanceof Float) {
                                    cell.getPoiCell().setCellValue(((Float) value).doubleValue());
                                }else if (value instanceof Double) {
                                    cell.getPoiCell().setCellValue(((Double) value).doubleValue());
                                } else if (value instanceof BigDecimal) {
                                    cell.getPoiCell().setCellValue(((BigDecimal) value).doubleValue());
                                } else if (value instanceof Date) {
                                    cell.getPoiCell().setCellValue((Date) value);
                                }else if (value instanceof Calendar) {
                                    cell.getPoiCell().setCellValue((Calendar) value);
                                } else if (value instanceof Byte) {
                                    cell.getPoiCell().setCellValue(((Byte) value).intValue());
                                }else if (value instanceof Short) {
                                    cell.getPoiCell().setCellValue(((Short) value).intValue());
                                }else if (value instanceof Integer) {
                                    cell.getPoiCell().setCellValue(((Integer) value).intValue());
                                }else if (value instanceof Long) {
                                    cell.getPoiCell().setCellValue(((Long) value).longValue());
                                } else if (value instanceof BigInteger) {
                                    cell.getPoiCell().setCellValue(((BigInteger) value).doubleValue());
                                }else {
                                    // fixing possible CR/LF problem
                                    String fixedValue = value.toString();
                                    if (fixedValue != null) {
                                        fixedValue = fixedValue.replaceAll("\r\n", "\n");
                                    }
                                    if( fixedValue.length() == 0 ){
                                        cell.getPoiCell().setCellType( CellType.BLANK );
                                    }else{
                                        cell.getPoiCell().setCellValue(cell.getPoiCell().getSheet().getWorkbook().getCreationHelper().createRichTextString(fixedValue));
                                    }
                                }
                            } else {
                                if (cell.getExpressions().size() > 1) {
                                    StringBuilder valueBuilder = new StringBuilder();
                                    for (int i = 0, c = cell.getExpressions().size(); i < c; i++) {
                                        Expression expr = (Expression) cell.getExpressions().get(i);
                                        Object propValue = expr.evaluate();
                                        if (propValue != null) {
                                            valueBuilder.append( propValue.toString() );
                                        }
                                    }
                                    setCellValue(cell, valueBuilder.toString());
                                }
                            }
                    }
                    else {
                        processFormulaCell( cell );
                    }
                } else {
                    StringBuilder valueBuilder = new StringBuilder();
                    for (int i = 0, c = cell.getExpressions().size(); i < c; i++) {
                        Expression expr = (Expression) cell.getExpressions().get(i);
                        if (expr.getCollectionProperty() == null) {
                            valueBuilder.append( expr.evaluate() );
                        } else {
                            valueBuilder.append(configuration.getStartExpressionToken());
                            valueBuilder.append(expr.getExpression());
                            valueBuilder.append(configuration.getEndExpressionToken());
                        }
                    }
                    setCellValue(cell, valueBuilder.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Can't parse expression");
        }
    }

    private void setCellValue(Cell cell, String value) {
        if (value == null || value.length() == 0) {
            cell.getPoiCell().setCellType( CellType.BLANK );
        } else {
            cell.getPoiCell().setCellValue(cell.getPoiCell().getSheet().getWorkbook().getCreationHelper().createRichTextString(value));
        }
    }

    private static void processFormulaCell(Cell cell) {
        // processing formula
        Formula formula = cell.getFormula();
        if (formula.isInline()) {
            if (cell.getCollectionName() != null) {
        // simple copy of inline formula template
        // it will be processed when individual rows are processed
                cell.getPoiCell().setCellValue(cell.getPoiCell().getSheet().getWorkbook().getCreationHelper().createRichTextString(cell.getStringCellValue()));
            } else {
        // processing of inline formulaString template
                String formulaString = formula.getInlineFormula(cell.getRow().getPoiRow().getRowNum() + 1);
                cell.getPoiCell().setCellFormula(formulaString);
            }
        }
    }
}
