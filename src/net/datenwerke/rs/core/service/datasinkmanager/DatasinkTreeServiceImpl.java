package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode__;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition__;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder__;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;

/**
 * 
 *
 */
public class DatasinkTreeServiceImpl extends SecuredTreeDBManagerImpl<AbstractDatasinkManagerNode>
      implements DatasinkTreeService {

   private final Provider<Set<Class<? extends DatasinkDefinition>>> installedDatasinkDefinitions;
   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<ConfigService> configServiceProvider;

   @Inject
   public DatasinkTreeServiceImpl(
         Provider<EntityManager> entityManagerProvider,
         @ReportServerDatasinkDefinitions Provider<Set<Class<? extends DatasinkDefinition>>> installedDataSourceDefinitions,
         Provider<ConfigService> configServiceProvider
         ) {

      /* store objects */
      this.entityManagerProvider = entityManagerProvider;
      this.installedDatasinkDefinitions = installedDataSourceDefinitions;
      this.configServiceProvider = configServiceProvider;
   }

   @Override
   public Set<Class<? extends DatasinkDefinition>> getInstalledDatasinkDefinitions() {
      return installedDatasinkDefinitions.get();
   }

   @QueryByAttribute(where = DatasinkDefinition__.name)
   @Override
   public DatasinkDefinition getDatasinkByName(String name) {
      return null; // by magic
   }

   @QueryByAttribute(where = DatasinkDefinition__.id)
   @Override
   public DatasinkDefinition getDatasinkById(Long id) {
      return null; // by magic
   }

   @QueryByAttribute(where = DatasinkFolder__.name)
   @Override
   public DatasinkFolder getDatasinkFolderByName(@Named("name") String name) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = AbstractDatasinkManagerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractDatasinkManagerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractDatasinkManagerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractDatasinkManagerNode getNodeById(long id) {
      return null; // magic
   }

   @Override
   @FireMergeEntityEvents
   public DatasinkContainer merge(DatasinkContainer container) {
      return entityManagerProvider.get().merge(container);
   }

   @Override
   public <T extends DatasinkDefinition> Optional<T> getDefaultDatasink(Class<T> type, String defaultDatasinkIdProperty,
         String defaultDatasinkNameProperty) {
      final ConfigService configService = configServiceProvider.get();
      
      Configuration config = configService.getConfigFailsafe(DatasinkModule.CONFIG_FILE);

      DatasinkDefinition datasink = null;
      if (config.containsKey(defaultDatasinkIdProperty)) {
         Long id = config.getLong(defaultDatasinkIdProperty);
         datasink = getDatasinkById(id);
      } else {
         if (config.containsKey(defaultDatasinkNameProperty)) {
            String name = config.getString(defaultDatasinkNameProperty);
            datasink = getDatasinkByName(name);
         }
      }
      if (null != datasink && type.isInstance(datasink))
         return Optional.of((T) datasink);

      return Optional.empty();
   }

}
