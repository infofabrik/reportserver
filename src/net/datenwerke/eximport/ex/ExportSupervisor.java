package net.datenwerke.eximport.ex;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.type.NullType;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ExImportIdService;
import net.datenwerke.rs.utils.reflection.ReflectionService;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * 
 *
 */
public class ExportSupervisor {

   private final ExImportIdService idService;
   private final ExImportHelperService eiHelper;
   private final ReflectionService reflectionServices;

   private final ExportConfig config;
   private final List<Exporter> exporters;
   private final XMLStreamWriter xsw;

   private final Set<String> requestedBaseElementIds = new HashSet<String>();

   private Set<EnclosedObjectConfig> enclosedConfigs = new HashSet<EnclosedObjectConfig>();

   @Inject
   public ExportSupervisor(
         ExImportIdService idService, 
         ExImportHelperService eiHelper,
         ReflectionService reflectionServices, 
         @Assisted ExportConfig config, 
         @Assisted List<Exporter> exporters,
         @Assisted XMLStreamWriter xsw
         ) {

      /* store objects */
      this.idService = idService;
      this.eiHelper = eiHelper;
      this.reflectionServices = reflectionServices;
      this.config = config;
      this.exporters = exporters;
      this.xsw = xsw;
   }

   public void export() throws XMLStreamException {
      /* configure exporters */
      configureExporters();

      /* create base element and a base for each exporter */
      xsw.writeStartElement(ExImportHelperService.DOCUMENT_DATA_ELEMENT);

      for (Exporter exporter : exporters)
         exporter.export(this);

      /* close base element */
      xsw.writeEndElement();

   }

   private void configureExporters() {
      Map<Exporter, Collection<ExportItemConfig<?>>> configMap = createConfigMap();
      Map<ExportItemConfig, Boolean> processedConfigs = new IdentityHashMap<ExportItemConfig, Boolean>();
      Map<EnclosedObjectConfig, Boolean> processedEnclosedConfigs = new IdentityHashMap<EnclosedObjectConfig, Boolean>();

      /* add references that are needed for export */
      boolean configChanged = true;
      while (configChanged) {
         configChanged = false;
         for (Exporter exporter : exporters) {
            Collection<ExportItemConfig<?>> configs = CollectionUtils.subtract(configMap.get(exporter),
                  processedConfigs.keySet());
            if (configs.isEmpty())
               continue;

            /* references */
            Collection<ExportItemConfig<?>> newConfigs = exporter.addReferences(this, configs);
            configChanged |= config.addItemConfig(newConfigs);

            /* enclosed objects */
            Collection<EnclosedObjectConfig> newEnclosed = exporter.addEnclosed(this, configs);
            configChanged |= enclosedConfigs.addAll(newEnclosed);

            /* mark configs as processed */
            for (ExportItemConfig c : configs)
               processedConfigs.put(c, true);
         }

         /* ask if the enclosed objects want to add any new objects */
         Collection<ExportItemConfig<?>> newConfigs = new HashSet<ExportItemConfig<?>>();
         Collection<EnclosedObjectConfig> newEnclosed = new HashSet<EnclosedObjectConfig>();
         for (EnclosedObjectConfig enclosedCon : enclosedConfigs) {
            if (!processedEnclosedConfigs.containsKey(enclosedCon)) {
               for (Exporter exporter : exporters) {
                  if (exporter.consumesEnclosedObject(enclosedCon)) {
                     /* references */
                     newConfigs.addAll(exporter.addReferences(this, enclosedCon));

                     /* enclosed */
                     newEnclosed.addAll(exporter.addEnclosed(this, enclosedCon));
                     break;
                  }
               }

               /* mark as processed */
               processedEnclosedConfigs.put(enclosedCon, true);
            }
         }
         configChanged |= config.addItemConfig(newConfigs);
         configChanged |= enclosedConfigs.addAll(newEnclosed);

         configMap = createConfigMap();
      }

      for (Exporter exporter : exporters)
         exporter.configure(config.getSpecificExporterConfigs(), configMap.get(exporter));
   }

   private Map<Exporter, Collection<ExportItemConfig<?>>> createConfigMap() {
      Map<Exporter, Collection<ExportItemConfig<?>>> configMap = new HashMap<Exporter, Collection<ExportItemConfig<?>>>();
      for (Exporter exporter : exporters)
         configMap.put(exporter, new LinkedList<ExportItemConfig<?>>());

      for (ExportItemConfig<?> itemConfig : config.getItemConfigs()) {
         for (Exporter exporter : exporters) {
            if (exporter.consumes(itemConfig)) {
               configMap.get(exporter).add(itemConfig);
               break;
            }
         }
      }
      return configMap;
   }

   public void beginExporterElement(Exporter exporter) throws XMLStreamException {
      xsw.writeStartElement(ExImportHelperService.EXPORTER_BASE_ELEMENT);
      eiHelper.setExporterType(xsw, exporter);
   }

   public void beginElement(String name) throws XMLStreamException {
      xsw.writeStartElement(name);
   }

   public void endElement() throws XMLStreamException {
      xsw.writeEndElement();
   }

   public String getReferenceId(Object value) {
      String id = idService.provideId(value);

      if (null == id || "".equals(id))
         throw new IllegalStateException("Could not find id for object: " + value.getClass());

      if (!hasEncounteredId(id))
         throw new IllegalStateException(
               "The encountered id has not been registered: " + id + ", " + value.getClass().getName());

      return id;
   }

   public boolean hasEncounteredId(String id) {
      /* test if we have encountered id */
      boolean encountered = false;
      for (ExportItemConfig<?> itemConfig : config.getItemConfigs()) {
         if (itemConfig.getId().equals(id)) {
            encountered = true;
            break;
         }
      }

      if (!encountered) {
         /* test enclosed objects */
         for (EnclosedObjectConfig itemConfig : enclosedConfigs) {
            if (itemConfig.getId().equals(id)) {
               encountered = true;
               break;
            }
         }
      }

      return encountered;
   }

   public ExportItemConfig<?> getNewExportConfigFor(Object object) {
      if (null == object)
         return null;

      if (object instanceof HibernateProxy)
         object = ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();

      /* test if object is an enclosed object */
      String objectId = idService.provideId(object);
      if (null != objectId && isEnclosedObjectId(objectId))
         return null;

      for (Exporter exporter : exporters) {
         ExportItemConfig<?> itemConfig = exporter.generateExportConfig(object);
         if (null != itemConfig)
            return itemConfig;
      }

      System.out.println("Notice: Could not find exporter to create config for object: " + object.getClass());
      return null;
   }

   protected boolean isEnclosedObjectId(String objectId) {
      for (EnclosedObjectConfig enclosedConfig : enclosedConfigs)
         if (objectId.equals(enclosedConfig.getId()))
            return true;
      return false;
   }

   public void exportEnclosedObject(Object enclosed) throws XMLStreamException {
      /* create config */
      EnclosedObjectConfig config = new EnclosedObjectConfig(enclosed);

      /* make sure object was registered */
      if (!enclosedConfigs.contains(config))
         throw new IllegalArgumentException("The enclosed object " + enclosed.getClass() + " with Id" + config.getId()
               + " has not been registered.");

      for (Exporter exporter : exporters) {
         if (exporter.consumesEnclosedObject(config)) {
            eiHelper.setEnclosedAttributes(xsw, exporter);
            exporter.exportEnclosed(this, config);
            break;
         }
      }
   }

   public EnclosedObjectConfig getNewEnclosedConfigFor(Object enclosed) {
      return new EnclosedObjectConfig(enclosed);
   }

   public void beginSimpleCollectionValueElement(Object value) throws XMLStreamException {
      xsw.writeStartElement(ExImportHelperService.COLLECTION_VALUE_ELEMENT);
      if (null != value)
         eiHelper.setTypeAttribute(xsw, value.getClass());
      else
         eiHelper.setTypeAttribute(xsw, NullType.class);
   }

   public void createSimpleCollectionValueElement(Object value) throws XMLStreamException {
      beginSimpleCollectionValueElement(value);

      if (null != value)
         eiHelper.setValueAttribute(xsw, value.toString());
      else
         eiHelper.setIsNullAttribute(xsw);

      endElement();
   }

   public void beginPropertyElement(Field field, Object value) throws XMLStreamException {
      beginPropertyElement(field.getName(), null == value ? field.getType() : value.getClass());
   }

   public void beginPropertyElement(String propertyName, Class<?> propertyType) throws XMLStreamException {
      beginElement(ExImportHelperService.EXPORTED_PROPERTY_ELEMENT_NAME);

      eiHelper.setTypeAttribute(xsw, propertyType);
      eiHelper.setNameAttribute(xsw, propertyName);
   }

   public void createPropertyElement(Field field, Object value) throws XMLStreamException {
      beginPropertyElement(field, value);
      eiHelper.setValueAttribute(xsw, value.toString());
      endElement();
   }

   public void createPropertyElement(String propertyName, Class<?> propertyType, Object value)
         throws XMLStreamException {
      beginPropertyElement(propertyName, propertyType);
      eiHelper.setValueAttribute(xsw, value.toString());
      endElement();
   }

   public void beginPropertyCollectionElement(Field field) throws XMLStreamException {
      beginPropertyElement(field, null);

      Class<?> collectionType = reflectionServices.getGenericType(field);
      eiHelper.setIsCollectionAttribute(xsw, collectionType);
   }

   public void beginPropertyCollectionElement(String propertyName, Class<?> propertyType,
         Class<? extends Collection> collectionType) throws XMLStreamException {
      beginPropertyElement(propertyName, collectionType);
      eiHelper.setIsCollectionAttribute(xsw, propertyType);
   }

   protected Exporter getExporterToId(String id) {
      for (ExportItemConfig<?> itemConfig : config.getItemConfigs())
         if (itemConfig.getId().equals(id))
            for (Exporter exporter : exporters)
               if (exporter.consumes(itemConfig))
                  return exporter;

      for (EnclosedObjectConfig itemConfig : enclosedConfigs)
         if (itemConfig.getId().equals(id))
            for (Exporter exporter : exporters)
               if (exporter.consumesEnclosedObject(itemConfig))
                  return exporter;

      return null;
   }

   public void addReferenceTo(Object value) throws XMLStreamException {
      addReferenceTo(value, false);
   }

   public void addReferenceTo(Object value, boolean optional) throws XMLStreamException {
      String id = getReferenceId(value);

      /* find exporter for id */
      Exporter exporter = getExporterToId(id);
      if (null == exporter)
         throw new IllegalStateException("Found a reference without finding an exporter to take responsibility: "
               + value.getClass() + ", id:" + id);

      eiHelper.setReferenceAttributes(xsw, id, exporter, optional);
   }

   public boolean canBeReferenced(Object object) {
      try {
         String id = getReferenceId(object);
         return null != id;
      } catch (Exception e) {
      }
      return false;
   }

   public void createCDataElement(String data) throws XMLStreamException {
      xsw.writeCData(data);
   }

   public void addIdAttributeToBaseElement(String toExportId) throws XMLStreamException {
      if (requestedBaseElementIds.contains(toExportId))
         throw new IllegalStateException("Object has already been exported: " + toExportId);
      requestedBaseElementIds.add(toExportId);

      eiHelper.addIdAttribute(xsw, toExportId);
   }

   public XMLStreamWriter getXmlStream() {
      return xsw;
   }
}
