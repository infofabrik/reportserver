package net.datenwerke.eximport.hooker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHookImpl;
import net.datenwerke.eximport.obj.SimpleItemProperty;

import com.google.inject.Inject;

/**
 * Handles Dates.
 *
 */
public class DateExporterHelperHooker extends BasicObjectExImporterHelperHookImpl<SimpleItemProperty> {

   private final ExImportHelperService eiHelper;

   @Inject
   public DateExporterHelperHooker(ExImportHelperService eiHelper) {

      this.eiHelper = eiHelper;
   }

   @Override
   public boolean consumes(Class<?> type) {
      return (null != type && Date.class.isAssignableFrom(type));
   }

   @Override
   public void export(ExportSupervisor exportSupervisor, Object value) throws XMLStreamException {
      eiHelper.setValueAttribute(exportSupervisor.getXmlStream(),
            (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z").format(value)));
   }

   @Override
   public Object doImport(SimpleItemProperty property) {
      if ("".equals(property.getValue()))
         return null;

      try {
         return (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z")).parseObject(property.getValue());
      } catch (ParseException e) {
         throw new ImportException("old value: " + property.getValue(), e);
      }
   }

}
