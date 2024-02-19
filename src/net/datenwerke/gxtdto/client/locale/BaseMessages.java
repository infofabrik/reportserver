package net.datenwerke.gxtdto.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface BaseMessages extends Messages {

   public final static BaseMessages INSTANCE = GWT.create(BaseMessages.class);

   /* Buttons */
   String submit();

   String ok();

   String apply();

   String cancel();

   String close();

   String add();

   String insert();

   String yes();

   String no();

   String edit();

   String save();

   String saveAs();

   String remove();

   String removeAll();

   String expandAll();

   String collapseAll();

   String reload();

   String refresh();

   String previewRefresh();

   String rename();

   String view();

   /* generic */
   String properties();

   String next();

   String prev();

   /* labels */
   String propertyName();

   String propertyDescription();

   String width();

   String height();

   String value();

   String name();

   String type();

   String description();

   String information();

   String createdOn();

   String folder();

   String id();

   String changedOn();

   String key();

   /* messages */
   String loadingMsg();

   String storingMsg();

   String waitMsg();

   String busyMsg();

   String progressMsg();

   String confirmDeleteMsg(String displayTitle);

   String confirmDeleteTitle();

   String needForcefulDeleteMsg(String message);

   String needForcefulDeleteTitle();

   String changesApplied();

   String open();

   String error();

   String displayErrorDetails();

   String unknown();

   /* tabs */
   String closeOtherTabsText();

   String closeTabText();

   String closeAllsTabText();

   /* error messages */
   String alphaNumericErrorMsg();

   String requestCanceled();

   String selectValueLabel(String name);

   String warning();

   String unsavedChanges();

   String encounteredError();

   String violatedSecurity();

   String menu();

   String unnamed();

   String revert();

   String confirmDeleteManyMsg(int size);

   String confirmDeleteAllMsg();

   String infoLabel();

   String helpLabel();

   String copy();

   String paste();

   String uploadError();

   String uploadErrorDetail(String detail);

   String validUploadTarget();

   String download();

   String duplicate();

   String lastModified();

   String informationOn(String displayTitle);

   String confirmPromptDescription(String operation);

   String gotoLabel();

   String test();

   String time();

   String configuration();

   String host();

   String port();

   String username();

   String password();

   String clearPassword();
   
   String path();

   String invalidKey();

   String restUrl();

   String apiKey();

   String clearApikey();
   
   String reportServerVersion();

   String schemaVersion();
   
   String tools();
   
   String duplicateKey();

   String createRandomKey();
}
