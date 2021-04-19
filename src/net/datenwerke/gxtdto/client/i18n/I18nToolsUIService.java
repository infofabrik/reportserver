package net.datenwerke.gxtdto.client.i18n;

public interface I18nToolsUIService {

	String getUserDecimalSeparator();

	String translateNumberFromUserToSystem(String number);

	String translateNumberFromSystemToUser(String number);
}
