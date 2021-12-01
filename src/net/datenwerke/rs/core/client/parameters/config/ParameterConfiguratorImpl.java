package net.datenwerke.rs.core.client.parameters.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;
import net.datenwerke.rs.core.client.parameters.locale.ParametersMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.gwt.user.client.ui.Widget;


public abstract class ParameterConfiguratorImpl<D extends ParameterDefinitionDto, I extends ParameterInstanceDto> implements ParameterConfigurator<D, I> {

	public ParameterType getType(){
		return ParameterType.Normal;
	}
	
	@Override
	public void updateDefinitionOnSubmit(D definition, Widget component){}
	
	@Override
	public final Widget getEditComponentForInstance(I instance,  D definition, Collection<ParameterInstanceDto> relevantInstances, boolean initial, int labelWidth, String executeReportToken, ReportDto report){
		return doGetEditComponentForInstance(instance, relevantInstances, definition, initial, labelWidth, executeReportToken, report);
	}

	protected abstract Widget doGetEditComponentForInstance(I instance, Collection<ParameterInstanceDto> relevantInstances, D definition, boolean initial, int labelWidth, String executeReportToken, ReportDto report);
	
	@Override
	public boolean canHandle(ParameterProposalDto proposal) {
		return false;
	}
	
	protected void setDefaultValueInInstance(I instance, D definition){
		doSetDefaultValueInInstance(instance,definition);

		boolean silent = instance.isSilenceEvents();
		instance.silenceEvents(true);
		instance.setStillDefault(true);
		instance.silenceEvents(silent);
	}
	
	protected void doSetDefaultValueInInstance(I instance, D definition) {
		
	}

	@Override
	public ParameterDefinitionDto getNewDto(ReportDto report){
		ParameterDefinitionDto definition = doGetNewDto();
		
		/* set default values */
		((ParameterDefinitionDtoDec)definition).setReport(report);
		definition.setHidden(false);
		definition.setEditable(true);
				
		return definition;
	}

	protected abstract D doGetNewDto();

	@Override
	public ParameterDefinitionDto getNewDto(ParameterProposalDto proposal, ReportDto report) {
		if(! canHandle(proposal))
			throw new IllegalArgumentException("Cannot handle proposal: " + proposal);
		return getNewDto(report);
	}


	@Override
	public void dependeeInstanceChanged(I instance,
			D aDefinition,
			Collection<ParameterInstanceDto> relevantInstances) {
		boolean silent = instance.isSilenceEvents();
		instance.silenceEvents(true);
		instance.setStillDefault(true);
		instance.silenceEvents(silent);
	}
	
	@Override
	public boolean canDependOnParameters(){
		return false;
	}
	
	@Override
	public List<String> validateParameter(D definition,
			I instance, Widget widget) {
		List<String> errList = new ArrayList<String>();
		
		if(definition.isMandatory()){
			if(! isParameterValueSelected(definition, instance, widget)){
				errList.add(ParametersMessages.INSTANCE.mandatoryParameterNotSelected(definition.getName()));
			}
		}
			
		return errList;
	}

	protected boolean isParameterValueSelected(D definition,
			I instance, Widget widget) {
		return true;
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
