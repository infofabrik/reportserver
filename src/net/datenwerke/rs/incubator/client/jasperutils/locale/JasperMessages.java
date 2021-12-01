package net.datenwerke.rs.incubator.client.jasperutils.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JasperMessages extends Messages {

	public final static JasperMessages INSTANCE = GWT.create(JasperMessages.class);
	
	String parameterProposalBtn();

	String noProposalsFoundTitle();

	String noProposalsFoundText();

	String name();

	String key();

	String proposal();

	String windowTitle();

}
