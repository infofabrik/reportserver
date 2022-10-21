package net.datenwerke.rs.base.service.parameters.string;

import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinitionForJuel;

/**
 * 
 *
 */
public class TextParameterDefinitionForJuel extends ParameterDefinitionForJuel {

   private Datatype returnType = Datatype.String;
   private String defaultValue = ""; //$NON-NLS-1$
   private Integer width = 210;
   private Integer height = 1;

   public Datatype getReturnType() {
      return returnType;
   }

   public void setReturnType(Datatype returnType) {
      this.returnType = returnType;
   }

   public String getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
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

}
