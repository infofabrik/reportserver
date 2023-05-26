package net.datenwerke.rs.pkg.client.pkg.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface PkgMessages extends Messages {

   public static final PkgMessages INSTANCE = GWT.create(PkgMessages.class);

   String rspUploadedTitle();

   String rspUploadedMsg();
   
   String executingRspMsg();

   String rspExecution();

   String rspExecutionResult();

}
