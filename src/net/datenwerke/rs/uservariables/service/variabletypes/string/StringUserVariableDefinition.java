package net.datenwerke.rs.uservariables.service.variabletypes.string;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

/**
 * 
 *
 */
@Entity
@Table(name = "STR_USERVARIABLE_DEF")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.uservariables.client.variabletypes.string.dto", displayTitle = "UserVariablesMessages.INSTANCE.stringVariableText()", additionalImports = UserVariablesMessages.class)
public class StringUserVariableDefinition extends UserVariableDefinition {

   /**
    * 
    */
   private static final long serialVersionUID = 2670503506233986073L;

   @ExposeToClient
   private Integer width = 150;

   @ExposeToClient
   private Integer height = 1;

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

   @Override
   protected UserVariableInstance doCreateVariableInstance() {
      return new StringUserVariableInstance();
   }

   @Transient
   @Override
   public Class<?> getType() {
      return String.class;
   }

}
