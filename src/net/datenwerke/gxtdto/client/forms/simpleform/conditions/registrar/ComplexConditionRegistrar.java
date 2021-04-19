package net.datenwerke.gxtdto.client.forms.simpleform.conditions.registrar;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.ComplexCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class ComplexConditionRegistrar implements ConditionRegistrar {

	private final SimpleFormAction action;
	private final ComplexCondition[] conditions;
	private final SimpleForm form;
	
	public ComplexConditionRegistrar(SimpleFormAction action,
			ComplexCondition[] conditions, SimpleForm form) {
		super();
		this.action = action;
		this.conditions = conditions;
		this.form = form;
	}

	@SuppressWarnings("unchecked")
	public void registerCondition() {
		if(null == conditions)
			return;
		
		/* attach listneres */
		for(ComplexCondition condition : conditions){
			FormFieldProviderHook responsibleHook = form.getResponsibleHook(condition.getFieldKey());
	
			/* add listener */
			responsibleHook.addValueChangeHandler(new ValueChangeHandler() {
				@Override
				public void onValueChange(ValueChangeEvent event) {
					checkCondition();
				}
			});
		}
		
		/* first time check */
		checkCondition();
	}
	
	protected void checkCondition(){
		/* create flag to store if a condition failed */
		boolean conditionsFailed = false;
		
		/* test each condition */
		for(ComplexCondition condition : conditions){
			/* get formfield and hook */
			Widget myFormField = form.getField(condition.getFieldKey());
			FormFieldProviderHook myResponsibleHook = form.getResponsibleHook(condition.getFieldKey());
			
			/* test condition */
			if(! condition.getCondition().isMet(myFormField, myResponsibleHook, form)){
				conditionsFailed = true;
				break;
			}
		}
		
		if(! conditionsFailed)
			action.onSuccess(form);
		else
			action.onFailure(form);
	}

}
