package net.datenwerke.rs.terminal.client.terminal.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TerminalMessages extends Messages {

   public final static TerminalMessages INSTANCE = GWT.create(TerminalMessages.class);

   String terminalWindowHeading();

   String init();

   String commandNotFound(String value);

   String adminLabel();

   String permissionModuleDescription();
}
