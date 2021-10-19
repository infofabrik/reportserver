package net.datenwerke.eximport.hooks;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * Allows to help the {@link BasicObjectExporter} by providing custom exporters
 * for simple objects.
 * 
 *
 */
public interface BasicObjectExImporterHelperHook extends Hook {

   public boolean consumes(Class<?> type);

   public void export(ExportSupervisor exportSupervisor, Object value) throws XMLStreamException;

   public Object importData(ItemProperty property);

}
