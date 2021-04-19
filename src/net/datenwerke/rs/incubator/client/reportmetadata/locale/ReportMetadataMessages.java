package net.datenwerke.rs.incubator.client.reportmetadata.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportMetadataMessages extends Messages {
	
	public final static ReportMetadataMessages INSTANCE = GWT.create(ReportMetadataMessages.class);
	
	String header();
	String description();
	String gridNameHeader();
	String gridValueHeader();
}
