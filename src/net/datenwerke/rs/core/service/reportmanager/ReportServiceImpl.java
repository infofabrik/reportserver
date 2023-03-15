package net.datenwerke.rs.core.service.reportmanager;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.service.reportmanager.vfs.ReportManagerVFS;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer__;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportTypes;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportVariantTypes;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode__;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata__;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty__;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report__;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeEditedHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.OrderBy;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.UpdateOwner;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class ReportServiceImpl extends SecuredTreeDBManagerImpl<AbstractReportManagerNode> implements ReportService {

   private final HookHandlerService hookHandler;
   private final Provider<Set<Class<? extends Report>>> installedReportTypes;
   private final Provider<Set<Class<? extends Report>>> installedReportVariantTypes;
   private final Provider<EntityManager> entityManagerProvider;
   private final EntityClonerService entityCloner;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final ReportParameterService reportParameterService;
   private final TerminalService terminalService;

   @Inject
   public ReportServiceImpl(
         Provider<EntityManager> entityManagerProvider, 
         HookHandlerService hookHandler,
         @ReportServerReportTypes Provider<Set<Class<? extends Report>>> installedReportTypes,
         @ReportServerReportVariantTypes Provider<Set<Class<? extends Report>>> installedReportVariantTypes,
         SecurityService securityService, 
         EntityClonerService entityCloner,
         ReportParameterService reportParameterService, 
         TerminalService terminalService,
         Provider<AuthenticatorService> authenticatorServiceProvider
         ) {
      this.entityManagerProvider = entityManagerProvider;
      this.hookHandler = hookHandler;
      this.installedReportTypes = installedReportTypes;
      this.installedReportVariantTypes = installedReportVariantTypes;
      this.securityService = securityService;
      this.entityCloner = entityCloner;
      this.reportParameterService = reportParameterService;
      this.terminalService = terminalService;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.rs.reportmanager.ReportManagerService#getAllReports()
    */
   @Override
   @SimpleQuery
   public List<Report> getAllReports() {
      return null; // finder magic;
   }

   @Override
   public AbstractReportManagerNode getNodeByPath(String path) {
      return getNodeByPath(path, true);
   }

   @Override
   public AbstractReportManagerNode getNodeByPath(String path, boolean checkRights) {
      if (path.startsWith("/"))
         path = "/" + ReportManagerVFS.FILESYSTEM_NAME + path;
      else
         path = "/" + ReportManagerVFS.FILESYSTEM_NAME + "/" + path;

      try {
         Object object = terminalService.getObjectByQuery(path, checkRights);
         if (object instanceof AbstractReportManagerNode)
            return (AbstractReportManagerNode) object;
         return null;
      } catch (ObjectResolverException e) {
         return null;
      }
   }

   @Override
   public Set<Class<? extends Report>> getInstalledReportTypes() {
      return installedReportTypes.get();
   }
   
   @Override
   public Set<Class<? extends Report>> getInstalledReportVariantTypes() {
      return installedReportVariantTypes.get();
   }

   @Override
   @QueryById
   public Report getReportById(long id) {
      return null; // by magic
   }

   @Override
   public Report getReportByKey(String key) {
      try {
         return doGetReportByKey(key);
      } catch (NonUniqueResultException e) {
         throw new IllegalArgumentException("There seem to be multiple reports with the same key: " + key, e);
      } catch (IllegalStateException e) {
         if (null != e.getCause() && e.getCause() instanceof NonUniqueResultException)
            throw new IllegalArgumentException("There seem to be multiple reports with the same key: " + key, e);
         throw e;
      }
   }

   @QueryByAttribute(where = Report__.key)
   public Report doGetReportByKey(String key) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = Report__.uuid)
   public Report getReportByUUID(String UUID) {
      return null; // by magic
   }

   @Override
   @SimpleQuery(from = Report.class, join = @Join(joinAttribute = Report__.datasourceContainer, where = @Predicate(attribute = DatasourceContainer__.datasource, value = "ds")))
   public List<Report> getReportsByDatasource(@Named("ds") DatasourceDefinition ds) {
      return null; // by magic
   }

   @Override
   @UpdateOwner(name = "node")
   @FirePersistEntityEvents
   public void persist(@Named("node") AbstractReportManagerNode node) {
      ensureKeyIsUnique(node);

      super.persist(node);
   }

   @Override
   @FireMergeEntityEvents
   public AbstractReportManagerNode merge(AbstractReportManagerNode node) {
      ensureKeyIsUnique(node);

      return super.merge(node);
   }

   protected void ensureKeyIsUnique(AbstractReportManagerNode node) {
      if (node instanceof Report && !StringUtils.isEmpty(((Report) node).getKey())) {
         Report report = getReportByKey(((Report) node).getKey());
         if (null != report && !report.equals(node))
            throw new IllegalArgumentException(
                  "Report key must be unique: " + node.getId() + ", " + ((Report) node).getKey());
      }
   }

   @Override
   public void persist(ReportMetadata reportMetadata) {
      entityManagerProvider.get().persist(reportMetadata);
   }

   @Override
   public void remove(Report report, ReportMetadata metadata) {
      if (!report.hasReportMetadata(metadata))
         throw new IllegalArgumentException("Report does not have metadata");

      report.removeReportMetadata(metadata);

      EntityManager em = entityManagerProvider.get();
      metadata = em.find(metadata.getClass(), metadata.getId());
      if (null != metadata)
         em.remove(metadata);
   }

   @Override
   public void persist(ReportProperty property) {
      entityManagerProvider.get().persist(property);
   }

   @Override
   public void remove(Report report, ReportProperty property) {
      if (!report.hasReportProperty(property))
         throw new IllegalArgumentException("Report does not have property");

      report.removeReportProperty(property);

      EntityManager em = entityManagerProvider.get();
      em.remove(em.find(property.getClass(), property.getId()));
   }

   @Override
   public Report getUnmanagedReportById(long id) {
      Report managedReport = getReportById(id);
      Report unmanagedReport = entityCloner.cloneEntity(managedReport);

      return unmanagedReport;
   }

   @Override
   public List<ReportVariant> getVariantsOf(AbstractReportManagerNode report) {
      AuthenticatorService authenticator = authenticatorServiceProvider.get();
      return getVariantsOf(report, authenticator.getCurrentUser());
   }

   @Override
   public List<ReportVariant> getVariantsOf(AbstractReportManagerNode report, User user) {
      List<ReportVariant> variants = new ArrayList<ReportVariant>();
      for (AbstractReportManagerNode variant : report.getChildrenSorted()) {
         if (variant instanceof Report && variant instanceof ReportVariant) {
            variants.add((ReportVariant) variant);
         }
      }

      return variants;
   }

   @Override
   @QueryByAttribute(select = Report__.id, from = Report.class, where = Report__.key, throwNoResultException = true)
   public long getReportIdFromKey(String key) {
      return -1; // magic
   }

   @Override
   @SimpleQuery(select = ReportMetadata__.name, from = ReportMetadata.class, distinct = true, orderBy = @OrderBy(attribute = ReportMetadata__.name))
   public List<String> getReportMetadataKeys() {
      return null; // magic
   }

   @Override
   @SimpleQuery(select = ReportProperty__.name, from = ReportStringProperty.class, distinct = true, orderBy = @OrderBy(attribute = ReportProperty__.name))
   public List<String> getReportStringPropertyKeys() {
      return null; // magic
   }

   @Override
   @QueryByAttribute(where = AbstractReportManagerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractReportManagerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractReportManagerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractReportManagerNode getNodeById(long id) {
      return null; // magic
   }

   @Override
   public void updateParameterDefinitions(final Report report, final List<ParameterDefinition> newDefinitions,
         final boolean allowParameterRemoval) {
      for (AbstractReportManagerNode variant : report.getChildren())
         if (!allowParameterRemoval
               && report.getParameterDefinitions().size() < ((Report) variant).getParameterInstances().size())
            throw new IllegalArgumentException("New parent's parameters are not a superset of the old parent (parentId="
                  + report.getId() + ", variantName=" + variant.getName() + ")");

      /* first step: save all new definitions */
      for (ParameterDefinition def : newDefinitions)
         if (null != def.getId())
            throw new IllegalArgumentException("Expected a new definition");
         else
            reportParameterService.persist(def);

      /* make sure all variants have the same set of parameters */
      if (report.hasChildren()) {
         IdentityHashMap<ParameterDefinition, Object> handledDefs = new IdentityHashMap<ParameterDefinition, Object>();
         boolean isFirst = true;
         for (AbstractReportManagerNode aVariant : report.getChildren()) {
            Report variant = (Report) aVariant;

            Set<ParameterInstance> forRemoval = new HashSet<ParameterInstance>();
            for (ParameterInstance pi : variant.getParameterInstances()) {
               String oldKey = pi.getDefinition().getKey();
               oldKey = null == oldKey ? "" : oldKey;

               boolean found = false;
               for (ParameterDefinition newDef : newDefinitions) {
                  if (oldKey.equals(newDef.getKey()) && pi.getClass().equals(newDef.getClass())) {
                     pi.setDefinition(newDef);
                     found = true;
                     if (isFirst)
                        handledDefs.put(newDef, 1);
                     else if (!handledDefs.containsKey(newDef))
                        throw new IllegalArgumentException("Something is wrong with this report's (" + report.getId()
                              + ") variants. Please redo parameters.");
                     break;
                  }
               }
               if (!found && !allowParameterRemoval)
                  throw new IllegalArgumentException("I am not allowed to remove parameters.");
               if (!found) {
                  pi.setDefinition(null);
                  forRemoval.add(pi);
               }
            }

            for (ParameterInstance instance : forRemoval)
               reportParameterService.remove(instance);

            isFirst = false;
         }

         for (AbstractReportManagerNode aVariant : report.getChildren()) {
            Report variant = (Report) aVariant;
            for (ParameterDefinition<?> def : newDefinitions) {
               if (handledDefs.containsKey(def))
                  continue;

               ParameterInstance<?> instance = def.createParameterInstance();
               variant.addParameterInstance(instance);
            }
         }
      }

      /* replace defs */
      for (ParameterDefinition def : new ArrayList<ParameterDefinition>(report.getParameterDefinitions()))
         reportParameterService.remove(def);

      report.setParameterDefinitions(new ArrayList<ParameterDefinition>(newDefinitions));

      report.setParameterInstances(new HashSet<ParameterInstance>());
      report.getParameterDefinitions().forEach(def -> report.addParameterInstance(def.createParameterInstance()));
   }

   @Override
   protected void beforeNodeCopy(AbstractReportManagerNode source, AbstractReportManagerNode target) {
      if (source instanceof ReportVariant) {
         if (!(target instanceof Report) || target instanceof ReportVariant)
            throw new IllegalArgumentException("Could not copy variant to " + target.getClass());
      }
   }

   @Override
   protected void beforeNodeMoveToParent(AbstractReportManagerNode node, AbstractReportManagerNode newParent,
         AbstractReportManagerNode oldParent) {
      if (node instanceof ReportVariant) {
         if (!(newParent instanceof Report) || newParent instanceof ReportVariant)
            throw new IllegalArgumentException("Could not move variant to " + newParent.getClass());
      }
   }

   @Override
   protected void nodeMovedToParent(AbstractReportManagerNode node, AbstractReportManagerNode newParent,
         AbstractReportManagerNode oldParent) {
      if (node instanceof ReportVariant) {

         /* parameters */
         Report oldParentReport = (Report) oldParent;
         Report parentReport = (Report) newParent;
         Report variant = (Report) node;

         if (oldParentReport.getParameterDefinitions().size() != parentReport.getParameterDefinitions().size())
            throw new IllegalArgumentException(
                  ReportManagerMessages.INSTANCE.exceptionCannotMoveVariantSinceMismatchedParams());

         for (int i = 0; i < oldParentReport.getParameterDefinitions().size(); i++) {
            ParameterDefinition oldDef = oldParentReport.getParameterDefinitions().get(i);
            ParameterDefinition newDef = parentReport.getParameterDefinitions().get(i);

            if (!oldDef.getClass().equals(newDef.getClass()))
               throw new IllegalArgumentException(
                     ReportManagerMessages.INSTANCE.exceptionCannotMoveVariantSinceMismatchedParams());

            ParameterInstance instance = variant.getParameterInstanceFor(oldDef);
            if (null == instance)
               throw new IllegalArgumentException("Could not find instance");
            instance.setDefinition(newDef);
         }

      }
   }

   @Override
   protected AbstractReportManagerNode cloneNode(AbstractReportManagerNode node) {
      AbstractReportManagerNode clone = super.cloneNode(node);

      if (clone instanceof Report) {
         ((Report) clone).setUuid(UUID.randomUUID().toString());
         ((Report) clone).setKey(null);
      }
      return clone;
   }

   @Override
   public ReportMetadata getOrCreateMetadata(Report report, String name) {
      ReportMetadata md = report.getReportMetadataByName(name);
      if (null == md) {
         md = new ReportMetadata();
         md.setName(name);
         report.addReportMetadata(md);
         persist(md);
      }
      return md;
   }

   @Override
   public ReportMetadata removeMetadataByName(Report report, String name) {
      ReportMetadata md = report.getReportMetadataByName(name);
      if (null != md)
         remove(report, md);
      return md;
   }

   @Override
   public void prepareVariantForStorage(final ReportVariant variant, final String executeToken)
         throws ExpectedException {
      hookHandler.getHookers(VariantToBeStoredHook.class)
            .forEach(vtbs -> vtbs.variantToBeStored((Report) variant, executeToken));
   }

   @Override
   public void prepareVariantForEdit(final ReportVariant referenceReport, final ReportDto reportDto,
         final String executeToken) throws ServerCallFailedException {
      hookHandler.getHookers(VariantToBeEditedHook.class).forEach(
            rethrowConsumer(vtbe -> vtbe.variantToBeEdited((Report) referenceReport, reportDto, executeToken)));
   }

   @Override
   public String extractQuery(Report report) {

      DatasourceDefinitionConfig datasourceConfig = report.getDatasourceContainer().getDatasourceConfig();
      if (datasourceConfig instanceof DatabaseDatasourceConfig)
         return ((DatabaseDatasourceConfig) report.getDatasourceContainer().getDatasourceConfig()).getQuery();

      throw new UnsupportedOperationException("Query can not be fetched for: " + report.getClass().getSimpleName());
   }

   @Override
   public InputStream createInputStream(Object report) {
      ByteArrayInputStream bis = null;
      if (report instanceof String)
         bis = new ByteArrayInputStream(((String) report).getBytes(StandardCharsets.UTF_8));
      else if (report instanceof byte[])
         bis = new ByteArrayInputStream(((byte[]) report));
      else
         throw new IllegalArgumentException("Report type not supported");

      return bis;
   }

}
