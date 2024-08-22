package net.datenwerke.rs.terminal.client.terminal.ui;

import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;

public class TerminalContainer extends BoxLayoutContainer {

   @Override
   protected void doLayout() {
      // don't do anything. HBoxLayoutContainer uses e.g. absolute positions, so we
      // can't use flex.
   }

}
