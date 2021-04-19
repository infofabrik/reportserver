package net.datenwerke.rs.legacysaiku.client.saiku.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SaikuMessages extends Messages {

	public final static SaikuMessages INSTANCE = GWT.create(SaikuMessages.class);
	
	String export2chartHtml();
	
	String reportTypeName();

	String editReport();
	
	String databaseDatasourceTypeName();

	String editDataSource();

	String propertySchema();

	String propertyProperties();

	String cubeLabel();

	String cubePreview();

	String chartType();

	String reportDadgetFormatChart(String string);

	String enableMdx();
	String fieldLabelEnableMdx();


}
