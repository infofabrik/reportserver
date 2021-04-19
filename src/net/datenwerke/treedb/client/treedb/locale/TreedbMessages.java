package net.datenwerke.treedb.client.treedb.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TreedbMessages extends Messages {
	
	public final static TreedbMessages INSTANCE = GWT.create(TreedbMessages.class);
	
	String deleted();
	
	String duplicated();
	String duplicateText();
	String infoMenuLabel();
	String inserted();
	String moved();
	
}
