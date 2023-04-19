package net.datenwerke.eximport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.nuxlets.StreamingPathFilter;
import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.eximport.obj.ItemPropertyCollection;
import net.datenwerke.eximport.obj.ReferenceItemProperty;
import net.datenwerke.eximport.obj.SimpleItemProperty;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;

public class ExportDataAnalyzerServiceImpl implements ExportDataAnalyzerService {

   private final ExImportHelperService eiHelper;
   private final ReflectionService reflectionService;

   @Inject
   public ExportDataAnalyzerServiceImpl(
         ExImportHelperService eiHelper, 
         ReflectionService reflectionService
         ) {

      /* store objects */
      this.eiHelper = eiHelper;
      this.reflectionService = reflectionService;
   }

   @Override
   public String getExportName(ExportDataProvider dataProvider) {
      String expr = "/rsexim:" + ExImportHelperService.DOCUMENT_ROOT_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_NAME_ELEMENT;

      Builder builder = new Builder(
            new StreamingPathFilter(expr, eiHelper.getNamespaceMap()).createNodeFactory(null, element -> new Nodes(element)));
      try {
         Document doc = builder.build(dataProvider.getXmlStream());
         return doc.getRootElement().getChildElements().get(0).getChildElements().get(0).getValue();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public String getExportDescription(ExportDataProvider dataProvider) {
      String expr = "/rsexim:" + ExImportHelperService.DOCUMENT_ROOT_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_DESCRIPTION_ELEMENT;
      Builder builder = new Builder(
            new StreamingPathFilter(expr, eiHelper.getNamespaceMap()).createNodeFactory(null, element -> new Nodes(element)));
      try {
         Document doc = builder.build(dataProvider.getXmlStream());
         return doc.getRootElement().getChildElements().get(0).getChildElements().get(0).getValue();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Date getExportDate(ExportDataProvider dataProvider) {
      String expr = "/rsexim:" + ExImportHelperService.DOCUMENT_ROOT_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_HEAD_DATE_ELEMENT;
      Builder builder = new Builder(
            new StreamingPathFilter(expr, eiHelper.getNamespaceMap()).createNodeFactory(null, element -> new Nodes(element)));
      try {
         Document doc = builder.build(dataProvider.getXmlStream());
         String dateString = doc.getRootElement().getChildElements().get(0).getChildElements().get(0).getValue();
         return (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z").parse(dateString));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Elements getExporterElements(ExportDataProvider dataProvider) {
      String expr = "/rsexim:" + ExImportHelperService.DOCUMENT_ROOT_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_DATA_ELEMENT + "/rsexim:" + ExImportHelperService.EXPORTER_BASE_ELEMENT;

      Builder builder = new Builder(
            new StreamingPathFilter(expr, eiHelper.getNamespaceMap()).createNodeFactory(null, element -> new Nodes(element)));
      try {
         Document doc = builder.build(dataProvider.getXmlStream());
         return doc.getRootElement().getChildElements().get(0).getChildElements();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public Collection<Class<?>> getExporterClasses(ExportDataProvider dataProvider) throws ClassNotFoundException {
      Set<Class<?>> exporters = new HashSet<Class<?>>();

      Elements nodeList = getExporterElements(dataProvider);
      if (null != nodeList) {
         for (int i = 0; i < nodeList.size(); i++) {
            Element el = (Element) nodeList.get(i);
            exporters.add(getExporterClass(el));
         }
      }

      return exporters;
   }

   @Override
   public Class<?> getExporterClass(Element el) throws ClassNotFoundException {
      if (!hasAttribute(el, ExImportHelperService.EXPORTER_TYPE))
         return null;

      String exporterType = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
      return reflectionService.getClassForName(exporterType);
   }

   @Override
   public List<ExportedItem> getExportedItemsFor(ExportDataProvider dataProvider, Class<? extends Exporter> exporter)
         throws ClassNotFoundException {
      List<ExportedItem> items = new ArrayList<ExportedItem>();

      Elements list = getExportedItemElementsFor(dataProvider, exporter);
      if (null != list) {
         for (int i = 0; i < list.size(); i++) {
            Element el = (Element) list.get(i);

            ExportedItem exportedItem = getExportedItemFor(el, dataProvider);
            items.add(exportedItem);
         }
      }

      return items;
   }

   @Override
   public ExportedItem getExportedItemFor(Element el, ExportDataProvider dataProvider)
         throws ClassNotFoundException {
      String id = getItemId(el);
      Class<?> type = getItemTypeAsClass(el);
      Collection<ItemProperty> itemProperties = getItemPropertiesFor(el);
      String exportTypeName = dataProvider.getExportertTypeById(id);
      Class<?> exporterType = reflectionService.getClassForName(exportTypeName);

      return new ExportedItem(id, type, itemProperties, exporterType, el);
   }

   @Override
   public Collection<ItemProperty> getItemPropertiesFor(final Element el) throws ClassNotFoundException {
      return getItemsPropertyElements(el)
         .stream()
         .map(rethrowFunction( this::getItemPropertyFor ) )
         .collect(toSet());
   }

   @Override
   public ItemProperty getItemPropertyFor(Element el) throws ClassNotFoundException {
      String name = getAttribute(el, ExImportHelperService.NAME_ATTRIBUTE);
      String typeName = getAttribute(el, ExImportHelperService.TYPE_ATTRIBUTE);
      Class<?> type = reflectionService.getClassForName(typeName);

      /* is simple ? */
      if (hasAttribute(el, ExImportHelperService.VALUE_ATTRIBUTE)) {
         String value = getAttribute(el, ExImportHelperService.VALUE_ATTRIBUTE);
         return new SimpleItemProperty(name, type, value, el);
      }

      /* is reference */
      if (hasAttribute(el, ExImportHelperService.REFERENCE_ID)) {
         String refId = getAttribute(el, ExImportHelperService.REFERENCE_ID);
         String exporterTypeName = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
         Class<?> exporterType = reflectionService.getClassForName(exporterTypeName);
         boolean optional = false;
         if (hasAttribute(el, ExImportHelperService.IS_OPTIONAL_REFERENCE))
            optional = ExImportHelperService.XML_TRUE
                  .equals(getAttribute(el, ExImportHelperService.IS_OPTIONAL_REFERENCE));

         return new ReferenceItemProperty(name, type, refId, exporterType, optional, el);
      }

      /* is collection */
      if (hasAttribute(el, ExImportHelperService.IS_COLLECTION_ATTRIBUTE)) {
         String propertyTypeName = getAttribute(el, ExImportHelperService.PROPERTY_TYPE);
         Class<?> propertyType = reflectionService.getClassForName(propertyTypeName);
         List<ItemProperty> collectionValues = getCollectionValuesFor(el);

         return new ItemPropertyCollection(name, type, propertyType, collectionValues, el);
      }

      /* is enclosed */
      if (hasAttribute(el, ExImportHelperService.IS_ENCLOSED_ATTRIBUTE)) {
         EnclosedItemProperty enclosed = getEnclosedPropertyFor(el);
         return enclosed;
      }

      return new ComplexItemProperty(name, type, el);
   }

   @Override
   public EnclosedItemProperty getEnclosedPropertyFor(Element el) throws ClassNotFoundException {
      String name = getAttribute(el, ExImportHelperService.NAME_ATTRIBUTE);
      String typeName = getAttribute(el, ExImportHelperService.TYPE_ATTRIBUTE);
      Class<?> type = reflectionService.getClassForName(typeName);

      String exporterTypeName = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
      Class<?> exporterType = reflectionService.getClassForName(exporterTypeName);
      Collection<ItemProperty> itemProperties = getItemPropertiesFor(el);
      String id = getItemId(el);

      return new EnclosedItemProperty(name, type, id, exporterType, itemProperties, el);
   }

   @Override
   public List<ItemProperty> getCollectionValuesFor(final Element el) throws ClassNotFoundException {
      return getCollectionValueElementsFor(el)
         .stream()
         .map(rethrowFunction(this::getItemPropertyFor))
         .collect(toList());
   }

   @Override
   public List<Element> getCollectionValueElementsFor(Element el) {
      List<Element> collectionValues = new ArrayList<Element>();
      Elements list = el.getChildElements();
      for (int i = 0; i < list.size(); i++) {
         Element node = list.get(i);
         if (ExImportHelperService.COLLECTION_VALUE_ELEMENT.equals(node.getLocalName()))
            collectionValues.add((Element) node);
      }
      return collectionValues;
   }

   @Override
   public Elements getExportedItemElementsFor(ExportDataProvider dataProvider,
         final Class<? extends Exporter> exporter) {
      String locationStr = "/rsexim:" + ExImportHelperService.DOCUMENT_ROOT_ELEMENT + "/rsexim:"
            + ExImportHelperService.DOCUMENT_DATA_ELEMENT + "/rsexim:" + ExImportHelperService.EXPORTER_BASE_ELEMENT;
      Builder builder = new Builder(
            new StreamingPathFilter(locationStr, eiHelper.getNamespaceMap()).createNodeFactory(null,
                  element -> element.query(
                        ".[@" + ExImportHelperService.EXPORTER_TYPE + "='" + exporter.getName() + "']",
                        eiHelper.xpathContext())));
      try {
         InputStream xmlStream = dataProvider.getXmlStream();
         Document doc = builder.build(xmlStream);
         Element root = doc.getRootElement();
         if (0 == root.getChildCount())
            return null;
         return root.getChildElements().get(0).getChildElements().get(0).getChildElements();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean isExportedItemElement(Element el) {
      return ExImportHelperService.EXPORTED_ITEM_ELEMENT_NAME.equals(el.getLocalName());
   }

   @Override
   public void validateExportedItemElement(Element el) {
      if (!isExportedItemElement(el))
         throw new IllegalArgumentException(
               "The given element is not a valid exported item element but: " + el.getLocalName());
   }

   @Override
   public String getItemId(Element el) {
      return eiHelper.getIdAttribute(el);
   }

   @Override
   public String getItemType(Element el) {
      return getAttribute(el, ExImportHelperService.TYPE_ATTRIBUTE);
   }

   @Override
   public Class<?> getItemTypeAsClass(Element el) throws ClassNotFoundException {
      String type = getItemType(el);
      return reflectionService.getClassForName(type);
   }

   @Override
   public List<Element> getItemsPropertyElements(Element el) {
      List<Element> propertyEls = new ArrayList<Element>();
      Elements list = el.getChildElements();
      for (int i = 0; i < list.size(); i++) {
         Element node = list.get(i);
         if (ExImportHelperService.EXPORTED_PROPERTY_ELEMENT_NAME.equals(node.getLocalName()))
            propertyEls.add((Element) node);
      }
      return propertyEls;
   }

   @Override
   public boolean hasAttribute(Element el, String attribute) {
      /*
       * no need for namespaces as attributes do not inherited the default namespace
       */
      return null != el.getAttribute(attribute);
   }

   @Override
   public String getAttribute(Element el, String attribute) {
      /*
       * no need for namespaces as attributes do not inherited the default namespace
       */
      return el.getAttributeValue(attribute);
   }

   @Override
   public ExportedItem getExportedItemById(ExportDataProvider dataProvider, String id) throws ClassNotFoundException {
      Node node = dataProvider.getElementById(id);
      ;
      if (null != node)
         return getExportedItemFor((Element) node, dataProvider);
      return null;
   }

   @Override
   public ExportedItem getExportedItemWithEnclosed(ExportDataProvider dataProvider, String id)
         throws ClassNotFoundException {
      Element element = dataProvider.getExportedItemWithEnclosed(id);
      if (null != element)
         return getExportedItemFor(element, dataProvider);
      return null;
   }

   @Override
   public Class<?> getExporterForEnclosed(ExportDataProvider dataProvider, String id) throws ClassNotFoundException {
      Element node = dataProvider.getElementById(id);
      ;
      if (node != null && null == node.getAttribute(ExImportHelperService.IS_ENCLOSED_ATTRIBUTE))
         throw new IllegalStateException("Property should have been enclosed");

      if (null != node) {
         Element el = (Element) node;
         String exporterTypeName = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
         Class<?> exporterType = reflectionService.getClassForName(exporterTypeName);
         return exporterType;
      }
      return null;
   }

   @Override
   public EnclosedItemProperty getEnclosedPropertyFor(ExportDataProvider dataProvider, String id)
         throws ClassNotFoundException {
      Element node = dataProvider.getElementById(id);
      if (null != node) {
         if (null == node.getAttribute(ExImportHelperService.IS_ENCLOSED_ATTRIBUTE))
            throw new IllegalStateException("Property should have been enclosed");
         return getEnclosedPropertyFor((Element) node);
      }
      return null;
   }

   @Override
   public ExportedItem getRoot(ExportDataProvider dataProvider, Class<? extends Exporter> exporter) throws ClassNotFoundException {
      /* loop over items to find root */
      return getExportedItemsFor(dataProvider, exporter)
         .stream()
         .filter(item -> null == item.getPropertyByName("parent"))
         .findAny()
         .orElse(null);
   }

}
