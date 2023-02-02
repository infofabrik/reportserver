package net.datenwerke.rs.core.service.parameters.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Provider;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

@Entity
@Table(name = "PARAMETER_DEFINITION")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.core.client.parameters.dto", 
      abstractDto = true, 
      createDecorator = true
)
abstract public class ParameterDefinition<I extends ParameterInstance> implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -2686485560923162445L;

   @Transient
   private final Provider<ReportManagerMessages> messages = LocalizationServiceImpl
         .getMessagesProvider(ReportManagerMessages.class);

   @Inject
   protected static I18nToolsService i18nTools;

   @ExposeToClient(view = DtoView.MINIMAL)
   private String name;

   @ExposeToClient(
         view = DtoView.MINIMAL, 
         validateDtoProperty = @PropertyValidator(
               string = @StringValidator(regex = "^[a-zA-Z0-9_\\-]*$")
         )
   )
   @Column(length = 128)
   private String key;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String description = "";// messages.parameterDefinitionDefaultDescription();

   @JoinTable(name = "PARAM_DEF_2_DEPENDANTS")
   @ExposeToClient
   @ManyToMany
   @JoinColumn(name = "PARAMETERDEFINITION_DEPENDSON")
   private List<ParameterDefinition> dependsOn = new ArrayList<ParameterDefinition>();

   @ExposeToClient
   private Boolean displayInline = false;

   @ExposeToClient
   private boolean mandatory = false;

   @ExposeToClient
   private Integer labelWidth = null;

   /**
    * The parameter's position;
    */
   @ExposeToClient
   private int n;

   @ExposeToClient
   private Boolean hidden = false;

   @ExposeToClient
   private Boolean editable = true;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Transient
   @TransientID
   private Long oldTransientId;

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

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public int getN() {
      return n;
   }

   public void setN(int n) {
      this.n = n;
   }

   public Boolean isHidden() {
      return hidden;
   }

   public void setHidden(Boolean hidden) {
      if (null == hidden)
         hidden = false;
      this.hidden = hidden;
   }

   public Boolean isEditable() {
      return editable;
   }

   public void setEditable(Boolean editable) {
      if (null == editable)
         editable = true;
      this.editable = editable;
   }

   /**
    * To be overriden
    */
   @Transient
   public void initWithDefaultValues() {
      setKey("key"); //$NON-NLS-1$
      setName(messages.get().parameterDefinitionDefaultName());
   }

   @Transient
   public final I createParameterInstance() {
      I instance = doCreateParameterInstance();
      instance.setDefinition(this);
      return instance;
   }

   @Transient
   protected abstract I doCreateParameterInstance();

   /**
    * Tests on equality of id field.
    */
   @Override
   public boolean equals(Object obj) {
      /* returns true if objects have the same id */
      if (!(obj instanceof ParameterDefinition))
         return false;

      if (!obj.getClass().equals(getClass()))
         return false;

      /* cast object */
      ParameterDefinition pd = (ParameterDefinition) obj;

      /* test id */
      if (null == getId() && null != pd.getId())
         return false;
      if (null != getId() && !getId().equals(pd.getId()))
         return false;

      return super.equals(obj);
   }

   @Override
   public int hashCode() {
      if (null != getId())
         return getId().hashCode();

      return super.hashCode();
   }

   public void setOldTransientId(Long oldTransientId) {
      this.oldTransientId = oldTransientId;
   }

   public Long getOldTransientId() {
      return oldTransientId;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setDependsOn(List<ParameterDefinition> dependsOn) {
      if (dependsOn.contains(this))
         dependsOn.remove(this);
      this.dependsOn = dependsOn;
   }

   public List<ParameterDefinition> getDependsOn() {
      if (dependsOn.contains(this))
         dependsOn.remove(this);
      return new ArrayList<ParameterDefinition>(dependsOn);
   }

   public List<ParameterDefinition> getAllDependents() {
      List<ParameterDefinition> dependents = new ArrayList<ParameterDefinition>();

      _getAllDependents(dependents, this, this);

      return dependents;
   }

   private void _getAllDependents(List<ParameterDefinition> dependents, ParameterDefinition def,
         ParameterDefinition doNotAdd) {
      if (dependents.contains(def))
         return;
      if (doNotAdd != def)
         dependents.add(def);

      Iterator<ParameterDefinition> it = def.getDependsOn().iterator();
      while (it.hasNext())
         _getAllDependents(dependents, it.next(), doNotAdd);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public ParameterDefinitionForJuel createParameterDefinitionForJuel() {
      return new ParameterDefinitionForJuel();
   }

   public void configureParameterDefinitionForJuel(ParameterDefinitionForJuel definition) {
      definition.setId(id);
      definition.setVersion(version);
   }

   public void setDisplayInline(Boolean displayInline) {
      if (null == displayInline)
         displayInline = false;
      this.displayInline = displayInline;
   }

   public boolean isDisplayInline() {
      return displayInline;
   }

   public boolean isMandatory() {
      return mandatory;
   }

   public void setMandatory(boolean mandatory) {
      this.mandatory = mandatory;
   }

   public Integer getLabelWidth() {
      return labelWidth;
   }

   public void setLabelWidth(Integer labelWidth) {
      this.labelWidth = labelWidth;
   }

   public boolean isSeparator() {
      return false;
   }
   
   @ClonePostProcessor
   public void guideCloningProcess(Object report) {
      setDependsOn(new ArrayList<ParameterDefinition>());
   }
}
