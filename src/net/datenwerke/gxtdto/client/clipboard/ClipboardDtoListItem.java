package net.datenwerke.gxtdto.client.clipboard;

import java.util.List;

import net.datenwerke.gxtdto.client.clipboard.locale.ClipboardMessages;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class ClipboardDtoListItem extends ClipboardItem implements ClipboardItemDescriber {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2781270065293838858L;
	
	private List<? extends Dto> list;
	private Object additionalInfo;

	public ClipboardDtoListItem(){
		super();
	}
	
	public ClipboardDtoListItem(List<? extends Dto> list, Class<?> type){
		setType(type);
		this.setList(list);
	}
	
	public ClipboardDtoListItem(List<? extends Dto> list, Class<?> type, Object addInfo){
		setType(type);
		this.setList(list);
		setAdditionalInfo(addInfo);
	}

	public List<? extends Dto> getList() {
		return list;
	}

	public void setList(List<? extends Dto> list) {
		this.list = list;
	}

	@Override
	public String describe() {
		if(null != list)
			return ClipboardMessages.INSTANCE.dtoListCopiedToClipboard(list.size());
		return "";
	}

	public void setAdditionalInfo(Object additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Object getAdditionalInfo() {
		return additionalInfo;
	}
	
}
