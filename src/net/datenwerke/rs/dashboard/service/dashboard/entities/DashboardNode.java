package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;

@Entity
@Table(name = "DASHBOARD_DASHBOARD_NODE")
@Audited
@Indexed
@GenerateDto(dtoPackage = "net.datenwerke.rs.dashboard.client.dashboard.dto", typeDescriptionMsg = DashboardMessages.class, typeDescriptionKey = "dashboard")
public class DashboardNode extends AbstractDashboardManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 3277112995376666835L;

   @ExposeToClient(view = DtoView.MINIMAL, displayTitle = true)
   @Column(length = 128)
   @Field
   @Title
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Field
   @Description
   private String description;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private Dashboard dashboard;

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

   public Dashboard getDashboard() {
      return dashboard;
   }

   public void setDashboard(Dashboard dashboard) {
      this.dashboard = dashboard;
   }

}
