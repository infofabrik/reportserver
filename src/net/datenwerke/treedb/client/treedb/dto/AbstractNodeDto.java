package net.datenwerke.treedb.client.treedb.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.treedb.client.treedb.dto.pa.AbstractNodeDtoPA;
import net.datenwerke.treedb.client.treedb.dto.posomap.AbstractNodeDto2PosoMap;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * Dto for {@link AbstractNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class AbstractNodeDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Date createdOn;
	private  boolean createdOn_m;
	public static final String PROPERTY_CREATED_ON = "dpi-abstractnode-createdon";

	private transient static PropertyAccessor<AbstractNodeDto, Date> createdOn_pa = new PropertyAccessor<AbstractNodeDto, Date>() {
		@Override
		public void setValue(AbstractNodeDto container, Date object) {
			container.setCreatedOn(object);
		}

		@Override
		public Date getValue(AbstractNodeDto container) {
			return container.getCreatedOn();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "createdOn";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.createdOn_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isCreatedOnModified();
		}
	};

	private long flags;
	private  boolean flags_m;
	public static final String PROPERTY_FLAGS = "dpi-abstractnode-flags";

	private transient static PropertyAccessor<AbstractNodeDto, Long> flags_pa = new PropertyAccessor<AbstractNodeDto, Long>() {
		@Override
		public void setValue(AbstractNodeDto container, Long object) {
			container.setFlags(object);
		}

		@Override
		public Long getValue(AbstractNodeDto container) {
			return container.getFlags();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "flags";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.flags_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isFlagsModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-abstractnode-id";

	private transient static PropertyAccessor<AbstractNodeDto, Long> id_pa = new PropertyAccessor<AbstractNodeDto, Long>() {
		@Override
		public void setValue(AbstractNodeDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(AbstractNodeDto container) {
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
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isIdModified();
		}
	};

	private Date lastUpdated;
	private  boolean lastUpdated_m;
	public static final String PROPERTY_LAST_UPDATED = "dpi-abstractnode-lastupdated";

	private transient static PropertyAccessor<AbstractNodeDto, Date> lastUpdated_pa = new PropertyAccessor<AbstractNodeDto, Date>() {
		@Override
		public void setValue(AbstractNodeDto container, Date object) {
			container.setLastUpdated(object);
		}

		@Override
		public Date getValue(AbstractNodeDto container) {
			return container.getLastUpdated();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "lastUpdated";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.lastUpdated_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isLastUpdatedModified();
		}
	};

	private int position;
	private  boolean position_m;
	public static final String PROPERTY_POSITION = "dpi-abstractnode-position";

	private transient static PropertyAccessor<AbstractNodeDto, Integer> position_pa = new PropertyAccessor<AbstractNodeDto, Integer>() {
		@Override
		public void setValue(AbstractNodeDto container, Integer object) {
			container.setPosition(object);
		}

		@Override
		public Integer getValue(AbstractNodeDto container) {
			return container.getPosition();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "position";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.position_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isPositionModified();
		}
	};

	private Boolean hasChildren;
	private  boolean hasChildren_m;
	public static final String PROPERTY_HAS_CHILDREN = "dpi-abstractnode-haschildren";

	private transient static PropertyAccessor<AbstractNodeDto, Boolean> hasChildren_pa = new PropertyAccessor<AbstractNodeDto, Boolean>() {
		@Override
		public void setValue(AbstractNodeDto container, Boolean object) {
			container.setHasChildren(object);
		}

		@Override
		public Boolean getValue(AbstractNodeDto container) {
			return container.isHasChildren();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasChildren";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.hasChildren_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isHasChildrenModified();
		}
	};

	private Long parentNodeId;
	private  boolean parentNodeId_m;
	public static final String PROPERTY_PARENT_NODE_ID = "dpi-abstractnode-parentnodeid";

	private transient static PropertyAccessor<AbstractNodeDto, Long> parentNodeId_pa = new PropertyAccessor<AbstractNodeDto, Long>() {
		@Override
		public void setValue(AbstractNodeDto container, Long object) {
			container.setParentNodeId(object);
		}

		@Override
		public Long getValue(AbstractNodeDto container) {
			return container.getParentNodeId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "parentNodeId";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.parentNodeId_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isParentNodeIdModified();
		}
	};

	private String parentNodeType;
	private  boolean parentNodeType_m;
	public static final String PROPERTY_PARENT_NODE_TYPE = "dpi-abstractnode-parentnodetype";

	private transient static PropertyAccessor<AbstractNodeDto, String> parentNodeType_pa = new PropertyAccessor<AbstractNodeDto, String>() {
		@Override
		public void setValue(AbstractNodeDto container, String object) {
			container.setParentNodeType(object);
		}

		@Override
		public String getValue(AbstractNodeDto container) {
			return container.getParentNodeType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "parentNodeType";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.parentNodeType_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isParentNodeTypeModified();
		}
	};

	private ArrayList<Long> rootPath;
	private  boolean rootPath_m;
	public static final String PROPERTY_ROOT_PATH = "dpi-abstractnode-rootpath";

	private transient static PropertyAccessor<AbstractNodeDto, ArrayList<Long>> rootPath_pa = new PropertyAccessor<AbstractNodeDto, ArrayList<Long>>() {
		@Override
		public void setValue(AbstractNodeDto container, ArrayList<Long> object) {
			container.setRootPath(object);
		}

		@Override
		public ArrayList<Long> getValue(AbstractNodeDto container) {
			return container.getRootPath();
		}

		@Override
		public Class<?> getType() {
			return ArrayList.class;
		}

		@Override
		public String getPath() {
			return "rootPath";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.rootPath_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isRootPathModified();
		}
	};

	private String rootName;
	private  boolean rootName_m;
	public static final String PROPERTY_ROOT_NAME = "dpi-abstractnode-rootname";

	private transient static PropertyAccessor<AbstractNodeDto, String> rootName_pa = new PropertyAccessor<AbstractNodeDto, String>() {
		@Override
		public void setValue(AbstractNodeDto container, String object) {
			container.setRootName(object);
		}

		@Override
		public String getValue(AbstractNodeDto container) {
			return container.getRootName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "rootName";
		}

		@Override
		public void setModified(AbstractNodeDto container, boolean modified) {
			container.rootName_m = modified;
		}

		@Override
		public boolean isModified(AbstractNodeDto container) {
			return container.isRootNameModified();
		}
	};


	public AbstractNodeDto() {
		super();
	}

	public Date getCreatedOn()  {
		if(! isDtoProxy()){
			return this.createdOn;
		}

		if(isCreatedOnModified())
			return this.createdOn;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().createdOn());

		return _value;
	}


	public void setCreatedOn(Date createdOn)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatedOn();

		/* set new value */
		this.createdOn = createdOn;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(createdOn_pa, oldValue, createdOn, this.createdOn_m));

		/* set indicator */
		this.createdOn_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.createdOn(), oldValue);
	}


	public boolean isCreatedOnModified()  {
		return createdOn_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Date> getCreatedOnPropertyAccessor()  {
		return createdOn_pa;
	}


	public long getFlags()  {
		if(! isDtoProxy()){
			return this.flags;
		}

		if(isFlagsModified())
			return this.flags;

		if(! GWT.isClient())
			return 0;

		long _value = dtoManager.getProperty(this, instantiatePropertyAccess().flags());

		return _value;
	}


	public void setFlags(long flags)  {
		/* old value */
		long oldValue = 0;
		if(GWT.isClient())
			oldValue = getFlags();

		/* set new value */
		this.flags = flags;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(flags_pa, oldValue, flags, this.flags_m));

		/* set indicator */
		this.flags_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.flags(), oldValue);
	}


	public boolean isFlagsModified()  {
		return flags_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Long> getFlagsPropertyAccessor()  {
		return flags_pa;
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


	public static PropertyAccessor<AbstractNodeDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public Date getLastUpdated()  {
		if(! isDtoProxy()){
			return this.lastUpdated;
		}

		if(isLastUpdatedModified())
			return this.lastUpdated;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().lastUpdated());

		return _value;
	}


	public void setLastUpdated(Date lastUpdated)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getLastUpdated();

		/* set new value */
		this.lastUpdated = lastUpdated;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lastUpdated_pa, oldValue, lastUpdated, this.lastUpdated_m));

		/* set indicator */
		this.lastUpdated_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.lastUpdated(), oldValue);
	}


	public boolean isLastUpdatedModified()  {
		return lastUpdated_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Date> getLastUpdatedPropertyAccessor()  {
		return lastUpdated_pa;
	}


	public int getPosition()  {
		if(! isDtoProxy()){
			return this.position;
		}

		if(isPositionModified())
			return this.position;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().position());

		return _value;
	}


	public void setPosition(int position)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getPosition();

		/* set new value */
		this.position = position;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(position_pa, oldValue, position, this.position_m));

		/* set indicator */
		this.position_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.position(), oldValue);
	}


	public boolean isPositionModified()  {
		return position_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Integer> getPositionPropertyAccessor()  {
		return position_pa;
	}


	public Boolean isHasChildren()  {
		if(! isDtoProxy()){
			return this.hasChildren;
		}

		if(isHasChildrenModified())
			return this.hasChildren;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasChildren());

		return _value;
	}


	public void setHasChildren(Boolean hasChildren)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasChildren();

		/* set new value */
		this.hasChildren = hasChildren;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasChildren_pa, oldValue, hasChildren, this.hasChildren_m));

		/* set indicator */
		this.hasChildren_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.hasChildren(), oldValue);
	}


	public boolean isHasChildrenModified()  {
		return hasChildren_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Boolean> getHasChildrenPropertyAccessor()  {
		return hasChildren_pa;
	}


	public Long getParentNodeId()  {
		if(! isDtoProxy()){
			return this.parentNodeId;
		}

		if(isParentNodeIdModified())
			return this.parentNodeId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentNodeId());

		return _value;
	}


	public void setParentNodeId(Long parentNodeId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getParentNodeId();

		/* set new value */
		this.parentNodeId = parentNodeId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentNodeId_pa, oldValue, parentNodeId, this.parentNodeId_m));

		/* set indicator */
		this.parentNodeId_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.parentNodeId(), oldValue);
	}


	public boolean isParentNodeIdModified()  {
		return parentNodeId_m;
	}


	public static PropertyAccessor<AbstractNodeDto, Long> getParentNodeIdPropertyAccessor()  {
		return parentNodeId_pa;
	}


	public String getParentNodeType()  {
		if(! isDtoProxy()){
			return this.parentNodeType;
		}

		if(isParentNodeTypeModified())
			return this.parentNodeType;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().parentNodeType());

		return _value;
	}


	public void setParentNodeType(String parentNodeType)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getParentNodeType();

		/* set new value */
		this.parentNodeType = parentNodeType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(parentNodeType_pa, oldValue, parentNodeType, this.parentNodeType_m));

		/* set indicator */
		this.parentNodeType_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.parentNodeType(), oldValue);
	}


	public boolean isParentNodeTypeModified()  {
		return parentNodeType_m;
	}


	public static PropertyAccessor<AbstractNodeDto, String> getParentNodeTypePropertyAccessor()  {
		return parentNodeType_pa;
	}


	public ArrayList<Long> getRootPath()  {
		if(! isDtoProxy()){
			ArrayList<Long> _currentValue = this.rootPath;
			if(null == _currentValue)
				this.rootPath = new ArrayList<Long>();

			return this.rootPath;
		}

		if(isRootPathModified())
			return this.rootPath;

		if(! GWT.isClient())
			return null;

		ArrayList<Long> _value = dtoManager.getProperty(this, instantiatePropertyAccess().rootPath());

		return _value;
	}


	public void setRootPath(ArrayList<Long> rootPath)  {
		/* old value */
		ArrayList<Long> oldValue = null;
		if(GWT.isClient())
			oldValue = getRootPath();

		/* set new value */
		this.rootPath = rootPath;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rootPath_pa, oldValue, rootPath, this.rootPath_m));

		/* set indicator */
		this.rootPath_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.rootPath(), oldValue);
	}


	public boolean isRootPathModified()  {
		return rootPath_m;
	}


	public static PropertyAccessor<AbstractNodeDto, ArrayList<Long>> getRootPathPropertyAccessor()  {
		return rootPath_pa;
	}


	public String getRootName()  {
		if(! isDtoProxy()){
			return this.rootName;
		}

		if(isRootNameModified())
			return this.rootName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().rootName());

		return _value;
	}


	public void setRootName(String rootName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRootName();

		/* set new value */
		this.rootName = rootName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rootName_pa, oldValue, rootName, this.rootName_m));

		/* set indicator */
		this.rootName_m = true;

		this.fireObjectChangedEvent(AbstractNodeDtoPA.INSTANCE.rootName(), oldValue);
	}


	public boolean isRootNameModified()  {
		return rootName_m;
	}


	public static PropertyAccessor<AbstractNodeDto, String> getRootNamePropertyAccessor()  {
		return rootName_pa;
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
		if(! (obj instanceof AbstractNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AbstractNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AbstractNodeDto2PosoMap();
	}

	public AbstractNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(AbstractNodeDtoPA.class);
	}

	public void clearModified()  {
		this.createdOn = null;
		this.createdOn_m = false;
		this.flags = 0;
		this.flags_m = false;
		this.id = null;
		this.id_m = false;
		this.lastUpdated = null;
		this.lastUpdated_m = false;
		this.position = 0;
		this.position_m = false;
		this.hasChildren = null;
		this.hasChildren_m = false;
		this.parentNodeId = null;
		this.parentNodeId_m = false;
		this.parentNodeType = null;
		this.parentNodeType_m = false;
		this.rootPath = null;
		this.rootPath_m = false;
		this.rootName = null;
		this.rootName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(createdOn_m)
			return true;
		if(flags_m)
			return true;
		if(id_m)
			return true;
		if(lastUpdated_m)
			return true;
		if(position_m)
			return true;
		if(hasChildren_m)
			return true;
		if(parentNodeId_m)
			return true;
		if(parentNodeType_m)
			return true;
		if(rootPath_m)
			return true;
		if(rootName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(createdOn_pa);
		list.add(flags_pa);
		list.add(id_pa);
		list.add(lastUpdated_pa);
		list.add(position_pa);
		list.add(hasChildren_pa);
		list.add(parentNodeId_pa);
		list.add(parentNodeType_pa);
		list.add(rootPath_pa);
		list.add(rootName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(createdOn_m)
			list.add(createdOn_pa);
		if(flags_m)
			list.add(flags_pa);
		if(id_m)
			list.add(id_pa);
		if(lastUpdated_m)
			list.add(lastUpdated_pa);
		if(position_m)
			list.add(position_pa);
		if(hasChildren_m)
			list.add(hasChildren_pa);
		if(parentNodeId_m)
			list.add(parentNodeId_pa);
		if(parentNodeType_m)
			list.add(parentNodeType_pa);
		if(rootPath_m)
			list.add(rootPath_pa);
		if(rootName_m)
			list.add(rootName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(position_pa);
			list.add(hasChildren_pa);
			list.add(parentNodeId_pa);
			list.add(parentNodeType_pa);
			list.add(rootPath_pa);
			list.add(rootName_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(createdOn_pa);
			list.add(lastUpdated_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(flags_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
