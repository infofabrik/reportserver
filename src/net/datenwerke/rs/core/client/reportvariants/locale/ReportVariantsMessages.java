package net.datenwerke.rs.core.client.reportvariants.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportVariantsMessages extends Messages {

   public final static ReportVariantsMessages INSTANCE = GWT.create(ReportVariantsMessages.class);

   String header();

   String description();
   
   String targetReport();

   String moveVariantToReport();
}