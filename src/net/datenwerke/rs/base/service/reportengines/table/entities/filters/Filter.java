package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;

/**
 * 
 *
 */
@Entity
@Table(name="FILTER")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -603604505949636058L;
	
	@Inject
	protected static I18nToolsService i18nTools;
	
	
	@JoinTable(name="FILTER_2_INCLUDE_VAL")
	@ExposeToClient
	@ElementCollection
	@OrderColumn(name="val_n")
	private List<String> includeValues = new ArrayList<String>();
	
	@JoinTable(name="FILTER_2_EXCLUDE_VAL")
	@ExposeToClient
	@ElementCollection
	@OrderColumn(name="val_n")
	private List<String> excludeValues = new ArrayList<String>();
	
	@ExposeToClient
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="FILTER_2_FILTER_RNG_INC")
	private List<FilterRange> includeRanges = new ArrayList<FilterRange>();
	
	@ExposeToClient
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="FILTER_2_FILTER_RNG_EXC")
	private List<FilterRange> excludeRanges = new ArrayList<FilterRange>();
	
	@ExposeToClient
	private Boolean caseSensitive = true;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public void setIncludeValues(List<String> includeValues) {
		if(null != includeValues){
			Iterator<String> it = includeValues.iterator();
			while(it.hasNext())
				if(null == it.next())
					it.remove();
		}
		this.includeValues = includeValues;
	}

	public List<String> getIncludeValues() {
		return includeValues == null ? new ArrayList<String>() : includeValues;
	}
	
	public void setExcludeValues(List<String> excludeValues) {
		if(null != excludeValues){
			Iterator<String> it = excludeValues.iterator();
			while(it.hasNext())
				if(null == it.next())
					it.remove();
		}
		this.excludeValues = excludeValues;
	}

	public List<String> getExcludeValues() {
		return excludeValues == null ? new ArrayList<String>() : excludeValues;
	}
	
	public void setIncludeRanges(List<FilterRange> includeRanges) {
		this.includeRanges = includeRanges;
	}
	
	public List<FilterRange> getIncludeRanges() {
		return includeRanges;
	}
	
	public void setExcludeRanges(List<FilterRange> excludeRanges) {
		this.excludeRanges = excludeRanges;
	}
	
	public List<FilterRange> getExcludeRanges() {
		return excludeRanges;
	}

	public void setCaseSensitive(Boolean caseSensitive) {
		if(null == caseSensitive)
			caseSensitive = true;
		this.caseSensitive = caseSensitive;
	}

	public Boolean isCaseSensitive() {
		return null == caseSensitive ? true : caseSensitive;
	}
	
	public static boolean containsWildcard(String filterexp){
		return filterexp.contains("?") || filterexp.contains("*");
	}
	
	public static boolean isAdvancedFilterExp(String filterexp){
		if(null == filterexp)
			return false;
		return filterexp.contains("${") || containsWildcard(filterexp);
	}

	public void verifyNumberFormat() throws ParseException {
		NumberFormat systemNumberFormatter = i18nTools.getSystemNumberFormatter();
		
		for(String filter : getIncludeValues())
			if(! isAdvancedFilterExp(filter))
				systemNumberFormatter.parse(filter);
		
		for(String filter : getExcludeValues())
			if(! isAdvancedFilterExp(filter))
				systemNumberFormatter.parse(filter);
		
		for(FilterRange range : getIncludeRanges()){
			if(null != range.getRangeFrom() && ! isAdvancedFilterExp(range.getRangeFrom()))
				systemNumberFormatter.parse(range.getRangeFrom());
			if(null != range.getRangeTo() && ! isAdvancedFilterExp(range.getRangeTo()))
				systemNumberFormatter.parse(range.getRangeTo());
		}
		
		for(FilterRange range : getExcludeRanges()){
			if(null != range.getRangeFrom() && ! isAdvancedFilterExp(range.getRangeFrom()))
				systemNumberFormatter.parse(range.getRangeFrom());
			if(null != range.getRangeTo() && ! isAdvancedFilterExp(range.getRangeTo()))
				systemNumberFormatter.parse(range.getRangeTo());
		}
	}

}
