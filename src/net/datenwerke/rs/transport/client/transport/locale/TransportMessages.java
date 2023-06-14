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
}