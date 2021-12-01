package net.datenwerke.rs.computedcolumns.client.computedcolumns.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ComputedColumnsMessages extends Messages {

	public static final ComputedColumnsMessages INSTANCE = GWT.create(ComputedColumnsMessages.class);
	
	String computedColumnsHeading();

	String removeAllConfirmHeading();
	String removeAllConfirmText();
	
	String addColumn();
	
	String expressionLabel();
	String editColumnHeading();
	String validateData();
	
	String newName();
	String newDescription();
	String expressionNotEmpty();
	
	String duplicateColumnLabel();
	
	String selectColBtnLabel();

	String importColumnConflictTitle();
	String importColumnConflictMsg(String name);
	
	
}