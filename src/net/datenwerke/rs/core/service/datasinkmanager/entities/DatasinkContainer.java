package net.datenwerke.rs.core.service.datasinkmanager.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@Entity
@Table(name = "DATASINK_CONTAINER")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.core.client.datasinkmanager.dto")
public class DatasinkContainer implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 6605057669794888889L;

   @ExposeToClient
   @ManyToOne
   private DatasinkDefinition datasink;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   public DatasinkContainer() {
   }

   public DatasinkContainer(DatasinkDefinition definition) {
      this.datasink = definition;
   }

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

   public DatasinkDefinition getDatasink() {
      return datasink;
   }

   public void setDatasink(DatasinkDefinition datasink) {
      this.datasink = datasink;
   }

}
