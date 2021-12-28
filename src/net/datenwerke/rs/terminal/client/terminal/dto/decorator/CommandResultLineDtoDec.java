package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

/**
 * Dto Decorator for {@link CommandResultLineDto}
 *
 */
public class CommandResultLineDtoDec extends CommandResultLineDto {


	private static final long serialVersionUID = 1L;

	public CommandResultLineDtoDec() {
		super();
	}

	public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
		/* double dispatch */
		displayHelper.format(this,builder);
	}

}
