package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Dto2ReportPostProcessor;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.post.Report2DtoPostProcessor;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.supervisor.Dto2ReportSupervisor;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterContainerNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.entitydiff.EntityDiffService;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuide;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuides;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
@Entity
@Table(name = "REPORT")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.core.client.reportmanager.dto.reports", 
      createDecorator = true, 
      dtoImplementInterfaces = DatasourceContainerProviderDto.class, 
      poso2DtoPostProcessors = Report2DtoPostProcessor.class, 
      dto2PosoSupervisor = Dto2ReportSupervisor.class, 
      dto2PosoPostProcessors = Dto2ReportPostProcessor.class, 
      typeDescriptionMsg = ReportmanagerMessages.class, 
      typeDescriptionKey = "reportLabel", 
      additionalFields = {
            @AdditionalField(name = "temporaryUid", type = String.class),
            @AdditionalField(name = "parentReportKey", type = String.class),
            @AdditionalField(name = "parentReportName", type = String.class),
            @AdditionalField(name = "parentReportDescription", type = String.class),
            @AdditionalField(name = "parentReportProperties", 
               type = HashSet.class, 
               generics = ReportPropertyDto.class) })
@EntityDiffGuides(guides = {
      @EntityDiffGuide(name = Report.ENTITY_DIFF_IDENTITCAL_FOR_EXECUTION, 
            ignoreId = true, 
            ignoreVersion = true, 
            whitelist = {
                  "parameterDefinitions", "parameterInstances", "datasourceContainer" }) })
abstract public class Report extends AbstractReportManagerNode
      implements ParameterContainerNode, DatasourceContainerProvider {

   public static final String ENTITY_DIFF_IDENTITCAL_FOR_EXECUTION = "report_identicalForExecution";

   /**
    * 
    */
   private static final long serialVersionUID = 3350868658886100690L;

   @Inject
   protected static Provider<EntityClonerService> entityCloner;

   @Inject
   protected static Provider<EntityDiffService> entityDiffService;

   @Inject
   protected static Provider<HookHandlerService> hookHandlerServiceProvider;

   @JoinTable(name = "REPORT_2_METADATA")
   @ExposeToClient(mergeDtoValueBack = false)
   @EnclosedEntity
   @OneToMany(cascade = CascadeType.ALL)
   private Set<ReportMetadata> reportMetadata = new HashSet<ReportMetadata>();

   @JoinTable(name = "REPORT_2_PROPERTY")
   @ExposeToClient(mergeDtoValueBack = false)
   @EnclosedEntity
   @OneToMany(cascade = CascadeType.ALL)
   private Set<ReportProperty> reportProperties = new HashSet<ReportProperty>();

   @ExposeToClient(displayTitle = true, view = DtoView.LIST)
   @Field
   @Column(length = 128)
   @Title
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Field
   @Description
   private String description;

   @ExposeToClient(view = DtoView.LIST, validateDtoProperty = @PropertyValidator(string = @StringValidator(regex = "^[a-zA-Z0-9_\\-]*$")))
   @Field
   @Column(length = 40)
   private String key;

   @ExposeToClient
   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private DatasourceContainer datasourceContainer = new DatasourceContainer();

   @JoinTable(name = "REPORT_2_PARAM_DEF")
   @ExposeToClient
   @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
   @OrderBy("n")
   @EnclosedEntity
   private List<ParameterDefinition> parameterDefinitions = new ArrayList<>();

   @JoinTable(name = "REPORT_2_PARAM_INST")
   @ExposeToClient
   @EnclosedEntity
   @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
   private Set<ParameterInstance> parameterInstances = new HashSet<>();

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private PreviewImage previewImage;

   @ExposeToClient(view = DtoView.LIST, mergeDtoValueBack = false)
   @Column(unique = true)
   @EntityClonerIgnore
   private String uuid = UUID.randomUUID().toString();

   @Transient
   /**
    * When creating a new temporary variant, the report's class is lost. We cannot
    * identify if it belonged to a variant or to a (base) report. This field allows
    * us to fetch the original report's class.
    */
   private Class<?> temporaryVariantType;

   /**
    * Returns the Report's type (the classes simple name).
    */
   public String getType() {
      return getClass().getSimpleName();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      if (null != key && "".equals(key.trim()))
         key = null;
      this.key = key;
   }

   public void setReportMetadata(Set<ReportMetadata> reportMetadata) {
      this.reportMetadata = reportMetadata;
   }

   public Set<ReportMetadata> getReportMetadata() {
      return reportMetadata;
   }

   public void addReportMetadata(ReportMetadata metadata) {
      this.reportMetadata.add(metadata);
   }

   public boolean hasReportMetadata(ReportMetadata metadata) {
      return this.reportMetadata.contains(metadata);
   }

   public ReportMetadata getReportMetadataByName(String key) {
      if (null == reportMetadata || null == key)
         return null;
      for (ReportMetadata rm : reportMetadata)
         if (key.equals(rm.getName()))
            return rm;

      return null;
   }

   /**
    * Do not call this method directly (use ReportService instead)
    * 
    * @param metadata
    * @see ReportService#remove(Report, ReportMetadata)
    */
   public void removeReportMetadata(ReportMetadata metadata) {
      this.reportMetadata.remove(metadata);
   }

   public Set<ReportProperty> getReportProperties() {
      return reportProperties;
   }

   public void setReportProperties(Set<ReportProperty> complexProperties) {
      this.reportProperties = complexProperties;
   }

   public void addReportProperty(ReportProperty property) {
      this.reportProperties.add(property);
   }

   public boolean hasReportProperty(ReportProperty property) {
      return this.reportProperties.contains(property);
   }

   /**
    * Returns the report property effective for the current report. I.e., If the
    * report is a variant, if returns the variant's property if found. If not
    * found, it returns the parent's property if found. If the report is not a
    * variant, returns the report's property if found.
    * 
    * @param name the name of the property
    * @return the report property applicable for the report
    */
   public ReportProperty getEffectiveReportProperty(String name) {
      ReportProperty property = getReportProperty(name);

      if (null == property && this instanceof ReportVariant)
         property = ((Report) getParent()).getReportProperty(name);

      return property;
   }

   public Object getEffectiveReportStringPropertyValue(String name, Object defaultValue,
         Function<ReportStringProperty, Object> f) {
      ReportProperty property = getEffectiveReportProperty(name);
      if (!(property instanceof ReportStringProperty))
         return defaultValue;

      return f.apply((ReportStringProperty) property);
   }

   /**
    * Do not call this method directly (use ReportService instead)
    * 
    * @param property
    * @see ReportService#remove(Report, ReportProperty)
    */
   public void removeReportProperty(ReportProperty property) {
      this.reportProperties.remove(property);
   }

   public DatasourceContainer getDatasourceContainer() {
      return datasourceContainer;
   }

   public void setDatasourceContainer(DatasourceContainer datasourceContainer) {
      this.datasourceContainer = datasourceContainer;
   }

   public List<ParameterDefinition> getParameterDefinitions() {
      return parameterDefinitions;
   }

   /**
    * {@link #getParameterDefinitions()} may fail when in an unmanaged report
    * 
    */
   public Set<ParameterDefinition> getParameterDefinitionsSafe() {
      Set<ParameterDefinition> defs = new HashSet<ParameterDefinition>();

      for (ParameterInstance instance : getParameterInstances())
         defs.add(instance.getDefinition());

      return defs;
   }

   public void setParameterDefinitions(List<ParameterDefinition> parameters) {
      if (null == parameters)
         parameters = new ArrayList<ParameterDefinition>();

      this.parameterDefinitions.clear();
      this.parameterDefinitions.addAll(parameters);

      /* update position */
      int i = 0;
      for (ParameterDefinition pd : parameterDefinitions)
         pd.setN(i++);
   }

   /**
    * Do not call directly
    */
   public void addParameterDefinition(ParameterDefinition definition) {
      /* add parameter */
      this.parameterDefinitions.add(definition);
      definition.setN(parameterDefinitions.size() - 1);
   }

   public void removeParameterDefinition(ParameterDefinition paramter) {
      this.parameterDefinitions.remove(paramter);
      int i = 0;
      for (ParameterDefinition pd : parameterDefinitions)
         pd.setN(i++);
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

   public void addParameterInstance(ParameterInstance instance) {
      this.parameterInstances.add(instance);
   }

   public PreviewImage getPreviewImage() {
      return previewImage;
   }

   public void setPreviewImage(PreviewImage previewImage) {
      this.previewImage = previewImage;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   /**
    * Creates a new variant of this report (or variant) with the changes given by
    * adjustedReport.
    * 
    * <p>
    * This method should only be called if you mean to store the variant as it is
    * added to the parent's list of children
    * </p>
    * 
    * @param adjustedReport
    * @see Report#createTemporaryVariant(Report)
    */
   public Report createNewVariant(Report adjustedReport) {
      Report variant = createTemporaryVariant(adjustedReport);

      /* set parent to either this (base report) or to my parent */
      if (this instanceof ReportVariant)
         getParent().addChild(variant);
      else
         addChild(variant);

      for (VariantCreatorHook hooker : hookHandlerServiceProvider.get().getHookers(VariantCreatorHook.class))
         hooker.newVariantCreated(this, adjustedReport, variant);

      return variant;
   }

   public Report createTemporaryVariant() {
      return createTemporaryVariant(this);
   }

   /**
    * Do not try to convert a temporary variant into a persistent one (rather
    * create a new one).
    * 
    * @param adjustedReport the unmanaged report.
    * @see Report#createNewVariant(Report)
    */
   public Report createTemporaryVariant(Report adjustedReport) {
      Report variant = createVariant(adjustedReport);

      /* set parent to either this (base report) or to my parent */
      variant.setParent(this instanceof ReportVariant ? getParent() : this);

      /* original type */
      variant.setTemporaryVariantType(adjustedReport.getClass());

      if (null != adjustedReport.getId())
         variant.setOldTransientId(adjustedReport.getId());
      else if (null != adjustedReport.getOldTransientId())
         variant.setOldTransientId(adjustedReport.getOldTransientId());

      if (null != adjustedReport.getKey())
         variant.setOldTransientKey(adjustedReport.getKey());
      else if (null != adjustedReport.getOldTransientKey())
         variant.setOldTransientKey(adjustedReport.getOldTransientKey());

      for (VariantCreatorHook hooker : hookHandlerServiceProvider.get().getHookers(VariantCreatorHook.class))
         hooker.temporaryVariantCreated(this, adjustedReport, variant);

      return variant;
   }

   abstract protected Report createVariant(Report adjustedReport);

   protected void initVariant(ReportVariant variant, Report adjustedReport) {
      if (!(variant instanceof Report))
         throw new IllegalArgumentException("Variant is not a report"); //$NON-NLS-1$

      /* base properties */
      ((Report) variant).setName(adjustedReport.getName());
      ((Report) variant).setDescription(adjustedReport.getDescription());

      /* handle parameters */
      Set<ParameterInstance> clonedParameterInstances = new HashSet<ParameterInstance>();

      for (ParameterDefinition definition : getParameterDefinitions()) {
         ParameterInstance instance = adjustedReport.getParameterInstanceFor(definition);
         if (definition.isEditable() && null != instance && !instance.isStillDefault()) {
            ParameterInstance clonedInstance = instance.cloneInstanceForReportVariant();
            clonedInstance.setDefinition(definition);
            clonedParameterInstances.add(clonedInstance);
         } else {
            clonedParameterInstances.add(definition.createParameterInstance());
         }
      }
      ((Report) variant).setParameterInstances(clonedParameterInstances);

      /* handle report metadata */
      Set<ReportMetadata> metadataSet = new HashSet<ReportMetadata>();
      for (ReportMetadata metadata : adjustedReport.getReportMetadata())
         metadataSet.add(entityCloner.get().cloneEntity(metadata));
      ((Report) variant).setReportMetadata(metadataSet);

      /* handle properties */
      Set<ReportProperty> propertiesSet = new HashSet<ReportProperty>();
      for (ReportProperty property : adjustedReport.getReportProperties())
         propertiesSet.add(entityCloner.get().cloneEntity(property));
      ((Report) variant).setReportProperties(propertiesSet);

      /* Flags */
      if (variant instanceof AbstractNode && adjustedReport instanceof AbstractNode) {
         ((AbstractNode) variant).setWriteProtection(((AbstractNode) adjustedReport).isWriteProtected());
         ((AbstractNode) variant)
               .setConfigurationProtection(((AbstractNode) adjustedReport).isConfigurationProtected());
      }
   }

   public void replaceWith(Report report, Injector injector) {
      ReportService reportService = injector.getInstance(ReportService.class);
      DatasourceService datasourceService = injector.getInstance(DatasourceService.class);

      setName(report.getName());
      setDescription(report.getDescription());

      /* parameters */
      reportService.updateParameterDefinitions(this, report.getParameterDefinitions(), true);

      /* remove metadata */
      Set<ReportMetadata> metadataSet = new HashSet<ReportMetadata>(getReportMetadata());
      Iterator<ReportMetadata> metaIt = metadataSet.iterator();
      while (metaIt.hasNext())
         reportService.remove(this, metaIt.next());

      /* remove properties */
      Set<ReportProperty> propertiesSet = new HashSet<ReportProperty>(getReportProperties());
      Iterator<ReportProperty> it = propertiesSet.iterator();
      while (it.hasNext())
         reportService.remove(this, it.next());

      /* set metadata */
      for (ReportMetadata rm : report.getReportMetadata())
         addReportMetadata(rm);

      /* set proeprties */
      for (ReportProperty prop : report.getReportProperties())
         addReportProperty(prop);

      /* datasource */
      if (null != datasourceContainer) {
         DatasourceDefinitionConfig oldConfig = datasourceContainer.getDatasourceConfig();
         datasourceService.remove(oldConfig);
      } else
         datasourceContainer = new DatasourceContainer();

      if (null != report.getDatasourceContainer()) {
         datasourceContainer.setDatasource(report.getDatasourceContainer().getDatasource());
         datasourceContainer.setDatasourceConfig(report.getDatasourceContainer().getDatasourceConfig());
      }

   }

   public ParameterInstance<?> getParamInstanceByDefinitionId(Long id) {
      if (null == id)
         return null;
      for (ParameterInstance<?> instance : getParameterInstances())
         if (id.equals(instance.getDefinition().getId()) || id.equals(instance.getDefinition().getOldTransientId()))
            return instance;
      return null;
   }

   @Transient
   public ParameterInstance getParameterInstanceFor(ParameterDefinition definition) {
      Set<ParameterInstance> instances = getParameterInstances();
      if (null == instances)
         return null;

      Long idA = definition.getId();
      if (null == idA)
         idA = definition.getOldTransientId();

      for (ParameterInstance instance : instances) {
         if (null == instance.getDefinition())
            throw new IllegalStateException("Instances should always have a definition"); //$NON-NLS-1$

         ParameterDefinition toCompare = instance.getDefinition();
         /* check ids */
         Long idB = toCompare.getId();
         if (null == idB)
            idB = toCompare.getOldTransientId();

         if (null != idA && null != idB && idA.equals(idB))
            return instance;

         if (toCompare.equals(definition))
            return instance;
      }

      return null;
   }

   public void removeParameterInstance(ParameterInstance instance) {
      parameterInstances.remove(instance);
   }

   @Transient
   public ParameterDefinition getParameterDefinitionByKey(String name) {
      for (ParameterDefinition def : getParameterDefinitionsSafe())
         if (name.equals(def.getKey()))
            return def;
      return null;
   }

   public <T extends ParameterDefinition<?>> List<T> getParameterDefinitionsOfType(Class<T> type) {
      List<T> definitions = new ArrayList<T>();
      for (ParameterDefinition def : getParameterDefinitionsSafe())
         if (type.isAssignableFrom(def.getClass()))
            definitions.add((T) def);
      return definitions;
   }

   public boolean usesParameter(String key) {
      return true;
   }

   public boolean isIdenticalForExecution(Report report) {
      if (null == report)
         return false;

      return entityDiffService.get().isEqual(this, report, ENTITY_DIFF_IDENTITCAL_FOR_EXECUTION);
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public String getUuid() {
      return uuid;
   }

   public ReportProperty getReportPropertyByName(String name) {
      return getReportProperty(name);
   }

   public ReportProperty getReportProperty(String name) {
      if (null == name)
         return null;
      for (ReportProperty property : reportProperties)
         if (name.equals(property.getName()))
            return property;
      return null;
   }

   public String getReportProperty(String propertyName, String defaultValue) {
      ReportProperty prop = getReportPropertyByName(propertyName);
      if (null != prop && prop instanceof ReportStringProperty)
         return ((ReportStringProperty) prop).getStrValue();

      if (this instanceof ReportVariant) {
         prop = ((ReportVariant) this).getBaseReport().getReportPropertyByName(propertyName);
         if (null != prop && prop instanceof ReportStringProperty)
            return ((ReportStringProperty) prop).getStrValue();
      }

      return defaultValue;
   }

   public Class<?> getTemporaryVariantType() {
      return temporaryVariantType;
   }

   public void setTemporaryVariantType(Class<?> temporaryVariantType) {
      this.temporaryVariantType = temporaryVariantType;
   }

}
