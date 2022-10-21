package net.datenwerke.rs.base.service.parameters.separator;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Entity
@Table(name = "SEP_PARAM_INST")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.parameters.separator.dto")
public class SeparatorParameterInstance extends ParameterInstance<SeparatorParameterDefinition> {

   /**
    * 
    */
   private static final long serialVersionUID = 8865343674834713704L;

   @Override
   public Object getSelectedValue(User user) {
      return null;
   }

   @Override
   public Object getDefaultValue(User user, ParameterSet parameterSet) {
      return getSelectedValue(user);
   }

   @Override
   protected Class<?> getType() {
      return String.class;
   }
}
