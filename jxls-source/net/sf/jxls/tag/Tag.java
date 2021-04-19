package net.sf.jxls.tag;

import net.sf.jxls.transformation.ResultTransformation;
import net.sf.jxls.transformer.SheetTransformer;

/**
 * Defines an interface for a general jx tag
 * @author Leonid Vysochyn
 */
public interface Tag {
    /**
     * @return number of rows to shift
     * @param sheetTransformer
     */
    public ResultTransformation process(SheetTransformer sheetTransformer);

    /**
     * @return tag name
     */
    public String getName();

    /**
     * This method is invoked after all tag attributes are set
     * @param tagContext
     */
    void init(TagContext tagContext);

    /**
     * @return {@link TagContext} for this tag
     */
    TagContext getTagContext();
}
