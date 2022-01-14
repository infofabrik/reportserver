package net.datenwerke.gf.client.juel;

import com.google.inject.ImplementedBy;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;

@ImplementedBy(JuelUiServiceImpl.class)
public interface JuelUiService {

   void evaluateExpression(String expression, RsAsyncCallback<JuelResultDto> callback);

}
