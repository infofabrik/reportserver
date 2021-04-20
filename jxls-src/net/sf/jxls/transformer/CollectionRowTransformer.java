package net.sf.jxls.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.formula.ListRange;
import net.sf.jxls.parser.ExpressionCollectionParser;
import net.sf.jxls.parser.Property;
import net.sf.jxls.transformation.ResultTransformation;
import net.sf.jxls.util.Util;

/**
 * Implementation of {@link RowTransformer} interface for duplicating a collection row
 * @author Leonid Vysochyn
 */
public class CollectionRowTransformer extends BaseRowTransformer {

    /**
     * {@link net.sf.jxls.transformer.Configuration} class
     */
    private Configuration configuration;

    /**
     * {@link net.sf.jxls.processor.CellProcessor} cell processors
     */
    private List cellProcessors = new ArrayList();


    List rowCollections = new ArrayList();

    private ResultTransformation resultTransformation;


    /**
     * Stores the names of all 'fixed size' collections.
     * 'Fixed size' collection is a collection with fixed number of items which do not require to create new rows in excel file
     * because all rows for them are already presented in template file.
     */
    private Set fixedSizeCollections = new HashSet();


    public CollectionRowTransformer(Row row, Set fixedSizeCollections, List cellProcessors, List rowProcessors, Configuration configuration) {
        this.fixedSizeCollections = fixedSizeCollections;
        this.cellProcessors = cellProcessors;
        this.configuration = configuration;
        this.row = row;
    }

    public ResultTransformation getTransformationResult() {
        return resultTransformation;
    }

    void addRowCollection( RowCollection rowCollection ){
        rowCollections.add( rowCollection );
    }

    public ResultTransformation transform(SheetTransformationController stc, SheetTransformer sheetTransformer, Map beans, ResultTransformation previousTransformation) {
        try {
            SimpleRowTransformer simpleRowTransformer = new SimpleRowTransformer(row, cellProcessors, configuration);
            simpleRowTransformer.transform(stc, sheetTransformer, beans, null);
            resultTransformation = processRowCollections(stc, sheetTransformer, beans );
            sheetTransformer.groupRows( row.getSheet() );
        } catch (ParsePropertyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            resultTransformation = new ResultTransformation(0);
        }
        return resultTransformation;
    }

    /**
     * Processes a row containing collection properties
     * @param sheetTransformationController - {@link SheetTransformationController} for given sheet
     * @param sheetTransformer - {@link SheetTransformer} to use when processing Row Collections
     * @param beans Beans to apply
     * @return number to SHIFT all other rows in template
     * @throws net.sf.jxls.exception.ParsePropertyException
     */
    ResultTransformation processRowCollections(SheetTransformationController sheetTransformationController, SheetTransformer sheetTransformer, Map beans) throws ParsePropertyException {
        int maxShiftNumber = 0;

        int rowNum = row.getPoiRow().getRowNum();
        Set keys = new HashSet( beans.keySet() );
        for (int i = 0, c = row.getRowCollections().size(); i < c; i++) {
            RowCollection rowCollection = (RowCollection) row.getRowCollections().get(i);
            if( !rowCollection.getCollectionProperty().getCollection().isEmpty() ){
                Property collectionProperty = rowCollection.getCollectionProperty();
                String collectionItem = generateCollectionItem( collectionProperty.getFullCollectionName(), keys);
                rowCollection.setCollectionItemName( collectionItem );
                keys.add( collectionItem );
                if( log.isDebugEnabled() ){
                    log.debug("----collection-property--------->" + collectionProperty.getCollectionName());
                }
                ListRange listRange = new ListRange(rowNum, rowNum + collectionProperty.getCollection().size() - 1, 0);
                listRange.setListName( collectionProperty.getCollectionName() );
                listRange.setListAlias(rowCollection.getCollectionItemName());
                addListRange(row.getSheet(), rowCollection.getCollectionItemName(), listRange);
                // this is mainly for grouping rows of this collection if required (after all processing is done)
                addListRange(row.getSheet(), collectionProperty.getFullCollectionName(), listRange);
                if (!fixedSizeCollections.contains(collectionProperty.getCollectionName())) {
                    Util.prepareCollectionPropertyInRowForDuplication( rowCollection, rowCollection.getCollectionItemName() );
                    sheetTransformationController.duplicateRow( rowCollection );
                } else {
// static list found - so we shouldn't copy or SHIFT rows and don't expect any collections in here
                    if( log.isDebugEnabled() ){
                        log.debug("Fixed size collection found: " + collectionProperty.getCollectionName());
                    }
                    if( rowCollection.getDependentRowNumber() !=0 ){
                        log.warn("Dependent rows for fixed size collections are not supported.");
                    }
                    Util.prepareCollectionPropertyInRowForContentDuplication( rowCollection );
                    Util.duplicateRowCollectionProperty( rowCollection );
                }
            }else{
                // collection is empty. removing row collection property from row and all its dependent rows
                Util.removeRowCollectionPropertiesFromRow( rowCollection );
            }
        }
        if( row.getRowCollections().size() > 0 ){
            // walk through all collections and processing rows
            RowCollection maxSizeCollection = row.getMaxSizeCollection();
            int minDependentRowNumber = row.getMinDependentRowNumber();
            int mainShiftNumber = 0;
            // create iterator for every row collection
            for (int i = 0, c = row.getRowCollections().size(); i < c; i++) {
                RowCollection rowCollection = (RowCollection) row.getRowCollections().get(i);
                rowCollection.createIterator( minDependentRowNumber );
            }
            // walk through all collection items, put them into bean context and invoke recursive processing of rows
            for(int k = 0; k < maxSizeCollection.getCollectionProperty().getCollection().size(); k++){
                for (int i = 0, c = row.getRowCollections().size(); i < c; i++) {
                    RowCollection rowCollection = (RowCollection) row.getRowCollections().get(i);
                    if( rowCollection.hasNextObject() ){
                        Object o = rowCollection.getNextObject();
                        beans.put( rowCollection.getCollectionItemName(), o);
                    }
                }
//                Util.writeToFile("beforeProcessRows.xls", row.getSheet().getPoiWorkbook());
//                int shiftNumber = sheetTransformer.processRows( row.getSheet(), rowNum + (minDependentRowNumber+1)*k, rowNum + (minDependentRowNumber+1)*k + minDependentRowNumber, beans, row);

                ResultTransformation processResult = sheetTransformer.processRows(sheetTransformationController, row.getSheet(), rowNum + (minDependentRowNumber+1)*k, rowNum + (minDependentRowNumber+1)*k + minDependentRowNumber, beans, row);
//                Util.writeToFile("afterProcessRows.xls", row.getSheet().getPoiWorkbook());
                int shiftNumber = processResult.getNextRowShift();
                mainShiftNumber += shiftNumber + 1;
                rowNum += shiftNumber;
            }
            // remove all processed collectionItems from bean map
            for (int i = 0, c = row.getRowCollections().size(); i < c; i++) {
                RowCollection rowCollection = (RowCollection) row.getRowCollections().get(i);
                beans.remove( rowCollection.getCollectionItemName() );
            }
            if( mainShiftNumber-1 > maxShiftNumber ){
                maxShiftNumber = mainShiftNumber-1;
            }
        }
        return new ResultTransformation(maxShiftNumber, maxShiftNumber);
    }

    /**
     * Generates a new bean key for the items in given collection
     * @param collectionName - Collection name to use as a base name for generation
     * @param keys - Existing keys
     * @return unique bean key to be put in the current bean map
     */
    private String generateCollectionItem(String collectionName, Set keys) {
        String origKey = collectionName.replace('.', '_');
        String key = origKey + ExpressionCollectionParser.COLLECTION_REFERENCE_SUFFIX;
        int index = 0;
        while( keys.contains( key ) ){
            key = origKey + index++ + ExpressionCollectionParser.COLLECTION_REFERENCE_SUFFIX;
        }
        return key;
    }
}
