package net.datenwerke.eximport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import nu.xom.Attribute.Type;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.NodeFactory;
import nu.xom.Nodes;
import nu.xom.Text;

public class ExportDataProviderImpl implements ExportDataProvider {

   private File input;
   private byte[] byteInput;

   private Map<String, String> elementMap = new HashMap<String, String>();
   private Map<String, String> exporterTypeMap = new HashMap<String, String>();
   private Map<String, String> enclosedMap = new HashMap<String, String>();

   public ExportDataProviderImpl(File input) {
      this.input = input;
      init();
   }

   public ExportDataProviderImpl(byte[] data) {
      this.byteInput = data;
      init();
   }

   private void init() {
      final NodeFactory nodeFactory = new NodeFactory() {
         private boolean isExportElement = false;
         private boolean isExporterType = false;

         private boolean inExportElement = false;

         private String id = null;
         private String exporter = null;
         private Nodes empty = new Nodes();

         @Override
         public Element startMakingElement(String name, String namespace) {
            isExporterType = ExImportHelperService.EXPORTER_BASE_ELEMENT.equals(name);
            isExportElement = ExImportHelperService.EXPORTED_ITEM_ELEMENT_NAME.equals(name);
            if (isExportElement)
               inExportElement = true;
            return super.startMakingElement(name, namespace);
         }

         @Override
         public Nodes makeAttribute(String name, String URI, String value, Type type) {
            if (isExportElement && "xml:id".equals(name))
               id = value;
            if (isExporterType && ExImportHelperService.EXPORTER_TYPE.equals(name))
               exporter = value;
            return super.makeAttribute(name, URI, value, type);
         }

         @Override
         public Nodes finishMakingElement(Element element) {
            if (element.getChildCount() == 1) {
               Node child = element.getChild(0);
               if (child instanceof Text) {
                  String orignalText = child.getValue();
                  element.removeChildren();
                  element.appendChild(new Text(orignalText.trim()));
               }
            }
            if (element.getQualifiedName().equals(ExImportHelperService.EXPORTED_PROPERTY_ELEMENT_NAME)
                  || element.getQualifiedName().equals(ExImportHelperService.COLLECTION_VALUE_ELEMENT)) {
               String innerExporter = element.getAttributeValue(ExImportHelperService.EXPORTER_TYPE);
               String innerId = element.getAttributeValue("id", XMLConstants.XML_NS_URI);
               if (null != innerExporter && null != innerId) {
                  exporterTypeMap.put(innerId, innerExporter);
                  elementMap.put(innerId, element.toXML());
                  enclosedMap.put(innerId, id);
               }
            } else if (element.getQualifiedName().equals(ExImportHelperService.EXPORTED_ITEM_ELEMENT_NAME)) {
               String xml = element.toXML();
               elementMap.put(id, xml);
               exporterTypeMap.put(id, exporter);

               isExportElement = false;
               inExportElement = false;
               id = null;
            }
            if (inExportElement || element.getParent() instanceof Document)
               return super.finishMakingElement(element);
            return empty;
         }

         @Override
         public Nodes makeDocType(String rootElementName, String publicID, String systemID) {
            return empty;
         }

         @Override
         public Nodes makeText(String data) {
            return super.makeText(data);
         }

      };

      try {
         XMLReader xmlReader = XMLReaderFactory.createXMLReader();
         // https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html
         xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
         // This may not be strictly required as DTDs shouldn't be allowed at all, per
         // previous line.
         xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
         xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
         xmlReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

         Builder builder = new Builder(xmlReader, false, nodeFactory);
         builder.build(getXmlStream());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

   }

   @Override
   public InputStream getXmlStream() {
      if (null != input) {
         try {
            return new FileInputStream(input);
         } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
         }
      }
      return new ByteArrayInputStream(byteInput);
   }

   @Override
   public Element getElementById(String id) {
      if (elementMap.containsKey(id)) {
         try {
            String xml = elementMap.get(id);
            Element val = (Element) new Builder().build(new StringReader(xml)).getRootElement();
            return val;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      } else
         throw new IllegalArgumentException("no idea who " + id + "is.");
   }

   @Override
   public String getExportertTypeById(String id) {
      if (!exporterTypeMap.containsKey(id))
         throw new IllegalArgumentException("No idea what exporter to use for " + id);
      return exporterTypeMap.get(id);
   }

   @Override
   public Element getExportedItemWithEnclosed(String id) {
      return getElementById(enclosedMap.get(id));
   }

}
