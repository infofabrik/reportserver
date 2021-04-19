package net.datenwerke.gxtdto.client.dialog.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * 
 *
 */
public class PropertiesDialog extends DwWindow {

	
	protected Collection<PropertiesDialogCard> cards = new ArrayList<PropertiesDialogCard>();
	
	protected boolean closeOnSubmit = true;
	
	protected ButtonClickedCallback submitButtonClicked = new ButtonClickedCallback() {
		@Override
		public String buttonClicked() {
			return null;
		}
	};

	protected ButtonClickedCallback cancelButtonClicked = new ButtonClickedCallback() {
		@Override
		public String buttonClicked() {
			return null;
		}
	};

	protected List<ToggleButton> buttons = new ArrayList<ToggleButton>();

	private DwTabPanel tabPanel;
	
	@Inject
	public PropertiesDialog(){
		
		/* init */
		initializeUI();
		setCenterOnShow(true);
	}

	protected void initializeUI() {
		addClassName("rs-prop-dialog");
		
		/* nsContainer */
		tabPanel = new DwTabPanel();

		/* gxt window center bug */
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				setWidget(tabPanel);
			}
		});
		
		
		/* add cancel button */
		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		getButtonBar().add(cancelBtn);
		cancelBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				cancelClicked();
			}
		});
		
		/* add ok button */
		DwTextButton okBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		getButtonBar().add(okBtn);
		okBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				submitClicked();
			}
		});
	}
	
	protected void cancelClicked() {
		String msg = cancelButtonClicked.buttonClicked();
		if(null != msg){
			if(!"".equals(msg))
				new DwAlertMessageBox(BaseMessages.INSTANCE.error(), msg).show();
			return;
		}
		
		for(PropertiesDialogCard card: cards)
			card.cancelPressed();
			
		/* close window */
		hide();
	}

	protected void submitClicked() {
		String msg = submitButtonClicked.buttonClicked();
		if(null != msg){
			if(!"".equals(msg))
				new DwAlertMessageBox(BaseMessages.INSTANCE.error(), msg).show();
			return;
		}
		
		for(PropertiesDialogCard card : cards){
			msg = card.isValid();
			if(null != msg){
				new DwAlertMessageBox(BaseMessages.INSTANCE.error(), msg).show();
				return;
			}
		}
		
		notifyCardsOfSubmit();
			
		/* close window */
		if(isCloseOnSubmit())
			hide();
	}

	protected void notifyCardsOfSubmit() {
		for(PropertiesDialogCard card : cards)
			card.submitPressed();
	}

	public void addCard(final PropertiesDialogCard card){
		if(0 == tabPanel.getWidgetCount())
			setHeight(card.getHeight());
		
		/* config */
		TabItemConfig cardConfig = new TabItemConfig();
		cardConfig.setText(card.getName());
		cardConfig.setIcon(card.getIcon());
		cardConfig.setClosable(false);
		
		/* get component */
		final Widget component = card.getCard();
		
		final VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.setScrollMode(ScrollMode.AUTOY);
		wrapper.add(component, new VerticalLayoutData(1, -1));
		
		/* add component */
		tabPanel.add(wrapper, cardConfig);
		
		/* add to cards */
		cards.add(card);
	}
	
	public void setCancelButtonClicked(ButtonClickedCallback cancelButtonClicked) {
		this.cancelButtonClicked = cancelButtonClicked;
	}

	public void setSubmitButtonClicked(ButtonClickedCallback submitButtonClicked) {
		this.submitButtonClicked = submitButtonClicked;
	}

	public boolean isCloseOnSubmit() {
		return closeOnSubmit;
	}

	public void setCloseOnSubmit(boolean closeOnSubmit) {
		this.closeOnSubmit = closeOnSubmit;
	}
	
	


}
