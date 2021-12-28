package net.datenwerke.rs.base.client.reportengines.table.helpers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowFieldsAction;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEnumList;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.helpers.format.FormatType;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ColumnFormatWindow extends DwWindow {

	private final ColumnDto column;

	private SimpleForm form;

	private String decimalPlacesKey;

	private String thousandSepKey;

	private String currencyKey;

	private String baseFormat;

	private String targetFormat;

	private String typeKey;

	private String template;

	private String dateRollover;

	private String dateReplaceError;

	private String dateReplaceErrorWith;

	private String nullReplacementFormat;

	
	public ColumnFormatWindow(TableReportDto report, ColumnDto column){
		this.column = column;
		
		initUi();
	}

	private void initUi() {
		setSize(800, 220);
		setModal(true);
		setHeaderIcon(BaseIcon.FORMAT);
		setHeading(FilterMessages.INSTANCE.formatDialogHeading(column.getName(), null != column.getType() ? SqlTypes.getName(column.getType()) : ""));
		setOnEsc(false);
		setClosable(false);
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		SimpleForm form = generateForm();
		
		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightHeader();
		wrapper.setHeading(FilterMessages.INSTANCE.formatTitle());
		wrapper.add(form);
		
		container.add(wrapper, new VerticalLayoutData(1,-1));
		
		add(container, new MarginData(10));
		
		/* cancel */
		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cancelBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide();
			}
		});
		addButton(cancelBtn);
		
		/* submit */
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.apply());
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				inheritColumnFormat();
				hide();
				onColumnFormatChange();
			}
		});
		addButton(submitBtn);
	}

	protected void onColumnFormatChange() {
	}

	private SimpleForm generateForm() {
		form = SimpleForm.getInlineInstance();
		
		/* add radio buttons for type */
		form.beginRow();
		form.setFieldWidth(250);
		typeKey = form.addField(List.class, FilterMessages.INSTANCE.formatTypeLabel(), new SFFCStaticDropdownList<FormatType>(){

			@Override
			public Map<String, FormatType> getValues() {
				Map<String, FormatType> map = new LinkedHashMap<String, FormatType>();
				
				map.put(FormatType.DEFAULT.toString(), FormatType.DEFAULT);
				
				if(null != column.getType() && SqlTypes.isDateLikeType(column.getType()))
					map.put(FormatType.DATE.toString(), FormatType.DATE);
				else {
					map.put(FormatType.NUMBER.toString(), FormatType.NUMBER);
					map.put(FormatType.PERCENT.toString(), FormatType.PERCENT);
					map.put(FormatType.SCIENTIFIC.toString(), FormatType.SCIENTIFIC);
					map.put(FormatType.CURRENCY.toString(), FormatType.CURRENCY);
					map.put(FormatType.DATE.toString(), FormatType.DATE);
				}
				
				map.put(FormatType.TEXT.toString(), FormatType.TEXT);
				map.put(FormatType.TEMPLATE.toString(), FormatType.TEMPLATE);
				
				return map;
			}
			
		});
		
		form.setFieldWidth(500);
		nullReplacementFormat = form.addField(String.class, FilterMessages.INSTANCE.nullReplacementLabel());
		form.endRow();

		form.setFieldWidth(300);
		
		decimalPlacesKey = form.addField(Integer.class, FilterMessages.INSTANCE.decimalPlacesLabel());
		thousandSepKey = form.addField(Boolean.class, FilterMessages.INSTANCE.thousandSeparatorLabel());
		currencyKey = form.addField(List.class,  FilterMessages.INSTANCE.currencySymbolLabel(), new SFFCEnumList(CurrencyTypeDto.class));
		
		baseFormat = form.addField(String.class, FilterMessages.INSTANCE.formatDateBaseFormat());
		targetFormat = form.addField(String.class, FilterMessages.INSTANCE.formatDateTargetFormat());
		dateRollover = form.addField(Boolean.class, FilterMessages.INSTANCE.formatDateRollover());
		dateReplaceError = form.addField(Boolean.class, FilterMessages.INSTANCE.formatDateReplaceError());
		dateReplaceErrorWith = form.addField(String.class, FilterMessages.INSTANCE.formatDateReplaceErrorWith());
		
		form.setFieldWidth(400);
		template = form.addField(String.class, FilterMessages.INSTANCE.formateTemplateLabel());
		
		final String[] allFields = new String[]{decimalPlacesKey, thousandSepKey, currencyKey, baseFormat, targetFormat, dateRollover, dateReplaceError, dateReplaceErrorWith, template};
		final String[] numberFields = new String[]{decimalPlacesKey, thousandSepKey};
		final String[] percentFields = new String[]{decimalPlacesKey};
		final String[] sciFields = new String[]{decimalPlacesKey};
		final String[] currencyFields = new String[]{decimalPlacesKey, thousandSepKey, currencyKey};
		final String[] templateFields = new String[]{template};
		
		final String[] dateFields = null != column.getType() && SqlTypes.isDateLikeType(column.getType()) ? new String[]{targetFormat} : new String[]{baseFormat, targetFormat, dateRollover, dateReplaceError, dateReplaceErrorWith};
		
		/* conditions */
		form.addCondition(typeKey, new FieldEquals(FormatType.DEFAULT), new ShowFieldsAction(allFields, new String[]{}){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					
					@Override
					public void execute() {
						setHeight(220);
					}
				});
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.CURRENCY), new ShowFieldsAction(allFields, currencyFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(330);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.DATE), new ShowFieldsAction(allFields, dateFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				if(null != column.getType() && SqlTypes.isDateLikeType(column.getType()))
					setHeight(260);
				else
					setHeight(400);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.NUMBER), new ShowFieldsAction(allFields, numberFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(280);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.PERCENT), new ShowFieldsAction(allFields, percentFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(230);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.SCIENTIFIC), new ShowFieldsAction(allFields, sciFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(230);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.TEXT), new ShowFieldsAction(allFields, new String[]{}){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(180);
			}
		});
		form.addCondition(typeKey, new FieldEquals(FormatType.TEMPLATE), new ShowFieldsAction(allFields, templateFields){
			@Override
			public void onSuccess(SimpleForm form) {
				super.onSuccess(form);
				setHeight(230);
			}
		});
		
		/* replace date error */
		form.addCondition(typeKey, new SimpleFormCondition() {
			@Override
			public boolean isMet(Widget formField,
					FormFieldProviderHook responsibleHook, SimpleForm form) {
				return Boolean.TRUE.equals(form.getValue(dateReplaceError));
			}
		}, new ShowHideFieldAction(dateReplaceErrorWith));
		form.addCondition(dateReplaceError, new FieldEquals(Boolean.TRUE), new ShowHideFieldAction(dateReplaceErrorWith));
		
		setValues(form);
		
		form.loadFields();
		
		return form;
	}


	private void setValues(SimpleForm form) {
		form.setValue(typeKey, FormatType.DEFAULT);
		form.setValue(decimalPlacesKey, 2);
		form.setValue(thousandSepKey, false);
		form.setValue(currencyKey, CurrencyTypeDto.EURO);
		form.setValue(baseFormat, "yyyyMMdd");
		form.setValue(targetFormat, "dd.MM.yyyy");
		form.setValue(nullReplacementFormat, null == column.getNullReplacementFormat() ? "" : column.getNullReplacementFormat());
		
		if(null != column.getFormat()){
			ColumnFormatDto format = column.getFormat();
			
			if(format instanceof ColumnFormatNumberDto){
				switch(((ColumnFormatNumberDto)format).getType()){
				case PERCENT: 
					form.setValue(typeKey, FormatType.PERCENT);
					break;
				case SCIENTIFIC: 
					form.setValue(typeKey, FormatType.SCIENTIFIC);
					break;
				default: form.setValue(typeKey, FormatType.NUMBER); 
				}
				
				form.setValue(decimalPlacesKey, ((ColumnFormatNumberDto)format).getNumberOfDecimalPlaces());
				form.setValue(thousandSepKey, ((ColumnFormatNumberDto)format).isThousandSeparator());
			}
			if(format instanceof ColumnFormatCurrencyDto){
				form.setValue(typeKey, FormatType.CURRENCY);
				form.setValue(currencyKey, ((ColumnFormatCurrencyDto)format).getCurrencyType());
			}
			if(format instanceof ColumnFormatDateDto){
				form.setValue(typeKey, FormatType.DATE);
				form.setValue(baseFormat, ((ColumnFormatDateDto)format).getBaseFormat());
				form.setValue(targetFormat, ((ColumnFormatDateDto)format).getTargetFormat());
				form.setValue(dateRollover, ((ColumnFormatDateDto)format).isRollOver());
				form.setValue(dateReplaceError, ((ColumnFormatDateDto)format).isReplaceErrors());
				form.setValue(dateReplaceErrorWith, ((ColumnFormatDateDto)format).getErrorReplacement());
			}
			if(format instanceof ColumnFormatTemplateDto){
				form.setValue(typeKey, FormatType.TEMPLATE);
				form.setValue(template, ((ColumnFormatTemplateDto)format).getTemplate());
			}
			if(format instanceof ColumnFormatTextDto){
				form.setValue(typeKey, FormatType.TEXT);
			}
		}
	}

	protected void inheritColumnFormat() {
		ColumnFormatDto format = null;
		switch((FormatType)form.getValue(typeKey)){
		case NUMBER:
			format = new ColumnFormatNumberDtoDec();
			((ColumnFormatNumberDtoDec)format).setNumberOfDecimalPlaces(Math.max(0, (Integer) form.getValue(decimalPlacesKey)));
			((ColumnFormatNumberDtoDec)format).setThousandSeparator((Boolean) form.getValue(thousandSepKey));
			((ColumnFormatNumberDtoDec)format).setType(NumberTypeDto.DEFAULT);
			break;
		case PERCENT:
			format = new ColumnFormatNumberDtoDec();
			((ColumnFormatNumberDtoDec)format).setNumberOfDecimalPlaces(Math.max(0, (Integer) form.getValue(decimalPlacesKey)));
			((ColumnFormatNumberDtoDec)format).setThousandSeparator((Boolean) form.getValue(thousandSepKey));
			((ColumnFormatNumberDtoDec)format).setType(NumberTypeDto.PERCENT);
			break;
		case SCIENTIFIC:
			format = new ColumnFormatNumberDtoDec();
			((ColumnFormatNumberDtoDec)format).setNumberOfDecimalPlaces(Math.max(0, (Integer) form.getValue(decimalPlacesKey)));
			((ColumnFormatNumberDtoDec)format).setThousandSeparator((Boolean) form.getValue(thousandSepKey));
			((ColumnFormatNumberDtoDec)format).setType(NumberTypeDto.SCIENTIFIC);
			break;
		case CURRENCY:
			format = new ColumnFormatCurrencyDtoDec();
			((ColumnFormatCurrencyDtoDec)format).setCurrencyType((CurrencyTypeDto) form.getValue(currencyKey));
			((ColumnFormatNumberDtoDec)format).setNumberOfDecimalPlaces(Math.max(0, (Integer) form.getValue(decimalPlacesKey)));
			((ColumnFormatNumberDtoDec)format).setThousandSeparator((Boolean) form.getValue(thousandSepKey));
			((ColumnFormatNumberDtoDec)format).setType(NumberTypeDto.DEFAULT);
			break;
		case DATE:
			format = new ColumnFormatDateDtoDec();
			((ColumnFormatDateDtoDec)format).setBaseFormat((String) form.getValue(baseFormat));
			((ColumnFormatDateDtoDec)format).setTargetFormat((String) form.getValue(targetFormat));
			((ColumnFormatDateDtoDec)format).setRollOver((Boolean) form.getValue(dateRollover));
			((ColumnFormatDateDtoDec)format).setReplaceErrors((Boolean) form.getValue(dateReplaceError));
			((ColumnFormatDateDtoDec)format).setErrorReplacement((String) form.getValue(dateReplaceErrorWith));
			break;
		case TEXT:
			format = new ColumnFormatTextDtoDec();
			break;
		case TEMPLATE:
			format = new ColumnFormatTemplateDtoDec();
			((ColumnFormatTemplateDtoDec)format).setTemplate((String)form.getValue(template));
			break;
		}
		
		column.setNullReplacementFormat((String) form.getValue(nullReplacementFormat));
		column.setFormat(format);
	}
}
