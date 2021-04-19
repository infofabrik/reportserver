package net.datenwerke.scheduler.client.scheduler.dto.history;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.pa.ActionEntryDtoPA;
import net.datenwerke.scheduler.client.scheduler.dto.history.posomap.ActionEntryDto2PosoMap;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;

/**
 * Dto for {@link ActionEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ActionEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String actionName;
	private  boolean actionName_m;
	public static final String PROPERTY_ACTION_NAME = "dpi-actionentry-actionname";

	private transient static PropertyAccessor<ActionEntryDto, String> actionName_pa = new PropertyAccessor<ActionEntryDto, String>() {
		@Override
		public void setValue(ActionEntryDto container, String object) {
			container.setActionName(object);
		}

		@Override
		public String getValue(ActionEntryDto container) {
			return container.getActionName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "actionName";
		}

		@Override
		public void setModified(ActionEntryDto container, boolean modified) {
			container.actionName_m = modified;
		}

		@Override
		public boolean isModified(ActionEntryDto container) {
			return container.isActionNameModified();
		}
	};

	private String errorDescription;
	private  boolean errorDescription_m;
	public static final String PROPERTY_ERROR_DESCRIPTION = "dpi-actionentry-errordescription";

	private transient static PropertyAccessor<ActionEntryDto, String> errorDescription_pa = new PropertyAccessor<ActionEntryDto, String>() {
		@Override
		public void setValue(ActionEntryDto container, String object) {
			container.setErrorDescription(object);
		}

		@Override
		public String getValue(ActionEntryDto container) {
			return container.getErrorDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "errorDescription";
		}

		@Override
		public void setModified(ActionEntryDto container, boolean modified) {
			container.errorDescription_m = modified;
		}

		@Override
		public boolean isModified(ActionEntryDto container) {
			return container.isErrorDescriptionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-actionentry-id";

	private transient static PropertyAccessor<ActionEntryDto, Long> id_pa = new PropertyAccessor<ActionEntryDto, Long>() {
		@Override
		public void setValue(ActionEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(ActionEntryDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(ActionEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(ActionEntryDto container) {
			return container.isIdModified();
		}
	};

	private OutcomeDto outcome;
	private  boolean outcome_m;
	public static final String PROPERTY_OUTCOME = "dpi-actionentry-outcome";

	private transient static PropertyAccessor<ActionEntryDto, OutcomeDto> outcome_pa = new PropertyAccessor<ActionEntryDto, OutcomeDto>() {
		@Override
		public void setValue(ActionEntryDto container, OutcomeDto object) {
			container.setOutcome(object);
		}

		@Override
		public OutcomeDto getValue(ActionEntryDto container) {
			return container.getOutcome();
		}

		@Override
		public Class<?> getType() {
			return OutcomeDto.class;
		}

		@Override
		public String getPath() {
			return "outcome";
		}

		@Override
		public void setModified(ActionEntryDto container, boolean modified) {
			container.outcome_m = modified;
		}

		@Override
		public boolean isModified(ActionEntryDto container) {
			return container.isOutcomeModified();
		}
	};


	public ActionEntryDto() {
		super();
	}

	public String getActionName()  {
		if(! isDtoProxy()){
			return this.actionName;
		}

		if(isActionNameModified())
			return this.actionName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().actionName());

		return _value;
	}


	public void setActionName(String actionName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getActionName();

		/* set new value */
		this.actionName = actionName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(actionName_pa, oldValue, actionName, this.actionName_m));

		/* set indicator */
		this.actionName_m = true;

		this.fireObjectChangedEvent(ActionEntryDtoPA.INSTANCE.actionName(), oldValue);
	}


	public boolean isActionNameModified()  {
		return actionName_m;
	}


	public static PropertyAccessor<ActionEntryDto, String> getActionNamePropertyAccessor()  {
		return actionName_pa;
	}


	public String getErrorDescription()  {
		if(! isDtoProxy()){
			return this.errorDescription;
		}

		if(isErrorDescriptionModified())
			return this.errorDescription;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().errorDescription());

		return _value;
	}


	public void setErrorDescription(String errorDescription)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getErrorDescription();

		/* set new value */
		this.errorDescription = errorDescription;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(errorDescription_pa, oldValue, errorDescription, this.errorDescription_m));

		/* set indicator */
		this.errorDescription_m = true;

		this.fireObjectChangedEvent(ActionEntryDtoPA.INSTANCE.errorDescription(), oldValue);
	}


	public boolean isErrorDescriptionModified()  {
		return errorDescription_m;
	}


	public static PropertyAccessor<ActionEntryDto, String> getErrorDescriptionPropertyAccessor()  {
		return errorDescription_pa;
	}


	public final Long getId()  {
		return dtoId;
	}

	public final void setId(Long id)  {
		if (null != dtoId)
			throw new IllegalStateException("Id already set!");
		this.dtoId = id;
	}

	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<ActionEntryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public OutcomeDto getOutcome()  {
		if(! isDtoProxy()){
			return this.outcome;
		}

		if(isOutcomeModified())
			return this.outcome;

		if(! GWT.isClient())
			return null;

		OutcomeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().outcome());

		return _value;
	}


	public void setOutcome(OutcomeDto outcome)  {
		/* old value */
		OutcomeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOutcome();

		/* set new value */
		this.outcome = outcome;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(outcome_pa, oldValue, outcome, this.outcome_m));

		/* set indicator */
		this.outcome_m = true;

		this.fireObjectChangedEvent(ActionEntryDtoPA.INSTANCE.outcome(), oldValue);
	}


	public boolean isOutcomeModified()  {
		return outcome_m;
	}


	public static PropertyAccessor<ActionEntryDto, OutcomeDto> getOutcomePropertyAccessor()  {
		return outcome_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setId((Long) id);
	}

	@Override
	public Object getDtoId()  {
		return getId();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ActionEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ActionEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ActionEntryDto2PosoMap();
	}

	public ActionEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(ActionEntryDtoPA.class);
	}

	public void clearModified()  {
		this.actionName = null;
		this.actionName_m = false;
		this.errorDescription = null;
		this.errorDescription_m = false;
		this.id = null;
		this.id_m = false;
		this.outcome = null;
		this.outcome_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(actionName_m)
			return true;
		if(errorDescription_m)
			return true;
		if(id_m)
			return true;
		if(outcome_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(actionName_pa);
		list.add(errorDescription_pa);
		list.add(id_pa);
		list.add(outcome_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(actionName_m)
			list.add(actionName_pa);
		if(errorDescription_m)
			list.add(errorDescription_pa);
		if(id_m)
			list.add(id_pa);
		if(outcome_m)
			list.add(outcome_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(actionName_pa);
			list.add(errorDescription_pa);
			list.add(outcome_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto wl_0;

}
