package net.datenwerke.scheduler.service.scheduler.stores.jpa.filter;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.filter")
public interface JobFilterCriteria {

   public List<Predicate> prepareCriteriaQuery(CriteriaBuilder builder, CriteriaQuery<?> cQuery,
         Root<? extends AbstractJob> root);
}
