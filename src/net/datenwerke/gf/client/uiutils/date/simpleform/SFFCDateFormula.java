package net.datenwerke.gf.client.uiutils.date.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCDateFormula extends SimpleFormFieldConfiguration {

	public enum Mode {
		Date,
		Time,
		DateTime
	}
	
	public Mode getMode();
	
	public String getDatePattern();
	public String getTimePattern();
}

