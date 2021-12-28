package net.datenwerke.rs.uservariables.service.uservariables.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

/**
 * 
 *
 */
@Entity
@Table(name = "USERVAR_DEF")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.uservariables.client.uservariables.dto", abstractDto = true)
abstract public class UserVariableDefinition implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 3957144231292503668L;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String description;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   @Transient
   public final UserVariableInstance createVariableInstance() {
      UserVariableInstance instance = doCreateVariableInstance();
      instance.setDefinition(this);
      return instance;
   }

   @Transient
   protected abstract UserVariableInstance doCreateVariableInstance();

   /**
    * Tests on equality of id field.
    */
   @Override
   public boolean equals(Object obj) {
      /* returns true if objects have the same id */
      if (!(obj instanceof UserVariableDefinition))
         return false;

      /* cast object */
      UserVariableDefinition pd = (UserVariableDefinition) obj;

      /* test id */
      if (null == getId() && null != pd.getId())
         return false;
      if (null != getId() && !getId().equals(pd.getId()))
         return false;

      return true;
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();

      return super.hashCode();
   }

   @Transient
   abstract public Class<?> getType();

}
