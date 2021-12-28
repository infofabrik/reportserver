package net.datenwerke.eximport.im.objectimporters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.eximport.ex.annotations.ExImportConfig;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHook;
import net.datenwerke.eximport.im.ImportSupervisor;
import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.eximport.obj.ItemPropertyCollection;
import net.datenwerke.eximport.obj.ReferenceItemProperty;
import net.datenwerke.eximport.obj.SimpleItemProperty;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * 
 *
 */
public class BasicObjectImporter {

   protected Collection<String> ignoredFieldNames = new HashSet<String>();

   protected final HookHandlerService hookHandler;
   protected final ReflectionService reflectionServices;

   protected final ImportSupervisor importSupervisor;
   protected final ImportableElement exportedItem;

   private boolean registerImportedObject = true;

   @Inject
   public BasicObjectImporter(HookHandlerService hookHandler, ReflectionService reflectionServices,
         @Assisted ImportSupervisor importSupervisor, @Assisted ImportableElement exportedItem) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.reflectionServices = reflectionServices;

      this.importSupervisor = importSupervisor;
      this.exportedItem = exportedItem;
   }

   public Collection<String> getIgnoredFieldNames() {
      return ignoredFieldNames;
   }

   public void setIgnoredFieldNames(Collection<String> ignoredFieldNames) {
      this.ignoredFieldNames = ignoredFieldNames;
   }

   public void addIgnoredFieldNames(String... names) {
      ignoredFieldNames.addAll(Arrays.asList(names));
   }

   public void addIgnoredFieldNames(Collection<String> names) {
      ignoredFieldNames.addAll(names);
   }

   public Object importObject() {
      try {
         /* create object and register it */
         Object obj = instantiateObject();
         if (registerImportedObject)
            importSupervisor.registerImportedObject(exportedItem.getId(), obj);

         importProperties(obj, exportedItem.getProperties());

         if (registerImportedObject)
            importSupervisor.notifyImportDone(exportedItem.getId(), obj);

         return obj;
      } catch (Exception e) {
         throw new ImportException("Could not import object: " + e.getMessage(), e);
      }
   }

   protected void importProperties(Object obj, Collection<ItemProperty> properties) throws SecurityException,
         NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException {
      Set<ItemProperty> processedProperties = new HashSet<ItemProperty>();
      boolean processedPropertiesChanged = true;
      while (processedPropertiesChanged) {
         processedPropertiesChanged = false;

         for (ItemProperty property : properties) {
            if (processedProperties.contains(property))
               continue;

            Field field = getField(obj, property);
            if (!allowImportField(obj, field))
               continue;

            Object value = null;

            if (property instanceof SimpleItemProperty)
               value = importSimpleProperty((SimpleItemProperty) property);
            else if (property instanceof ItemPropertyCollection)
               value = importPropertyCollection((ItemPropertyCollection) property);
            else if (property instanceof ReferenceItemProperty)
               value = importReferenceProperty((ReferenceItemProperty) property);
            else if (property instanceof EnclosedItemProperty)
               value = importEnclosedProperty((EnclosedItemProperty) property);
            else if (property instanceof ComplexItemProperty)
               value = importComplexProperty((ComplexItemProperty) property);

            if (null != value) {
               boolean processed = false;

               if (value instanceof Collection) {
                  Object oldValue = field.get(obj);
                  if (null != oldValue) {
                     ((Collection) oldValue).clear();
                     ((Collection) oldValue).addAll((Collection) value);
                     processed = true;
                  }
               }
               if (!processed)
                  field.set(obj, value);
            }

            processedPropertiesChanged |= processedProperties.add(property);
         }
      }

      if (processedProperties.size() != properties.size()) {
         for (ItemProperty property : properties)
            if (!processedProperties.contains(property) && allowImportField(obj, getField(obj, property)))
               throw new IllegalStateException("I could apparently not import all properties: " + obj.getClass()
                     + ", property: " + property.getName());
      }
   }

   protected boolean allowImportField(Object obj, Field field) {
      if (ignoredFieldNames.contains(field.getName()))
         return false;
      ExportableField ef = field.getAnnotation(ExportableField.class);
      if (null == ef)
         return true;

      ExImportConfig exImportConfig = getExportConfigAnno(obj);
      if (null != exImportConfig) {
         for (String excluded : exImportConfig.excludeFields())
            if (excluded.contains(field.getName()))
               return false;
      }

      return ef.importField();
   }

   protected ExImportConfig getExportConfigAnno(Object obj) {
      return obj.getClass().getAnnotation(ExImportConfig.class);
   }

   protected Object importComplexProperty(ComplexItemProperty property) throws SecurityException, NoSuchFieldException {
      Class<?> type = property.getType();
      if (reflectionServices.representsNull(type))
         return null;

      BasicObjectExImporterHelperHook helper = getImporterHelper(type);
      if (null != helper)
         return helper.importData(property);
      return null;
   }

   protected Object importEnclosedProperty(EnclosedItemProperty property) {
      return importSupervisor.getEnclosedObject(property, registerImportedObject);
   }

   protected Object importReferenceProperty(ReferenceItemProperty property) {
      return importSupervisor.getReferencedObject(property.getReferenceId(), registerImportedObject);
   }

   protected Object importPropertyCollection(ItemPropertyCollection property)
         throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
      /* instantiate collection */
      Collection collection = (Collection) reflectionServices.createCollection(property.getType());

      /* fill collection */
      Set<ItemProperty> processedProperties = new HashSet<ItemProperty>();
      boolean processedPropertiesChanged = true;
      while (processedPropertiesChanged) {
         processedPropertiesChanged = false;
         for (ItemProperty colProperty : property.getCollectionValues()) {
            if (processedProperties.contains(colProperty))
               continue;

            Object value = null;
            if (colProperty instanceof SimpleItemProperty)
               value = importSimpleProperty((SimpleItemProperty) colProperty);
            else if (colProperty instanceof ItemPropertyCollection)
               value = importPropertyCollection((ItemPropertyCollection) colProperty);
            else if (colProperty instanceof ReferenceItemProperty)
               value = importReferenceProperty((ReferenceItemProperty) colProperty);
            else if (colProperty instanceof EnclosedItemProperty)
               value = importEnclosedProperty((EnclosedItemProperty) colProperty);
            else if (colProperty instanceof ComplexItemProperty)
               value = importComplexProperty((ComplexItemProperty) colProperty);
            else
               throw new IllegalStateException("Could no determine property type: " + colProperty.getClass());

            if (null != value)
               collection.add(value);

            processedPropertiesChanged |= processedProperties.add(colProperty);
         }
      }

      if (processedProperties.size() != property.getCollectionValues().size())
         throw new IllegalStateException("I could apparently not import all properties.");

      return collection;
   }

   protected Object importSimpleProperty(SimpleItemProperty property) throws SecurityException, NoSuchFieldException {
      Class<?> type = property.getType();

      if (reflectionServices.representsNull(type))
         return null;
      else if (reflectionServices.isSimpleType(type)) {
         return reflectionServices.convertStringToSimpleType(property.getValue(), type);
      } else {
         BasicObjectExImporterHelperHook helper = getImporterHelper(type);
         if (null != helper)
            return helper.importData(property);
      }

      return null;
   }

   protected Field getField(Object obj, ItemProperty property) throws SecurityException, NoSuchFieldException {
      return getField(obj, property.getName());
   }

   protected Field getField(Object obj, String name) throws SecurityException, NoSuchFieldException {
      return getField(obj.getClass(), name);
   }

   protected Field getField(Class<?> type, String name) throws SecurityException, NoSuchFieldException {
      if (null == type)
         throw new IllegalArgumentException("type may not be null");
      try {
         Field field = type.getDeclaredField(name);
         field.setAccessible(true);
         return field;
      } catch (NoSuchFieldException e) {
         type = type.getSuperclass();
         if (null == type) {
            throw e;
         }
         return getField(type, name);
      }
   }

   protected Object instantiateObject() throws InstantiationException, IllegalAccessException {
      return exportedItem.getType().newInstance();
   }

   protected BasicObjectExImporterHelperHook getImporterHelper(Field exportableField) {
      return getImporterHelper(exportableField.getType());
   }

   protected BasicObjectExImporterHelperHook getImporterHelper(Class<?> type) {
      for (BasicObjectExImporterHelperHook helper : hookHandler.getHookers(BasicObjectExImporterHelperHook.class))
         if (helper.consumes(type))
            return helper;
      return null;
   }

   public void setRegisterImportedObject(boolean registerImportedObject) {
      this.registerImportedObject = registerImportedObject;
   }

   public boolean isRegisterImportedObject() {
      return registerImportedObject;
   }
}
