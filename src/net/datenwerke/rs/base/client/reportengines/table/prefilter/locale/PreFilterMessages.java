package net.datenwerke.rs.base.client.reportengines.table.prefilter.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface PreFilterMessages extends Messages {

	public static final PreFilterMessages INSTANCE = GWT.create(PreFilterMessages.class);
	
	String preFilterHeading();
	
	String andBlockName();
	String orBlockName();
	
	String addBlockLabel();
	
	String addFilterLabel();
	
	String removeAllConfirmHeading();
	String removeAllConfirmText();
	
	String removeSelectedConfirmHeading();
	String removeSelectedConfirmText();

	
	String noBlockSelectedText();
	
	String columnFilterHeadline();
	
	String binaryColumnFilterHeadline();
	
	String binaryColumnFilterColumnALabel();
	String binaryColumnFilterColumnBLabel();
	String binaryColumnFilterOperatorLabel();

	String infoTitleColumnToPrefilter();
	String infoTextColumnToPrefilter();

	String toggleAndOrBtnLabel();

	
}