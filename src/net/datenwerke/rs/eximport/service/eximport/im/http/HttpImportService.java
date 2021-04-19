package net.datenwerke.rs.eximport.service.eximport.im.http;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.exceptions.InvalidImportDocumentException;
import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;


public interface HttpImportService {

	boolean hasCurrentConfig();

	HttpImportConfiguration getCurrentConfig();

	void invalidateCurrentConfig();

	HttpImportConfiguration createNewConfig();

	void setData(String xmldata) throws InvalidImportDocumentException;

	Collection<String> getInvolvedExporterIds();

	void resetImportConfig();

	void addItemConfig(ImportItemConfig nodeConfig);

	void configureImport(Map<String, ImportConfigDto> configMap);

	List<ExportedItem> getExportedItemsFor(
			Class<? extends Exporter> exporterType) throws ClassNotFoundException;

	void runPostProcess(Map<String, ImportPostProcessConfigDto> configMap,
			ImportResult result);

}
