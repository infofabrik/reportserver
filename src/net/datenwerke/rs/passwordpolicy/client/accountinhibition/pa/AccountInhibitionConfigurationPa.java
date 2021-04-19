package net.datenwerke.rs.passwordpolicy.client.accountinhibition.pa;

import java.util.Date;

import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionConfiguration;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface AccountInhibitionConfigurationPa extends PropertyAccess<AccountInhibitionConfiguration> {
	
	public static final AccountInhibitionConfigurationPa INSTANCE = GWT.create(AccountInhibitionConfigurationPa.class);
	
	public ValueProvider<AccountInhibitionConfiguration, Boolean> inhibitionState();
	public ValueProvider<AccountInhibitionConfiguration, Date> expirationDate();
	public ValueProvider<AccountInhibitionConfiguration, Long> userId();

}
