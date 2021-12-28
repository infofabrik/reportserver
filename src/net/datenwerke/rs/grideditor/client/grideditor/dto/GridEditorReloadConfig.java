package net.datenwerke.rs.grideditor.client.grideditor.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sencha.gxt.data.shared.SortInfo;

import net.datenwerke.gxtdto.client.model.DwModel;

public class GridEditorReloadConfig implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 4950625065910601657L;

   private int pagesize;
   private int pagenumber;
   private SortInfo sortInfo;

   private Map<String, String> filterMap = new HashMap<String, String>();
   private Set<String> csFilter = new HashSet<String>();

   public int getPagesize() {
      return pagesize;
   }

   public void setPagesize(int pagesize) {
      this.pagesize = pagesize;
   }

   public int getPagenumber() {
      return pagenumber;
   }

   public void setPagenumber(int pagenumber) {
      this.pagenumber = pagenumber;
   }

   public SortInfo getSortInfo() {
      return sortInfo;
   }

   public void setSortInfo(SortInfo sortInfo) {
      this.sortInfo = sortInfo;
   }

   public Map<String, String> getFilterMap() {
      return filterMap;
   }

   public void setFilterMap(Map<String, String> filterMap) {
      this.filterMap = filterMap;
   }

   public void clearFilters() {
      filterMap.clear();
      csFilter.clear();
   }

   public void addFilter(String col, String v, boolean caseSensitive) {
      filterMap.put(col, v);
      if (caseSensitive)
         csFilter.add(col);
   }

   public boolean isCaseSensitive(String col) {
      return csFilter.contains(col);
   }

}
