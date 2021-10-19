package net.datenwerke.eximport;

import java.io.InputStream;

import nu.xom.Element;

public interface ExportDataProvider {

   InputStream getXmlStream();

   Element getElementById(String id);

   String getExportertTypeById(String id);

   Element getExportedItemWithEnclosed(String id);

}