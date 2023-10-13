package net.datenwerke.rs.dot.client.dot.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;


public interface DotMessages extends Messages {
   public final static DotMessages INSTANCE = GWT.create(DotMessages.class);
   
   String noFileData();
   String wrongContentType();
}


   