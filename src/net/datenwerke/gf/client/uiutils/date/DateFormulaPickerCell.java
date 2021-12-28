package net.datenwerke.gf.client.uiutils.date;

import java.text.ParseException;
import java.util.Date;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.DateCell.DateCellAppearance;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.Style.Anchor;
import com.sencha.gxt.core.client.Style.AnchorAlignment;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.shared.event.GroupingHandlerRegistration;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.HasCollapseHandlers;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.HasExpandHandlers;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwDateMenu;

public class DateFormulaPickerCell extends TriggerFieldCell<DateFormulaContainer> implements HasExpandHandlers, HasCollapseHandlers {

	private GroupingHandlerRegistration menuHandler;
	private DwDateMenu menu;
	private boolean expanded;
	
	private String adaptedFieldValueOriginal;
	private String adaptedFieldValue;
	
	private boolean hasUpdatedOnFinish;

	/**
	 * Creates a new date cell.
	 */
	public DateFormulaPickerCell() {
		this(GWT.<DateCellAppearance> create(DateCellAppearance.class));
	}

	/**
	 * Creates a new date cell.
	 * 
	 * @param appearance the date cell appearance
	 */
	public DateFormulaPickerCell(DateCellAppearance appearance) {
		super(appearance);
		setPropertyEditor(new DateFormulaPickerEditor());
	}

	@Override
	public HandlerRegistration addCollapseHandler(CollapseHandler handler) {
		return addHandler(handler, CollapseEvent.getType());
	}

	@Override
	public HandlerRegistration addExpandHandler(ExpandHandler handler) {
		return addHandler(handler, ExpandEvent.getType());
	}


	public void render(Context context, DateFormulaContainer value,
			JuelResultDto result, SafeHtmlBuilder sb) {
		String v = "";
		if (value != null) {
			v = getPropertyEditor().render(value);
		}

		FieldViewData viewData = checkViewData(context, v);

		FieldAppearanceOptions options = new FieldAppearanceOptions(width, height, isReadOnly());
		options.setEmptyText(getEmptyText());
		options.setHideTrigger(isHideTrigger());
		options.setEditable(isEditable());
		options.setName(name);
		options.setDisabled(isDisabled());

		String s = (viewData != null) ? viewData.getCurrentValue() : v;

		if(null != s && null != value.getFormula() && null != result.getDateValue()){
			String date = ((DateFormulaPickerEditor)getPropertyEditor()).format(result.getDateValue());
			
			adaptedFieldValueOriginal = null;
			s = date;
			adaptedFieldValue = s;
		}

		getAppearance().render(sb, s == null ? "" : s , options);
	}

	public void collapse(final Context context, final XElement parent) {
		if (!expanded) {
			return;
		}

		expanded = false;

		menu.hide();
		getInputElement(parent).focus();
		fireEvent(context, new CollapseEvent(context));
	}

	public void expand(final Context context, final XElement parent, DateFormulaContainer value, ValueUpdater<DateFormulaContainer> valueUpdater) {
		if (expanded) {
			return;
		}

		this.expanded = true;

		// expand may be called without the cell being focused
		// saveContext sets focusedCell so we clear if cell 
		// not currently focused
		boolean focused = focusedCell != null;
		saveContext(context, parent, null, valueUpdater, value);
		if (!focused) {
			focusedCell = null;
		}

		DatePicker picker = getDatePicker();

		Date d = null;

		try {
			DateFormulaContainer intermediate = getPropertyEditor().parse(getText(parent));
			d = intermediate.getDate() == null ? new Date() : intermediate.getDate();
		} catch (ParseException e) {
			d = value == null ? new Date() : new Date();
		}

		picker.setValue(d, false);

		// handle case when down arrow is opening menu
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				menu.show(parent, new AnchorAlignment(Anchor.TOP_LEFT, Anchor.BOTTOM_LEFT, true));
				menu.getDatePicker().focus();

				fireEvent(context, new ExpandEvent(context));
			}
		});


	}

	/**
	 * Returns the cell's date picker.
	 * 
	 * @return the date picker
	 */
	public DatePicker getDatePicker() {
		if (menu == null) {
			setMenu(new DwDateMenu());
		}
		return menu.getDatePicker();
	}

	/**
	 * Sets the DateMenu instance to use in this cell when drawing a datepicker
	 * @param menu the menu instance to get the datepicker from
	 */
	public void setMenu(final DwDateMenu menu) {
		if (this.menu != null) {
			menuHandler.removeHandler();
			menuHandler = null;
		}
		this.menu = menu;
		if (this.menu != null) {
			menuHandler = new GroupingHandlerRegistration();

			menuHandler.add(menu.getDatePicker().addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					Date value = event.getValue();
					String s = ((DateFormulaPickerEditor) getPropertyEditor()).format(value);
					FieldViewData viewData = ensureViewData(lastContext, lastParent);
					
					clearAdapted();
					
					if (viewData != null) {
						setViewDate(viewData,s);
					}
					getInputElement(lastParent).setValue(s);
					getInputElement(lastParent).focus();
					
					

					Scheduler.get().scheduleFinally(new ScheduledCommand() {

						@Override
						public void execute() {
							getInputElement(lastParent).focus();
						}
					});

					menu.hide();
				}
			}));
			menuHandler.add(menu.addHideHandler(new HideHandler() {
				@Override
				public void onHide(HideEvent event) {
					collapse(lastContext, lastParent);
				}
			}));
		}
	}

	protected void clearAdapted() {
		adaptedFieldValueOriginal = null;
		adaptedFieldValue = null;
	}

	/* resort to violator pattern */
	protected native void setViewDate(FieldViewData viewData, String value) /*-{
	  viewData.@com.sencha.gxt.cell.core.client.form.ViewData::setCurrentValue(Ljava/lang/String;)(value);
	}-*/;

	@Override
	public void finishEditing(Element parent, DateFormulaContainer value,
			Object key, final ValueUpdater<DateFormulaContainer> valueUpdater) {
		hasUpdatedOnFinish = false;
		super.finishEditing(parent, value, key, new ValueUpdater<DateFormulaContainer>(){
			@Override
			public void update(DateFormulaContainer value) {
				if(null != value.getDate()){
					String strDate = ((DateFormulaPickerEditor)getPropertyEditor()).format(value.getDate());
					if(null != strDate && strDate.equals(adaptedFieldValue)){
						// nothing changed, let's go back
						return;
					}
				}
				
				hasUpdatedOnFinish = true;
				clearAdapted();
				valueUpdater.update(value);
			}
		});
		
		if(! hasUpdatedOnFinish){
			InputElement inputElement = getInputElement(parent);
			if(null != inputElement && null != adaptedFieldValue){
				adaptedFieldValueOriginal = null;
				inputElement.setValue(adaptedFieldValue);
			}
		}
	}
	
	
	@Override
	protected void onFocus(com.google.gwt.cell.client.Cell.Context context,
			XElement parent, DateFormulaContainer value, NativeEvent event,
			ValueUpdater<DateFormulaContainer> valueUpdater) {
		super.onFocus(context, parent, value, event, valueUpdater);
		
		InputElement inputElement = getInputElement(parent);
		if(null != inputElement && null != adaptedFieldValueOriginal)
			inputElement.setValue(adaptedFieldValueOriginal);
		
	}
	
	public void startEditFormula(String formula) {
		adaptedFieldValueOriginal=formula;
	}
	
	/**
	 * @return the menu instance used to get the datepicker from
	 */
	public DwDateMenu getMenu() {
		return menu;
	}

	public boolean isExpanded() {
		return expanded;
	}

	@Override
	protected boolean isFocusedWithTarget(Element parent, Element target) {
		boolean result = parent.isOrHasChild(target)
				|| (menu != null && (menu.getElement().isOrHasChild(target) || menu.getDatePicker().getElement().isOrHasChild(
						target)));
		return result;
	}

	@Override
	protected void onNavigationKey(Context context, Element parent, DateFormulaContainer value, NativeEvent event,
			ValueUpdater<DateFormulaContainer> valueUpdater) {
		if (event.getKeyCode() == KeyCodes.KEY_DOWN && !isExpanded()) {
			event.stopPropagation();
			event.preventDefault();
			onTriggerClick(context, parent.<XElement> cast(), event, value, valueUpdater);
		}
	}

	@Override
	protected void onTriggerClick(Context context, XElement parent, NativeEvent event, DateFormulaContainer value,
			ValueUpdater<DateFormulaContainer> updater) {
		super.onTriggerClick(context, parent, event, value, updater);
		if (!isReadOnly() && !isDisabled()) {
			// blur is firing after the expand so context info on expand is being cleared
			// when value change fires lastContext and lastParent are null without this code
			if ((GXT.isWebKit()) && lastParent != null && lastParent != parent) {
				getInputElement(lastParent).blur();
			}
			onFocus(context, parent, value, event, updater);
			expand(context, parent, value, updater);
		}
	}



}
