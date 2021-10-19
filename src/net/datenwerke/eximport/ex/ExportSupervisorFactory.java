package net.datenwerke.eximport.ex;

import java.util.List;

import javax.xml.stream.XMLStreamWriter;

public interface ExportSupervisorFactory {

   public ExportSupervisor create(ExportConfig config, List<Exporter> exporters, XMLStreamWriter xsw);
}
