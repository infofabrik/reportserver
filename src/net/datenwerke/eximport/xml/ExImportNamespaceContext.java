package net.datenwerke.eximport.xml;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import net.datenwerke.eximport.ExImportHelperService;

public class ExImportNamespaceContext implements NamespaceContext {

   @Override
   public String getNamespaceURI(String prefix) {
      if (prefix == null)
         throw new NullPointerException("Null prefix");
      else if (ExImportHelperService.EXIMPORT_XML_NAMESPACE_PREFIX.equals(prefix))
         return ExImportHelperService.EXIMPORT_XML_NAMESPACE;
      else if ("xml".equals(prefix))
         return XMLConstants.XML_NS_URI;
      else if ("null".equals(prefix))
         return XMLConstants.NULL_NS_URI;

      return ExImportHelperService.EXIMPORT_XML_NAMESPACE;
   }

   @Override
   public String getPrefix(String namespaceURI) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Iterator getPrefixes(String namespaceURI) {
      throw new UnsupportedOperationException();
   }

}
