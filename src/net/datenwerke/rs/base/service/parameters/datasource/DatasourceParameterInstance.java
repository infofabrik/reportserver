package net.datenwerke.rs.base.service.parameters.datasource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.parameters.helpers.ParameterValueArrayList;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.security.service.usermanager.entities.User;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;

/**
 * 
 *
 */
@SuppressWarnings("unchecked")
@Entity
@Table(name="DATASOURCE_PARAM_INST")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.datasource.dto"
)
public class DatasourceParameterInstance extends ParameterInstance<DatasourceParameterDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6749646121944345546L;

	@Inject @Transient
	private DatasourceParameterService datasourceParameterService;
	
	@Inject @Transient
	private ParameterSetFactory parameterSetFactory;

	
	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private DatasourceParameterData singleValue;
	
	@EnclosedEntity
	@ExposeToClient
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinTable(name="DATASOURCE_P_INS_2_ML_VAL")
	private List<DatasourceParameterData> multiValue = new ArrayList<DatasourceParameterData>();

	public DatasourceParameterData getSingleValue() {
		return singleValue;
	}

	public void setSingleValue(DatasourceParameterData singleValue) {
		/* https://hibernate.onjira.com/browse/HHH-5559 */
		if(null == singleValue || null == this.singleValue)
			this.singleValue = singleValue;
		else 
			this.singleValue.update(singleValue);
	}
	
	public List<DatasourceParameterData> getMultiValue() {
		return multiValue;
	}

	public void setMultiValue(List<DatasourceParameterData> multiValue) {
		if(null == multiValue)
			multiValue = new ArrayList<DatasourceParameterData>();
		if(null == this.multiValue)
			this.multiValue = new ArrayList<DatasourceParameterData>();
			
		this.multiValue.clear();
		this.multiValue.addAll(multiValue);
	}
	
	@Override
	public Object getSelectedValue(User user) {
		Object returnValue = null;
		if(Mode.Single.equals(((DatasourceParameterDefinition)getDefinition()).getMode()))
			returnValue = getSelectedParameterValue_single();
		else
			returnValue = getSelectedParameterValue_multi();
		
		return returnValue;
	}

	
	public DatasourceParameterData getSingleDefaultValue(ParameterSet parameterSet){
		return getDefinition().getSingleDefaultValue(datasourceParameterService, parameterSetFactory, parameterSet);
	}
	
	public List<DatasourceParameterData> getMultiDefaultValue(ParameterSet parameterSet){
		return getDefinition().getMultiDefaultValueSimpleData();
	}
	
	
	
	private Object getSelectedParameterValue_multi() {
		if(null != getMultiValue()){
			// TODO : verify parameters
			List listValues = new ParameterValueArrayList();
			for(DatasourceParameterData dataObject : getMultiValue())
				listValues.add(getCastedValue(dataObject.getValue()));
			return listValues;
		}
		
		return null;
	}

	private Object getSelectedParameterValue_single() {
		if(null != getSingleValue()){
			String value = getSingleValue().getValue();

			return getCastedValue(value);
		}

		return null;
	}
	

	@Override
	protected void doParseStringValue(String value){
		if(((DatasourceParameterDefinition)getDefinition()).getMode() == Mode.Single){
			DatasourceParameterData data = new DatasourceParameterData();
			data.setKey(value); //$NON-NLS-1$
			data.setValue(value);
			setSingleValue(data);
		} else {
			List<DatasourceParameterData> dataList = new ArrayList<DatasourceParameterData>();
			if(null != value && ! "".equals(value.trim())){
				for(String dataValue : value.split("\\|")){ //$NON-NLS-1$
					DatasourceParameterData data = new DatasourceParameterData();
					data.setKey(value); //$NON-NLS-1$
					data.setValue(dataValue);
					dataList.add(data);
				}
			}
			
			setMultiValue(dataList);
		}
	}

	@Override
	public Object getDefaultValue(User user, ParameterSet parameterSet) {
		return ((DatasourceParameterDefinition)getDefinition()).getDefaultValue(datasourceParameterService, parameterSetFactory, parameterSet);
	}
	
	public Object getCastedValue(String value){
		return ((DatasourceParameterDefinition)getDefinition()).getCastedValue(value);
	}

	@Override
	protected Class<?> getType() {
		return ((DatasourceParameterDefinition)getDefinition()).getType();
	}

}
