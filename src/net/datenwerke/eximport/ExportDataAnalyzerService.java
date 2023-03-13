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

import org.w3c.dom.NodeList;

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

/**
 * Helper Service that allows to analize an export file.
 * 
 * @see ExportService
 *
 */
public class ExportDataAnalyzerService {

   private final ExImportHelperService eiHelper;
   private final ReflectionService reflectionService;

   @Inject
   public ExportDataAnalyzerService(ExImportHelperService eiHelper, ReflectionService reflectionService) {

      /* store objects */
      this.eiHelper = eiHelper;
      this.reflectionService = reflectionService;
   }

   /**
    * Returns the export name of a given {@link Document}
    * 
    * @return The export name of the {@link Document}
    */
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

   /**
    * Returns the export description of a given {@link Document}
    * 
    * @return The export description of the {@link Document}
    */
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

   /**
    * Returns the export {@link Date} of a given {@link Document}
    * 
    * @return The export {@link Date}
    */
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

   /**
    * Returns a {@link NodeList} of exporter elements of a given {@link Document}
    * 
    * @return A {@link NodeList} of exporter elements
    */
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

   /**
    * Returns a {@link Collection} of exporter {@link Class}es of a given
    * {@link Document}
    * 
    * @return A {@link Collection} of exporter {@link Class}es
    * @throws ClassNotFoundException
    */
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

   /**
    * Returns the {@link Class} of the exporter of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link Class} of the exporter
    * @throws ClassNotFoundException
    */
   public Class<?> getExporterClass(Element el) throws ClassNotFoundException {
      if (!hasAttribute(el, ExImportHelperService.EXPORTER_TYPE))
         return null;

      String exporterType = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
      return reflectionService.getClassForName(exporterType);
   }

   /**
    * Get a {@link List} of ExportedItems from the given {@link Document} and
    * {@link Exporter}
    * 
    * @param exporter The {@link Exporter} to use
    * @return A {@link List} of ExportedItems
    * @throws ClassNotFoundException
    */
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

   /**
    * Gets the {@link ExportedItem} for the given {@link Element}
    * 
    * @param el           The {@link Element}
    * @param dataProvider
    * @return The {@link ExportedItem}
    * @throws ClassNotFoundException
    */
   protected ExportedItem getExportedItemFor(Element el, ExportDataProvider dataProvider)
         throws ClassNotFoundException {
      String id = getItemId(el);
      Class<?> type = getItemTypeAsClass(el);
      Collection<ItemProperty> itemProperties = getItemPropertiesFor(el);
      String exportTypeName = dataProvider.getExportertTypeById(id);
      Class<?> exporterType = reflectionService.getClassForName(exportTypeName);

      return new ExportedItem(id, type, itemProperties, exporterType, el);
   }

   /**
    * Returns a {@link Collection} of {@link ItemProperty}s for the given
    * {@link Element}
    * 
    * @param el The {@link Element}
    * @return A {@link Collection} of {@link ItemProperty}s
    * @throws ClassNotFoundException
    */
   public Collection<ItemProperty> getItemPropertiesFor(final Element el) throws ClassNotFoundException {
      return getItemsPropertyElements(el)
         .stream()
         .map(rethrowFunction( this::getItemPropertyFor ) )
         .collect(toSet());
   }

   /**
    * Returns the {@link ItemProperty} for the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link ItemProperty}
    * @throws ClassNotFoundException
    */
   protected ItemProperty getItemPropertyFor(Element el) throws ClassNotFoundException {
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

   /**
    * Returns the {@link EnclosedItemProperty} for the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The {@link EnclosedItemProperty}
    * @throws ClassNotFoundException
    */
   protected EnclosedItemProperty getEnclosedPropertyFor(Element el) throws ClassNotFoundException {
      String name = getAttribute(el, ExImportHelperService.NAME_ATTRIBUTE);
      String typeName = getAttribute(el, ExImportHelperService.TYPE_ATTRIBUTE);
      Class<?> type = reflectionService.getClassForName(typeName);

      String exporterTypeName = getAttribute(el, ExImportHelperService.EXPORTER_TYPE);
      Class<?> exporterType = reflectionService.getClassForName(exporterTypeName);
      Collection<ItemProperty> itemProperties = getItemPropertiesFor(el);
      String id = getItemId(el);

      return new EnclosedItemProperty(name, type, id, exporterType, itemProperties, el);
   }

   /**
    * Returns a {@link List} of {@link ItemProperty}s for the given
    * {@link Element}s collection value element
    * 
    * @param el The {@link Element}
    * @return A {@link List} of {@link ItemProperty}s
    * @throws ClassNotFoundException
    */
   protected List<ItemProperty> getCollectionValuesFor(final Element el) throws ClassNotFoundException {
      return getCollectionValueElementsFor(el)
         .stream()
         .map(rethrowFunction(this::getItemPropertyFor))
         .collect(toList());
   }

   /**
    * Returns the collection value elements for a given {@link element} as a
    * {@link NodeList}
    * 
    * @param el The {@link Element}
    * @return The collection value elements as a {@link NodeList}
    */
   private List<Element> getCollectionValueElementsFor(Element el) {
      List<Element> collectionValues = new ArrayList<Element>();
      Elements list = el.getChildElements();
      for (int i = 0; i < list.size(); i++) {
         Element node = list.get(i);
         if (ExImportHelperService.COLLECTION_VALUE_ELEMENT.equals(node.getLocalName()))
            collectionValues.add((Element) node);
      }
      return collectionValues;
   }

   /**
    * Returns the exported item elements for a {@link Document} with a given
    * {@link Exporter} as a {@link NodeList}
    * 
    */
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

   /**
    * Checks wether a given {@link Element} is an exported item element or not
    * 
    * @param el The {@link Element}
    * @return True if the {@link Element} is an exporter item element; False
    *         otherwise
    */
   public boolean isExportedItemElement(Element el) {
      return ExImportHelperService.EXPORTED_ITEM_ELEMENT_NAME.equals(el.getLocalName());
   }

   /**
    * Validates the given exported item {@link Element}
    * 
    * @param el The exported item {@link Element}
    */
   protected void validateExportedItemElement(Element el) {
      if (!isExportedItemElement(el))
         throw new IllegalArgumentException(
               "The given element is not a valid exported item element but: " + el.getLocalName());
   }

   /**
    * Returns the ID attribute of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The ID attribute
    */
   public String getItemId(Element el) {
      return eiHelper.getIdAttribute(el);
   }

   /**
    * Returns the type attribute of the given {@link Element}
    * 
    * @param el The {@link Element}
    * @return The type attribute
    */
   public String getItemType(Element el) {
      return getAttribute(el, ExImportHelperService.TYPE_ATTRIBUTE);
   }

   /**
    * Returns the type attribute of the given {@link Element} as a {@link Class}
    * 
    * @param el The {@link Element}
    * @return The type attribute as a {@link Class}
    * @throws ClassNotFoundException
    */
   public Class<?> getItemTypeAsClass(Element el) throws ClassNotFoundException {
      String type = getItemType(el);
      return reflectionService.getClassForName(type);
   }

   /**
    * Returns the {@link Element}s property elements as a {@link NodeList}
    * 
    * @param el The {@link Element}
    * @return The property elements as a {@link NodeList}
    */
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

   /**
    * Checks wether the given {@link Element} has the given attribute or not
    * 
    * @param el        The {@link Element}
    * @param attribute The attribute
    * @return True if the {@link Element} as the given attribute; False otherwise
    */
   public boolean hasAttribute(Element el, String attribute) {
      /*
       * no need for namespaces as attributes do not inherited the default namespace
       */
      return null != el.getAttribute(attribute);
   }

   /**
    * Returns the {@link Element}s value of the given attribute
    * 
    * @param el        The {@link Element}
    * @param attribute The attribute
    * @return The value of the given attribute
    */
   public String getAttribute(Element el, String attribute) {
      /*
       * no need for namespaces as attributes do not inherited the default namespace
       */
      return el.getAttributeValue(attribute);
   }

   /**
    * Gets the {@link ExportedItem} identified by the given ID.
    * 
    * @param id The ID of the {@link ExportedItem}
    * @return The {@link ExportedItem} or <b>null</b>
    * @throws ClassNotFoundException
    */
   public ExportedItem getExportedItemById(ExportDataProvider dataProvider, String id) throws ClassNotFoundException {
      Node node = dataProvider.getElementById(id);
      ;
      if (null != node)
         return getExportedItemFor((Element) node, dataProvider);
      return null;
   }

   /**
    * Gets the {@link ExportedItem} identified by the given ID including enclosed
    * elements.
    * 
    * @param id The ID of the {@link ExportedItem}
    * @return The {@link ExportedItem} including enclosed elements or <b>null</b>
    * @throws ClassNotFoundException
    */
   public ExportedItem getExportedItemWithEnclosed(ExportDataProvider dataProvider, String id)
         throws ClassNotFoundException {
      Element element = dataProvider.getExportedItemWithEnclosed(id);
      if (null != element)
         return getExportedItemFor(element, dataProvider);
      return null;
   }

   /**
    * Gets the {@link Class} of the exporter type used for the enclosed elements of
    * the {@link Element} identified by the given ID.
    * 
    * @param id The ID of the exported element
    * @return The {@link Class} of the used exporter type or <b>null</b>
    * @throws ClassNotFoundException
    */
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

   /**
    * Returns the {@link EnclosedItemProperty} for the exported element identified
    * by the given ID.
    * 
    * @param id The ID of the exported Element
    * @return The {@link EnclosedItemProperty} of the exported element
    * @throws ClassNotFoundException
    */
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

}
