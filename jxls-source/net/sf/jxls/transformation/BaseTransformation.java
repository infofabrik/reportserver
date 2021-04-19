package net.sf.jxls.transformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Base class for {@link Transformation} interface implementations
 * @author Leonid Vysochyn
 */
public abstract class BaseTransformation implements Transformation{
    int firstRowNum;
    int lastRowNum;

    List transformations = new ArrayList();

    protected BaseTransformation() {
    }

    protected BaseTransformation(int firstRowNum, int lastRowNum) {
        this.firstRowNum = firstRowNum;
        this.lastRowNum = lastRowNum;
    }


    public void addTransformation( Transformation transformation ){
        transformations.add( transformation );
    }

    public int getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(int firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public int getLastRowNum() {
        return lastRowNum;
    }

    public void setLastRowNum(int lastRowNum) {
        this.lastRowNum = lastRowNum;
    }

    public List getTransformations() {
        return transformations;
    }

    public int getShiftNumber() {
        int shiftNumber = 0;
        for (Iterator iterator = transformations.iterator(); iterator.hasNext();) {
            Transformation transformation = (Transformation) iterator.next();
            shiftNumber += transformation.getShiftNumber();
        }
        return shiftNumber + lastRowNum - firstRowNum;
    }

    public int getNextRowShiftNumber() {
        int shiftNumber = 0;
        for (Iterator iterator = transformations.iterator(); iterator.hasNext();) {
            Transformation transformation = (Transformation) iterator.next();
            shiftNumber += transformation.getNextRowShiftNumber();
        }
        return shiftNumber;
    }

}
