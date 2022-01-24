package net.datenwerke.annotationprocessing.utils;

/**
 * 
 *
 */
public interface SourceFileGenerator {

   public void generateSource();

   public String getPackageName();

   public String getClassName();

   public String getFullyQualifiedClassName();

   public String getSource();
}
