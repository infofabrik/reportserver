package net.sf.jxls.transformation;

import java.util.ArrayList;
import java.util.List;

import net.sf.jxls.formula.CellRef;
import net.sf.jxls.tag.Block;
import net.sf.jxls.tag.Point;

/**
 * Remove transformation
 * @author Leonid Vysochyn
 */
public class RemoveTransformation extends BlockTransformation {

    public RemoveTransformation(Block block) {
        super(block);
    }

    public Block getBlockAfterTransformation() {
        return null;
    }


    public List transformCell(Point p) {
        List cells = null;
        if( !block.contains( p ) ){
            cells = new ArrayList(1);
            cells.add(p);
        }
        return cells;
    }

    public List transformCell(String sheetName, CellRef cellRef) {
        List cells = null;
        String refSheetName = cellRef.getSheetName();
        if( block.getSheet().getSheetName().equalsIgnoreCase( refSheetName ) || (cellRef.getSheetName() == null && block.getSheet().getSheetName().equalsIgnoreCase( sheetName ))){
            if( !block.contains( cellRef.getRowNum(), cellRef.getColNum() ) ){
                cells = new ArrayList();
                cells.add( cellRef.toString() );
            }
        }else{
            cells = new ArrayList(1);
            cells.add( cellRef.toString() );
        }
        return cells;
    }

    public String toString() {
        return "RemoveTransformation: {" + super.toString() + "}";
    }
}
