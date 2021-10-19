package net.datenwerke.eximport;

import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import net.datenwerke.eximport.ex.Exporter;
import nu.xom.Element;
import nu.xom.XPathContext;

import com.google.inject.Inject;

/**
 * Helper service used during im and export.
 * 
 * @see ExportService
 * @see ImportService
 *
 */
public class ExImportHelperService {

   public static final String EXIMPORT_XML_NAMESPACE = "http://reportserver.datenwerke.net/eximport";
   public static final String EXIMPORT_XML_NAMESPACE_PREFIX = "rsexim";

   public static final String DOCUMENT_ROOT_ELEMENT = "reportServerExport";
   public static final String DOCUMENT_HEAD_ELEMENT = "head";
   public static final String DOCUMENT_HEAD_USER_ELEMENT = "user";
   public static final String DOCUMENT_HEAD_NAME_ELEMENT = "name";
   public static final String DOCUMENT_HEAD_DESCRIPTION_ELEMENT = "description";
   public static final String DOCUMENT_HEAD_DATE_ELEMENT = "date";

   public static final String DOCUMENT_DATA_ELEMENT = "data";

   public static final String XML_TRUE = "true";
   public static final String VALUE_ATTRIBUTE = "value";
   public static final String TYPE_ATTRIBUTE = "type";
   public static final String IS_NULL_ATTRIBUTE = "isNull";
   public static final String IS_ENCLOSED_ATTRIBUTE = "isEnclosed";
   public static final String NAME_ATTRIBUTE = "name";

   public static final String IS_COLLECTION_ATTRIBUTE = "isCollection";
   public static final String PROPERTY_TYPE = "propertyType";

   public static final String EXPORTER_TYPE = "exporterType";

   public static final String REFERENCE_ID = "referenceId";
   public static final String IS_OPTIONAL_REFERENCE = "isOptionalReference";

   public static final String COLLECTION_VALUE_ELEMENT = "collectionValue";

   public static final String EXPORTED_ITEM_ELEMENT_NAME = "exportedItem";
   public static final String EXPORTED_PROPERTY_ELEMENT_NAME = "itemProperty";
   public static final String EXPORTER_BASE_ELEMENT = "exporterData";
   public static final String DOCUMENT_HEAD_VERSION_ELEMENT = "reportServerVersion";

   public static Map<String, String> namespaceMap;
   static {
      namespaceMap = new HashMap<String, String>();
      namespaceMap.put(EXIMPORT_XML_NAMESPACE_PREFIX, EXIMPORT_XML_NAMESPACE);
      namespaceMap.put("xml", XMLConstants.XML_NS_URI);
   }

   private final ExImportIdService idService;

   @Inject
   public ExImportHelperService(ExImportIdService idService) {
      this.idService = idService;
   }

   public String getIdFor(Object object) {
      return idService.provideId(object);
   }

   /**
    * Sets the attribute <i>name</i> to the given <i>value</i>
    * 
    * @param xsw   The {@link XMLStreamWriter} holding the attribute
    * @param name  The name of the attribute
    * @param value The value of the attribute
    * @throws XMLStreamException
    */
   public void setAttribute(XMLStreamWriter xsw, String name, String value) throws XMLStreamException {
      xsw.writeAttribute(name, value == null ? "" : value);
   }

   /**
    * Adds an <i>entityId</i> to the attribute <b>xml:id</b> in the given
    * {@link XMLStreamWriter}
    * 
    * @param xsw      The {@link XMLStreamWriter} holding the <b>xml:id</b>
    *                 attribute
    * @param entityId The ID to add
    * @throws XMLStreamException
    */
   public void addIdAttribute(XMLStreamWriter xsw, String entityId) throws XMLStreamException {
      /* has to be xml:id */
      xsw.writeNamespace("xml", XMLConstants.XML_NS_URI);
      xsw.writeAttribute(XMLConstants.XML_NS_URI, "id", entityId);
   }

   /**
    * Returns the <b>id</b> attribute of the given {@link Element}
    * 
    * @param el The {@link Element} holding the attribute
    * @return The <b>id</b> attribute of the given {@link Element}
    */
   public String getIdAttribute(Element el) {
      /* has to be xml:id */
      return null != el.getAttribute("id", XMLConstants.XML_NS_URI)
            ? el.getAttribute("id", XMLConstants.XML_NS_URI).getValue()
            : null;
   }

   /**
    * Sets the <b>IS_NULL_ATTRIBUTE</b> attribute of the given
    * {@link XMLStreamWriter} to <b>XML_TRUE</b>
    * 
    * @param xsw The {@link XMLStreamWriter} to set the attribute on
    * @throws XMLStreamException
    */
   public void setIsNullAttribute(XMLStreamWriter xsw) throws XMLStreamException {
      xsw.writeAttribute(IS_NULL_ATTRIBUTE, XML_TRUE);
   }

   /**
    * Sets the <b>VALUE_ATTRIBUTE</b> attribute of the given
    * {@link XMLStreamWriter} to the given <i>value</i>
    * 
    * @param xsw   The {@link XMLStreamWriter} to set the attribute on
    * @param value The value to set
    * @throws XMLStreamException
    */
   public void setValueAttribute(XMLStreamWriter xsw, String value) throws XMLStreamException {
      xsw.writeAttribute(VALUE_ATTRIBUTE, value);
   }

   /**
    * Sets the <b>TYPE_ATTRIBUTE</b> attribute of the given {@link XMLStreamWriter}
    * to the given <i>type</i>
    * 
    * @param xsw  The {@link XMLStreamWriter} to set the attribute on
    * @param type The type to set
    * @throws XMLStreamException
    */
   public void setTypeAttribute(XMLStreamWriter xsw, Class<?> type) throws XMLStreamException {
      xsw.writeAttribute(TYPE_ATTRIBUTE, type.getName());
   }

   /**
    * Sets the <b>IS_COLLECTION_ATTRIBUTE</b> attribute of the given
    * {@link XMLStreamWriter} to <b>XML_TRUE</b> and the <b>PROPERTY_TYPE</b> to
    * <i>propertyType</i>.getName()
    * 
    * @param xsw          The {@link XMLStreamWriter} to set the attributes on
    * @param propertyType The type to set
    * @throws XMLStreamException
    */
   public void setIsCollectionAttribute(XMLStreamWriter xsw, Class<?> propertyType) throws XMLStreamException {
      xsw.writeAttribute(IS_COLLECTION_ATTRIBUTE, XML_TRUE);
      xsw.writeAttribute(PROPERTY_TYPE, propertyType.getName());
   }

   /**
    * Sets the following attributes to the corresponding values:
    * <ul>
    * <li><b>TYPE_ATTRIBUTE</b> &lt;- <i>type</i>.getName()</li>
    * <li><b>REFERENCE_ID</b> &lt;- <i>id</i></li>
    * <li><b>EXPORTER_TYPE</b> &lt;- <i>exporter</i>.getClass().getName()</li>
    * </ul>
    * 
    * @param xsw      The {@link XMLStreamWriter} to set the attributes on
    * @param id       The ID to set <b>REFERENCE_ID</b> to
    * @param exporter The {@link Exporter} to set <b>EXPORTER_TYPE</b> to
    * @throws XMLStreamException
    */
   public void setReferenceAttributes(XMLStreamWriter xsw, String id, Exporter exporter) throws XMLStreamException {
      setReferenceAttributes(xsw, id, exporter.getClass(), false);
   }

   /**
    * Sets the following attributes to the corresponding values:
    * <ul>
    * <li><b>TYPE_ATTRIBUTE</b> &lt;- <i>type</i>.getName()</li>
    * <li><b>REFERENCE_ID</b> &lt;- <i>id</i></li>
    * <li><b>EXPORTER_TYPE</b> &lt;- <i>exporter</i>.getClass().getName()</li>
    * </ul>
    * 
    * If <i>optional</i> is <b>true</b>:
    * <ul>
    * <li><b>IS_OPTIONAL_REFERENCE</b> &lt;- <b>XML_TRUE</b></li>
    * </ul>
    * 
    * @param xsw      The {@link XMLStreamWriter} to set the attributes on
    * @param id       The ID to set <b>REFERENCE_ID</b> to
    * @param exporter The {@link Exporter} to set <b>EXPORTER_TYPE</b> to
    * @param optional Whether to set <b>IS_OPTIONAL_REFERENCE</b> or not
    * @throws XMLStreamException
    */
   public void setReferenceAttributes(XMLStreamWriter xsw, String id, Exporter exporter, boolean optional)
         throws XMLStreamException {
      setReferenceAttributes(xsw, id, exporter.getClass(), optional);
   }

   /**
    * Sets the following attributes to the corresponding values:
    * <ul>
    * <li><b>TYPE_ATTRIBUTE</b> &lt;- <i>type</i>.getName()</li>
    * <li><b>REFERENCE_ID</b> &lt;- <i>id</i></li>
    * <li><b>EXPORTER_TYPE</b> &lt;- <i>exporterType</i>.getName()</li>
    * </ul>
    * 
    * If <i>optional</i> is <b>true</b>:
    * <ul>
    * <li><b>IS_OPTIONAL_REFERENCE</b> &lt;- <b>XML_TRUE</b></li>
    * </ul>
    * 
    * @param xsw          The {@link XMLStreamWriter} to set the attributes on
    * @param id           The ID to set <b>REFERENCE_ID</b> to
    * @param exporterType The type to set <b>EXPORTER_TYPE</b> to
    * @param optional     Whether to set <b>IS_OPTIONAL_REFERENCE</b> or not
    * @throws XMLStreamException
    */
   public void setReferenceAttributes(XMLStreamWriter xsw, String id, Class<?> exporterType, boolean optional)
         throws XMLStreamException {
      if (null == id || "".equals(id))
         throw new IllegalArgumentException("Cannot have an empty referenceId");
      xsw.writeAttribute(REFERENCE_ID, id);
      xsw.writeAttribute(EXPORTER_TYPE, exporterType.getName());
      if (optional)
         xsw.writeAttribute(IS_OPTIONAL_REFERENCE, XML_TRUE);
   }

   /**
    * Sets the <b>EXPORTER_TYPE</b> attribute of the given {@link XMLStreamWriter}
    * 
    * @param xsw      The {@link XMLStreamWriter} to set the attribute on
    * @param exporter The {@link Exporter} to set the <b>EXPORTER_TYPE</b>
    *                 attribute to
    * @throws XMLStreamException
    */
   public void setExporterType(XMLStreamWriter xsw, Exporter exporter) throws XMLStreamException {
      setExporterType(xsw, exporter.getClass());
   }

   /**
    * Sets the <b>EXPORTER_TYPE</b> attribute of the given {@link XMLStreamWriter}
    * 
    * @param xsw  The {@link XMLStreamWriter} to set the attribute on
    * @param type The type to set the <b>EXPORTER_TYPE</b> attribute to
    * @throws XMLStreamException
    */
   public void setExporterType(XMLStreamWriter xsw, Class<? extends Exporter> type) throws XMLStreamException {
      xsw.writeAttribute(EXPORTER_TYPE, type.getName());
   }

   /**
    * Sets the {@link XMLStreamWriter}s <b>EXPORTER_TYPE</b> and
    * <b>IS_ENCLOSED_ATTRIBUTE</b> attributes
    * 
    * @param xsw      The {@link XMLStreamWriter} to set the attributes on
    * @param exporter The {@link Exporter} to set the <b>EXPORTER_TYPE</b> to
    * @throws XMLStreamException
    */
   public void setEnclosedAttributes(XMLStreamWriter xsw, Exporter exporter) throws XMLStreamException {
      setExporterType(xsw, exporter);
      setAttribute(xsw, IS_ENCLOSED_ATTRIBUTE, XML_TRUE);
   }

   /**
    * Sets the <b>NAME_ATTRIBUTE</b> to the given <i>name</i>
    * 
    * @param xsw  The {@link XMLStreamWriter} to set the attribute on
    * @param name The name to set the <b>NAME_ATTRIBUTE</b> to
    * @throws XMLStreamException
    */
   public void setNameAttribute(XMLStreamWriter xsw, String name) throws XMLStreamException {
      setAttribute(xsw, NAME_ATTRIBUTE, name);
   }

   public void addNamespaces(XMLStreamWriter xsw) throws XMLStreamException {
      xsw.setPrefix("xml", XMLConstants.XML_NS_URI);
   }

   public void writeExImportNamespace(XMLStreamWriter xsw) throws XMLStreamException {
      xsw.writeDefaultNamespace(EXIMPORT_XML_NAMESPACE);
   }

   public Map getNamespaceMap() {
      return namespaceMap;
   }

   public String xqueryNamespaces() {
      return "declare namespace " + EXIMPORT_XML_NAMESPACE_PREFIX + " = \"" + EXIMPORT_XML_NAMESPACE + "\";\n";
   }

   public XPathContext xpathContext() {
      return new XPathContext(EXIMPORT_XML_NAMESPACE_PREFIX, EXIMPORT_XML_NAMESPACE);
   }
}
