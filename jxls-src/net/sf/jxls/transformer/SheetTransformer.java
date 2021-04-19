package net.sf.jxls.transformer;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.controller.SheetTransformationControllerImpl;
import net.sf.jxls.controller.WorkbookTransformationController;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.formula.ListRange;
import net.sf.jxls.parser.Cell;
import net.sf.jxls.parser.CellParser;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.tag.Block;
import net.sf.jxls.transformation.ResultTransformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * @author Leonid Vysochyn
 */
public class SheetTransformer {
    protected static final Log log = LogFactory.getLog(SheetTransformer.class);

    /**
     * {@link java.util.Set} of all collections to outline
     */
    private Set groupedCollections = new HashSet();

    /**
     * Stores the names of all 'fixed size' collections.
     * 'Fixed size' collection is a collection with fixed number of items which do not require to create new rows in excel file
     * because all rows for them are already presented in template file.
     */
    private Set fixedSizeCollections = new HashSet();

    /**
     * {@link net.sf.jxls.transformer.Configuration} class
     */
    private Configuration configuration;

    private List rowProcessors = new ArrayList();

    private List ownTransformers = new ArrayList();


    /**
     * {@link net.sf.jxls.processor.CellProcessor} cell processors
     */
    private List cellProcessors = new ArrayList();

    public SheetTransformer(Set fixedSizeCollections, Set groupedCollections, List rowProcessors, List cellProcessors, Configuration configuration) {
        this.fixedSizeCollections = fixedSizeCollections;
        this.groupedCollections = groupedCollections;
        this.rowProcessors = rowProcessors;
        this.cellProcessors = cellProcessors;
        this.configuration = configuration;
    }

    public SheetTransformer(Set fixedSizeCollections, Set groupedCollections, List rowProcessors, List cellProcessors) {
        this.fixedSizeCollections = fixedSizeCollections;
        this.groupedCollections = groupedCollections;
        this.rowProcessors = rowProcessors;
        this.cellProcessors = cellProcessors;
        this.configuration = new Configuration();
    }

    public void setGroupedCollections(Set groupedCollections) {
        this.groupedCollections = groupedCollections;
    }

    void transformSheet(WorkbookTransformationController workbookTransformationController, Sheet sheet, Map beans) throws ParsePropertyException {
        log.debug("Processing sheet: " + sheet.getSheetName());
        exposePOIBeans(sheet, beans);
        if (!beans.isEmpty()) {
            SheetTransformationController stc = new SheetTransformationControllerImpl(sheet);
            workbookTransformationController.addSheetTransformationController(stc);
            for (int i = sheet.getPoiSheet().getFirstRowNum(); i <= sheet.getPoiSheet().getLastRowNum(); i++) {
                org.apache.poi.ss.usermodel.Row hssfRow = sheet.getPoiSheet().getRow(i);
                if (hssfRow != null) {
                    List rowTransformers = parseRow(sheet, hssfRow, beans);
                    if (!rowTransformers.isEmpty()) {
                        // process first Transformer
                        ResultTransformation processResult = new ResultTransformation();
                        RowTransformer rowTransformer = (RowTransformer) rowTransformers.get(0);
                        if (rowTransformer != null) {
                            Row row = rowTransformer.getRow();
                            applyRowProcessors(sheet, row);
                            processResult = rowTransformer.transform(stc, this, beans, null);
                            ownTransformers.add(rowTransformer);
                            if (!processResult.isTagProcessResult()) {
                                i += processResult.getNextRowShift();
                            } else {
                                if (processResult.getLastProcessedRow() >= 0) {
                                    i = processResult.getLastProcessedRow();
                                } else {
                                    i--;
                                }
                            }
                        }
                        // process other transformers
                        for (int j = 1, c2=rowTransformers.size(); j < c2; j++) {
                            rowTransformer = (RowTransformer) rowTransformers.get(j);
                            if (rowTransformer != null) {
                                Block transformationBlock = rowTransformer.getTransformationBlock();
                                transformationBlock = processResult.transformBlock( transformationBlock );
                                rowTransformer.setTransformationBlock( transformationBlock );

                                ResultTransformation newTransformation = rowTransformer.transform(stc, this, beans, processResult);

                                processResult.add(newTransformation);
                                ownTransformers.add(rowTransformer);
                            }
                        }
                    }
                }
            }
            groupRows(sheet);
        }
    }

    private void exposePOIBeans(Sheet sheet, Map beans) {
        beans.put(configuration.getSheetKeyName(), sheet.getPoiSheet());
    }


    /**
     * Processes rows in a template sheet using map of beans as parameter
     *
     * @param stc       - {@link SheetTransformationController} corresponding to the sheet containing given rows
     * @param sheet     {@link Sheet} object
     * @param startRow  Row to start processing
     * @param endRow    Last row to be processed
     * @param beans     Beans for substitution
     * @param parentRow - {@link Row} object representing original template row linked to rows to process
     * @return A number of rows to be shifted
     * @throws ParsePropertyException
     */
    public ResultTransformation processRows(SheetTransformationController stc, Sheet sheet, int startRow, int endRow, Map beans, Row parentRow) throws ParsePropertyException {
        int origEndRow = endRow;
        int nextRowShiftNumber = 0;
        boolean hasTagProcessing = false;
        int lastProcessedRow = -1;
        for (int i = startRow; i <= endRow; i++) {
            org.apache.poi.ss.usermodel.Row hssfRow = sheet.getPoiSheet().getRow(i);
            if (hssfRow != null) {
                ResultTransformation processResult = processRow(stc, sheet, hssfRow, beans, parentRow);
                if (!processResult.isTagProcessResult()) {
                    int shiftNumber = processResult.getNextRowShift();
                    nextRowShiftNumber += shiftNumber;
                    endRow += processResult.getLastRowShift();
                    i += shiftNumber;
                    lastProcessedRow = i;
                } else {
                    hasTagProcessing = true;
                    if (processResult.getLastProcessedRow() >= 0) {
                        i = processResult.getLastProcessedRow();
                        lastProcessedRow = i;
                    } else {
                        i--;
                    }
                    endRow += processResult.getLastRowShift();
                }
            }
        }
        ResultTransformation r = new ResultTransformation(nextRowShiftNumber, endRow - origEndRow);
        r.setTagProcessResult(hasTagProcessing);
        r.setLastProcessedRow(lastProcessedRow);
        return r;
    }

    ResultTransformation processRow(SheetTransformationController stc, Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow, Map beans, Row parentRow) {
        return processRow(stc, sheet, hssfRow, hssfRow.getFirstCellNum(), hssfRow.getLastCellNum(), beans, parentRow);
    }

    public ResultTransformation processRow(SheetTransformationController stc, Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow, int startCell, int endCell, Map beans, Row parentRow) {
        List transformers = parseCells(sheet, hssfRow, startCell, endCell, beans);


        ChainTransformer chainTransformer = new ChainTransformer(transformers, sheet, rowProcessors, parentRow);
        return chainTransformer.transform(stc, this, beans);

    }

    private List parseCells(Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow, int startCell, int endCell, Map beans) {
        if (configuration.getRowKeyName() != null) {
            beans.put(configuration.getRowKeyName(), hssfRow);
        }

        List transformers = new ArrayList();
        RowTransformer rowTransformer = null;
        Row row = new Row(sheet, hssfRow);
        SimpleRowTransformer simpleRowTransformer = new SimpleRowTransformer(row, cellProcessors, configuration);
//        transformations.add( simpleRowTransformer );
        boolean hasCollections = false;

          if (startCell >= 0 && endCell >= 0) {
              for (int j = startCell; j <= endCell && j>-1; j++) {
                    org.apache.poi.ss.usermodel.Cell hssfCell = hssfRow.getCell(j);
                    if (configuration.getCellKeyName() != null) {
                        beans.put(configuration.getCellKeyName(), hssfCell);
                    }

                    CellParser cellParser = new CellParser(hssfCell, row, configuration);
                    Cell cell = cellParser.parseCell(beans);
                    if (cell.getTag() == null) {
                         if (cell.getLabel() != null && cell.getLabel().length() > 0) {
                              sheet.addNamedCell(cell.getLabel(), cell);
                         }
                         RowCollection rowCollection = row.addCell(cell);
                         if (cell.getCollectionProperty() != null) {
                              hasCollections = true;
                              if (rowTransformer == null) {
                                    rowTransformer = new CollectionRowTransformer(row, fixedSizeCollections, cellProcessors, rowProcessors, configuration);
                                    transformers.add(rowTransformer);

                              }
                              ((CollectionRowTransformer) rowTransformer).addRowCollection(rowCollection);

    //                    rowTransformer

                              ListRange listRange = new ListRange(row.getPoiRow().getRowNum(), row.getPoiRow().getRowNum() + rowCollection.getCollectionProperty().getCollection().size() - 1, j);

                              addListRange(sheet, cell.getCollectionProperty().getProperty(), listRange);
                         } else {
                              if (!cell.isEmpty()) {
                                    simpleRowTransformer.addCell(cell);
                              }
                         }
                    } else {
                         rowTransformer = new TagRowTransformer(row, cell);
                         Block tagBody = cell.getTag().getTagContext().getTagBody();
                         j += tagBody.getNumberOfColumns() - 1;
                         transformers.add(rowTransformer);
                    }
              }
          }
        if (!hasCollections && simpleRowTransformer.getCells().size() > 0) {
            transformers.add(simpleRowTransformer);
        }

        // update references to parent RowCollections and process formula cells
        for (int i = 0; i < row.getCells().size(); i++) {
            Cell cell = (Cell) row.getCells().get(i);
            if (cell.getTag() == null) {
                if (cell.getRowCollection() == null && cell.getCollectionName() != null) {
                    RowCollection rowCollection = row.getRowCollectionByCollectionName(cell.getCollectionName());
                    if (rowCollection != null) {
                        rowCollection.addCell(cell);
                    } else {
                        log.debug("RowCollection with name " + cell.getCollectionName() + " not found");
                    }
                } else {
                    // add null cells to all hssfRow collections
                    if (cell.isEmpty() && cell.getRowCollection() == null && cell.getMergedRegion() == null && row.getRowCollections().size() == 1) {
                        ((RowCollection) row.getRowCollections().get(0)).addCell(cell);
                    }
                }
                // process formula cell
                if (cell.isFormula()) {
                    // create list range for inline formula
                    if (cell.getFormula().isInline() && cell.getLabel() != null && cell.getLabel().length() > 0) {
                        ListRange listRange = new ListRange(row.getPoiRow().getRowNum(),
                                row.getPoiRow().getRowNum() + cell.getRowCollection().getCollectionProperty().getCollection().size() - 1,
                                cell.getPoiCell().getColumnIndex());
                        addListRange(sheet, cell.getLabel(), listRange);
                    }
                }
            }
        }
        return transformers;
    }


    List parseRow(Sheet sheet, org.apache.poi.ss.usermodel.Row hssfRow, Map beans) {
          List transformers = parseCells(sheet, hssfRow, hssfRow.getFirstCellNum(), hssfRow.getLastCellNum(), beans);
          return transformers;
    }


    /**
     * Adds new {@link net.sf.jxls.formula.ListRange} to the map of ranges and updates formulas if there is range with the same name already
     *
     * @param sheet     - {@link Sheet} to add List Range
     * @param rangeName - The name of {@link ListRange} to add
     * @param range     - actual {@link ListRange} to add
     * @return true     - if a range with such name already exists or false if not
     */
    private boolean addListRange(Sheet sheet, String rangeName, ListRange range) {
        if (sheet.getListRanges().containsKey(rangeName)) {
            // update all formulas that can be updated and remove them from formulas list ( ignore all others )
            sheet.addListRange(rangeName, range);
            return true;
        }
        sheet.addListRange(rangeName, range);
        return false;
    }

    /**
     * Applies all registered RowProcessors to a row
     *
     * @param sheet - {@link Sheet} containing given {@link Row} object
     * @param row   - {@link Row} object with row information
     */
    private void applyRowProcessors(Sheet sheet, Row row) {
        for (int i = 0, c=rowProcessors.size(); i < c; i++) {
            RowProcessor rowProcessor = (RowProcessor) rowProcessors.get(i);
            rowProcessor.processRow(row, sheet.getNamedCells());
        }
    }

    /**
     * Outlines all required collections in a sheet
     *
     * @param sheet - {@link Sheet} where to outline collections
     */
    void groupRows(Sheet sheet) {
        for (Iterator iterator = groupedCollections.iterator(); iterator.hasNext();) {
            String collectionName = (String) iterator.next();
            if (sheet.getListRanges().containsKey(collectionName)) {
                ListRange listRange = (ListRange) sheet.getListRanges().get(collectionName);
                sheet.getPoiSheet().groupRow(listRange.getFirstRowNum(), listRange.getLastRowNum());
            }
        }
    }


}
