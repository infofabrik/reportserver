package net.sf.jxls.transformation;

import java.util.List;

/**
 * General transformation interface
 * @author Leonid Vysochyn
 */
public interface Transformation {
    public int getShiftNumber();
    public int getFirstRowNum();
    public int getLastRowNum();
    public List getTransformations();
    public void addTransformation( Transformation transformation );
    public int getNextRowShiftNumber();
}
