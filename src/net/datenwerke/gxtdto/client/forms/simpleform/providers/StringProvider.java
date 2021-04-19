package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.Arrays;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;

import net.datenwerke.gxtdto.client.codemirror.CodeMirrorConfig;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormFieldJson;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCNoHtmlDecode;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPlaceHolder;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCReadOnly;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCRichTextEditor;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringMaxLength;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringNoHtmlDecode;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;

/**
 * 
 *
 */
public class StringProvider extends FormFieldProviderHookImpl {

	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		return type.equals(String.class);
	}
	
	@Override
	public boolean doConsumes(String type, SimpleFormFieldJson config) {
		return type.equals("string");
	}

	@Override
	public Widget createFormField() {
		if(json)
			return createFormFieldFromJson();
		
		SFFCTextArea textAreaConfig = getTextareaConfig();
		
		Widget field = null;
		if(null != textAreaConfig && (textAreaConfig.getHeight() > 1 || textAreaConfig.getHeight() == -1)){
			SFFCCodeMirror codeMirrorConfig = getCodeMirrorConfig();
			if(null != codeMirrorConfig){
				field = new CodeMirrorPanel(new CodeMirrorConfig(codeMirrorConfig.getLanguage(), codeMirrorConfig.lineNumbersVisible()), codeMirrorConfig.getEnhancer());
			} else if(null != getRichTextEditorConfig()){
				field = new HtmlEditor();		
				((HtmlEditor)field).setEnableFont(false);
			} else  {
				field = new TextArea();
			}
			
			((Component)field).setWidth(textAreaConfig.getWidth());
			((Component)field).setHeight(textAreaConfig.getHeight());
		} else {
			if(null != getPasswordConfig() ){
				field = new PasswordField();
				SFFCPasswordField passwordConfig = getPasswordConfig();
				if(Boolean.TRUE.equals(passwordConfig.isPasswordSet())){
					((PasswordField)field).setEmptyText("****");
				}
			} else 
				field = new TextField();
			((ValueBaseField)field).setWidth(null);
			((ValueBaseField)field).setHeight(24);
			if(null != textAreaConfig)
				((ValueBaseField)field).setWidth(textAreaConfig.getWidth());
		}
		
		if(field instanceof ValueBaseField){
			/* validator */
			SFFCStringValidator validatorConfig = getStringValidatorConfig();
			if(null != validatorConfig)
				((Field)field).addValidator(validatorConfig.getValidator());
			
			/* max length */
			SFFCStringMaxLength maxLengthConfig = getMaxLengthConfig();
			if(null != maxLengthConfig)
				((Field)field).addValidator(new MaxLengthValidator(maxLengthConfig.maxLength()));
			
			/* blank */
			installBlankValidation(field);
			
			/* readOnly */
			SFFCReadOnly readOnly = getReadOnlyConfig();
			if(null != readOnly)
				((ValueBaseField)field).setReadOnly(readOnly.isReadOnly());
			
			SFFCPlaceHolder placeHolder = getPlaceHolderConfig();
			if(null != placeHolder)
				((ValueBaseField)field).setEmptyText(placeHolder.getPlaceholder());
		}
		
		/* add listener for change events */
		addChangeListener(field);
		
		return field;
	}
	
	protected Widget createFormFieldFromJson() {
		TextField field = new TextField();
		addChangeListener(field);
		return field;
	}

	
	protected void addChangeListener(Widget field) {
		if(field instanceof HasValueChangeHandlers){
			((HasValueChangeHandlers)field).addValueChangeHandler(
					event -> ValueChangeEvent.fire(StringProvider.this, event.getValue()));
		}
	}


	@Override
	public void addFieldBindings(final Object model, final ValueProvider vp, Widget field) {
		if(field instanceof HasValueChangeHandlers){
			fieldBinding = new HasValueFieldBinding((HasValueChangeHandlers) field, model, vp){
				@Override
				protected Object convertModelValue(Object value) {
					if(isDecodeHtml() && value instanceof String)
						return StringEscapeUtils.unescapeHTML((String) value);
					return value;
				}
			};
		}
	}

	protected boolean isDecodeHtml(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCNoHtmlDecode)
				.map(config -> Boolean.FALSE)
				.findAny()
				.orElseGet(() -> Boolean.TRUE);
	}
	
	protected SFFCCodeMirror getCodeMirrorConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCCodeMirror)
				.map(config -> (SFFCCodeMirror) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCRichTextEditor getRichTextEditorConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCRichTextEditor)
				.map(config -> (SFFCRichTextEditor) config)
				.findAny()
				.orElse(null);
	}
	
	protected boolean decodeHtml(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCStringNoHtmlDecode)
				.map(config -> Boolean.FALSE)
				.findAny()
				.orElseGet(() -> Boolean.TRUE);
	}
	
	
	protected SFFCStringValidator getStringValidatorConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCStringValidator)
				.map(config -> (SFFCStringValidator) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCPasswordField getPasswordConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCPasswordField)
				.map(config -> (SFFCPasswordField) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCTextArea getTextareaConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCTextArea)
				.map(config -> (SFFCTextArea) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCStringMaxLength getMaxLengthConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCStringMaxLength)
				.map(config -> (SFFCStringMaxLength) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCAllowBlank getAllowBlankConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCAllowBlank)
				.map(config -> (SFFCAllowBlank) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCReadOnly getReadOnlyConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCReadOnly)
				.map(config -> (SFFCReadOnly) config)
				.findAny()
				.orElse(null);
	}
	
	protected SFFCPlaceHolder getPlaceHolderConfig(){
		return Arrays
				.stream(configs)
				.filter(config -> config instanceof SFFCPlaceHolder)
				.map(config -> (SFFCPlaceHolder) config)
				.findAny()
				.orElse(null);
	}

	@Override
	public Object getValue(Widget field) {
		if(field instanceof HtmlEditor){
			/* HtmlEditor does not implement HasValue */
			HtmlEditor editor = (HtmlEditor) field;
			return editor.getValue();
		}else{
			return super.getValue(field);
		}
	}
	
	@Override
	public void setValue(Widget field, Object object) {
		if(field instanceof HtmlEditor){
			/* HtmlEditor does not implement HasValue */
			HtmlEditor editor = (HtmlEditor) field;
			editor.setValue((String)object);
		}else
			super.setValue(field, object);
	}
	
	@Override
	protected void setStringValue(Widget field, String value) {
		((HasValue<String>) field).setValue(value);
	}

}
