package net.datenwerke.scheduler.service.scheduler.entities.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.history")
@Table(name = "SCHED_HIST_ENTRY_PROPERTY")
@Entity
public class HistoryEntryProperty {

   @ExposeToClient
   @Column(length = 64, nullable = false)
   private String key;

   @ExposeToClient
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String value;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   public HistoryEntryProperty() {

   }

   public HistoryEntryProperty(String key, String value) {
      setKey(key);
      setValue(value);
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

}
