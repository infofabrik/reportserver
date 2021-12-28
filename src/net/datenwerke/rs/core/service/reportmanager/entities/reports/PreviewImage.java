package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name = "REPORT_PREVIEW_IMAGE")
@Audited
public class PreviewImage implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -1309737230090405977L;

   @Column(length = 128)
   private String name;

   @Lob
   private byte[] content;

   @Version
   private Long version;

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
