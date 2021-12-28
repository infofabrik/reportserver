package net.datenwerke.gxtdto.client.utils.labelprovider;

import com.sencha.gxt.data.shared.LabelProvider;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class DisplayTitleLabelProvider<D extends Dto> implements LabelProvider<D> {

	@Override
	public String getLabel(D item) {
		return null == item ? BaseMessages.INSTANCE.unknown() : item.toDisplayTitle();
	}

}
