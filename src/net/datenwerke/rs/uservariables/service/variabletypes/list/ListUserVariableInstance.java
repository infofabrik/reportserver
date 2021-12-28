package net.datenwerke.rs.uservariables.service.variabletypes.list;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

/**
 * 
 *
 */
@Entity
@Table(name = "LIST_USERVARIABLE_INST")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.uservariables.client.variabletypes.list.dto")
public class ListUserVariableInstance extends UserVariableInstance {

   /**
    * 
    */
   private static final long serialVersionUID = 8818703141839593844L;

   @JoinTable(name = "LIST_USERVARIABLE_INST_VL", joinColumns = @JoinColumn(name = "list_user_var_instanc_id"))
   @ElementCollection
   @ExposeToClient
   @OrderColumn(name = "val_n")
   private Set<String> value;

   @Override
   public Object getVariableValue() {
      return getValue();
   }

   public void setValue(Set<String> value) {
      this.value = value;
   }

   public Set<String> getValue() {
      return value;
   }
}
