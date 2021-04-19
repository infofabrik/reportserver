package net.datenwerke.rs.core.client.i18tools.rpc;

import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("i18ntools")
public interface I18nToolsRpcService extends RemoteService {

	
	public String getDecimalSeparator();
	public void setDecimalSeparator(String separatorChar);
	FormatPatternsDto getFormatPatterns();
}
