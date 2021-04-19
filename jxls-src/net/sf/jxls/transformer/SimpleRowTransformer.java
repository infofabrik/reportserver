package net.sf.jxls.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.parser.Cell;
import net.sf.jxls.processor.CellProcessor;
import net.sf.jxls.transformation.ResultTransformation;

/**
 * @author Leonid Vysochyn
 */
public class SimpleRowTransformer extends BaseRowTransformer {

    Configuration configuration;
    List cellProcessors;
    List cells = new ArrayList();

    private ResultTransformation resultTransformation;

    public SimpleRowTransformer(Row row, List cellProcessors, Configuration configuration) {
        this.row = row;
        this.cellProcessors = cellProcessors;
        this.configuration = configuration;
    }

    public void addCell(Cell cell){
        if( !cell.isEmpty() ){
            cells.add( cell );
        }
    }

    public ResultTransformation getTransformationResult() {
        return resultTransformation;
    }

    public List getCells() {
        return cells;
    }


    public ResultTransformation transform(SheetTransformationController stc, SheetTransformer sheetTransformer, Map beans, ResultTransformation previousTransformation){
        CellTransformer cellTransformer = new CellTransformer( configuration );
        if( cells.isEmpty() ){
//            throw new RuntimeException("Don't expect to execute this code");
            for (int j = 0, c = row.getCells().size(); j < c; j++) {
                Cell cell = (Cell) row.getCells().get(j);
                if (configuration.getCellKeyName() != null) {
                    beans.put(configuration.getCellKeyName(), cell.getPoiCell() );
                }                
                applyCellProcessors(row.getSheet(), cell );
                cellTransformer.transform( cell );
            }
        }else{
            for (int i = 0, c = cells.size(); i < c; i++) {
                Cell cell = (Cell) cells.get(i);
                if (configuration.getCellKeyName() != null) {
                    beans.put(configuration.getCellKeyName(), cell.getPoiCell() );
                }                

                if( previousTransformation != null && cell.getPoiCell().getColumnIndex()>= previousTransformation.getStartCellShift()
                        && previousTransformation.getStartCellShift() != 0){
                    cell.replaceCellWithNewShiftedBy(previousTransformation.getLastCellShift());
                }
                applyCellProcessors( row.getSheet(), cell );
                cellTransformer.transform( cell );
            }
        }
        resultTransformation = new ResultTransformation();
        return resultTransformation;
    }

    /**
     * Applies all registered CellProcessors to a cell
     * @param sheet - {@link Sheet} to apply Cell Processors to
     * @param cell - {@link net.sf.jxls.parser.Cell} object with cell information
     */
    private void applyCellProcessors(Sheet sheet, Cell cell) {
        for (int i = 0, c = cellProcessors.size(); i < c; i++) {
            CellProcessor cellProcessor = (CellProcessor) cellProcessors.get(i);
            cellProcessor.processCell(cell, sheet.getNamedCells());
        }
    }




}
