package net.datenwerke.rs.dashboard.client.dashboard.dadgets.i;

import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;

public interface LibrarySpeficDrawer {

	public void drawForLibrary(LibraryDadgetDto libraryDadget, DadgetDto dadgetToDraw, final DadgetPanel panel);
}
