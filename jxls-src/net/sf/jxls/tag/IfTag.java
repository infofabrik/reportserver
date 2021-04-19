package net.sf.jxls.tag;

import java.util.Map;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.controller.SheetTransformationControllerImpl;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.parser.Expression;
import net.sf.jxls.parser.ExpressionParser;
import net.sf.jxls.transformation.ResultTransformation;
import net.sf.jxls.transformer.SheetTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * jx:if tag implementation
 * @author Leonid Vysochyn
 */
public class IfTag extends BaseTag {
    protected static final Log log = LogFactory.getLog(IfTag.class);

    public static final String TAG_NAME = "if";

    String test;

    Boolean testResult = null;

    public IfTag() {
        name = TAG_NAME;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void init(TagContext context) {
        super.init(context);
        ExpressionParser exprParser = new ExpressionParser( test, context.getBeans(), context.getSheet().getConfiguration() );
        Expression testExpr = exprParser.parse();
        try {
            testResult = (Boolean) testExpr.evaluate();
        } catch (Exception e) {
            log.error("Can't evaluate test expression: " + test, e);
            throw new RuntimeException("Can't evaluate test expression: " + test, e);
        }

    }


    public ResultTransformation process(SheetTransformer sheetTransformer) {
        if( log.isDebugEnabled() ){
            log.debug("if tag processing. Parameters: test=" + test);
        }

        Block body = tagContext.getTagBody();
        if( body.getNumberOfRows()==1 ){
            return processOneRowTag(sheetTransformer);
        }
        int shiftNumber = 0;

        ResultTransformation shift = new ResultTransformation(0);
        shift.setLastProcessedRow( -1 );
        if( testResult != null ){
            if(testResult.booleanValue() ){
                tagContext.getSheetTransformationController().removeBorders(body);
                shiftNumber += -2;
                try {
                    ResultTransformation processResult = sheetTransformer.processRows(tagContext.getSheetTransformationController(), tagContext.getSheet(), body.getStartRowNum(), body.getEndRowNum(), tagContext.getBeans(), null );
                    shift.add( processResult );
//                    lastProcessedRow = processResult.getLastProcessedRow();
                } catch (ParsePropertyException e) {
                    log.error("Can't parse property ", e);
                }
            }else{
                tagContext.getSheetTransformationController().removeBodyRows( body );
                shift.add( new ResultTransformation(-1, -body.getNumberOfRows() ));
            }
        }
        shift.setTagProcessResult( true );
        return shift.add( new ResultTransformation(0, shiftNumber) );
    }

    private ResultTransformation processOneRowTag(SheetTransformer sheetTransformer) {
        Block body = tagContext.getTagBody();
        int shiftNumber = 0;
        SheetTransformationController stc = new SheetTransformationControllerImpl( tagContext.getSheet() );
        stc.removeLeftRightBorders(body);
        shiftNumber += -2;
        Map beans = tagContext.getBeans();
        ResultTransformation shift = new ResultTransformation();
        shift.setLastProcessedRow( -1 );
        shift.addRightShift( (short) shiftNumber );
        if( testResult!=null ){
            if( testResult.booleanValue() ){
                ResultTransformation processResult = sheetTransformer.processRow(tagContext.getSheetTransformationController(), tagContext.getSheet(),
                        tagContext.getSheet().getPoiSheet().getRow( body.getStartRowNum() ),
                        body.getStartCellNum(), body.getEndCellNum(), beans, null);
                shift.add( processResult );
            }else{
                stc.removeRowCells(
                        tagContext.getSheet().getPoiSheet().getRow( body.getStartRowNum() ), body.getStartCellNum(), body.getEndCellNum() );

                shift.add( new ResultTransformation((short)-body.getNumberOfColumns(), (short)(-body.getNumberOfColumns() )));
            }
        }
        shift.setTagProcessResult( true );
//        Util.writeToFile("ifTagFinished.xls", tagContext.getSheet().getPoiWorkbook());
        return shift;
    }
}
