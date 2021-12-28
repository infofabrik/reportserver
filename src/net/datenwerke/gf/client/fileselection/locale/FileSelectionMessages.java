package net.datenwerke.gf.client.fileselection.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FileSelectionMessages extends Messages {

   public final static FileSelectionMessages INSTANCE = GWT.create(FileSelectionMessages.class);

   String maxFilesReachedTitle();

   String maxFilesReachedMessage(int maxfiles);

   String noFileUploaded();

}
