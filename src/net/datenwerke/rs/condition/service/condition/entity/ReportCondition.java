package net.datenwerke.rs.condition.service.condition.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.client.condition.Condition;

@Entity
@Table(name = "CONDITION")
@GenerateDto(dtoPackage = "net.datenwerke.rs.condition.client.condition.dto", dtoImplementInterfaces = Condition.class, createDecorator = true)
public class ReportCondition implements Serializable, Condition {

   /**
   * 
   */
   private static final long serialVersionUID = -2064489443721722192L;

   @ExposeToClient(view = DtoView.LIST)
   @ManyToOne
   private TableReport report;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 64)
   private String key;

   @ExposeToClient(view = DtoView.MINIMAL, displayTitle = true)
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }

   public void setReport(TableReport report) {
      this.report = report;
   }

   public TableReport getReport() {
      return report;
   }

   @Override
   public boolean hasExpression() {
      return true;
   }

}
