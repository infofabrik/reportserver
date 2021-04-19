package net.datenwerke.gxtdto.client.clipboard.processor;

import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoListItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;

public abstract class ClipboardDtoPasteProcessor implements ClipboardPasteProcessor {

	private Class<?> type;

	public ClipboardDtoPasteProcessor(Class<?> type){
		this.type = type;
	}
	
	@Override
	public void paste(ClipboardItem item) {
		if(item instanceof ClipboardDtoItem){
			ClipboardDtoItem dtoItem = (ClipboardDtoItem) item;
			if(null != dtoItem.getDto()){
				Class<?> dtoType = dtoItem.getDto().getClass();
				while(null != dtoType){
					if(dtoType.equals(type)){
						doPaste((ClipboardDtoItem)dtoItem);
						break;
					}
					dtoType = dtoType.getSuperclass();
				}
			}
		} else  if(item instanceof ClipboardDtoListItem){
			ClipboardDtoListItem listItem = (ClipboardDtoListItem) item;
			if(null != listItem.getList() && ! listItem.getList().isEmpty()){
				Class<?> dtoType = listItem.getType();
				while(null != dtoType){
					if(dtoType.equals(type)){
						doPaste(listItem);
						break;
					}
					dtoType = dtoType.getSuperclass();
				}
			}
		}
		
	}

	protected void doPaste(ClipboardDtoListItem listItem) {
	}

	protected void doPaste(ClipboardDtoItem dtoItem){
	}

}
