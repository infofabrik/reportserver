package net.sf.jxls.tag;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.parser.Expression;
import net.sf.jxls.transformation.ResultTransformation;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.SheetTransformer;
import net.sf.jxls.util.GroupData;
import net.sf.jxls.util.ReportUtil;
import net.sf.jxls.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * jx:forEach tag implementation
 *
 * @author Leonid Vysochyn
 */
public class ForEachTag extends BaseTag {
    protected static final Log log = LogFactory.getLog(ForEachTag.class);

    public static final String TAG_NAME = "forEach";
    Configuration configuration = new Configuration();
    static final String GROUP_DATA_KEY = "group";

    private String select;

    public ForEachTag() {
        name = TAG_NAME;
    }

    private String items;
    private String var;
    private String varStatus;

    private String itemsKey;
    private String collectionPropertyName;
    private String groupBy;

    private String groupOrder;

    private Collection itemsCollection;


    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(String groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getVarStatus() {
        return varStatus;
    }

    public void setVarStatus(String varStatus) {
        this.varStatus = varStatus;
    }

    public void init(TagContext context) {
        super.init(context);
        configuration = context.getSheet().getConfiguration();
        parseItemsProperty();
        parseSelectProperty();
        if (context.getBeans().containsKey(itemsKey)) {
            Object itemsObject = context.getBeans().get(itemsKey);
            if (collectionPropertyName != null) {
                itemsCollection = (Collection) Util.getProperty(itemsObject, collectionPropertyName);
            } else {
                itemsCollection = (Collection) itemsObject;
            }
        }
    }

    private void parseSelectProperty() {
        if (select != null) {
            if (select.startsWith(configuration.getStartExpressionToken()) && select.endsWith(configuration.getEndExpressionToken())) {
                select = select.substring(2, select.length() - 1);
            } else {
                log.error("select attribute should start with " + configuration.getStartExpressionToken() + " and end with " +
                        configuration.getEndExpressionToken());
            }
        }
    }


    private void parseItemsProperty() {
        if (items != null) {
            if (items.startsWith(configuration.getStartExpressionToken()) && items.endsWith(configuration.getEndExpressionToken())) {
                items = items.substring(2, items.length() - 1);

                try {
                    Expression expr = new Expression(items, tagContext.getBeans(), configuration);
                    Object obj = expr.evaluate();
                    if (obj instanceof Collection) {
                        itemsCollection = (Collection) obj;
                    } else if (obj.getClass().isArray()){
                    	itemsCollection = Arrays.asList( this.toObjectArray( obj ) );
                    } else {
                        throw new RuntimeException("items property in forEach tag must be either a collection or an array. " + items + " is not ");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Can't parse an expression " + items, e);
                }
            } else {
                log.error("items attribute should start from " + configuration.getStartExpressionToken() + " and end " +
                        "with " + configuration.getEndExpressionToken());
            }
        } else {
            log.error("Collection key is null");
        }
    }
    
    private Object[] toObjectArray( Object array ) {
    	if ( this.isPrimitiveArray( array ) ) {
	        int arrayLength = Array.getLength( array );
	        Object[] result = (Object[]) Array.newInstance(Object.class, arrayLength);
	        for (int i = 0; i < arrayLength; i++) {
	            Array.set(result, i, Array.get(array, i));
	        }
	        return result;
    	}
    	return (Object[])array;
    }

    private boolean isPrimitiveArray( Object obj ) {
    	return obj instanceof boolean[]
    			|| obj instanceof byte[]
    			|| obj instanceof char[]
    			|| obj instanceof short[]
    			|| obj instanceof int[]
    			|| obj instanceof long[]
    			|| obj instanceof float[]
    			|| obj instanceof double[];
    }

    public ResultTransformation process(SheetTransformer sheetTransformer) {
        if (log.isDebugEnabled()) {
            log.debug("forEach tag processing. Attributes: var = " + var + ", items=" + items);
            log.debug("Current tagContext: " + tagContext);
            log.debug("Items Collection: " + itemsCollection);
        }
        Block body = tagContext.getTagBody();
        if (body.getNumberOfRows() == 1) {
            return processOneRowTag(sheetTransformer);
        }
        int shiftNumber = 0;
        Map beans = tagContext.getBeans();
        Collection collectionToProcess = null;
        if (groupBy == null || groupBy.length() == 0) {
            collectionToProcess = selectCollectionDataToProcess(beans);
        }
        if (itemsCollection != null && !itemsCollection.isEmpty() && (collectionToProcess == null || !collectionToProcess.isEmpty())) {
            tagContext.getSheetTransformationController().removeBorders(body);
            shiftNumber += -2; // due to the borders
            ResultTransformation shift = new ResultTransformation(0);

            if (groupBy == null || groupBy.length() == 0) {
                shiftNumber += tagContext.getSheetTransformationController().duplicateDown(body, collectionToProcess.size() - 1);
                shift = processCollectionItems(collectionToProcess, beans, body, sheetTransformer);
            } else {
                try {
                    Collection groupedData = ReportUtil.groupCollectionData(itemsCollection, groupBy, groupOrder, select, configuration);
                    shiftNumber += tagContext.getSheetTransformationController().duplicateDown(body, groupedData.size() - 1);
                    Object savedGroupData = null;
                    if (beans.containsKey(GROUP_DATA_KEY)) {
                        savedGroupData = beans.get(GROUP_DATA_KEY);
                    }
                    shift = processGroupedData(groupedData, beans, body, sheetTransformer);
                    beans.remove(GROUP_DATA_KEY);
                    if (savedGroupData != null) {
                        beans.put(GROUP_DATA_KEY, savedGroupData);
                    }
                } catch (NoSuchMethodException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                } catch (IllegalAccessException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                } catch (InvocationTargetException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                }
            }
            shift.add(new ResultTransformation(shiftNumber, shiftNumber));
            shift.setTagProcessResult(true);
            return shift;
        }
        log.warn("Collection " + items + " is empty");
        tagContext.getSheetTransformationController().removeBodyRows(body);
        ResultTransformation shift = new ResultTransformation(0);
        shift.add(new ResultTransformation(-1, -body.getNumberOfRows()));
        shift.setLastProcessedRow(-1);
        shift.setTagProcessResult(true);
        return shift;
    }

    private ResultTransformation processOneRowTag(SheetTransformer sheetTransformer) {
        Block body = tagContext.getTagBody();
        int shiftNumber = 0;
        Map beans = tagContext.getBeans();
        Collection collectionToProcess = null;
        if (groupBy == null || groupBy.length() == 0) {
            collectionToProcess = selectCollectionDataToProcess(beans);
        }
        if (itemsCollection != null && !itemsCollection.isEmpty() && (collectionToProcess == null || !collectionToProcess.isEmpty())) {
            body.setSheet(tagContext.getSheet());
            tagContext.getSheetTransformationController().removeLeftRightBorders(body);
            shiftNumber += -2;
            ResultTransformation shift = new ResultTransformation();
            shift.setLastProcessedRow(0);
            shift.setStartCellShift(body.getEndCellNum()+1);
            if (groupBy == null || groupBy.length() == 0) {
                shiftNumber += tagContext.getSheetTransformationController().duplicateRight(body, collectionToProcess.size() - 1);
                processCollectionItemsOneRow(collectionToProcess, beans, body, shift, sheetTransformer);
            } else {
                try {
                    Collection groupedData = ReportUtil.groupCollectionData(itemsCollection, groupBy, groupOrder, select, configuration);
                    shiftNumber += tagContext.getSheetTransformationController().duplicateRight(body, groupedData.size() - 1);
                    Object savedGroupData = null;
                    if (beans.containsKey(GROUP_DATA_KEY)) {
                        savedGroupData = beans.get(GROUP_DATA_KEY);
                    }
                    processGroupedDataOneRow(groupedData, beans, body, shift, sheetTransformer);
                    beans.remove(GROUP_DATA_KEY);
                    if (savedGroupData != null) {
                        beans.put(GROUP_DATA_KEY, savedGroupData);
                    }
                } catch (NoSuchMethodException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                } catch (IllegalAccessException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                } catch (InvocationTargetException e) {
                    log.error(e, new Exception("Can't group collection data by " + groupBy, e));
                }
            }
            shift.addRightShift((short) shiftNumber);
            shift.setTagProcessResult(true);
            return shift;
        }
        log.warn("Collection " + items + " is empty");
        Row currentRow = tagContext.getSheet().getPoiSheet().getRow(body.getStartRowNum());
        tagContext.getSheetTransformationController().removeRowCells(currentRow, body.getStartCellNum(), body.getEndCellNum());
        ResultTransformation shift = new ResultTransformation(0);
        shift.add( new ResultTransformation((short)-body.getNumberOfColumns(), (short)(-body.getNumberOfColumns() )));
        shift.setTagProcessResult(true);
        return shift;
    }

    private ResultTransformation processGroupedData(Collection groupedData, Map beans, Block body, SheetTransformer sheetTransformer) {
        ResultTransformation shift;
        int startRowNum;
        int endRowNum;
        ResultTransformation processResult;
        shift = new ResultTransformation(0);
        int k = 0;
        for (Iterator iterator = groupedData.iterator(); iterator.hasNext();) {
            GroupData groupData = (GroupData) iterator.next();
            beans.put(GROUP_DATA_KEY, groupData);
            try {
                startRowNum = body.getStartRowNum() + shift.getLastRowShift() + body.getNumberOfRows() * k++;
                endRowNum = startRowNum + body.getNumberOfRows() - 1;
                processResult = sheetTransformer.processRows(tagContext.getSheetTransformationController(), tagContext.getSheet(), startRowNum, endRowNum, beans, null);
                shift.add(processResult);
            } catch (ParsePropertyException e) {
                log.error("Can't parse property ", e);
            }
        }
        return shift;
    }

    private void processGroupedDataOneRow(Collection groupedData, Map beans, Block body, ResultTransformation shift, SheetTransformer sheetTransformer) {
        ResultTransformation processResult;
        short startColNum, endColNum;
        int k = 0;
        for (Iterator iterator = groupedData.iterator(); iterator.hasNext();) {
            GroupData groupData = (GroupData) iterator.next();
            beans.put(GROUP_DATA_KEY, groupData);
            try {
                startColNum = (short) (body.getStartCellNum() + shift.getLastCellShift() + body.getNumberOfColumns() * k++);
                endColNum = (short) (startColNum + body.getNumberOfColumns() - 1);
                processResult = sheetTransformer.processRow(tagContext.getSheetTransformationController(), tagContext.getSheet(), tagContext.getSheet().getPoiSheet().getRow(body.getStartRowNum()),
                        startColNum, endColNum, beans, null);
                shift.add(processResult);
            } catch (ParsePropertyException e) {
                log.error("Can't parse property ", e);
            }
        }
    }



    private ResultTransformation processCollectionItems(Collection c2, Map beans, Block body, SheetTransformer sheetTransformer) {
        ResultTransformation shift = new ResultTransformation(0);
        int startRowNum;
        int endRowNum;
        int index = 0;
        ResultTransformation processResult;
        int k = 0;
        LoopStatus status = new LoopStatus();
        if( varStatus != null ){
            beans.put( varStatus, status );
        }
        for (Iterator iterator = c2.iterator(); iterator.hasNext(); index++) {
            Object o = iterator.next();
            beans.put(var, o);
            status.setIndex( index );
                try {
                    startRowNum = body.getStartRowNum() + shift.getLastRowShift() + body.getNumberOfRows() * k++;
                    endRowNum = startRowNum + body.getNumberOfRows() - 1;
                    processResult = sheetTransformer.processRows(tagContext.getSheetTransformationController(), tagContext.getSheet(), startRowNum, endRowNum, beans, null);
                    shift.add(processResult);
                } catch (ParsePropertyException e) {
                    log.error("Can't parse property ", e);
                    throw new RuntimeException("Can't parse property", e);
                }
        }
        if( varStatus != null ){
            beans.remove( varStatus );
        }
        return shift;
    }

    private void processCollectionItemsOneRow(Collection c2, Map beans, Block body, ResultTransformation shift, SheetTransformer sheetTransformer) {
        int k = 0;
        int index = 0;
        LoopStatus status = new LoopStatus();
        if( varStatus != null ){
            beans.put( varStatus, status );
        }
          for (Iterator iterator = c2.iterator(); iterator.hasNext(); index++) {
            Object o = iterator.next();
            beans.put(var, o);
            status.setIndex( index );
            try {
                short startCellNum = (short) (body.getStartCellNum() + shift.getLastCellShift() + body.getNumberOfColumns() * k++);
                short endCellNum = (short) (startCellNum + body.getNumberOfColumns() - 1);
                ResultTransformation processResult = sheetTransformer.processRow(tagContext.getSheetTransformationController(), tagContext.getSheet(),
                        tagContext.getSheet().getPoiSheet().getRow(body.getStartRowNum()),
                        startCellNum, endCellNum, beans, null);
                shift.add(processResult);
            } catch (Exception e) {
                log.error("Can't parse property ", e);
            }
        }
        if( varStatus != null ){
            beans.remove( varStatus );
        }
    }

    private Collection selectCollectionDataToProcess(Map beans) {
        Collection c2 = new ArrayList();
        for (Iterator iterator = itemsCollection.iterator(); iterator.hasNext();) {
            Object o = iterator.next();
            beans.put(var, o);
            if (ReportUtil.shouldSelectCollectionData(beans, select, configuration)) {
                c2.add(o);
            }
        }
        return c2;
    }


}
