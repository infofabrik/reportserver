package net.datenwerke.rs.base.service.reportengines.table.entities.post;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;

import com.google.inject.Inject;

public class ColumnDtoPost implements Dto2PosoPostProcessor<ColumnDto, Column>, Poso2DtoPostProcessor<Column, ColumnDto> {

	private final I18nToolsService i18nToolsService;
	
	@Inject
	public ColumnDtoPost(I18nToolsService i18nToolsService){
		this.i18nToolsService = i18nToolsService;
	}
	
	@Override
	public void posoCreated(ColumnDto arg0, Column poso) {
		adjustFromUserToSystem(poso);
	}

	@Override
	public void posoCreatedUnmanaged(ColumnDto arg0, Column poso) {
		adjustFromUserToSystem(poso);
	}

	@Override
	public void posoInstantiated(Column poso) {
		
	}

	@Override
	public void posoLoaded(ColumnDto arg0, Column poso) {
		adjustFromUserToSystem(poso);
	}

	@Override
	public void posoMerged(ColumnDto arg0, Column poso) {
		adjustFromUserToSystem(poso);
	}

	@Override
	public void dtoCreated(Column poso, ColumnDto dto) {
		adjustFromSystemToUser(dto);
	}

	@Override
	public void dtoInstantiated(Column arg0, ColumnDto arg1) {
		
	}
	
	/**
	 * Converts all decimal values in the supplied reports filters from the users locale to the system default setting
	 * 
	 * @param report
	 */
	private void adjustFromUserToSystem(Column c){
		if(SqlTypes.isNumerical(c.getType())){
			Filter filter = c.getFilter();
			if(null != filter){
				filter.setIncludeValues(translateFilterToSystem(filter.getIncludeValues()));
				filter.setExcludeValues(translateFilterToSystem(filter.getExcludeValues()));

				translateFilterRangeToSystem(filter.getIncludeRanges());
				translateFilterRangeToSystem(filter.getExcludeRanges());
			}
		}
	}
	
	/**
	 * Changes all decimal numbers in filters in the given report to reflect the users locale
	 * 
	 * @param tmpReport
	 */
	private void adjustFromSystemToUser(ColumnDto c){
		if(SqlTypes.isNumerical(c.getType())){
			FilterDto filter = c.getFilter();
			if(null != filter){
				filter.setIncludeValues(translateFilterToUser(filter.getIncludeValues()));
				filter.setExcludeValues(translateFilterToUser(filter.getExcludeValues()));
	
				translateFilterRangeDtoToUser(filter.getIncludeRanges());
				translateFilterRangeDtoToUser(filter.getExcludeRanges());
			}
		}
	}
	
	private void translateFilterRangeToSystem(List<FilterRange> filter){
		for(FilterRange fr : filter){
			if(! Filter.isAdvancedFilterExp(fr.getRangeFrom())){
				String from = i18nToolsService.translateNumberFromUserToSystem(fr.getRangeFrom());
				i18nToolsService.validateSystemNumber(from);
				fr.setRangeFrom(from);
			}
			
			if(! Filter.isAdvancedFilterExp(fr.getRangeTo())){
				String to = i18nToolsService.translateNumberFromUserToSystem(fr.getRangeTo());
				i18nToolsService.validateSystemNumber(to);
				fr.setRangeTo(to);
			}
		}
	}
	
	private List<String> translateFilterToSystem(List<String> filter){
		ArrayList<String> res = new ArrayList<String>();
		for(String fe : filter){
			if(! Filter.isAdvancedFilterExp(fe)){
				String translated = i18nToolsService.translateNumberFromUserToSystem(fe);
				i18nToolsService.validateSystemNumber(translated);
				res.add(translated);
			} else 
				res.add(fe);
		}
		return res;

	}
	


	private void translateFilterRangeDtoToUser(List<FilterRangeDto> list){
		for(FilterRangeDto fr : list){
			if(! Filter.isAdvancedFilterExp(fr.getRangeFrom()))
				fr.setRangeFrom(i18nToolsService.translateNumberFromSystemToUser(fr.getRangeFrom()));
			if(! Filter.isAdvancedFilterExp(fr.getRangeTo()))
				fr.setRangeTo(i18nToolsService.translateNumberFromSystemToUser(fr.getRangeTo()));
		}
	}
		
	private List<String> translateFilterToUser(List<String> filter){
		ArrayList<String> res = new ArrayList<String>();
		for(String fe : filter){
			if(! Filter.isAdvancedFilterExp(fe)){
				String translated = i18nToolsService.translateNumberFromSystemToUser(fe);
				res.add(translated);
			} else 
				res.add(fe);
		}
		return res;
	}
	


}
