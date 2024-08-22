package net.datenwerke.rs.transport.client.transport.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TransportMessages extends Messages {

   public final static TransportMessages INSTANCE = GWT.create(TransportMessages.class);

   String transports();

   String transportPermissionModuleDescription();

   String importWhereTo();

   String importMainPropertiesDescription();

   String importMainPropertiesHeadline();

   String importConfigFailureNoParent();

   String editTransport();

   String closed();

   String createdByFirstname();

   String createdByLastname();

   String createdByUsername();

   String createdByEmail();
   
   String transport();
   
   String addToTransportText();
   
   String addedToTransport();

   String rpull();

   String rpullOk();

   String status();

   String serverIdLabel();

   String reportServerVersion();

   String schemaVersion();

   String importedOn();

   String importedBy();

   String appliedOn();

   String appliedBy();

   String appliedLog();

   String applyTransport();

   String applyFailed();

   String success();

   String applySuccess();

   String pleaseWait();

   String applyingTitle();

   String applyingProgressMessage();

   String viewNavigationTitle();

   String dialogDescription();

   String shortKeyLabel();

   String createdOnLabel();

   String descriptionLabel();
   
   String checkApplyPreconditions();

   String analysisResults();
   
   String result();

   String errorMessage();
   
   String analyzing();

   String deleteSuccess();

   String deleteAllSelectedObjects();

   String confirmDeleteAllMsg();

   String objects();
   
   String duplicateAsOpen();
   
   String transportManagementPermissionModuleDescription();

   String close();
   
   String selectTransport();
   
}