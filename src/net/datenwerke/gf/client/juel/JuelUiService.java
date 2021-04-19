package net.datenwerke.gf.client.juel;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;

import com.google.inject.ImplementedBy;

@ImplementedBy(JuelUiServiceImpl.class)
public interface JuelUiService {

	void evaluateExpression(String expression,
			RsAsyncCallback<JuelResultDto> callback);

}
