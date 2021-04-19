package net.datenwerke.gxtdto.client.clipboard;

import net.datenwerke.gxtdto.client.clipboard.locale.ClipboardMessages;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;

public class ClipboardDtoItem extends ClipboardItem implements ClipboardItemDescriber {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790924594059629123L;
	
	private Dto dto;
	private Object id;
	private Object additionalInfo;
	
	public ClipboardDtoItem(){
		super();
	}
	
	public ClipboardDtoItem(Dto dto){
		if(dto instanceof IdedDto)
			setId(((IdedDto)dto).getDtoId());
		setType(dto.getClass());
		this.setDto(dto);
	}
	
	public ClipboardDtoItem(Dto dto, Object addInfo){
		this(dto);
		setAdditionalInfo(addInfo);
	}
	
	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
	}


	public void setDto(Dto dto) {
		this.dto = dto;
	}

	public Dto getDto() {
		return dto;
	}

	@Override
	public String describe() {
		if(null != dto)
			return ClipboardMessages.INSTANCE.dtoCopiedToClipboard(dto.toDisplayTitle());
		return "";
	}

	public void setAdditionalInfo(Object additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Object getAdditionalInfo() {
		return additionalInfo;
	}
	
}
