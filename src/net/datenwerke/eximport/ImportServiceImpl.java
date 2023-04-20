package net.datenwerke.eximport;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportMode;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.eximport.im.ImportSupervisor;
import net.datenwerke.eximport.im.ImportSupervisorFactory;
import net.datenwerke.eximport.im.Importer;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.eximport.obj.ReferenceItemProperty;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.annotations.CommitFlushMode;
import net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
public class ImportServiceImpl implements ImportService {

   final ExportDataAnalyzerService dataAnalizer;
   private final HookHandlerService hookHandler;
   private final ImportSupervisorFactory importSupervisorFactory;

   @Inject
   public ImportServiceImpl(
         ExportDataAnalyzerService dataAnalizer, 
         HookHandlerService hookHandler,
         ImportSupervisorFactory importSupervisorFactory
         ) {

      /* store objects */
      this.dataAnalizer = dataAnalizer;
      this.hookHandler = hookHandler;
      this.importSupervisorFactory = importSupervisorFactory;
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * net.datenwerke.eximport.ImportService#importData(net.datenwerke.eximport.im.
    * ImportConfig)
    */
   @Override
   @CommitFlushMode
   public ImportResult importData(ImportConfig config) {
      List<Importer> importers = getImporters();
      ImportSupervisor supervisor = importSupervisorFactory.create(config, importers);
      try {
         ImportResult importedData = supervisor.importData();
         return importedData;
      } catch (Exception e) {
         throw new ImportException(e);
      }
   }

   protected List<Importer> getImporters() {
      return hookHandler.getHookers(ImporterProviderHook.class)
            .stream()
            .map(ImporterProviderHook::getObject)
            .collect(toList());
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.eximport.ImportService#getToBeConfigured(net.datenwerke.
    * eximport.im.ImportConfig)
    */
   @Override
   public List<ExportedItem> getToBeConfigured(ImportConfig config) {
      Set<String> referencedIds = new HashSet<>();
      Set<String> configuredIds = new HashSet<>();

      for (ImportItemConfig itemConfig : config.getItemConfigs()) {
         String id = itemConfig.getId();
         ExportedItem exportedItem = getExportedItemById(config.getExportDataProvider(), id);
         referencedIds.addAll(exportedItem.getReferencedIDs());
         configuredIds.add(id);
      }

      boolean referencesAdded = true;
      while (referencesAdded) {
         referencesAdded = false;

         Set<String> newReferences = new HashSet<>();
         for (String id : referencedIds) {
            ExportedItem exportedItem = getExportedItemById(config.getExportDataProvider(), id);
            newReferences.addAll(exportedItem.getReferencedIDs());
         }

         referencesAdded |= referencedIds.addAll(newReferences);
      }

      List<ExportedItem> exportedItems = new ArrayList<>();
      for (String id : referencedIds) {
         if (!configuredIds.contains(id)) {
            ExportedItem exportedItem = getExportedItemById(config.getExportDataProvider(), id);
            exportedItems.add(exportedItem);
         }
      }

      return exportedItems;
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * net.datenwerke.eximport.ImportService#getExportedItemById(net.datenwerke.
    * eximport.ExportDataProvider, java.lang.String)
    */
   @Override
   public ExportedItem getExportedItemById(ExportDataProvider dataProvider, String id) {
      ExportedItem exportedItem = null;

      try {
         exportedItem = dataAnalizer.getExportedItemById(dataProvider, id);
      } catch (ClassNotFoundException e) {
         throw new ImportException("Could not load exported item", e);
      }

      if (null == exportedItem)
         throw new ImportException("Could not find exported item with id " + id);

      return exportedItem;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.eximport.ImportService#getEnclosedItemPropertyForId(net.
    * datenwerke.eximport.ExportDataProvider, java.lang.String)
    */
   @Override
   public EnclosedItemProperty getEnclosedItemPropertyForId(ExportDataProvider dataProvider, String id) {
      EnclosedItemProperty exportedItem = null;
      try {
         exportedItem = dataAnalizer.getEnclosedPropertyFor(dataProvider, id);
      } catch (ClassNotFoundException e) {
         throw new ImportException("Could not load exported item", e);
      }

      if (null == exportedItem)
         throw new ImportException("Could not find exported item with id " + id);

      return exportedItem;
   }

   @Override
   public void configureParents(ImportConfig config, String topMostParentId, AbstractNode<?> topMostParentTarget,
         Class<? extends Exporter> exporter) throws ClassNotFoundException {
      /* configure file import's parents */
      dataAnalizer.getExportedItemsFor(config.getExportDataProvider(), exporter).forEach(LambdaExceptionUtil.rethrowConsumer(item -> {
         configureParents(item, config, topMostParentId, topMostParentTarget, exporter);
      }));
   }

   @Override
   public void configureParents(ExportedItem item, ImportConfig config, String topMostParentId,
         AbstractNode<?> topMostParentTarget, Class<? extends Exporter> exporter) throws ClassNotFoundException {
      ItemProperty parentProp = item.getPropertyByName("parent");
      if (null != parentProp) {
         TreeNodeImportItemConfig itemConfig = new TreeNodeImportItemConfig(item.getId());
         /* set parent */
         ReferenceItemProperty referenceParentProp = (ReferenceItemProperty) parentProp;
         if (parentProp instanceof ReferenceItemProperty
               && topMostParentId.equals(referenceParentProp.getReferenceId()))
            itemConfig.setParent(topMostParentTarget);

         config.addItemConfig(itemConfig);
      } else {
         if (!topMostParentId.equals(item.getId())) {
            TreeNodeImportItemConfig itemConfig = new TreeNodeImportItemConfig(item.getId(), ImportMode.CREATE);
            itemConfig.setParent(topMostParentTarget);
            config.addItemConfig(itemConfig);
         }
      }
   }
}
