package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Dto Decorator for {@link CommandResultTableDto}
 *
 */
public class CommandResultTableDtoDec extends CommandResultTableDto {


	private static final long serialVersionUID = 1L;

	public CommandResultTableDtoDec() {
		super();
	}

	public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
		/* double dispatch */
		displayHelper.format(this,builder);
	}

}
