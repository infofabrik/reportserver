package net.datenwerke.rs.tabletemplate.client.engines.velocity.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface VelocityTemplateMessages extends Messages {

   public static VelocityTemplateMessages INSTANCE = GWT.create(VelocityTemplateMessages.class);

   String templateTypeDescription();

}
