package net.datenwerke.treedb.ext.service.eximport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;

import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportResult.ImportResultExtraData;
import net.datenwerke.eximport.im.ImportResult.ImportResultExtraData.ImportStrategy;
import net.datenwerke.eximport.im.ImporterImpl;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporterFactory;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.eximport.obj.ItemPropertyCollection;
import net.datenwerke.eximport.obj.ReferenceItemProperty;
import net.datenwerke.rs.core.server.transport.TransportApplyNodeException;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.utils.entitymerge.service.EntityMergeService;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * 
 *
 */
public abstract class TreeNodeImporter extends ImporterImpl<TreeNodeImportItemConfig> {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   private BasicObjectImporterFactory objectImporterFactory;

   private FileServerService fileServerService;
   private ReportService reportService;
   private DatasourceService dataSourceService;
   private DatasinkTreeService dataSinkService;
   private EntityMergeService entityMergeService;

   private boolean useMergeImporter = false;

   @Inject
   public void setObjectImporterFactory(BasicObjectImporterFactory objectImporterFactory) {
      this.objectImporterFactory = objectImporterFactory;
   }

   @Inject
   public void setServices(FileServerService fileServerService, ReportService reportService,
         DatasourceService dataSourceService, DatasinkTreeService dataSinkService,
         EntityMergeService entityMergeService) {
      this.fileServerService = fileServerService;
      this.reportService = reportService;
      this.dataSourceService = dataSourceService;
      this.dataSinkService = dataSinkService;
      this.entityMergeService = entityMergeService;
   }

   public void setUseMergeImporter(boolean value) {
      useMergeImporter = value;
   }

   @Override
   public final void importData() {
      if (itemConfigs.isEmpty())
         return;
      if (!useMergeImporter) {
         super.importData();
         return;
      }

      List<TreeNodeImportItemConfig> items = new ArrayList<>();
      AbstractNode parent = null;
      List<AbstractNode> nodes = new ArrayList<>();
      while (!itemConfigs.isEmpty()) {
         ImportItemConfig config = itemConfigs.poll();

         TreeNodeImportItemConfig itemConfig = (TreeNodeImportItemConfig) config;
         items.add(itemConfig);
         if (itemConfig.getParent() != null)
            parent = (AbstractNode) itemConfig.getParent();
      }
      for (TreeItem item : makeTree(items)) {
         item.parentNode = parent;
         try {
            walk(nodes, item, true);
         } catch (InstantiationException | IllegalAccessException | SecurityException | NoSuchFieldException
               | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new TransportApplyNodeException(item, e);
         }
      }
   }

   @SuppressWarnings("unchecked")
   private <T extends AbstractNode<T>> T createFolder(List<T> nodes, T parent, String name, String id)
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
         NoSuchMethodException, SecurityException {
      Optional<T> existingItem = parent.getChildren().stream().filter(c -> c.getNodeName().contentEquals(name))
            .findAny();
      
      if (existingItem.isPresent()) {
         registerNode(id, existingItem.get());
         registerExtraData(id, ImportResultExtraData.skipped(existingItem.get(), ImportStrategy.NONE));
         return existingItem.get();
      }

      T folder = (T) parent.getClass().getDeclaredConstructor(String.class).newInstance(name);
      parent.addChild(folder);
      nodes.add(folder);

      registerNode(id, folder);
      registerExtraData(id, ImportResultExtraData.imported(folder, ImportStrategy.CREATE));

      return folder;
   }

   private void createFileServerFile(List<AbstractNode> nodes, AbstractFileServerNode parent, String fileName,
         String content, String contentType, String id, TreeNodeImportItemConfig config) throws InstantiationException,
         IllegalAccessException, SecurityException, NoSuchFieldException, IllegalArgumentException {
      Optional<AbstractFileServerNode> existingItem = parent.getChildren().stream()
            .filter(c -> c.getNodeName().contentEquals(fileName)).findAny();

      int i = 1;
      String name = fileName;
      while (existingItem.isPresent()) {
         boolean hasFileending = fileName.lastIndexOf(".") != -1;
         name = hasFileending
               ? fileName.substring(0, fileName.lastIndexOf(".")) 
                     + "_" + (i++)
                     + fileName.substring(fileName.lastIndexOf("."))
               : fileName + "_" + (i++);
         final String newName = name;
         existingItem = parent.getChildren().stream().filter(c -> c.getNodeName().contentEquals(newName)).findAny();
      }

      config.setParent(parent);
      config.addIgnoredField("parent");

      ExportedItem exportedItem = importSupervisor.getExportedItemFor(config);
      
      String key = exportedItem.getPropertyByName("key").getElement().getValue();
      FileServerFile file = fileServerService.getFileByKey(key);
      boolean keyExists = file != null;
      
      if (!keyExists) {
         BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedItem);
         AbstractFileServerNode node = (AbstractFileServerNode) exportedItem.getType().newInstance();
         importer.importProperties(node, exportedItem.getProperties());
         node.setName(name);
         // we set parent to null because child.equals checks only for equal id (because our nodes
         // are not yet imported to RS their IDs are all null)
         node.setParent(null);
         addParent(node, parent);
         registerNode(id, node);
         registerExtraData(id, ImportResultExtraData.imported(node, ImportStrategy.CREATE));
      } else {
         config.addIgnoredField("key");
         ExportedItem exportedKeyItem = importSupervisor.getExportedItemFor(config);
         BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedKeyItem );
         AbstractFileServerNode node = (AbstractFileServerNode) exportedKeyItem .getType().newInstance();
         importer.importProperties(node, exportedKeyItem.getProperties());
         
         entityMergeService.mergeEntity(file, node);
         registerNode(id, file);
         registerExtraData(id, ImportResultExtraData.imported(file, ImportStrategy.MERGE));
      }
   }

   private void registerNode(String id, AbstractNode node) {
      importSupervisor.registerImportedObject(id, node);
      importSupervisor.notifyImportDone(id, node);
   }
   
   private void registerExtraData(String id, ImportResultExtraData data) {
      importSupervisor.registerImportExtraData(id, data);
   }

   @SuppressWarnings("unchecked")
   private void walk(List<AbstractNode> nodes, TreeItem item, boolean root)
         throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException,
         IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
      AbstractNode parent = item.parentNode;

      switch (item.name == null ? "" : item.name) {
      case "Report Root":
         List<AbstractReportManagerNode> reportRoots = reportService.getRoots();
         if (!reportRoots.isEmpty())
            parent = reportRoots.get(0);
         break;
      case "Datasource Root":
         List<AbstractDatasourceManagerNode> dataSourceRoots = dataSourceService.getRoots();
         if (!dataSourceRoots.isEmpty())
            parent = dataSourceRoots.get(0);
         break;
      case "FileServer Root":
         List<AbstractFileServerNode> fileRoots = fileServerService.getRoots();
         if (!fileRoots.isEmpty())
            parent = fileRoots.get(0);
         break;
      case "Datasink Root":
         List<AbstractDatasinkManagerNode> sinkRoots = dataSinkService.getRoots();
         if (!sinkRoots.isEmpty())
            parent = sinkRoots.get(0);
         break;
      default:
         if (!(parent instanceof AbstractReportManagerNode) && !(parent instanceof AbstractFileServerNode)
               && !(parent instanceof AbstractDatasourceManagerNode)
               && !(parent instanceof AbstractDatasinkManagerNode)) {
            if (parent != null)
               doImport(item.config);
         } else if (item.isFolder)
            parent = createFolder(nodes, parent, item.name, item.id);
         else {
            if (parent instanceof AbstractFileServerNode) {
               createFileServerFile(nodes, (AbstractFileServerNode) parent, item.name, item.content, item.contentType,
                     item.id, item.config);
            } else {
               Optional<AbstractNode<?>> existingItem = parent.getChildren().stream().filter(c -> {
                  try {
                     Method getKey = c.getClass().getMethod("getKey");
                     boolean hasKey = getKey != null;
                     return hasKey && (getKey.invoke(c) != null) && ((String) getKey.invoke(c)).contentEquals(item.key);
                  } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                     return false;
                  }
               }).findAny();

               item.config.setParent(parent);
               item.config.addIgnoredField("parent");
               item.config.addIgnoredField("children");

               boolean exists = existingItem.isPresent();
               AbstractNode<?> node = (AbstractNode<?>) getImporter(item.config, false).importObject();

               if (exists) {
                  AbstractNode<?> oldItem = (AbstractNode<?>) existingItem.get();

                  entityMergeService.mergeEntity(oldItem, node);
                  registerNode(item.id, oldItem);
                  registerExtraData(item.id, ImportResultExtraData.imported(oldItem, ImportStrategy.MERGE));
               } else {
                  boolean keyExists = false;

                  if (node instanceof Report) {
                     keyExists = reportService.getReportByKey(((Report) node).getKey()) != null;
                  } else if (node instanceof DatasinkDefinition) {
                     keyExists = dataSinkService.getDatasinkByKey(((DatasinkDefinition) node).getKey()) != null;
                  } else if (node instanceof DatasourceDefinition) {
                     keyExists = dataSourceService.getDatasourceByKey(((DatasourceDefinition) node).getKey()) != null;
                  }

                  if (keyExists) {
                     registerExtraData(item.id, ImportResultExtraData.skipped(node, ImportStrategy.MERGE));
                     return; // TODO: maybe merge and move this later
                  }

                  if (node instanceof ReportVariant && parent instanceof Report) {
                     ((Report) parent).createNewVariant((Report) node);
                  } else {
                     addParent(node, parent);
                  }
                  registerNode(item.id, node);
                  registerExtraData(item.id, ImportResultExtraData.imported(node, ImportStrategy.CREATE));
               }
            }
         }
      }

      for (TreeItem c : item.children) {
         c.parentNode = parent;
         try {
            walk(nodes, c, false);
         } catch (InstantiationException | IllegalAccessException | SecurityException | NoSuchFieldException
               | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new TransportApplyNodeException(item, e);
         }
      }
   }

   private BasicObjectImporter getImporter(final TreeNodeImportItemConfig itemConfig) {
      return getImporter(itemConfig, true);
   }

   private BasicObjectImporter getImporter(final TreeNodeImportItemConfig itemConfig, boolean registerImportedObject) {
      ExportedItem exportedItem = importSupervisor.getExportedItemFor(itemConfig);
      BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedItem);
      importer.setRegisterImportedObject(registerImportedObject);

      /* configure importer */
      TreeNodeImporterConfig config = getSpecificConfig(TreeNodeImporterConfig.class);
      if (null == config || !config.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_OWNER))
         importer.addIgnoredFieldNames(TreeNodeExporter.OWNER_FIELD_NAME);
      if (null == config || !config.containsExImporterOption(TreeNodeExImportOptions.INCLUDE_SECURITY))
         importer.addIgnoredFieldNames(TreeNodeExporter.ACL_FIELD_NAME);

      if (null != itemConfig.getIgnoredFields())
         importer.addIgnoredFieldNames(itemConfig.getIgnoredFields());

      configureObjectImporter(itemConfig, importer);

      return importer;
   }

   @Override
   protected void doImportCreateMode(final TreeNodeImportItemConfig itemConfig) {
      /* import node */
      AbstractNode<?> node = (AbstractNode<?>) getImporter(itemConfig).importObject();

      /* set parent */
      if (null != itemConfig.getParent())
         addParent(node, itemConfig.getParent());

      finishUpImportCreateMode(itemConfig, node);
   }

   protected void configureObjectImporter(TreeNodeImportItemConfig itemConfig, BasicObjectImporter importer) {

   }

   protected void finishUpImportCreateMode(TreeNodeImportItemConfig itemConfig, AbstractNode<?> node) {

   }

   @SuppressWarnings("unchecked")
   protected void addParent(AbstractNode<?> node, AbstractNode parent) {
      if (parent instanceof HibernateProxy)
         parent = (AbstractNode<?>) ((HibernateProxy) parent).getHibernateLazyInitializer().getImplementation();
      parent.addChild(node);
   }

   @SuppressWarnings("unchecked")
   protected void persist(AbstractNode<?> node) {
      getTreeDBManager().persist(node);
   }

   @Override
   public boolean postProcess(String id, Object object, boolean enclosed) {
      try {
         AbstractNode node = (AbstractNode) object;
         if (null == node.getId())
            persist(node);
         return true;
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         return false;
      }
   }

   @Override
   protected void doImportMergeMode(TreeNodeImportItemConfig itemConfig) {
      /* create importer */
   }

   public static class TreeItem {
      String id = null;
      String name = null;
      TreeItem parent = null;
      transient List<TreeItem> children = new ArrayList<TreeItem>();

      String key = null;

      transient String content = null;
      String contentType = null;

      boolean isFolder = false;

      transient AbstractNode<?> parentNode = null;
      transient TreeNodeImportItemConfig config = null;
      
      @Override
      public String toString() {
         return MoreObjects.toStringHelper(getClass())
               .add("ID", id)
               .add("NAME", name)
               .add("KEY", key)
               .add("CONTENT_TYPE", contentType)
               .add("PARENT", parent == null ? "" : parent.toString())
               .toString();
      }
   }

   @Nullable
   private Attribute getIdAttribute(Element element) {
      for (int i = 0; i < element.getAttributeCount(); i++) {
         Attribute attribute = element.getAttribute(i);
         if (attribute.getQualifiedName() == "xml:id")
            return attribute;
      }
      return null;
   }

   private void handleProperty(Map<String, String> idKeyMap, Map<String, TreeItem> treeItems, ExportedItem exportedItem,
         ItemProperty property, TreeNodeImportItemConfig config) {
      Element element = property.getElement();
      String id = exportedItem.getId();
      if (id == null)
         return;

      String name = property.getName();

      TreeItem treeItem = treeItems.get(id);
      if (treeItem == null) {
         treeItem = new TreeItem();
      }
      treeItem.config = config;
      treeItem.id = id;
      treeItem.isFolder = exportedItem.getElement().getAttributeValue("type").endsWith("Folder");

      String value = element.getValue();

      if (name == null) {
         treeItems.put(id, treeItem);
         return;
      }

      if (property instanceof ReferenceItemProperty && name.contentEquals("parent")) {
         ReferenceItemProperty referenceProperty = (ReferenceItemProperty) property;
         TreeItem parent = treeItems.get(referenceProperty.getReferenceId());
         if (parent != null) {
            treeItem.parent = parent;
            parent.children.add(treeItem);
            treeItems.put(id, treeItem);
         } else {
            parent = new TreeItem();
            treeItem.parent = parent;
            parent.children.add(treeItem);
            treeItems.put(referenceProperty.getReferenceId(), parent);
         }
      } else if (property instanceof ItemPropertyCollection && name.contentEquals("children")) {
         treeItems.put(id, treeItem);
         ItemPropertyCollection collectionItemProperty = (ItemPropertyCollection) property;
         for (String refId : collectionItemProperty.getReferencedIds()) {
            TreeItem collectionTreeItem = treeItems.get(refId);
            if (collectionTreeItem == null) {
               collectionTreeItem = new TreeItem();
            }
            collectionTreeItem.parent = treeItem;
            treeItem.children.add(collectionTreeItem);
         }
      } else if (name.contentEquals("fileData")) {
         Elements data = element.getChildElements();
         for (Element dataElement : data) {
            String dataName = dataElement.getAttributeValue("name");
            if (dataName.contentEquals("data")) {
               Base64.Decoder decoder = Base64.getDecoder();
               treeItem.content = new String(decoder.decode(dataElement.getValue()));
            }
         }
      } else if (name.contentEquals("name")) {
         treeItem.name = value;
      } else if (name.contentEquals("key")) {
         treeItem.key = value;
         idKeyMap.put(id, value);
      } else if (name.contentEquals("contentType")) {
         treeItem.contentType = value;
      }
      treeItems.put(id, treeItem);
   }

   private List<TreeItem> makeTree(List<TreeNodeImportItemConfig> importConfigs) {
      Map<String, TreeItem> treeItems = new HashMap<>();
      Map<String, String> idKeyMap = new HashMap<>();
      importConfigs.forEach(config -> {
         ExportedItem exportedItem = importSupervisor.getExportedItemFor(config);
         exportedItem.getProperties()
               .forEach(property -> handleProperty(idKeyMap, treeItems, exportedItem, property, config));
      });

      return treeItems.values().stream().filter(it -> it.parent == null).collect(Collectors.toList());
   }

   @Override
   protected void doImportReferenceMode(TreeNodeImportItemConfig itemConfig) {
      importSupervisor.registerExternalReference(itemConfig.getId(), itemConfig.getReferenceObject());
   }

   protected abstract TreeDBManager getTreeDBManager();
}
