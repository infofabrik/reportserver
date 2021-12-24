package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import static java.util.stream.Collectors.toSet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@Entity
@Table(name = "FILTER_BLOCK")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto", createDecorator = true)
public class FilterBlock implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -2009925371672029341L;

   @JoinTable(name = "FILTER_BLOCK_2_FILTERS")
   @EnclosedEntity
   @ExposeToClient(allowForeignPosoForEnclosed = true)
   @OneToMany(cascade = CascadeType.ALL)
   private Set<FilterSpec> filters = new HashSet<FilterSpec>();

   @JoinTable(name = "FILTER_BLOCK_2_CHILD_BL")
   @EnclosedEntity
   @ExposeToClient(allowForeignPosoForEnclosed = true)
   @OneToMany(cascade = CascadeType.ALL)
   private Set<FilterBlock> childBlocks = new HashSet<FilterBlock>();

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String description;

   @Version
   private Long version;

   @Transient
   private BlockType blockType;

   public Set<FilterSpec> getFilters() {
      return filters;
   }

   public void setFilters(Set<FilterSpec> filters) {
      this.filters = filters;
   }

   public Set<FilterBlock> getChildBlocks() {
      return childBlocks;
   }

   public void setChildBlocks(Set<FilterBlock> childBlocks) {
      this.childBlocks = childBlocks;
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void initBlockTypes(BlockType blockType) {
      this.blockType = blockType;

      final BlockType cblock = blockType == BlockType.AND ? BlockType.OR : BlockType.AND;
      childBlocks.forEach(c -> c.initBlockTypes(cblock));
   }

   public BlockType getBlockType() {
      if (null == blockType)
         throw new IllegalStateException("BlockTypes have not been initialized");
      return blockType;
   }
   
   public Map<BlockType, Object> asMap() {
      Map<BlockType, Object> asMap = new HashMap<>();
      Set<Object> childElements = new HashSet<>();
      
      // add all filter specs
      childElements.addAll(getFilters().stream().map(FilterSpec::asMap).collect(toSet()));
      // add all subblocks
      childElements.addAll(getChildBlocks().stream().map(FilterBlock::asMap).collect(toSet()));
      
      asMap.put(getBlockType(), childElements);
      return asMap;
   }

}
