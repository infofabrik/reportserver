package net.datenwerke.eximport.hooker;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.hooks.BasicObjectExImporterHelperHookImpl;
import net.datenwerke.eximport.obj.SimpleItemProperty;
import net.datenwerke.rs.utils.reflection.ReflectionService;

import com.google.inject.Inject;

/**
 * Handles enums.
 *
 */
public class EnumExporterHelperHooker extends BasicObjectExImporterHelperHookImpl<SimpleItemProperty> {

   private final ReflectionService reflectionService;
   private final ExImportHelperService eiHelper;

   @Inject
   public EnumExporterHelperHooker(ExImportHelperService eiHelper, ReflectionService reflectionService) {

      this.eiHelper = eiHelper;
      this.reflectionService = reflectionService;
   }

   @Override
   public boolean consumes(Class<?> type) {
      return (null != type && type.isEnum());
   }

   @Override
   public void export(ExportSupervisor exportSupervisor, Object value) throws XMLStreamException {
      eiHelper.setValueAttribute(exportSupervisor.getXmlStream(), value.toString());
   }

   @Override
   public Object doImport(SimpleItemProperty property) {
      Object enumObject = reflectionService.getEnumByString(property.getType(), property.getValue());
      return enumObject;
   }
}
