package net.datenwerke.rs.passwordpolicy.client.accountinhibition.rpc;

import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionConfiguration;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.dto.InhibitionState;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountInhibitionRpcServiceAsync {

	void getInhibitionState(UserDto user, AsyncCallback<InhibitionState> callback);

	void applyAccountInhibitionConfiguration(AccountInhibitionConfiguration accountInhibitionConfiguration, AsyncCallback<Void> callback);

	void getAccountInhibitionConfiguration(UserDto user,
			AsyncCallback<AccountInhibitionConfiguration> callback);


}
