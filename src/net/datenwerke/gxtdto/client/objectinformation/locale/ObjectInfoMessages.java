package net.datenwerke.gxtdto.client.objectinformation.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ObjectInfoMessages extends Messages {

	public final static ObjectInfoMessages INSTANCE = GWT.create(ObjectInfoMessages.class);

	String general();

	String infoOn();
	

}
