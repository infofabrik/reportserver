package net.sf.jxls.transformer;

import java.util.Map;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.tag.Block;
import net.sf.jxls.transformation.ResultTransformation;

/**
 * Defines row transformation methods
 */
public interface RowTransformer {
    Row getRow();
    ResultTransformation transform(SheetTransformationController stc, SheetTransformer sheetTransformer, Map beans, ResultTransformation previousTransformation);
    Block getTransformationBlock();
    void setTransformationBlock(Block block);
    ResultTransformation getTransformationResult();

}
