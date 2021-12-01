package net.datenwerke.eximport.hooker;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHookImpl;
import net.datenwerke.eximport.obj.ComplexItemProperty;

import org.apache.commons.codec.binary.Base64;

public class ByteArrayExporterHelperHooker extends BasicObjectExImporterHelperHookImpl<ComplexItemProperty> {

   @Override
   public boolean consumes(Class<?> type) {
      return Byte[].class.equals(type) || byte[].class.equals(type);
   }

   @Override
   public void export(ExportSupervisor exportSupervisor, Object value) throws XMLStreamException {
      byte[] bValue = (byte[]) value;
      exportSupervisor.createCDataElement(new String(Base64.encodeBase64(bValue)));
   }

   @Override
   protected Object doImport(ComplexItemProperty property) {
      String text = property.getElement().getValue();
      return Base64.decodeBase64(text.getBytes());
   }

}
