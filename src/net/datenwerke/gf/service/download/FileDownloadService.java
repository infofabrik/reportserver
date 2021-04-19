package net.datenwerke.gf.service.download;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface FileDownloadService {

	void processDownload(String id, String handler,
			Map<String, String> metadata, HttpServletResponse response);

}
