package net.datenwerke.gxtdto.client.widgets;

import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.widget.core.client.container.MarginData;

public class SeparatorTextLabel extends Label {

	public SeparatorTextLabel(String label) {
		super(label);
	}
	
	public static SeparatorTextLabel createHeadlineLarge(String label){
		return createHeadlineLarge(label, null);
	}
	
	public static SeparatorTextLabel createHeadlineLarge(String label, MarginData layoutData){
		SeparatorTextLabel l = new SeparatorTextLabel(label);
		l.addStyleName("rs-l-head-large");
		if(null != layoutData)
			l.setLayoutData(layoutData);
		return l;
	}
	
	public static SeparatorTextLabel createHeadlineMedium(String label){
		return createHeadlineMedium(label, null);
	}
	
	public static SeparatorTextLabel createHeadlineMedium(String label, MarginData layoutData){
		SeparatorTextLabel l = new SeparatorTextLabel(label);
		l.addStyleName("rs-l-head-medium");
		if(null != layoutData)
			l.setLayoutData(layoutData);
		return l;
	}
	
	public static SeparatorTextLabel createHeadlineSmall(String label){
		return createHeadlineSmall(label, null);
	}
	
	public static SeparatorTextLabel createHeadlineSmall(String label, MarginData layoutData){
		SeparatorTextLabel l = new SeparatorTextLabel(label);
		l.addStyleName("rs-l-head-small");
		if(null != layoutData)
			l.setLayoutData(layoutData);
		return l;
	}

	public static SeparatorTextLabel createText(String text) {
		return createText(text, null);
	}
	
	public static SeparatorTextLabel createText(String text, MarginData layoutData) {
		SeparatorTextLabel l = new SeparatorTextLabel(text);
		l.addStyleName("rs-l-text");
		if(null != layoutData)
			l.setLayoutData(layoutData);
		return l;
	}
	
	public static SeparatorTextLabel createSmallText(String text) {
		return createSmallText(text, null);
	}
	
	public static SeparatorTextLabel createSmallText(String text, MarginData layoutData) {
		SeparatorTextLabel l = new SeparatorTextLabel(text);
		l.addStyleName("rs-l-text-small");
		if(null != layoutData)
			l.setLayoutData(layoutData);
		return l;
	}
	
}
