package net.datenwerke.gxtdto.client.forms.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FormsMessages extends Messages {

   public final static FormsMessages INSTANCE = GWT.create(FormsMessages.class);

   String addAll();

   String defaultValues();

   String finish();

   String invalidConfigMessage();

   String invalidConfigTitle();

   String invalidDecimal();

   String invalidDouble();

   String invalidFloatingpoint();

   String invalidInteger();

   String invalidTime();

   String noDataAvailable();

   String noDataSelected();

   String onlySelectable();

   String select();

   String selectedItems();

   String validationFailedMessage();

   String validationFailedTitle();

   String selectedValues();

   String fieldMustBeAlphaNumeric();

   String regexDefaultErrorMessage(String regex);

   String invalidBoolean();

   String dropFilesHere();
}
