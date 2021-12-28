package net.datenwerke.rs.base.service.parameters.string;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.string.post.TextParameterDefinitionPost;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinitionForJuel;

/**
 * A parameter that allows for a simple input of a basic java type (String, int,
 * long ...)
 *
 */
@Entity
@Table(name = "TEXT_PARAM_DEF")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.parameters.string.dto", displayTitle = "RsMessages.INSTANCE.textParameterText()", additionalImports = RsMessages.class, dto2PosoPostProcessors = TextParameterDefinitionPost.class, poso2DtoPostProcessors = TextParameterDefinitionPost.class)
public class TextParameterDefinition extends ParameterDefinition<TextParameterInstance> {

   /**
    * 
    */
   private static final long serialVersionUID = -3077174119143361427L;

   @ExposeToClient
   private Datatype returnType = Datatype.String;

   @ExposeToClient
   private boolean returnNullWhenEmpty;

   @ExposeToClient
   private String defaultValue = ""; //$NON-NLS-1$

   @ExposeToClient
   private String validatorRegex = ""; //$NON-NLS-1$

   @ExposeToClient
   private Integer width = 210;

   @ExposeToClient
   private Integer height = 1;

   public String getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(String value) {
      this.defaultValue = value;
   }

   public Integer getWidth() {
      return width;
   }

   public void setWidth(Integer width) {
      this.width = width;
   }

   public Integer getHeight() {
      return height;
   }

   public void setHeight(Integer height) {
      this.height = height;
   }

   public String getValidatorRegex() {
      return validatorRegex;
   }

   public void setValidatorRegex(String validatorRegex) {
      this.validatorRegex = validatorRegex;
   }

   public boolean isReturnNullWhenEmpty() {
      return returnNullWhenEmpty;
   }

   public void setReturnNullWhenEmpty(boolean returnNullWhenEmpty) {
      this.returnNullWhenEmpty = returnNullWhenEmpty;
   }

   @Override
   public void initWithDefaultValues() {
      super.initWithDefaultValues();
      setWidth(210);
      setHeight(1);
   }

   @Override
   protected TextParameterInstance doCreateParameterInstance() {
      return new TextParameterInstance();
   }

   @Override
   public ParameterDefinitionForJuel createParameterDefinitionForJuel() {
      return new TextParameterDefinitionForJuel();
   }

   @Override
   public void configureParameterDefinitionForJuel(ParameterDefinitionForJuel definition) {
      super.configureParameterDefinitionForJuel(definition);

      TextParameterDefinitionForJuel def = (TextParameterDefinitionForJuel) definition;
      def.setWidth(width);
      def.setHeight(height);
      def.setDefaultValue(defaultValue);
      def.setReturnType(returnType);
   }

   public void setReturnType(Datatype returnType) {
      if (null == returnType)
         returnType = Datatype.String;
      this.returnType = returnType;
   }

   public Datatype getReturnType() {
      return returnType;
   }

   public Object getCastedValue(String value) {
      /* if is null, return null */
      if (null == value)
         return null;

      if ("".equals(value) && !Datatype.String.equals(getReturnType()))
         return null;

      if (Datatype.String.equals(getReturnType()) && returnNullWhenEmpty && "".equals(value.trim()))
         return null;

      switch (getReturnType()) {
      case String:
         return value;
      case Integer:
         return Integer.parseInt(value);
      case Long:
         return Long.valueOf(value);
      case BigDecimal:
         return new BigDecimal(value);
      case Float:
         return Float.valueOf(value);
      case Double:
         return Double.valueOf(value);
      case Boolean:
         return Boolean.valueOf(value);
      default:
         return value;
      }
   }

   public Class<?> getType() {
      switch (getReturnType()) {
      case String:
         return String.class;
      case Integer:
         return Integer.class;
      case Long:
         return Long.class;
      case BigDecimal:
         return BigDecimal.class;
      case Float:
         return Float.class;
      case Double:
         return Double.class;
      case Boolean:
         return Boolean.class;
      default:
         return String.class;
      }
   }

}
