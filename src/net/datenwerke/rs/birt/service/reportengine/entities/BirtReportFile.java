package net.datenwerke.rs.birt.service.reportengine.entities;

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

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

@Entity
@Table(name = "BIRT_REPORT_FILE")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.birt.client.reportengines.dto")
public class BirtReportFile implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -430543923010097624L;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   private String name;

   @Basic(fetch = FetchType.LAZY)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String content;

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

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

}
