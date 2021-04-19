package net.datenwerke.usermanager.ext.client.properties.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserPropertiesMessages extends Messages {
	
	public final static UserPropertiesMessages INSTANCE = GWT.create(UserPropertiesMessages.class);
	
	String header();
	String description();
	String gridNameHeader();
	String gridValueHeader();
	String keyIsNotUnique();
}
