package net.datenwerke.gxtdto.client.dtomanager.redoundo;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;

public class UndoManager {

	private class UndoableObject{
		private final Dto dto;
		private final int index;
		
		public UndoableObject(Dto dto, int index) {
			this.dto = dto;
			this.index = index;
		}
		public Dto getDto() {
			return dto;
		}
		public int getIndex() {
			return index;
		}
	
		@Override
		public int hashCode() {
			return dto.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if(! (obj instanceof UndoableObject) )
				return false;
			return dto.equals(((UndoableObject)obj).dto);
		}
	}
	
	private List<UndoableObject> undoableList = new ArrayList<UndoableObject>();
	
	public UndoManager(Dto dto){
		int index = dto.trackChanges();
		undoableList.add(new UndoableObject(dto, index));
		
		init(dto);
	}

	private void init(Dto dto) {
		for(PropertyAccessor pa : dto.getPropertyAccessorsForDtos()){
			Object obj = pa.getValue(dto);
			if(obj instanceof Dto){
				Dto dtoObj = (Dto) obj;
				if(undoableList.contains(new UndoableObject(dtoObj, 0)))
					continue;
				
				int index = dtoObj.trackChanges();
				undoableList.add(new UndoableObject(dtoObj, index));
				init(dtoObj);
			}
		}
	}
	
	public void rollback(){
		for(int i = undoableList.size() - 1; i >= 0; i--){
			UndoableObject undoableObject = undoableList.get(i);
			undoableObject.getDto().rollback(undoableObject.getIndex());
		}
	}

	public void clear() {
		for(int i = undoableList.size() - 1; i >= 0; i--){
			UndoableObject undoableObject = undoableList.get(i);
			if(undoableObject.getIndex() == 0)
				undoableObject.getDto().commit();
		}
		undoableList.clear();
	}
}
