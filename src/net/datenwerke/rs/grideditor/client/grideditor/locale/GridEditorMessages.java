package net.datenwerke.rs.grideditor.client.grideditor.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface GridEditorMessages extends Messages{

	public final static GridEditorMessages INSTANCE = GWT.create(GridEditorMessages.class);
	
	String reportTypeName();

	String editReport();

	String script();
	
	String arguments();

	String setNull();

	String discardChangesTitle();

	String discardChangesMsg();

	String clearFilters();

	String editRecordHeading();

	String rollbackRecord();


}
