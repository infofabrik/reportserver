package net.datenwerke.rs.core.client.i18tools;

import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;

import com.google.inject.Singleton;

@Singleton
public class I18nToolsUiServiceImpl implements I18nToolsUIService {

	private String userDecimalSeparator;

	public void setUserDecimalSeparator(String separator) {
		this.userDecimalSeparator = separator;
	}
	
	@Override
	public String getUserDecimalSeparator() {
		return userDecimalSeparator;
	}

	@Override
	public String translateNumberFromUserToSystem(String number) {
		if(null == number)
			return null;
		if(null == userDecimalSeparator)
			return number;
		
		return number.replace(getUserDecimalSeparator(), getSystemDecimalSeparator());
	}
	
	@Override
	public String translateNumberFromSystemToUser(String number) {
		if(null == number)
			return null;
		if(null == userDecimalSeparator)
			return number;
		
		return number.replace(getSystemDecimalSeparator(),getUserDecimalSeparator());
	}

	public String getSystemDecimalSeparator() {
		return ".";
	}

}
