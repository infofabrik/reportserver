package net.datenwerke.rs.base.service.parameters.blatext;

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
@Table(name = "BLATEXT_PARAM_INST")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.parameters.blatext.dto")
public class BlatextParameterInstance extends ParameterInstance<BlatextParameterDefinition> {

   /**
    * 
    */
   private static final long serialVersionUID = -2332220199796956927L;

   @Override
   public Object getSelectedValue(User user) {
      return ((BlatextParameterDefinition) getDefinition()).getValue();
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
