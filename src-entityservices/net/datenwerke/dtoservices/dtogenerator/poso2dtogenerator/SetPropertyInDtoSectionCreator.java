package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator;

import java.util.Collection;

import javax.lang.model.type.DeclaredType;

import org.apache.commons.lang3.StringUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.ExposedClientMethod;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;

/**
 * 
 *
 */
public class SetPropertyInDtoSectionCreator {

   private String name;
   private DtoView dtoView;
   private PosoAnalizer referencedPoso;
   private String setMethodForDto;
   private String getMethodForDto;
   private String getMethod;
   private DeclaredType referencedPosoCollectionType;
   private boolean enclosedPoso = false;
   private DeclaredType collectionType;
   private boolean isString;
   private boolean disableHtmlEncode;
   private boolean doNotCutOffStrings;
   private boolean enableSimpleHtmlPolicy;
   private boolean inheritDtoView;

   public SetPropertyInDtoSectionCreator(PosoFieldDescriptor field) {
      setName(field.getName());
      setDtoView(field.getDtoView());
      setSetMethodForDto(field.getSetMethodForDto());
      setGetMethodForDto(field.getGetMethodForDto());
      setGetMethod(field.getGetMethod());
      setEnclosedReferencedPoso(field.referencesEnclosedPoso());

      if (field.referencesCollection())
         collectionType = (DeclaredType) field.getType();

      if (field.referencesPosoCollection()) {
         setReferencedPoso(field.getPosoReferencedInCollection());
         setReferencedPosoCollectionType((DeclaredType) field.getType());
      } else if (field.referencesPoso()) {
         setReferencedPoso(field.getPoso());
      } else if (field.isString()) {
         isString = true;
      }
      disableHtmlEncode = field.getExposeToClientAnno().disableHtmlEncode();
      enableSimpleHtmlPolicy = field.getExposeToClientAnno().enableSimpleHtmlPolicy();
      doNotCutOffStrings = field.getExposeToClientAnno().allowArbitraryLobSize();

      inheritDtoView = field.getExposeToClientAnno().inheritDtoView();
   }

   public SetPropertyInDtoSectionCreator(ExposedClientMethod method) {
      setName(method.getSimpleName().substring(3));
      setDtoView(method.getDtoView());
      setSetMethodForDto(method.getSetMethodForDto());
      setGetMethodForDto(method.getGetMethodForDto());
      setGetMethod(method.getGetMethod());
      setEnclosedReferencedPoso(method.referencesEnclosedPoso());

      if (method.returnsPosoCollection()) {
         setReferencedPoso(method.getPosoReferencedInReturnedCollection());
         setReferencedPosoCollectionType((DeclaredType) method.getReturnType());
      } else if (method.returnsPoso()) {
         setReferencedPoso(method.getPoso());
      }
   }

   public void addSetSection(Collection<String> referenceAccu, MethodBuilder createDtoMethod) {
      createDtoMethod.addBodyComment(" set " + getName());

      /* find out with which AccessRight */
      String newHereDtoView = isEnclosedReferencedPoso() ? "here" : inheritDtoView ? "here" : "referenced";

      /* set field */
      if (referencesPosoCollection()) {
         /* get poso and add reference */
         referenceAccu.add(referencedPoso.getDtoInformation().getFullyQualifiedClassName());
         referenceAccu.add(referencedPoso.getFullyQualifiedClassName());

         /* build collection interface name */
         Class<?> collectionInterface = SourceFileGenerationUtils.isCollection(getReferencedPosoCollectionType());

         referenceAccu.add(collectionInterface.getName());
         String collectionInterfaceName = collectionInterface.getSimpleName();

         collectionInterfaceName += "<" + referencedPoso.getDtoInformation().getClassName() + ">";

         /* build collection class name */
         Collection collection = SourceFileGenerationUtils
               .instantiateCollection(SourceFileGenerationUtils.isCollection(getReferencedPosoCollectionType()));

         referenceAccu.add(collection.getClass().getName());

         String collectionClassName = collection.getClass().getSimpleName() + "<"
               + referencedPoso.getDtoInformation().getClassName() + ">";

         String collectionVar = "col_" + getName();

         /* write method part */
         createDtoMethod.addBodyLine(
               "final " + collectionInterfaceName + " " + collectionVar + " = new " + collectionClassName + "();");
         createDtoMethod.beginBodyBlock("if( null != poso." + getGetMethod() + "())");

         createDtoMethod
               .beginBodyBlock("for(" + referencedPoso.getSimpleName() + " refPoso : poso." + getGetMethod() + "())");

         String tmpname = "tmpDto" + referencedPoso.getDtoInformation().getClassName() + getGetMethod();
         createDtoMethod.addBodyLine("final Object " + tmpname + " = dtoServiceProvider.get().createDto(refPoso, "
               + newHereDtoView + ", referenced);");
         createDtoMethod.addBodyLine(
               collectionVar + ".add((" + referencedPoso.getDtoInformation().getClassName() + ") " + tmpname + ");");
         createDtoMethod.addBodyComment("ask for dto with higher view if generated");

         createDtoMethod
               .addCustomLine("\t\t\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation("
                     + tmpname + ", refPoso, new " + CallbackOnDtoCreation.class.getName() + "(){\n");
         createDtoMethod.incrementIndent();
         createDtoMethod.beginBodyBlock("public void callback(Object dto)");

         createDtoMethod.beginOneLineBlock("if(null == dto)");
         createDtoMethod
               .addBodyLine("throw new IllegalArgumentException(\"expected to get dto object (" + getName() + ")\");");

         if (SourceFileGenerationUtils.isList(getReferencedPosoCollectionType())) {
            createDtoMethod.addBodyLine("int tmp_index = " + collectionVar + ".indexOf(" + tmpname + ");");
            createDtoMethod.addBodyLine(
                  collectionVar + ".set(tmp_index,(" + referencedPoso.getDtoInformation().getClassName() + ") dto);");
         } else {
            createDtoMethod.addBodyLine(collectionVar + ".remove(" + tmpname + ");");
            createDtoMethod.addBodyLine(
                  collectionVar + ".add((" + referencedPoso.getDtoInformation().getClassName() + ") dto);");
         }

         createDtoMethod.endBodyBlock();
         createDtoMethod.decrementIndent();
         createDtoMethod.addCustomLine("\t\t\t\t\t});\n");

         createDtoMethod.endBodyBlock();

         createDtoMethod.addBodyLine("dto." + getSetMethodForDto() + "(" + collectionVar + ");");
         createDtoMethod.endBodyBlock();
      } else if (referencesCollection()) {
         /* build collection interface name */
         Class<?> collectionInterface = SourceFileGenerationUtils.isCollection(collectionType);

         referenceAccu.add(collectionInterface.getName());
         String collectionInterfaceName = collectionInterface.getSimpleName();

         collectionInterfaceName += "<" + SourceFileGenerationUtils.getTypeArguments(collectionType) + ">";

         /* build collection class name */
         Collection collection = SourceFileGenerationUtils
               .instantiateCollection(SourceFileGenerationUtils.isCollection(collectionType));

         referenceAccu.add(collection.getClass().getName());

         String collectionClassName = collection.getClass().getSimpleName() + "<"
               + SourceFileGenerationUtils.getTypeArguments(collectionType) + ">";

         String collectionVar = "col_" + getName();

         /* write method part */
         createDtoMethod
               .addBodyLine(collectionInterfaceName + " " + collectionVar + " = new " + collectionClassName + "();");

         String typeArgument = SourceFileGenerationUtils.getTypeArguments(collectionType);
         createDtoMethod.beginBodyBlock("if( null != poso." + getGetMethod() + "())");
         createDtoMethod.beginOneLineBlock("for(" + typeArgument + " obj : poso." + getGetMethod() + "())");
         createDtoMethod.addBodyLine(collectionVar + ".add((" + typeArgument + ") obj);");

         createDtoMethod.addBodyLine("dto." + getSetMethodForDto() + "(" + collectionVar + ");");
         createDtoMethod.endBodyBlock();
      } else if (referencesPoso()) {
         /* add poso to references */
         referenceAccu.add(referencedPoso.getDtoInformation().getFullyQualifiedClassName());

         /* add to method body */
         String tmpname = "tmpDto" + referencedPoso.getDtoInformation().getClassName() + getGetMethod();

         createDtoMethod.addBodyLine("Object " + tmpname + " = dtoServiceProvider.get().createDto(poso."
               + getGetMethod() + "(), " + newHereDtoView + ", referenced);");
         createDtoMethod.addBodyLine("dto." + getSetMethodForDto() + "(("
               + referencedPoso.getDtoInformation().getClassName() + ")" + tmpname + ");");
         createDtoMethod.addBodyComment("ask for a dto with higher view if generated");
         createDtoMethod.addCustomLine(
               "\t\t\t((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(" + tmpname
                     + ", poso." + getGetMethod() + "(), new " + CallbackOnDtoCreation.class.getName() + "(){\n");
         createDtoMethod.incrementIndent();
         createDtoMethod.beginBodyBlock("public void callback(Object refDto)");

         createDtoMethod.beginOneLineBlock("if(null != refDto)");
         createDtoMethod.addBodyLine(
               "dto." + getSetMethodForDto() + "((" + referencedPoso.getDtoInformation().getClassName() + ")refDto);");
         createDtoMethod.endBodyBlock();
         createDtoMethod.decrementIndent();
         createDtoMethod.addCustomLine("\t\t\t});\n");

      } else if (isString && disableHtmlEncode && enableSimpleHtmlPolicy) {
         createDtoMethod.addBodyLine("dto." + getSetMethodForDto()
               + "(new HtmlPolicyBuilder().allowCommonInlineFormattingElements().allowCommonBlockElements().allowStyling().allowAttributes(\"size\", \"color\").onElements(\"font\").toFactory().sanitize(poso."
               + getGetMethod() + "() ));");

         referenceAccu.add(PolicyFactory.class.getName());
         referenceAccu.add(Sanitizers.class.getName());
         referenceAccu.add(HtmlPolicyBuilder.class.getName());

      } else if (isString && !disableHtmlEncode) {
         if (doNotCutOffStrings)
            createDtoMethod.addBodyLine(
                  "dto." + getSetMethodForDto() + "(StringEscapeUtils.escapeXml(poso." + getGetMethod() + "() ));");
         else
            createDtoMethod
                  .addBodyLine("dto." + getSetMethodForDto() + "(StringEscapeUtils.escapeXml(StringUtils.left(poso."
                        + getGetMethod() + "()," + DtoAnnotationProcessor.CUT_OFF_CLOBS_SIZE + ")));");
         referenceAccu.add(StringEscapeUtils.class.getName());
         referenceAccu.add(StringUtils.class.getName());
      } else
         createDtoMethod.addBodyLine("dto." + getSetMethodForDto() + "(poso." + getGetMethod() + "() );");

      createDtoMethod.addBodyLine();
   }

   private boolean referencesPosoCollection() {
      return null != referencedPoso && null != referencedPosoCollectionType;
   }

   private boolean referencesPoso() {
      return null != referencedPoso && null == referencedPosoCollectionType;
   }

   private boolean referencesCollection() {
      return null != collectionType;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public DtoView getDtoView() {
      return dtoView;
   }

   public void setDtoView(DtoView dtoView) {
      this.dtoView = dtoView;
   }

   public PosoAnalizer getReferencedPoso() {
      return referencedPoso;
   }

   public void setReferencedPoso(PosoAnalizer referencedPoso) {
      this.referencedPoso = referencedPoso;
   }

   public String getSetMethodForDto() {
      return setMethodForDto;
   }

   public void setSetMethodForDto(String setMethodForDto) {
      this.setMethodForDto = setMethodForDto;
   }

   public String getGetMethodForDto() {
      return getMethodForDto;
   }

   public void setGetMethodForDto(String getMethodForDto) {
      this.getMethodForDto = getMethodForDto;
   }

   public String getGetMethod() {
      return getMethod;
   }

   public void setGetMethod(String getMethod) {
      this.getMethod = getMethod;
   }

   public void setReferencedPosoCollectionType(DeclaredType referencedPosoCollectionType) {
      this.referencedPosoCollectionType = referencedPosoCollectionType;
   }

   public DeclaredType getReferencedPosoCollectionType() {
      return referencedPosoCollectionType;
   }

   public boolean isEnclosedReferencedPoso() {
      return enclosedPoso;
   }

   public void setEnclosedReferencedPoso(boolean enclosed) {
      enclosedPoso = enclosed;
   }

}
