package net.datenwerke.rs.base.ext.service.reportmanager.eximport;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class ReportManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "ReportManagerImporter";

   private final ReportService reportService;
   
   private final KeyNameGeneratorService keyNameGeneratorService;

   @Inject
   public ReportManagerImporter(ReportService reportService, KeyNameGeneratorService keyNameGeneratorService) {

      /* store objects */
      this.reportService = reportService;
      this.keyNameGeneratorService = keyNameGeneratorService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { ReportManagerExporter.class };
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return reportService;
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {
      super.finishUpImportCreateMode(itemConfig, node);

      /* check key */
      if (node instanceof Report) {
         String key = ((Report) node).getKey();
         if (null != key) {
            Report reportByKey = reportService.getReportByKey(key);
            if (null != reportByKey) {
               if (itemConfig instanceof ImportItemWithKeyConfig && ((ImportItemWithKeyConfig) itemConfig).isCreateRandomKeys())
                  ((Report) node).setKey(keyNameGeneratorService.generateDefaultKey());
               else
                  throw new IllegalStateException("A report with key '" + key + "' already exists");
            }
         }
      }

      /*
       * if we have a variant make sure we get the definitions for the instances right
       */
      if (node instanceof ReportVariant) {
         if (null == itemConfig.getParent())
            return; // everything should be ok

         Report parentReport = (Report) node.getParent();

         /* parameters */
         Report variant = (Report) node;

         if (parentReport.getParameterDefinitions().size() < variant.getParameterInstances().size())
            throw new ImportException("New parent's parameters are not a superset of the old parent (parentId="
                  + parentReport.getId() + ", variantName=" + variant.getName() + ")");

         Set<Long> handledParameters = new HashSet<Long>();
         for (ParameterInstance<?> instance : variant.getParameterInstances()) {
            String importId = importSupervisor.getImportIdForImportedObject(instance);
            EnclosedItemProperty exItem = importSupervisor.getEnclosedItemPropertyForId(importId);
            String key = exItem.getElement().getAttribute(ParameterInstanceExporter.KEY_ATTRIBUTE).getValue();
            ParameterDefinition<?> definition = parentReport.getParameterDefinitionByKey(key);
            if (null == definition)
               throw new ImportException("New parent's parameters are not a superset of the old parent (parentId="
                     + parentReport.getId() + ", variantName=" + variant.getName() + ")");

            if (!(definition.createParameterInstance().getClass().isAssignableFrom(instance.getClass()))) {
               throw new ImportException(
                     "ParameterInstance and definitiontype do not match between variant and parent (parentId="
                           + parentReport.getId() + ", variantName=" + variant.getName() + ")");
            }

            instance.setDefinition(definition);
            handledParameters.add(definition.getId());
         }

         for (ParameterDefinition<?> def : parentReport.getParameterDefinitions()) {
            if (handledParameters.contains(def.getId()))
               continue;

            ParameterInstance<?> instance = def.createParameterInstance();
            variant.addParameterInstance(instance);
         }
      }
   }
}
