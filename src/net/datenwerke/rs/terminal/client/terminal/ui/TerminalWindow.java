package net.datenwerke.rs.terminal.client.terminal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.exceptions.CommandNotFoundExceptionDto;
import net.datenwerke.rs.terminal.client.terminal.helper.AutocompleteHelper;
import net.datenwerke.rs.terminal.client.terminal.helper.AutocompleteHelper.AutocompleteCallback;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;
import net.datenwerke.rs.terminal.client.terminal.helper.HistoryHelper;
import net.datenwerke.rs.terminal.client.terminal.hooks.ClientCommandHook;
import net.datenwerke.rs.terminal.client.terminal.locale.TerminalMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TerminalWindow extends DwWindow {

   private enum Mode {
      NORMAL, INTERACTIVE
   }

   private final Resources resources = GWT.create(Resources.class);

   interface Resources extends ClientBundle {
      @Source("terminal.gss")
      Style css();
   }

   interface Style extends CssResource {
      @ClassName("rs-terminal-history-area")
      String rsTerminalHistoryArea();

      @ClassName("rs-terminal-input-field")
      String rsTerminalInputField();

      @ClassName("rs-terminal-input-pretext")
      String rsTerminalInputPretext();

      @ClassName("rs-terminal-window")
      String rsTerminalWindow();

      @ClassName("x-window-mc")
      String xWindowMc();
   }

   private final AutocompleteHelper autocompleteHelper;
   private final DisplayHelper displayHelper;
   private final HookHandlerService hookHandler;
   private final HistoryHelper historyHelper;
   private final TerminalDao terminalDao;
   private final TerminalUIService terminalService;

   private DwContentPanel historyPanel;
   private DwContentPanel inputPanel;
   private DwContentPanel mainPanel;

   private HTML historyArea;
   private TextField inputField;

   private Mode mode = Mode.NORMAL;
   private DwContentPanel inputPreTextPanel;

   private boolean singleKeypressMode = false;
   private DwFlowContainer mainPanelContainer;
   private boolean isDisabled;

   @Inject
   public TerminalWindow(AutocompleteHelper autocompleteHelper, DisplayHelper displayHelper,
         HookHandlerService hookHandler, HistoryHelper historyHelper, TerminalDao terminalDao,
         TerminalUIService terminalService) {

      /* store objects */
      this.autocompleteHelper = autocompleteHelper;
      this.displayHelper = displayHelper;
      this.hookHandler = hookHandler;
      this.historyHelper = historyHelper;
      this.terminalDao = terminalDao;
      this.terminalService = terminalService;

      /* create autocomplete helper (needs to have same dao) */
      this.displayHelper.init(this);
      this.autocompleteHelper.init(terminalDao);

      /* init */
      initializeUI();

      /* init session */
      initSession();

      resources.css().ensureInjected();
      addClassName("rs-terminal");
      addClassName("rs-text-wrapping");
   }

   protected void initSession() {
      mainPanel.mask(TerminalMessages.INSTANCE.init());
      isDisabled = true;
      terminalDao.init(null, null, new RsAsyncCallback<HashMap<String, String>>() {
         @Override
         public void onSuccess(HashMap<String, String> result) {
            mainPanel.unmask();
            isDisabled = false;
            inputField.focus();
         }
      });

   }

   public void initSessionInNodeLocation(AbstractNodeDto node, Dto2PosoMapper dto2PosoMapper) {
      mainPanel.mask(TerminalMessages.INSTANCE.init());
      isDisabled = true;
      terminalDao.init(node, dto2PosoMapper, new RsAsyncCallback<HashMap<String, String>>() {
         @Override
         public void onSuccess(HashMap<String, String> result) {
            mainPanel.unmask();
            isDisabled = false;
            inputField.focus();
            Scheduler.get().scheduleDeferred(() -> enterPressed("cd " + terminalDao.getPathWay()));
         }
      });
   }

   public void initSessionWithPath(String path) {
      mainPanel.mask(TerminalMessages.INSTANCE.init());
      isDisabled = true;
      terminalDao.init(null, null, new RsAsyncCallback<HashMap<String, String>>() {
         @Override
         public void onSuccess(HashMap<String, String> result) {
            mainPanel.unmask();
            isDisabled = false;
            inputField.focus();
            Scheduler.get().scheduleDeferred(() -> enterPressed("cd " + path));
         }
      });
   }
   
   protected void initializeUI() {
      setSize(640, 480);
      setOnEsc(false);
      setHeading(TerminalMessages.INSTANCE.terminalWindowHeading());
      setHeaderIcon(BaseIcon.TERMINAL);
      setCollapsible(true);
      setTitleCollapse(true);
      setMaximizable(true);
      setResizable(true);
      
      addStyleName("rs-terminal-window");

      /* create panels */
      mainPanel = DwContentPanel.newInlineInstance();
      initHistoryPanel();
      initInputPanel();

      /* main container */
      mainPanelContainer = new DwFlowContainer();
      mainPanel.setWidget(mainPanelContainer);

      /* add panels */
      mainPanelContainer.add(historyPanel, new MarginData(5, 5, 0, 5));
      mainPanelContainer.add(inputPanel, new MarginData(0, 5, 5, 5));
      mainPanelContainer.setScrollMode(ScrollMode.AUTOY);

      add(mainPanel);

      /* listeners */
      addMouseListener();
      addKeyListener();
   }

   protected void addKeyListener() {
   }

   protected void addMouseListener() {
      addDomHandler(new ClickHandler() {

         @Override
         public void onClick(ClickEvent event) {
            String selection = getSelection();

            if (!event.isControlKeyDown() && (null == selection || selection.isEmpty()))
               scrollIntoFocusAndFocus();
         }

      }, ClickEvent.getType());
   }

   private native String getSelection() /*-{
         var text = "";
         if ($wnd.getSelection) {
             text = $wnd.getSelection().toString();
         } else if ($doc.selection && $doc.selection.type != "Control") {
             text = $doc.selection.createRange().text;
         }
         return text;
    }-*/;

   protected void initInputPanel() {
      inputPanel = DwContentPanel.newInlineInstance();
      inputPanel.setHeight(25);

      TerminalContainer layoutContainer = new TerminalContainer();
      layoutContainer.setStyleName("rs-terminal-input-box");
      inputPanel.setWidget(layoutContainer);

      inputPreTextPanel = DwContentPanel.newInlineInstance();
      inputPreTextPanel.add(new Label(getInputPrefix()));
      inputPreTextPanel.addStyleName("rs-terminal-input-pretext");

      inputField = new TextField() {
         protected void onAfterFirstAttach() {
            super.onAfterFirstAttach();
            getInputEl().setAttribute("autocomplete", "off");
         }
      };
      inputField.addStyleName(resources.css().rsTerminalInputField() + " rs-terminal-input");

      inputField.addKeyDownHandler(event -> {
         specialKeyPressed(event);
         inputKeyPressed(event);
      });

      layoutContainer.add(inputPreTextPanel);
      layoutContainer.add(inputField);
   }

   @Override
   protected void onResize(int width, int height) {
      super.onResize(width, height);
      inputPanel.forceLayout();
   }

   protected String getInputPrefix() {
      switch (mode) {
      case INTERACTIVE:
         return "> ";
      default:
         return "reportserver$ ";
      }
   }

   protected void initHistoryPanel() {
      historyPanel = DwContentPanel.newInlineInstance();

      historyArea = new HTML(new SafeHtmlBuilder().appendEscaped("hrlooowoo").toSafeHtml());
      historyArea.addStyleName(resources.css().rsTerminalHistoryArea());

      historyPanel.addResizeHandler(new com.google.gwt.event.logical.shared.ResizeHandler() {
         @Override
         public void onResize(ResizeEvent event) {
            scrollIntoFocusAndFocus();
         }
      });

      historyPanel.add(historyArea);

      clearHistoryArea();
   }

   protected void clearHistoryArea() {
      historyArea.setHTML("");
   }

   protected void clearInputField() {
      inputField.clear();
      inputField.setText("");
      inputField.focus();

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            scrollIntoFocusAndFocus();
         }
      });
   }

   protected void inputKeyPressed(KeyDownEvent fe) {
      int charcode = fe.getNativeKeyCode();

      if (mode == Mode.INTERACTIVE && fe.isControlKeyDown() && (charcode == 'c' || charcode == 'C'))
         ctrlCPressed();
      else if (singleKeypressMode && charcode != KeyCodes.KEY_ENTER && charcode != KeyCodes.KEY_ESCAPE)
         enterPressed(inputField.getCurrentValue());
      if (charcode != KeyCodes.KEY_TAB)
         autocompleteHelper.invalidateCurrentAutocompleteResult();
   }

   protected void specialKeyPressed(KeyDownEvent fe) {

      if (fe.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
         enterPressed(inputField.getCurrentValue());
      } else if (fe.getNativeKeyCode() == KeyCodes.KEY_TAB) {
         tabPressed(inputField.getCurrentValue(), inputField.getCursorPos());
         fe.stopPropagation();
         fe.preventDefault();
      } else if (fe.getNativeKeyCode() == KeyCodes.KEY_UP) {
         lastHistory();
      } else if (fe.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
         nextHistory();
      }

   }

   protected void ctrlCPressed() {
      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());

      clearInputField();
      terminalDao.ctrlCPressed(getResultCallback(""));
   }

   public void clearHistory() {
      historyArea.setHTML("");
      clearInputField();
   }

   private void nextHistory() {
      if (historyHelper.inHistory())
         setInputFieldValue(historyHelper.next());
      else
         setInputFieldValue(historyHelper.getLast());

      if (null != inputField.getCurrentValue())
         setInputFieldCursorPos(inputField.getCurrentValue().length());

      inputField.focus();
   }

   private void setInputFieldValue(String value) {
      inputField.setValue(value, true);
      inputField.setText(value);
   }

   private void setInputFieldCursorPos(final int pos) {
      scrollIntoFocusAndFocus();
      Scheduler.get().scheduleDeferred(() -> Scheduler.get()
            .scheduleFinally(() -> {
               scrollIntoFocusAndFocus();
               inputField.getCell().setCursorPos(inputField.getElement(), pos);
               scrollIntoFocusAndFocus();
            }));
   }

   private void lastHistory() {
      if (!historyHelper.inHistory())
         historyHelper.addCurrent(inputField.getCurrentValue());
      setInputFieldValue(historyHelper.last());

      if (null != inputField.getCurrentValue())
         setInputFieldCursorPos(inputField.getCurrentValue().length());

      inputField.focus();
   }

   protected void enterPressed(final String value) {
      if (isDisabled)
         return;

      if (mode == Mode.NORMAL && isQuit(value)) {
         hide();
         return;
      } else if (mode == Mode.NORMAL && isEmpty(value)) {
         clearInputField();
         return;
      }

      /* ask hookers */
      boolean abort = false;
      for (ClientCommandHook clientCmdHooker : hookHandler.getHookers(ClientCommandHook.class)) {
         if (clientCmdHooker.consumes(value)) {
            abort = clientCmdHooker.execute(value, this);
            if (abort)
               break;
         }
      }

      if (!abort) {
         mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
         isDisabled = true;

         historyHelper.addCommand(value);
         clearInputField();

         terminalDao.execute(value, getResultCallback(value));
      } else
         clearInputField();
   }

   private boolean isEmpty(String value) {
      return null == value || "".equals(value.replaceAll(" ", ""));
   }

   private AsyncCallback<CommandResultDto> getResultCallback(final String value) {
      return new RsAsyncCallback<CommandResultDto>() {
         public void onSuccess(CommandResultDto result) {
            if (null != result) {
               terminalService.processExternalResult(result);

               if (((CommandResultDtoDec) result).hasModifier(InteractiveResultModifierDto.class))
                  mode = Mode.INTERACTIVE;
               else
                  mode = Mode.NORMAL;

               addExecuteCommandLine(value);

               /* return on single key press */
               singleKeypressMode = ((CommandResultDtoDec) result).hasModifier(PressKeyResultModifierDto.class);

               SafeHtml resultText = displayHelper.format(result);
               if (DisplayModeDto.NORMAL == result.getDisplayMode())
                  addResultText(resultText);
               else
                  displayResultWindow(resultText);
            }

            mainPanel.unmask();
            isDisabled = false;
            clearInputField();
            scrollIntoFocusAndFocus();
         };

         @Override
         public void onFailure(Throwable caught) {
            mainPanel.unmask();
            isDisabled = false;
            if (caught instanceof CommandNotFoundExceptionDto)
               addResultText(new SafeHtmlBuilder().appendEscaped(TerminalMessages.INSTANCE.commandNotFound(value))
                     .toSafeHtml());
            else
               addResultText(new SafeHtmlBuilder().appendEscaped(caught.getMessage()).toSafeHtml());
            scrollIntoFocusAndFocus();
         }
      };
   }

   protected void scrollIntoFocusAndFocus() {
      int height = mainPanel.getElement().getScrollHeight();
      int height2 = historyPanel.getElement().getScrollHeight();

      mainPanelContainer.getScrollSupport().setVerticalScrollPosition(height > height2 ? height : height2);

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            inputField.focus();
         }
      });
   }

   protected void tabPressed(final String value, final int cursorPos) {
      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
      autocompleteHelper.autoComplete(value, cursorPos, new AutocompleteCallback() {

         @Override
         public void displayAutoCompleteResult(AutocompleteResultDto currentAutoCompleteResult) {
            mainPanel.unmask();
            scrollIntoFocusAndFocus();
         }

         @Override
         public void autocomplete(final String result) {
            /* do we have to begin quotes */
            String prefix = value.substring(0, cursorPos);
            final boolean haveToInsertQuote = result.contains(" ") && prefix.split("\"").length % 2 == 1;
            if (haveToInsertQuote) {
               int spacePos = prefix.lastIndexOf(' ') + 1;
               setInputFieldValue(prefix.substring(0, spacePos) + "\"" + prefix.substring(spacePos) + result
                     + value.substring(cursorPos));
            } else {
               setInputFieldValue(value.substring(0, cursorPos) + result + value.substring(cursorPos));
            }

            mainPanel.unmask();
            scrollIntoFocusAndFocus();
            Scheduler.get().scheduleFinally(new ScheduledCommand() {

               @Override
               public void execute() {
                  setInputFieldCursorPos(cursorPos + result.length() + (haveToInsertQuote ? 1 : 0));
               }
            });
         }

         @Override
         public void noResult() {
            mainPanel.unmask();
            scrollIntoFocusAndFocus();
         }

         @Override
         public void manyResults(AutocompleteResultDto result) {
            addExecuteCommandLine(value);

            List<String> resultList = new ArrayList<String>();

            if (resultList.addAll(result.getObjectEntries().getList())
                  && (result.getCmdEntries().getList().size() > 0 || result.getEntries().getList().size() > 0))
               resultList.add("");
            if (resultList.addAll(result.getCmdEntries().getList()) && result.getEntries().getList().size() > 0)
               resultList.add("");
            resultList.addAll(result.getEntries().getList());

            addResultText(displayHelper.format(resultList));
            mainPanel.unmask();
            scrollIntoFocusAndFocus();
         }

         @Override
         public void onFailure(Throwable caught) {
            mainPanel.unmask();
            scrollIntoFocusAndFocus();
         }

      });

   }

   protected void addResultText(SafeHtml resultText) {
      if (null == resultText || "".equals(resultText.asString()))
         return;

      String output = resultText.asString();
      
      /*
       * Replace all tabs with 3 spaces
       */
      output = output.replaceAll("\t", "   ");
      
      /*
       * Replace all but first white spaces. If we replace all whitespaces instead,
       * the browser does not break lines on space and the text is not being
       * completely shown on screen.
       */
      output = output.replaceAll("(?<= ) ", "&nbsp;");
      /*
       * In case we have more than one blank space directly after each other, both are
       * not rendered, so we replace both by &nbsp; in this case.
       */
      output = output.replaceAll(" " + "&nbsp;", "&nbsp;&nbsp;");
      
      if ("".equals(historyArea.getHTML()))
         historyArea.setHTML(output);
      else
         historyArea.setHTML(historyArea.getHTML() + "<br/>" + output);

      inputPreTextPanel.clear();
      inputPreTextPanel.add(new Label(getInputPrefix()));
      inputPreTextPanel.addStyleName("rs-terminal-input-pretext");
      inputPanel.forceLayout();
   }

   protected void displayResultWindow(SafeHtml resultText) {
      DwWindow window = new DwWindow();
      window.setSize(640, 480);
      window.setOnEsc(true);

      VerticalLayoutContainer container = new VerticalLayoutContainer();
      container.setScrollMode(ScrollMode.AUTO);
      container.add(new HTML(resultText));

      window.add(container);

      window.show();
   }

   private void addExecuteCommandLine(String value) {
      if ("".equals(historyArea.getHTML()))
         historyArea.setHTML(getInputPrefix() + (null == value ? "" : value));
      else
         historyArea.setHTML(historyArea.getHTML() + "<br/>" + getInputPrefix() + (null == value ? "" : value));

      inputPreTextPanel.clear();
      inputPreTextPanel.add(new Label(getInputPrefix()));
      inputPreTextPanel.addStyleName("rs-terminal-input-pretext");
      inputPanel.forceLayout();
   }

   protected boolean isQuit(String value) {
      if (null == value)
         return false;
      String trimmed = value.trim();
      if ("quit".equals(trimmed) || "bye".equals(trimmed))
         return true;
      return false;
   }

   @Override
   public void hide() {
      super.hide();
      terminalDao.closeSession();
   }
   
}
