/**
 * @author Leonid Vysochyn
 */
package net.sf.jxls.transformation;

/**
 * Enumeration class for different transformation types
 */
public class TransformationType {
    public static final TransformationType SHIFT = new TransformationType("SHIFT");
    public static final TransformationType DUPLICATE = new TransformationType("DUPLICATE");
    public static final TransformationType REMOVE = new TransformationType("REMOVE");


    private final String myName; // for debug only

    private TransformationType(String name) {
        myName = name;
    }

    public String toString() {
        return myName;
    }
}
