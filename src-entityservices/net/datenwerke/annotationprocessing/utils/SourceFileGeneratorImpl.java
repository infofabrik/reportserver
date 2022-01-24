package net.datenwerke.annotationprocessing.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;

/**
 * 
 *
 */
public abstract class SourceFileGeneratorImpl implements SourceFileGenerator {

   public enum ClassType {
      Type, Interface, Enum
   };

   public static final String HASH_MAP_LOCATION = "java.util.HashMap";
   public static final String MAP_LOCATION = "java.util.Map";

   public static final String SET_LOCATION = "java.util.Set";
   public static final String HASH_SET_LOCATION = "java.util.HashSet";
   public static final String LIST_LOCATION = "java.util.List";
   public static final String ARRAY_LIST_LOCATION = "java.util.ArrayList";

   protected String source = "";
   private AbstractProcessor processor;

   public SourceFileGeneratorImpl(AbstractProcessor processor) {
      this.processor = processor;
   }

   public void generateSource() {
      StringBuilder sourceBuilder = new StringBuilder();

      addClassDeclaration(sourceBuilder);

      /* create preamble */
      StringBuilder preamble = new StringBuilder();
      addPackageDescription(preamble);
      addImportSection(preamble);

      source = preamble.append(sourceBuilder).toString();
   }

   protected void addPackageDescription(StringBuilder sourceBuilder) {
      sourceBuilder.append("package ").append(getPackageName()).append(";\n\n");
   }

   protected void addImportSection(StringBuilder sourceBuilder) {
      SortedSet<String> imports = new TreeSet<String>();

      /* references */
      if (null != getReferencedClasses())
         for (String imp : getReferencedClasses())
            imports.add(new StringBuilder("import ").append(imp).append(";\n").toString());

      for (String imp : imports)
         sourceBuilder.append(imp);
   }

   protected void addClassDeclaration(StringBuilder sourceBuilder) {
      addClassComment(sourceBuilder);
      addAnnotations(sourceBuilder);

      /* get class to extend dto from */
      String extendedClass = getExtendedClass();

      /* get interfaces to implement */
      Collection<String> implmentedInterfaces = getImplementedInterfaces();
      Iterator<String> implementedInterfacesIterator = implmentedInterfaces.iterator();

      if (!isInterface() && isAbstract())
         sourceBuilder.append("abstract ");

      /* create class/interface definition */
      sourceBuilder.append(getClassModifier());
      if (isInterface())
         sourceBuilder.append(" interface ");
      else if (isEnum())
         sourceBuilder.append(" enum ");
      else
         sourceBuilder.append(" class ");

      sourceBuilder.append(getClassName());

      /* any superclasses/interfaces */
      boolean seenExtends = false;
      if (null != extendedClass) {
         seenExtends = true;
         sourceBuilder.append(" extends ").append(extendedClass);
      }

      if (implementedInterfacesIterator.hasNext()) {
         sourceBuilder.append(isInterface() ? seenExtends ? "" : " extends " : " implements ")
               .append(implementedInterfacesIterator.next());
         while (implementedInterfacesIterator.hasNext())
            sourceBuilder.append(", ").append(implementedInterfacesIterator.next());
      }

      sourceBuilder.append(" {\n\n");

      addClassBody(sourceBuilder);

      sourceBuilder.append("\n}\n");
   }

   protected void addAnnotations(StringBuilder sourceBuilder) {
      if (addGeneratedAnnotation())
         sourceBuilder.append("@").append(GeneratedType.class.getSimpleName()).append("(\"")
               .append(processor.getClass().getName()).append("\")\n");
   }

   protected boolean addGeneratedAnnotation() {
      return true;
   }

   public String getFullyQualifiedClassName() {
      if (!"".equals(getPackageName()))
         return getPackageName() + "." + getClassName();
      return getClassName();
   }

   public String getSource() {
      return source;
   }

   /**
    * To be overridden
    */
   protected boolean isAbstract() {
      return false;
   }

   protected String getClassModifier() {
      return "public";
   }

   protected Collection<String> getReferencedClasses() {
      Collection<String> references = new TreeSet<String>();

      if (addGeneratedAnnotation())
         references.add(GeneratedType.class.getName());

      return references;
   }

   protected void addClassComment(StringBuilder sourceBuilder) {
      sourceBuilder.append("\n/**\n").append(" * This file was automatically created by ")
            .append(DtoAnnotationProcessor.name).append(", version ").append(DtoAnnotationProcessor.version)
            .append("\n").append(" */\n");
   }

   protected String getExtendedClass() {
      return null;
   }

   abstract protected void addClassBody(StringBuilder sourceBuilder);

   protected Collection<String> getImplementedInterfaces() {
      return new TreeSet<String>();
   }

   protected ClassType getClassType() {
      return ClassType.Type;
   }

   protected boolean isInterface() {
      return getClassType() == ClassType.Interface;
   }

   protected boolean isEnum() {
      return getClassType() == ClassType.Enum;
   }

   protected boolean isClass() {
      return getClassType() == ClassType.Type;
   }

}
