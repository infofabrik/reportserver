package net.sf.jxls.processor;

/**
 * Allows to process cell in template before actual processing starts
 * @author Leonid Vysochin
 */
public interface PropertyPreprocessor {
    /**
     * This method is invoked for each cell in template before all other processing starts
     *
     * @param propertyTemplateName The actual cell value in template
     * @return New cell value
     */
    String processProperty(String propertyTemplateName);
}
