package net.sf.jxls.transformer;

import org.apache.poi.ss.util.CellRangeAddress;


/**
 * Represents merged region
 * @author Leonid Vysochyn
 */
public class MergedRegion {
    private CellRangeAddress region;
    private RowCollection rowCollection;
    private int index;

    public MergedRegion(CellRangeAddress region, RowCollection rowCollection) {
        this.region = region;
        this.rowCollection = rowCollection;
    }

    public MergedRegion(CellRangeAddress region, int index) {
        this.region = region;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public CellRangeAddress getRegion() {
        return region;
    }

    public void setRegion(CellRangeAddress region) {
        this.region = region;
    }

    public RowCollection getRowCollection() {
        return rowCollection;
    }

    public void setRowCollection(RowCollection rowCollection) {
        this.rowCollection = rowCollection;
    }
}
