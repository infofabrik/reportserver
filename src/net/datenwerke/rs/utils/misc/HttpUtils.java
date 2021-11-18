package net.datenwerke.rs.utils.misc;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.safehtml.shared.UriUtils;

import net.datenwerke.rs.core.service.RsCoreModule;
import net.datenwerke.rs.utils.filename.FileNameService;

public class HttpUtils {
	
	public final static String CONTENT_DISPOSITION = "Content-Disposition";

	private final Provider<FileNameService> fileNameServiceProvider;
	
	@Inject
	public HttpUtils(Provider<FileNameService> fileNameServiceProvider) {
		this.fileNameServiceProvider = fileNameServiceProvider;
	}
	
	public String makeContentDispositionHeader(boolean download, String filename) {
		String cd = download ? "attachment" : "inline";

		String filenameToUse = null == filename ? RsCoreModule.UNNAMED_FIELD: filename;
		String sanitizedfilename = fileNameServiceProvider.get().sanitizeFileName(filenameToUse);
		String strictfileName = fileNameServiceProvider.get().sanitizeFileNameStrict(filenameToUse);
		
		return cd + "; filename=\"" + strictfileName  +"\"; filename*=UTF-8''" + replaceChars(UriUtils.encode(sanitizedfilename)) + "";
	}
	
	/* Replace the punctuation and punctuation characters and URL component delimiter characters that are not replaced
	 * as shown here: http://www.gwtproject.org/javadoc/latest/com/google/gwt/http/client/URL.html#encode-java.lang.String-
	 */
	private String replaceChars(String toEncode) {
		return toEncode.replace("-", "%2D").replace("_", "%5F").replace(".", "%2E")
				.replace("!", "%21").replace("~", "%7E").replace("*", "%2A").replace("'", "%27")
				.replace("(", "%28").replace(")", "%29")
				.replace(";", "%3B").replace("/", "%2F").replace("?", "%3F").replace(":", "%3A")
				.replace("&", "%26").replace("=", "%3D").replace("+", "%2B").replace("$", "%24")
				.replace(",", "%2C").replace("#", "%23");
	}
}
