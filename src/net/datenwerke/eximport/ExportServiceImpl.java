package net.datenwerke.eximport;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.ExportSupervisorFactory;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.service.ExportOptions;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.reportmanager.ReportExportOptions;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
public class ExportServiceImpl implements ExportService {

   private final ExImportHelperService eiHelper;
   private final ExportSupervisorFactory exportSupervisorFactory;
   private final HookHandlerService hookHandler;

   @Inject
   public ExportServiceImpl(ExImportHelperService eiHelper, ExportSupervisorFactory exportSupervisorFactory,
         HookHandlerService hookHandler) {

      /* store objects */
      this.eiHelper = eiHelper;
      this.exportSupervisorFactory = exportSupervisorFactory;
      this.hookHandler = hookHandler;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.eximport.ExportService#export(net.datenwerke.eximport.ex.
    * ExportConfig)
    */
   @Override
   public String export(ExportConfig config) {
      try {
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         exportAsStream(config, os);

         return os.toString();
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * net.datenwerke.eximport.ExportService#exportIndent(net.datenwerke.eximport.ex
    * .ExportConfig)
    */
   @Override
   public String exportIndent(ExportConfig config) {
      try {
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         exportAsStream(config, os);

         TransformerFactory factory = TransformerFactory.newInstance();

         Transformer transformer = factory.newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");

         ByteArrayOutputStream bout = new ByteArrayOutputStream();
         transformer.transform(new StreamSource(new ByteArrayInputStream(os.toByteArray())), new StreamResult(bout));

         return bout.toString();
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public void exportAsStream(ExportConfig config, OutputStream os) throws XMLStreamException {
      XMLOutputFactory xmlOf = XMLOutputFactory.newInstance();
      XMLStreamWriter xsw = xmlOf.createXMLStreamWriter(os, "UTF-8");

      xsw.writeStartDocument();

      eiHelper.addNamespaces(xsw);

      xsw.writeStartElement(ExImportHelperService.DOCUMENT_ROOT_ELEMENT);
      eiHelper.writeExImportNamespace(xsw);

      /* head */
      xsw.writeStartElement(ExImportHelperService.DOCUMENT_HEAD_ELEMENT);

      xsw.writeStartElement(ExImportHelperService.DOCUMENT_HEAD_NAME_ELEMENT);
      xsw.writeCharacters(config.getName());
      xsw.writeEndElement();

      xsw.writeStartElement(ExImportHelperService.DOCUMENT_HEAD_DESCRIPTION_ELEMENT);
      xsw.writeCharacters(config.getDescription());
      xsw.writeEndElement();

      xsw.writeStartElement(ExImportHelperService.DOCUMENT_HEAD_DATE_ELEMENT);
      xsw.writeCharacters((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z")).format(config.getDate()));
      xsw.writeEndElement();

      xsw.writeStartElement(ExImportHelperService.DOCUMENT_HEAD_VERSION_ELEMENT);
      xsw.writeEndElement();

      xsw.writeEndElement();
      /* end head */

      /* main document */
      List<Exporter> exporters = getExporters();

      /* create supervisor and start actual export operation */
      ExportSupervisor supervisor = exportSupervisorFactory.create(config, exporters, xsw);
      supervisor.export();

      /* close doc */
      xsw.writeEndElement();

      xsw.flush();
      xsw.close();
   }

   private List<Exporter> getExporters() {
      return hookHandler.getHookers(ExporterProviderHook.class)
            .stream()
            .map(ExporterProviderHook::getObject)
            .collect(toList());
   }

   @Override
   public Collection<String> getExporterIds(Collection<Class<? extends Exporter>> exporterTypes) {
      List<String> ids = new ArrayList<>();

      for (Class<?> type : exporterTypes) {
         for (Exporter exporter : getExporters()) {
            if (type.equals(exporter.getClass())) {
               ids.add(exporter.getExporterId());
               break;
            }
         }
      }

      return ids;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.eximport.ExportService#getExporterFor(java.lang.Class)
    */
   @Override
   public Exporter getExporterFor(final Class<? extends Exporter> exporterType) {
      return getExporters()
            .stream()
            .filter(exporter -> exporterType.equals(exporter.getClass()))
            .findAny()
            .orElse(null);
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.eximport.ExportService#getExporterFor(java.lang.Object)
    */
   @Override
   public Exporter getExporterFor(final Object object) {
      return getExporters()
            .stream()
            .filter(exporter -> exporter.consumes(object))
            .findAny()
            .orElse(null);
   }

   @Override
   public Optional<ExportConfig> configureExport(final AbstractNode<?> node, final boolean includeVariants) {
      return hookHandler.getHookers(ExportConfigHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(node))
            .map(hooker -> { 
               ExportOptions exportOptions = null;
               if (node instanceof AbstractReportManagerNode) {
                  exportOptions = new ReportExportOptions() {
                     @Override
                     public boolean includeVariants() {
                        return includeVariants;
                     }
                  };
               } else {
                  exportOptions = new ExportOptions() {};
               }
               return hooker.configure(node, exportOptions);
            })
            .findAny();
   }

}
