package net.sf.jxls.tag;

import net.sf.jxls.controller.SheetTransformationController;
import net.sf.jxls.transformer.Sheet;
import net.sf.jxls.transformer.SheetTransformer;

import java.util.Map;

/**
 * Contains tag related information
 * @author Leonid Vysochyn
 */
public class TagContext {
    Map beans;
    Block tagBody;

    Sheet sheet;
    SheetTransformationController stc;

    public TagContext(SheetTransformationController stc, SheetTransformer sheetTransformer, Sheet sheet, Block tagBody, Map beans) {
        this.stc = stc;
        this.sheet = sheet;
        this.tagBody = tagBody;
        this.beans = beans;
    }

    public TagContext(SheetTransformer sheetTransformer, Sheet sheet, Block tagBody, Map beans) {
        this.sheet = sheet;
        this.tagBody = tagBody;
        this.beans = beans;
    }

    public TagContext(Sheet sheet, Block tagBody, Map beans) {
        this.sheet = sheet;
        this.tagBody = tagBody;
        this.beans = beans;
    }


    public TagContext(Map beans, Block tagBody) {
        this.beans = beans;
        this.tagBody = tagBody;
    }

    public TagContext(Map beans) {
        this.beans = beans;
    }

    public Map getBeans() {
        return beans;
    }

    public void setBeans(Map beans) {
        this.beans = beans;
    }

    public String toString() {
        return "Beans: " + beans.toString() + ", Body: " + tagBody;
    }

    public Block getTagBody() {
        return tagBody;
    }

    public void setTagBody(Block tagBody) {
        this.tagBody = tagBody;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public SheetTransformationController getSheetTransformationController() {
        return stc;
    }

    public void setSheetTransformationController(SheetTransformationController stc) {
        this.stc = stc;
    }
}
