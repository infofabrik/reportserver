package net.datenwerke.eximport.hooker;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.text.StringEscapeUtils;

import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHookImpl;
import net.datenwerke.eximport.obj.ComplexItemProperty;

/**
 * Handles Dates.
 *
 */
public class StringExporterHelperHooker extends BasicObjectExImporterHelperHookImpl<ComplexItemProperty> {

   @Override
   public boolean consumes(Class<?> type) {
      return (null != type && String.class.isAssignableFrom(type));
   }

   @Override
   public void export(ExportSupervisor exportSupervisor, Object value) throws XMLStreamException {
      exportSupervisor.createCDataElement(StringEscapeUtils.escapeXml10(value.toString()));
   }

   @Override
   public Object doImport(ComplexItemProperty property) {
      String text = StringEscapeUtils.unescapeXml(property.getElement().getValue());
      return text;
   }
}
