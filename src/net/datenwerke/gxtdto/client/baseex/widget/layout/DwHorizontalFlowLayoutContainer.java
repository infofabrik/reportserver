package net.datenwerke.gxtdto.client.baseex.widget.layout;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class DwHorizontalFlowLayoutContainer extends FlowLayoutContainer {

	protected VerticalAlign verticalAlign = VerticalAlign.TOP;
	
	protected boolean nobreak = false;
	
	public DwHorizontalFlowLayoutContainer() {
		super();
	}
	
	@Override
	protected void onInsert(int index, Widget child) {
		super.onInsert(index, child);
		
		child.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		child.getElement().getStyle().setVerticalAlign(verticalAlign);
		if(nobreak)
			child.getElement().getStyle().setWhiteSpace(WhiteSpace.NOWRAP);
	}
	
	public void setVerticalAlign(VerticalAlign verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
	
	public VerticalAlign getVerticalAlign() {
		return verticalAlign;
	}

	public boolean isNobreak() {
		return nobreak;
	}

	public void setNobreak(boolean nobreak) {
		this.nobreak = nobreak;
	}

	
}
