package net.datenwerke.rs.base.service.parameters.datasource;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.helpers.ParameterValueArrayList;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

/**
 * A parameter that uses database queries to display its selection.
 * 
 *
 */
@Entity
@Table(name="DATASOURCE_PARAM_DEF")
@Audited
@GenerateDto(
	dtoImplementInterfaces=DatasourceContainerProviderDto.class,
	dtoPackage="net.datenwerke.rs.base.client.parameters.datasource.dto",
	displayTitle="RsMessages.INSTANCE.datasourceParameterText()",
	additionalImports=RsMessages.class
)
public class DatasourceParameterDefinition extends ParameterDefinition<DatasourceParameterInstance> implements DatasourceContainerProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478441833324395566L;
	
	@ExposeToClient
	private String format = "";
	
	@ExposeToClient
	private int height = 1;
	
	@ExposeToClient
	private int width = 550;
	
	@ExposeToClient
	private SingleSelectionMode singleSelectionMode = SingleSelectionMode.Dropdown;
	
	@ExposeToClient
	private MultiSelectionMode multiSelectionMode = MultiSelectionMode.Popup;
	
	@ExposeToClient
	private BoxLayoutMode boxLayoutMode = BoxLayoutMode.TopDownLeftRight;
	
	@ExposeToClient
	private BoxLayoutPackMode boxLayoutPackMode = BoxLayoutPackMode.Columns;
	
	@ExposeToClient
	private int boxLayoutPackColSize = 1;
	
	@ExposeToClient
	private Datatype returnType = Datatype.String;
	
	@ExposeToClient
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String postProcess;
	
	@ExposeToClient
	private Mode mode = Mode.Multi;
	
	@ExposeToClient
	@EnclosedEntity 
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private DatasourceContainer datasourceContainer;
	
	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private DatasourceParameterData singleDefaultValueSimpleData;
	
	@EnclosedEntity
	@ExposeToClient
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinTable(name="DATASOURCE_P_DF_2_ML_DEF")
	private List<DatasourceParameterData> multiDefaultValueSimpleData;
	
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		if(null == mode)
			mode = Mode.Multi;
		this.mode = mode;
	}
	
	@Override
	public DatasourceContainer getDatasourceContainer() {
		return datasourceContainer;
	}

	@Override
	public void setDatasourceContainer(DatasourceContainer datasourceContainer) {
		this.datasourceContainer = datasourceContainer;
	}

	public DatasourceParameterData getSingleDefaultValueSimpleData() {
		return singleDefaultValueSimpleData;
	}

	public void setSingleDefaultValueSimpleData(
			DatasourceParameterData singleDefaultValueSimpleData) {
		/* https://hibernate.onjira.com/browse/HHH-5559 */
		if(null == singleDefaultValueSimpleData || null == this.singleDefaultValueSimpleData)
			this.singleDefaultValueSimpleData = singleDefaultValueSimpleData;
		else 
			this.singleDefaultValueSimpleData.update(singleDefaultValueSimpleData);
	}

	public List<DatasourceParameterData> getMultiDefaultValueSimpleData() {
		return multiDefaultValueSimpleData;
	}

	public void setMultiDefaultValueSimpleData(
			List<DatasourceParameterData> multiDefaultValueSimpleData) {
		if(null == multiDefaultValueSimpleData)
			multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
		if(null == this.multiDefaultValueSimpleData)
			this.multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
			
		this.multiDefaultValueSimpleData.clear();
		this.multiDefaultValueSimpleData.addAll(multiDefaultValueSimpleData);
	}
	

	public void setReturnType(Datatype returnType) {
		if(null == returnType)
			returnType = Datatype.String;
		this.returnType = returnType;
	}

	public Datatype getReturnType() {
		return returnType;
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setSingleSelectionMode(SingleSelectionMode singleSelectionMode) {
		if(null == singleSelectionMode)
			singleSelectionMode = SingleSelectionMode.Dropdown;
		this.singleSelectionMode = singleSelectionMode;
	}

	public SingleSelectionMode getSingleSelectionMode() {
		return singleSelectionMode;
	}
	
	public void setMultiSelectionMode(MultiSelectionMode multiSelectionMode) {
		if(null == multiSelectionMode)
			multiSelectionMode = MultiSelectionMode.Popup;
		this.multiSelectionMode = multiSelectionMode;
	}
	
	public MultiSelectionMode getMultiSelectionMode() {
		return multiSelectionMode;
	}
	
	public void setBoxLayoutMode(BoxLayoutMode boxLayoutMode) {
		if(null == boxLayoutMode)
			boxLayoutMode = BoxLayoutMode.LeftRightTopDown;
		this.boxLayoutMode = boxLayoutMode;
	}
	
	public BoxLayoutMode getBoxLayoutMode() {
		return boxLayoutMode;
	}
	
	public void setBoxLayoutPackMode(BoxLayoutPackMode boxLayoutPackMode) {
		if(null == boxLayoutPackMode)
			boxLayoutPackMode = BoxLayoutPackMode.Columns;
		this.boxLayoutPackMode = boxLayoutPackMode;
	}
	
	public BoxLayoutPackMode getBoxLayoutPackMode() {
		return boxLayoutPackMode;
	}
	
	public void setBoxLayoutPackColSize(int boxLayoutPackColSize) {
		this.boxLayoutPackColSize = boxLayoutPackColSize;
	}
	
	public int getBoxLayoutPackColSize() {
		return boxLayoutPackColSize;
	}

	@Override
	protected DatasourceParameterInstance doCreateParameterInstance(){
		return new DatasourceParameterInstance();
	}

	@Transient
	Object getDefaultValue(DatasourceParameterService datasourceParameterService, ParameterSetFactory parameterSetFactory, ParameterSet parameterSet) {
		if(Mode.Single.equals(getMode())){
			DatasourceParameterData data = getSingleDefaultValue(datasourceParameterService, parameterSetFactory, parameterSet);
			if(null == data)
				return null;
			return getCastedValue(data.getValue());
		} else {
			List<DatasourceParameterData> data = getMultiDefaultValueSimpleData();
			if(null == data)
				return null;
			
			/* create value list */
			List<Object> valueList = new ParameterValueArrayList<Object>();
			for(DatasourceParameterData d : data)
				valueList.add(getCastedValue(d.getValue()));
			return valueList;
		}
	}

	protected DatasourceParameterData getSingleDefaultValue(
			DatasourceParameterService datasourceParameterService,ParameterSetFactory parameterSetFactory, ParameterSet parameterSet) {
		try {
			DatasourceParameterData value = getSingleDefaultValueSimpleData();
			if(null != value)
				return value;
			
			ParameterSet dependees = parameterSetFactory.create(parameterSet.getUser(), parameterSet.getReport());
			for(ParameterDefinition def : getAllDependents()){
				for(ParameterInstance inst : parameterSet.getParameterList()){
					if(null != inst.getDefinition() && null != inst.getDefinition().getKey() && inst.getDefinition().getKey().equals(def.getKey())){
						dependees.add(inst);
						break;
					}
				}
			}
			
			List<DatasourceParameterData> list = datasourceParameterService.getParameterData(this, dependees);
			if(list.isEmpty())
				return null;

			return list.get(0);
		} catch (ReportExecutorException e) {
			throw new ReportExecutorRuntimeException(e);
		}
	}

	public Object getCastedValue(String value){
		switch(getReturnType()){
		case String:
			return value;
		case Integer:
			return Integer.parseInt(value);
		case Long:
			return Long.valueOf(value);
		case BigDecimal:
			return new BigDecimal(value);
		case Float:
			return Float.valueOf(value);
		case Double:
			return Double.valueOf(value);
		case Boolean:
			return Boolean.valueOf(value);
		case Date:
			try {
				if(null == format || "".equals(format))
					return DateFormat.getDateInstance().parseObject(value);
				else
					return new SimpleDateFormat(format).parse(value);
			} catch (ParseException e) {
				throw new RuntimeException("Could not parse date: " + value, e); 
			}
		default:
			return value;
		}
	}

	public Class<?> getType() {
		switch(getReturnType()){
		case String:
			return String.class;
		case Integer:
			return Integer.class;
		case Long:
			return Long.class;
		case BigDecimal:
			return BigDecimal.class;
		case Float:
			return Float.class;
		case Double:
			return Double.class;
		case Date:
			return Date.class;
		case Boolean:
			return Boolean.class;
		default:
			return String.class;
		}
	}

	public String getPostProcess() {
		return postProcess;
	}

	public void setPostProcess(String postProcess) {
		this.postProcess = postProcess;
	}



	
}
