package net.datenwerke.rs.computedcolumns.client.computedcolumns.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.AdditionalColumnSpecDtoDec;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.pa.ComputedColumnDtoPA;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.posomap.ComputedColumnDto2PosoMap;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn;

/**
 * Dto for {@link ComputedColumn}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ComputedColumnDto extends AdditionalColumnSpecDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String expression;
	private  boolean expression_m;
	public static final String PROPERTY_EXPRESSION = "dpi-computedcolumn-expression";

	private transient static PropertyAccessor<ComputedColumnDto, String> expression_pa = new PropertyAccessor<ComputedColumnDto, String>() {
		@Override
		public void setValue(ComputedColumnDto container, String object) {
			container.setExpression(object);
		}

		@Override
		public String getValue(ComputedColumnDto container) {
			return container.getExpression();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "expression";
		}

		@Override
		public void setModified(ComputedColumnDto container, boolean modified) {
			container.expression_m = modified;
		}

		@Override
		public boolean isModified(ComputedColumnDto container) {
			return container.isExpressionModified();
		}
	};


	public ComputedColumnDto() {
		super();
	}

	public String getExpression()  {
		if(! isDtoProxy()){
			return this.expression;
		}

		if(isExpressionModified())
			return this.expression;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().expression());

		return _value;
	}


	public void setExpression(String expression)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getExpression();

		/* set new value */
		this.expression = expression;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(expression_pa, oldValue, expression, this.expression_m));

		/* set indicator */
		this.expression_m = true;

		this.fireObjectChangedEvent(ComputedColumnDtoPA.INSTANCE.expression(), oldValue);
	}


	public boolean isExpressionModified()  {
		return expression_m;
	}


	public static PropertyAccessor<ComputedColumnDto, String> getExpressionPropertyAccessor()  {
		return expression_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof ComputedColumnDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ComputedColumnDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ComputedColumnDto2PosoMap();
	}

	public ComputedColumnDtoPA instantiatePropertyAccess()  {
		return GWT.create(ComputedColumnDtoPA.class);
	}

	public void clearModified()  {
		this.expression = null;
		this.expression_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(expression_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(expression_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(expression_m)
			list.add(expression_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(expression_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
