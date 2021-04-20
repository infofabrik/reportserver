package net.sf.jxls.transformation;

import net.sf.jxls.tag.Block;

/**
 * Result information about transformation
 * @author Leonid Vysochyn
 */
public class ResultTransformation extends BaseTransformation {
    int lastRowShift;
    int nextRowShift;
    short nextCellShift;
    short lastCellShift;
    int lastProcessedRow = -1;
    int startCellShift;

    boolean isTagProcessResult;

    public ResultTransformation() {
    }

    public ResultTransformation(short nextCellShift, short lastCellShift) {
        this.nextCellShift = nextCellShift;
        this.lastCellShift = lastCellShift;
    }

    public ResultTransformation(int nextRowShift) {
        this.nextRowShift = nextRowShift;
    }

    public ResultTransformation(int nextRowShift, int lastRowShift) {
        this.nextRowShift = nextRowShift;
        this.lastRowShift = lastRowShift;
    }


    public boolean isTagProcessResult() {
        return isTagProcessResult;
    }

    public void setTagProcessResult(boolean tagProcessResult) {
        isTagProcessResult = tagProcessResult;
    }

    public int getLastProcessedRow() {
        return lastProcessedRow;
    }

    public void setLastProcessedRow(int lastProcessedRow) {
        this.lastProcessedRow = lastProcessedRow;
    }

    public ResultTransformation add(ResultTransformation transformation){
        lastRowShift += transformation.getLastRowShift();
        nextRowShift += transformation.getNextRowShift();
        startCellShift += transformation.getStartCellShift();
//        if( nextRowShift < - 1){
//            // next row shift can't be less than 1 because we must not process already processed rows
//            nextRowShift = -1;
//        }
        lastCellShift += transformation.getLastCellShift();
        nextCellShift += transformation.getNextCellShift();
        if( transformation.getLastProcessedRow() >= 0 ){
            this.lastProcessedRow = Math.max( this.lastProcessedRow, transformation.getLastProcessedRow() );
        }
        this.isTagProcessResult = isTagProcessResult || transformation.isTagProcessResult();
        return this;
    }

    public Block transformBlock(Block block){
        if( block!=null ){
            block = block.horizontalShift( lastCellShift );
            block = block.verticalShift( lastRowShift );
        }
        return block;
    }

    public ResultTransformation addNextRowShift( int shift ){
        nextRowShift += shift;
//        if( nextRowShift < -1 ){
//            nextRowShift = -1;
//        }
        return this;
    }

    public ResultTransformation addRightShift( short shift ){
        lastCellShift += shift;
        return this;
    }

    public short getLastCellShift() {
        return lastCellShift;
    }

    public int getLastRowShift() {
        return lastRowShift;
    }

    public void setLastRowShift(int lastRowShift) {
        this.lastRowShift = lastRowShift;
    }

    public int getNextRowShift() {
        return nextRowShift;
    }

    public void setNextRowShift(int nextRowShift) {
        this.nextRowShift = nextRowShift;
    }

    public short getNextCellShift() {
        return nextCellShift;
    }

    public int getStartCellShift() {
        return startCellShift;
    }

    public void setStartCellShift(int startCellShift) {
        this.startCellShift = startCellShift;
    }
}
