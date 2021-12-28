package net.datenwerke.rs.core.service.parameters.entities;

import java.io.Serializable;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.VariableMapper;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 * 
 */
@Entity
@Table(name="PARAMETER_INSTANCE")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.parameters.dto",
	abstractDto=true
)
public abstract class ParameterInstance<D extends ParameterDefinition> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8174735009965133463L;

	@Inject
	protected static EntityClonerService entityCloner;
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@ManyToOne
	private ParameterDefinition definition;
	
	@ExposeToClient
	private boolean stillDefault = true;
	
	@Version
	private Long version;

	@ExposeToClient(id=true)
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	@TransientID
	private Long transientId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTransientId(Long transientId) {
		this.transientId = transientId;
	}

	public Long getTransientId() {
		return transientId;
	}

	public void setStillDefault(boolean stillDefault) {
		this.stillDefault = stillDefault;
	}

	public boolean isStillDefault() {
		return stillDefault;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setDefinition(ParameterDefinition definition) {
		this.definition = (D) definition;
	}

	public D getDefinition() {
		return (D)definition;
	}

	@Transient
	public final String getKey() {
		return definition.getKey();
	}
	
	@Transient
	public ParameterInstance cloneInstanceForReportVariant(){
		return entityCloner.cloneEntity(this);
	}

	@Transient
	public abstract Object getSelectedValue(User user);
	
	public String prettyPrintSelectedValue(User user){
		Object val = getSelectedValue(user);
		return null == val ? "null" : val.toString();
	}

	@Transient
	public abstract Object getDefaultValue(User user, ParameterSet parameterSet);
	
	/**
	 * Configures the EL that is used to parse the parameters into the query
	 * strings.
	 * 
	 * @param user The user the report is executed with
	 * @param context
	 *            The context object to be configured
	 * @param parameterSet 
	 * @param factory
	 */
	@Transient
	public void configureEL(User user, ExpressionFactory factory, ELContext context, ParameterSet parameterSet) {
		VariableMapper vm = context.getVariableMapper();
		
		/* store the objects main value */ 
		Object value;
		if(getDefinition().isEditable() && ! isStillDefault())
			value = this.getSelectedValue(user);
		else
			value = this.getDefaultValue(user, parameterSet);
		
		/* provide access to the actual object */
		ParameterInstanceForJuel instanceForJuel = createParameterInstanceForJuel();
		configureParameterInstanceForJuel(instanceForJuel, value);
		vm.setVariable("_" + getKey(), factory.createValueExpression(instanceForJuel, ParameterInstanceForJuel.class)); //$NON-NLS-1$
		
		ParameterDefinitionForJuel definitionForJuel = getDefinition().createParameterDefinitionForJuel();
		getDefinition().configureParameterDefinitionForJuel(definitionForJuel);
		vm.setVariable("__" + getKey(), factory.createValueExpression(definitionForJuel, ParameterDefinitionForJuel.class)); //$NON-NLS-1$

		
		if(null != value)
			vm.setVariable(getKey(), factory.createValueExpression(value, value.getClass()));
		else
			vm.setVariable(getKey(), factory.createValueExpression(null, Object.class));
	}
	
	protected ParameterInstanceForJuel createParameterInstanceForJuel(){
		return new ParameterInstanceForJuel();
	}
	
	protected void configureParameterInstanceForJuel(ParameterInstanceForJuel instance, Object value){
		instance.setId(id);
		instance.setVersion(version);
	}


	@Transient
	public void configureParameterMap(User user, Map<String, ParameterValue> parameterMap, ParameterSet parameterSet) {
		Object value;
		
		if(getDefinition().isEditable() && ! isStillDefault())
			value = this.getSelectedValue(user);
		else
			value = this.getDefaultValue(user, parameterSet);
		
		Class<?> type = getType();
		
		parameterMap.put(getKey(), new ParameterValueImpl(getKey(), value, type));
	}
	
	abstract protected Class<?> getType();

	/**
	 * Called for example by the xport via url servlet.
	 * @param value
	 */
	@Transient
	public void parseStringValue(String value){
		setStillDefault(false);
		doParseStringValue(value);
	}

	protected void doParseStringValue(String value){}

	@Override
	public int hashCode() {
		if (null == id)
			return super.hashCode();
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof ParameterInstance))
			return false;

		ParameterInstance p = (ParameterInstance) obj;
		if (null == p.getId())
			return getId() == null ? super.equals(obj) : false;

		return p.getId().equals(getId());
	}

	/**
	 * Should provide a short summary about the selected value
	 */
	public String toInformationString(User user) {
		Object value = getSelectedValue(user);
		if(null == value)
			return "";
		return value.toString();
	}
	
}
