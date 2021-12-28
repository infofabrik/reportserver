package net.datenwerke.rs.crystal.client.crystal.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface CrystalMessages extends Messages {

   public final static CrystalMessages INSTANCE = GWT.create(CrystalMessages.class);

   String reportTypeName();

   String editReport();

   String rptfile();

   String fileName();

   String downloadReportToolbarButtonText();

   String parameterProposalBtn();

   String noProposalsFoundTitle();

   String noProposalsFoundText();

}
