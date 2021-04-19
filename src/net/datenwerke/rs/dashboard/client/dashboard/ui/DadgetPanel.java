package net.datenwerke.rs.dashboard.client.dashboard.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.tool.DwToolButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook.DadgetConfigureCallback;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardContainer.ConfigType;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DadgetPanel extends DwContentPanel {

	@CssClassConstant
	public static final String CSS_NAME = "rs-dadget";
	
	private final HookHandlerService hookHandler;
	private final ReportExecutorUIService reportExecutorService;
	
	protected DadgetDto dadget;
	protected DashboardView view;
	
	private int reloadCounter = 0;
	
	@Inject
	public DadgetPanel(
		HookHandlerService hookHandler,
		ReportExecutorUIService reportExecutorService
		){
		this.hookHandler = hookHandler;
		this.reportExecutorService = reportExecutorService;
	}
	
	@Override
	public String getCssName() {
		return super.getCssName() + " " + CSS_NAME;
	}

	public void init(DashboardView view, DadgetDto dadget) {
		this.dadget = dadget;
		this.view = view;
		
		setHeading(null+"");
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		initReload();
	}

	protected void initReload() {
		if(dadget.getReloadInterval() > 0){
			reloadCounter++;
			final int myCounter = reloadCounter;
			Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
				@Override
				public boolean execute() {
					if(reloadCounter != myCounter) 
						return false;
					
					/* if not visible do not reload */
					if(! isVisible(true))
						return false;
					
					clear();
					getProcessor().draw(dadget, DadgetPanel.this);
					forceLayout();
					
					return true;
				}
			}, (int) dadget.getReloadInterval()*1000);
		} else 
			reloadCounter = 0;
	}
	
	@Override
	protected void onShow() {
		super.onShow();
		initReload();
	}


	public DadgetDto getDadget() {
		return dadget;
	}
	
	public void updateDadget(DadgetDto dadget){
		this.dadget = dadget;
		update();
	}
	
	@Override
	public void setHeading(String text) {
		if(null == text || "".equals(text.trim()))
			super.setHeading(BaseMessages.INSTANCE.unnamed());
		else
			super.setHeading(text);
	}

	public void refresh(){
		clear();
		getProcessor().draw(dadget, DadgetPanel.this);
		forceLayout();
	}
	
	public void onMove() {
		if(getProcessor().isRedrawOnMove()){
			refresh();
		}
	}
	
	@Override
	public void initTools() {
		super.initTools();
		
		getProcessor().addTools(this);
		
		/* add execute button */
		if (dadget instanceof LibraryDadgetDto) {
			LibraryDadgetDto libraryDadget = (LibraryDadgetDto) dadget;
			DadgetDto dadget = libraryDadget.getDadgetNode().getDadget();
			if (dadget instanceof ReportDadgetDto) {
				final ReportDadgetDto reportDadget = (ReportDadgetDto) dadget;
				if (reportDadget.isShowExecuteButton()) {
					IconButton execBtn = new ToolButton(DwToolButton.PLAY);
					execBtn.addSelectHandler(event -> {
						if (null != ((ReportDadgetDto) reportDadget).getReportForDisplay())
							reportExecutorService
									.executeReport(((ReportDadgetDto) reportDadget).getReportForDisplay());
					});
					addTool(execBtn);
				}
			}
		}
		
		ToolButton refreshBtn = new ToolButton(ToolButton.REFRESH);
		addTool(refreshBtn);
		refreshBtn.addSelectHandler(event -> refresh());

		if(! view.isProtected()){
			if (getProcessor().hasConfigDialog()) {
				ToolButton configBtn = new ToolButton(ToolButton.GEAR);
				addTool(configBtn);
				configBtn.addSelectHandler(event -> {
					getProcessor().displayConfigDialog(dadget, new DadgetConfigureCallback() {
						@Override
						public void configuringDone() {
							view.dadgetConfigured(DadgetPanel.this, ConfigType.CONFIG);

							initReload();
						}

						@Override
						public void cancelled() {

						}
					});
				});
			}
			
			ToolButton closeBtn = new ToolButton(ToolButton.CLOSE);
			addTool(closeBtn);
			closeBtn.addSelectHandler(selectEvent -> {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(DashboardMessages.INSTANCE.removeDadgetConfirmTitle(),
						DashboardMessages.INSTANCE.removeDadgetConfirmMsg());
				cmb.addDialogHideHandler(dialogHideEvent -> {
					if (dialogHideEvent.getHideButton() == PredefinedButton.YES)
						removeDadget();
				});

				cmb.show();
			});
		}
	}
	
	protected void removeDadget() {
		view.removeDadget(this);
	}
	
	public void update(){
		clear();
		for(Widget w : new ArrayList<>(getHeader().getTools()))
			getHeader().removeTool(w);
		initTools();
		
		initReload();
	}
	
	protected DadgetProcessorHook getProcessor() {
		for(DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class))
			if(processor.consumes(dadget))
				return processor;
		throw new IllegalArgumentException("Could not find dadgetprocessor for " + dadget.getClass());
	}
	
	public DashboardView getView() {
		return view;
	}

	public void setHeaderIcon(BaseIcon icon) {
		getHeader().setIcon(icon.toImageResource());
	}

	public void makeAwareOfSelection() {
		initReload();
	}


}
