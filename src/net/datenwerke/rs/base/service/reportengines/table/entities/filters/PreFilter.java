package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Entity
@Table(name="PRE_FILTER")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class PreFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 735717078346133105L;

	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade=CascadeType.ALL)
	private FilterBlock rootBlock = new FilterBlock();
	
	@ExposeToClient
	private BlockType rootBlockType = BlockType.OR;
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String description = "";
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;

	public FilterBlock getRootBlock() {
		initBlockTypes();
		return rootBlock;
	}

	public void setRootBlock(FilterBlock rootBlock) {
		this.rootBlock = rootBlock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public BlockType getRootBlockType() {
		return rootBlockType;
	}

	public void setRootBlockType(BlockType rootBlockType) {
		if(null == rootBlockType)
			rootBlockType = BlockType.OR;
		this.rootBlockType = rootBlockType;
	}
	
	public void initBlockTypes(){
		rootBlock.initBlockTypes(rootBlockType);
	}
	
	public Collection<Column> getAllColumns(){
		Set<Column> columns = new HashSet<Column>();
		
		FilterBlock block = getRootBlock();
		getAllColumns(columns, block);
		
		return columns;
	}

	private void getAllColumns(Set<Column> columns, FilterBlock block) {
		if(null == block)
			return;
		for(FilterSpec f : block.getFilters())
			columns.addAll(f.getColumns());
		for(FilterBlock c : block.getChildBlocks())
			getAllColumns(columns, c);
	}
	
}
