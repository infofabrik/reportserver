package net.datenwerke.rs.base.client.reportengines.table.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TableMessages extends Messages {

	public final static TableMessages INSTANCE = GWT.create(TableMessages.class);
	
	String reportTypeName();

	String rowPreviewHeader();

	String columnWidth();

	String setColumnWidthMsg();

	String allColumnWidth();

	String optimalColumnWidth();

	String cubifyLabel();

	String cubifyExplanation();

	String cubeConfigAspectHeader();

	String selectMeasures();



}
