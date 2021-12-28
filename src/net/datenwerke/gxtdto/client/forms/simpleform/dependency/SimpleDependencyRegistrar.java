package net.datenwerke.gxtdto.client.forms.simpleform.dependency;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

public class SimpleDependencyRegistrar implements DependencyRegistrar {

	private final String dependant;
	private final String dependsOn;
	private final SimpleForm form;
	
	private boolean reloading = false;
	
	public SimpleDependencyRegistrar(String dependant, String dependsOn,
			SimpleForm form) {
		super();
		this.dependant = dependant;
		this.dependsOn = dependsOn;
		this.form = form;
	}



	@SuppressWarnings("unchecked")
	public void registerDependency() {
		final FormFieldProviderHook responsibleHook = form.getResponsibleHook(dependsOn);
		
		responsibleHook.addValueChangeHandler(new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				/* test that we only reload once */
				if(reloading)
					return;
				reloading = true;
				
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						reloading = false;
						form.reloadField(dependant);	
					}
				});
			}
		});
	}

}
