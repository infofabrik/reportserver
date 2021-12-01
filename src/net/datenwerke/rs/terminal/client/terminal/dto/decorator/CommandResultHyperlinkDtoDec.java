package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Dto Decorator for {@link CommandResultHyperlinkDto}
 *
 */
public class CommandResultHyperlinkDtoDec extends CommandResultHyperlinkDto {


	private static final long serialVersionUID = 1L;

	public CommandResultHyperlinkDtoDec() {
		super();
	}

	public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
		/* double dispatch */
		displayHelper.format(this,builder);
	}

}
