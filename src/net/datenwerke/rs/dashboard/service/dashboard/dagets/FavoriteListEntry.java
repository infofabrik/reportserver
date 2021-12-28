package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;

@Entity
@Table(name = "FAVORITE_LIST_ENTRY")
@GenerateDto(dtoPackage = "net.datenwerke.rs.dashboard.client.dashboard.dto", createDecorator = true)
public class FavoriteListEntry implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -3297885707219281925L;

   @ExposeToClient(inheritDtoView = true)
   @OneToOne
   private AbstractTsDiskNode referenceEntry;

   private int position;

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

   public int getPosition() {
      return position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public AbstractTsDiskNode getReferenceEntry() {
      return referenceEntry;
   }

   public void setReferenceEntry(AbstractTsDiskNode referenceEntry) {
      this.referenceEntry = referenceEntry;
   }
}
