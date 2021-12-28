package net.datenwerke.rs.search.service.search.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.search.service.search.results.post.SearchEntry2DtoPost;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.search.client.search.dto", createDecorator = true, poso2DtoPostProcessors = SearchEntry2DtoPost.class, displayTitle = "getTitle()", additionalFields = {
      @AdditionalField(name = "resultObject", type = DwModel.class) })
public class SearchResultEntry implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 8865021870054654248L;

   @ExposeToClient
   private String title;

   @ExposeToClient
   private String description;

   @ExposeToClient
   private Date lastModified;

   @ExposeToClient
   private Long objectId;

   @ExposeToClient
   private String iconSmall;

   @ExposeToClient(id = true)
   private Long id;

   @ExposeToClient
   @EnclosedEntity
   private List<HistoryLink> links = new ArrayList<HistoryLink>();

   @ExposeToClient
   @EnclosedEntity
   private Set<SearchResultTag> tags = new HashSet<SearchResultTag>();

   private Object resultObject;

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getIconSmall() {
      return iconSmall;
   }

   public void setIconSmall(String iconSmall) {
      this.iconSmall = iconSmall;
   }

   public void setLinks(List<HistoryLink> links) {
      this.links = links;
   }

   public List<HistoryLink> getLinks() {
      return links;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setResultObject(Object resultObject) {
      this.resultObject = resultObject;
   }

   public Object getResultObject() {
      return resultObject;
   }

   public void setTags(Set<SearchResultTag> tags) {
      this.tags = tags;
   }

   public Set<SearchResultTag> getTags() {
      return tags;
   }

   public void addTag(SearchResultTag tag) {
      tags.add(tag);
   }

   public Date getLastModified() {
      return lastModified;
   }

   public void setLastModified(Date lastModified) {
      this.lastModified = lastModified;
   }

   public Long getObjectId() {
      return objectId;
   }

   public void setObjectId(Long objectId) {
      this.objectId = objectId;
   }

}
