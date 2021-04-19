package net.datenwerke.gf.client.treedb.simpleform;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TreeNodeDtoProvider extends FormFieldProviderHookImpl {

	private final ClipboardUiService clipboardService;

	@Inject
	public TreeNodeDtoProvider(ClipboardUiService clipboardService){
		this.clipboardService = clipboardService;
	}
			
	
	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		if(configs.length == 0 || ! (configs[0] instanceof SFFCGenericTreeNode))
			return false;
		
		while(type != null){
			if(type.equals(AbstractNodeDto.class))
				return true;
			type = type.getSuperclass();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Widget createFormField() {
		SFFCGenericTreeNode config = (SFFCGenericTreeNode) configs[0];
		
		final SingleTreeSelectionField ssf = new SingleTreeSelectionField((Class<? extends AbstractNodeDto>)type);
		
		ssf.addValueChangeHandler(new ValueChangeHandler<AbstractNodeDto>() {
			@Override
			public void onValueChange(ValueChangeEvent<AbstractNodeDto> event) {
				ValueChangeEvent.fire(TreeNodeDtoProvider.this, event.getValue());
			}
		});
		
		/* clipboard */
		clipboardService.registerPasteHandler(ssf, new ClipboardDtoPasteProcessor(type) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				ssf.setValue((AbstractNodeDto) dtoItem.getDto(), true);
			}
		});
		
		ssf.setName(name);
		ssf.setTreePanel(config.getTreeForPopup());
		
		return ssf;
	}
	
	
	public Object getValue(Widget field){
		return ((SingleTreeSelectionField)field).getValue();
	}

}
