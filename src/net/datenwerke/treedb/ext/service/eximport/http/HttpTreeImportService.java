package net.datenwerke.treedb.ext.service.eximport.http;

import java.util.List;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface HttpTreeImportService {

   List<ImportTreeModel> loadTreeDto(Class<? extends Exporter> exporterType);

}
