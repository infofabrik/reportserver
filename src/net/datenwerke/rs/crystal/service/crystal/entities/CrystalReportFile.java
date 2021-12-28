package net.datenwerke.rs.crystal.service.crystal.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

@Entity
@Table(name = "CRYSTAL_REPORT_FILE")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.crystal.client.crystal.dto")
public class CrystalReportFile implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 642897329427675906L;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   private String name;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   private byte[] content;

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

   public byte[] getContent() {
      return content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }

}
