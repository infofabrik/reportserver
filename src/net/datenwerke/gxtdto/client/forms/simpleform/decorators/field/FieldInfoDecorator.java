package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.Anchor;
import com.sencha.gxt.core.client.Style.AnchorAlignment;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.widget.core.client.WidgetComponent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler.SideErrorTooltipAppearance;
import com.sencha.gxt.widget.core.client.tips.ToolTip;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class FieldInfoDecorator implements SimpleFormFieldDecorator {

	public static final String DECORATOR_ID = "FieldInfoDecorator";
	
	protected Map<String, String> fieldInfoMap = new HashMap<String, String>();
	protected Map<String, DelayedInfoMessage> fieldInfoMapDelayed = new HashMap<String, DelayedInfoMessage>();
	
	public interface InfoCallback{
		void setInfo(String msg);
	}
	
	public interface DelayedInfoMessage{
		void getMessage(InfoCallback callback);
		String getWaitText();		
		boolean updateAlways();
	}
	
	@Override
	public String getDecoratorId() {
		return DECORATOR_ID;
	}

	
	@Override
	public void configureFieldOnLoad(SimpleForm form, Widget field, String key) {
	}
	
	@Override
	public void configureFieldAfterLayout(SimpleForm simpleForm, final Widget widget, String key) {
		if(fieldInfoMap.containsKey(key) || fieldInfoMapDelayed.containsKey(key)){
			final WidgetComponent fieldIcon = new WidgetComponent(new HTML(BaseIcon.INFO.toSafeHtml()));
	        fieldIcon.setHideMode(HideMode.VISIBILITY);
	        fieldIcon.getElement().makePositionable(true);
	        fieldIcon.getElement().setDisplayed(true);
	        
        	widget.getElement().getParentElement().appendChild(fieldIcon.getElement());
        	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					fieldIcon.getElement().alignTo(widget.getElement(), new AnchorAlignment(Anchor.TOP_LEFT, Anchor.TOP_RIGHT), 2, 3);		
				}
			});
        	
        	final ToolTip tip = new ToolTip(fieldIcon, GWT.<SideErrorTooltipAppearance>create(SideErrorTooltipAppearance.class));
	        
        	String info = fieldInfoMap.get(key);
	        if(null != info){
	        	ToolTipConfig config = new ToolTipConfig(info);
	        	tip.update(config);
	        }else {
	        	final DelayedInfoMessage delayedInfo = fieldInfoMapDelayed.get(key);
	        	
	        	tip.addShowHandler(new ShowHandler() {
	        		
	        		boolean tipLoaded = false;
					
	        		@Override
					public void onShow(ShowEvent event) {
						if(tipLoaded && ! delayedInfo.updateAlways())
							return;
						tipLoaded = true;
						ToolTipConfig config = tip.getToolTipConfig();
						config.setBody(delayedInfo.getWaitText());
						tip.update(config);
						
						delayedInfo.getMessage(new InfoCallback() {
							@Override
							public void setInfo(String msg) {
								ToolTipConfig config = tip.getToolTipConfig();
								config.setBody(msg);
								tip.update(config);
							}
						});
					}
				});
	        }
	        
	        /* show icon */
	        fieldIcon.show();
		}
	}

	public void addInfo(String key, String data) {
		fieldInfoMap.put(key, data);
	}
	
	public void addInfo(String key, DelayedInfoMessage data) {
		fieldInfoMapDelayed.put(key, data);
	}


	@Override
	public Widget adjustFieldForDisplay(SimpleForm simpleForm,
			Widget formField, String fieldKey) {
		return formField;
	}

}
