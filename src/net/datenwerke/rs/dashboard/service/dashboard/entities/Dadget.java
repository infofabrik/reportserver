package net.datenwerke.rs.dashboard.service.dashboard.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Audited
@Entity
@Table(name = "DADGET")
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(dtoPackage = "net.datenwerke.rs.dashboard.client.dashboard.dto", createDecorator = true, abstractDto = true)
public abstract class Dadget implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -9148252532248599659L;

   @ExposeToClient
   private int n = 1;

   @ExposeToClient
   private int col = 1;

   @ExposeToClient
   private int height = 250;

   @ExposeToClient
   private long reloadInterval = -1;

   @ExposeToClient
   private DadgetContainer container = DadgetContainer.CENTER;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JoinTable(name = "DADGET_REPORT_2_PARAM_INST")
   @ExposeToClient
   @EnclosedEntity
   @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
   @NotAudited
   private Set<ParameterInstance> parameterInstances = new HashSet<ParameterInstance>();

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

   final public void setN(int n) {
      this.n = n;
   }

   final public int getN() {
      return n;
   }

   public void setCol(int column) {
      this.col = column;
   }

   public int getCol() {
      return col;
   }

   public long getReloadInterval() {
      return reloadInterval;
   }

   public void setReloadInterval(long reloadInterval) {
      this.reloadInterval = reloadInterval;
   }

   public void init() {

   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getHeight() {
      return height;
   }

   public DadgetContainer getContainer() {
      return container;
   }

   public void setContainer(DadgetContainer container) {
      this.container = container;
   }

   public Set<ParameterInstance> getParameterInstances() {
      return parameterInstances;
   }

   public void setParameterInstances(Set<ParameterInstance> parameterInstances) {
      if (null == parameterInstances)
         parameterInstances = new HashSet<ParameterInstance>();

      this.parameterInstances.clear();
      this.parameterInstances.addAll(parameterInstances);
   }
}
