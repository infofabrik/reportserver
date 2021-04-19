package net.datenwerke.gf.client.uiutils.info;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.BaseIconComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.HasResizeHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.Anchor;
import com.sencha.gxt.core.client.Style.AnchorAlignment;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.ComponentHelper;
import com.sencha.gxt.widget.core.client.form.error.ErrorHandler;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler.SideErrorTooltipAppearance;
import com.sencha.gxt.widget.core.client.tips.ToolTip;

/**
 * Based on {@link SideErrorHandler}
 * 
 *
 */
public class FieldInfoHandler implements HasClickHandlers, ErrorHandler {

	private class Handler implements AttachEvent.Handler {

		@Override
		public void onAttachOrDetach(AttachEvent event) {
			if (event.isAttached()) {
				doAttach();
			} else {
				doDetach();
			}
		}

	}

	protected Widget target;
	protected BaseIconComponent infoIcon;
	protected boolean infoIconInitialized = false;
	protected ToolTip tip;

	private List<String> messages;
	private boolean showingInfo;
	private boolean adjustTargetWidth = true;
	private Handler handler = new Handler();
	private int originalWidth = -1;

	public FieldInfoHandler(Widget target){
		this(target, BaseIcon.INFO_CIRCLE);
	}

	public FieldInfoHandler(Widget target, BaseIcon icon){
		this.target = target;

		target.addAttachHandler(handler);

		if (target.isAttached()) {
			doAttach();
		}

		if (target instanceof HasResizeHandlers) {
			((HasResizeHandlers) target).addResizeHandler(new ResizeHandler() {
				@Override
				public void onResize(ResizeEvent event) {
					adjustSize();
				}
			});
		}

		icon.addClassName("rs-fieldinfo");
		infoIcon = icon.toComponent();
	}

	public Widget getTarget() {
		return target;
	}

	public void clear() {
		this.messages = null;
		if (infoIcon != null && infoIconInitialized) {
			restoreSize();

			ComponentHelper.doDetach(infoIcon);
			infoIcon.hide();
			target.getElement().setAttribute("aria-describedby", "");
			showingInfo = false;
		}
	}

	/**
	 * Returns {@code true} if the target width is adjusted.
	 * 
	 * @return the target width resize state
	 */
	public boolean isAdjustTargetWidth() {
		return adjustTargetWidth;
	}


	public void mark(String message) {
		if(null == message)
			mark((List<String>)null);
		ArrayList<String> l = new ArrayList<String>();
		l.add(message);
		mark(l);
	}

	public void mark(List<String> messages) {
		if (null == messages || messages.isEmpty()) {
			clear();
			return;
		}

		this.messages = new ArrayList<String>(messages);
		if (!target.isAttached()) {
			// follow up later, after attached
			return;
		}

		String msg = messages.get(0);

		if (showingInfo && tip != null) {
			tip.getToolTipConfig().setBody(msg);
			tip.update(tip.getToolTipConfig());
			return;
		}

		showingInfo = true;

		if (! infoIconInitialized) {
			infoIconInitialized = true;

			infoIcon.setHideMode(HideMode.VISIBILITY);
			infoIcon.hide();

			Element p = target.getElement().getParentElement();
			p.appendChild(infoIcon.getElement());

			infoIcon.getElement().setDisplayed(true);
			infoIcon.getElement().makePositionable(true);
		} else if (!infoIcon.getElement().isConnected()) {
			infoIcon.setHideMode(HideMode.VISIBILITY);
			infoIcon.hide();
			Element p = target.getElement().getParentElement();
			p.appendChild(infoIcon.getElement());
		}

		if (!infoIcon.isAttached()) {
			ComponentHelper.doAttach(infoIcon);
		}

		adjustSize();

		infoIcon.getElement().setVisibility(false);
		infoIcon.show();
		alignInfoIcon();

		// needed to prevent flickering
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				if (infoIcon.isAttached()) {
					infoIcon.show();
					alignInfoIcon();
					infoIcon.getElement().setVisibility(true);
				}
			}
		});
		
		if (tip == null) {
			tip = new ToolTip(infoIcon.getWidget(), GWT.<SideErrorTooltipAppearance> create(SideErrorTooltipAppearance.class));
		}

		tip.getToolTipConfig().setBody(msg);
		tip.update(tip.getToolTipConfig());
	}

	/**
	 * Adjusts the size of the widget to account for the side icon, if visible. May be called multiple times, whether or
	 * not an error is displayed, and must be called again when size changes to account for that change.
	 */
	protected void adjustSize() {
		// Only run if showing a tooltip
		if (!this.showingInfo) {
			return;
		}
		if (adjustTargetWidth) {
			int w = target.getElement().<XElement> cast().getStyleSize().getWidth();

			if (w != -1) {
				if(originalWidth == -1)
					originalWidth = w;

				// we're currently adjusting, so ignore any incoming attempts to adjust
				this.adjustTargetWidth = false;
				target.setWidth(w - 18 + "px");
				this.adjustTargetWidth = true;
			}
		}
		alignInfoIcon();
	}

	/**
	 * Restores the widget to its original size. Need only be called when in the process of getting rid of an error, when
	 * showingError is still true.
	 */
	protected void restoreSize() {
		if (showingInfo && adjustTargetWidth) {
			this.adjustTargetWidth = false;
			target.setWidth(originalWidth + "px");
			this.adjustTargetWidth = true;
		}
	}

	/**
	 * True to adjust the target width when an error is displayed (defaults to true).
	 * 
	 * @param adjustTargetWidth true to adjust target width
	 */
	public void setAdjustTargetWidth(boolean adjustTargetWidth) {
		this.adjustTargetWidth = adjustTargetWidth;
	}

	protected void alignInfoIcon() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				if (!showingInfo || !target.isAttached()) {
					return;
				}
				assert infoIcon.isAttached() : "errorIcon not attached";
				Element input = null;// target.getElement().<XElement> cast().selectNode("input");
				if (input == null) {
					input = target.getElement();
				}
				infoIcon.getElement().alignTo(input, new AnchorAlignment(Anchor.TOP_LEFT, Anchor.TOP_RIGHT, false),
						2, 3);
			}
		});
	}

	protected void doAttach() {
		if (!showingInfo && messages != null) {
			mark(messages);
		}
		ComponentHelper.doAttach(infoIcon);
	}

	protected void doDetach() {
		ComponentHelper.doDetach(infoIcon);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		infoIcon.fireEvent(event);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return infoIcon.addClickHandler(handler);
	}

	@Override
	public void release() {
		infoIcon.release();
	}
	
	@Override
	public void markInvalid(List<EditorError> errors) {
		List<String> msgs = new ArrayList<String>();
		for(EditorError err : errors)
			msgs.add(err.getMessage());
		mark(msgs);
	}
	
	@Override
	public void clearInvalid() {
		clear();
	}
	
}