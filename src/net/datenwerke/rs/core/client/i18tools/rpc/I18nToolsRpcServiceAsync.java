package net.datenwerke.rs.core.client.i18tools.rpc;

import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface I18nToolsRpcServiceAsync {

	void getDecimalSeparator(AsyncCallback<String> callback);

	void setDecimalSeparator(String separatorChar, AsyncCallback<Void> callback);

	void getFormatPatterns(AsyncCallback<FormatPatternsDto> callback);

}
