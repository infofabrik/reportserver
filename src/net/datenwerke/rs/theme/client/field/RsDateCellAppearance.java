package net.datenwerke.rs.theme.client.field;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.sencha.gxt.cell.core.client.form.DateCell.DateCellAppearance;


public class RsDateCellAppearance extends RsTriggerFieldAppearance implements DateCellAppearance  {

	public RsDateCellAppearance() {
		super();
		setTriggerIcon(BaseIcon.CALENDAR);
		setTriggerPlainAppearance(true);
	}
}
