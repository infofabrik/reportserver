package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.usermanager.entities.User;

@Audited
@Entity
@Table(name = "DASHBOARD_USER")
public class UserDashboard {

   @ExposeToClient
   @ManyToOne
   private User user;

   @ExposeToClient
   @OneToOne(cascade = CascadeType.ALL)
   @EnclosedEntity
   private DashboardContainer dashboardContainer;

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

   public void setUser(User user) {
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public void setDashboardContainer(DashboardContainer dashboardContainer) {
      this.dashboardContainer = dashboardContainer;
   }

   public DashboardContainer getDashboardContainer() {
      return dashboardContainer;
   }
}
