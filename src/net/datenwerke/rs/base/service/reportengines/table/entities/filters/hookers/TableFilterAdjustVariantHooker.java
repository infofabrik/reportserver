package net.datenwerke.rs.base.service.reportengines.table.entities.filters.hookers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeEditedHook;

public class TableFilterAdjustVariantHooker implements VariantToBeEditedHook {

   private final Provider<EntityManager> entityManagerProvider;
   private final TableReportUtils tableReportUtils;

   @Inject
   public TableFilterAdjustVariantHooker(Provider<EntityManager> entityManagerProvider,
         TableReportUtils tableReportUtils) {
      this.entityManagerProvider = entityManagerProvider;
      this.tableReportUtils = tableReportUtils;
   }

   @Override
   public void variantToBeEdited(Report referenceVariant, ReportDto reportDto, String executorToken)
         throws ServerCallFailedException {
      if (!(referenceVariant instanceof TableReportVariant))
         return;

      Set<Long> filterBlocksInDb = getAllFilterBlocks((TableReportVariant) referenceVariant);
      Set<Long> filterBlocksInUserVariant = getAllFilterBlocks((TableReportDto) reportDto);

      Set<Long> deletedFilterBlockIds = Sets.difference(filterBlocksInDb, filterBlocksInUserVariant).immutableCopy();
      Set<FilterBlock> deletedFilterBlocks = getFilterBlocks(deletedFilterBlockIds);

      Iterator<FilterBlock> it = deletedFilterBlocks.iterator();
      while (it.hasNext()) {
         FilterBlock toDelete = it.next();
         tableReportUtils.remove(toDelete);
      }

      /*
       * Call flush because of
       * https://developer.jboss.org/wiki/HibernateFAQ-AdvancedProblems?_sscc=t#
       * jive_content_id_Hibernate_is_violating_a_unique_constraint (RS-3645)
       */
      if (!deletedFilterBlocks.isEmpty()) {
         EntityManager em = entityManagerProvider.get();
         em.flush();
      }

   }

   private Set<FilterBlock> getFilterBlocks(Set<Long> filterBlockIds) throws ServerCallFailedException {
      Set<FilterBlock> filterBlocks = new HashSet<>();

      EntityManager em = entityManagerProvider.get();

      Iterator<Long> it = filterBlockIds.iterator();
      while (it.hasNext()) {
         Long filterBlockId = it.next();

         FilterBlock filterBlock = em.find(FilterBlock.class, filterBlockId);
         if (null != filterBlock)
            filterBlocks.add(filterBlock);
         else
            throw new ServerCallFailedException("No filter block found for id: " + filterBlockId);
      }

      return filterBlocks;
   }

   private Set<Long> getAllFilterBlocks(TableReport report) {
      Set<Long> filterBlocks = new HashSet<>();

      PreFilter prefilter = report.getPreFilter();
      FilterBlock rootBlock = prefilter.getRootBlock();

      filterBlocks = doGetAllFilterBlocks(rootBlock);

      return filterBlocks;
   }

   private Set<Long> doGetAllFilterBlocks(FilterBlock filterBlock) {
      Set<Long> filterBlocks = new HashSet<>();
      filterBlocks.add(filterBlock.getId());

      if (filterBlock.getChildBlocks().isEmpty())
         return filterBlocks;

      Iterator<FilterBlock> childrenFilterBlockIt = filterBlock.getChildBlocks().iterator();
      while (childrenFilterBlockIt.hasNext()) {
         FilterBlock nextFilterBlock = childrenFilterBlockIt.next();
         filterBlocks.addAll(doGetAllFilterBlocks(nextFilterBlock));
      }

      return filterBlocks;
   }

   private Set<Long> getAllFilterBlocks(TableReportDto report) {
      Set<Long> filterBlocks = new HashSet<>();

      PreFilterDto prefilter = report.getPreFilter();
      FilterBlockDto rootBlock = prefilter.getRootBlock();

      filterBlocks = doGetAllFilterBlocks(rootBlock);

      return filterBlocks;
   }

   private Set<Long> doGetAllFilterBlocks(FilterBlockDto filterBlock) {

      Set<Long> filterBlocks = new HashSet<>();
      filterBlocks.add((Long) filterBlock.getDtoId());

      if (filterBlock.getChildBlocks().isEmpty())
         return filterBlocks;

      Iterator<FilterBlockDto> childrenFilterBlockIt = filterBlock.getChildBlocks().iterator();
      while (childrenFilterBlockIt.hasNext()) {
         FilterBlockDto nextFilterBlock = childrenFilterBlockIt.next();
         filterBlocks.addAll(doGetAllFilterBlocks(nextFilterBlock));
      }

      return filterBlocks;

   }

}
