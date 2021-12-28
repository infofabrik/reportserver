package net.datenwerke.rs.base.service.parameters.headline;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

/**
 * 
 *
 */
@Entity
@Table(name = "HEADLINE_PARAM_DEF")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.parameters.headline.dto", displayTitle = "RsMessages.INSTANCE.headlineParameterText()", additionalImports = RsMessages.class)
public class HeadlineParameterDefinition extends ParameterDefinition<HeadlineParameterInstance> {

   /**
    * 
    */
   private static final long serialVersionUID = 3960732034147394530L;

   @ExposeToClient
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String value = ""; //$NON-NLS-1$

   public void setValue(String value) {
      this.value = value;
   }

   public String getValue() {
      if (null == value || "".equals(value))
         return getName();
      return value;
   }

   @Override
   protected HeadlineParameterInstance doCreateParameterInstance() {
      return new HeadlineParameterInstance();
   }

   @Override
   public boolean isSeparator() {
      return true;
   }
}
