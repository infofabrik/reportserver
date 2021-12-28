package net.datenwerke.rs.core.client.i18tools.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

@RemoteServiceRelativePath("i18ntools")
public interface I18nToolsRpcService extends RemoteService {

   public String getDecimalSeparator();

   public void setDecimalSeparator(String separatorChar);

   FormatPatternsDto getFormatPatterns();
}
