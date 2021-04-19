package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.FavoriteList2DtoGenerator;

/**
 * Poso2DtoGenerator for FavoriteList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FavoriteList2DtoGenerator implements Poso2DtoGenerator<FavoriteList,FavoriteListDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FavoriteList2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FavoriteListDtoDec instantiateDto(FavoriteList poso)  {
		FavoriteListDtoDec dto = new FavoriteListDtoDec();
		return dto;
	}

	public FavoriteListDtoDec createDto(FavoriteList poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FavoriteListDtoDec dto = new FavoriteListDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set referenceEntries */
			final List<FavoriteListEntryDto> col_referenceEntries = new ArrayList<FavoriteListEntryDto>();
			if( null != poso.getReferenceEntries()){
				for(FavoriteListEntry refPoso : poso.getReferenceEntries()){
					final Object tmpDtoFavoriteListEntryDtogetReferenceEntries = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_referenceEntries.add((FavoriteListEntryDto) tmpDtoFavoriteListEntryDtogetReferenceEntries);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFavoriteListEntryDtogetReferenceEntries, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (referenceEntries)");
							int tmp_index = col_referenceEntries.indexOf(tmpDtoFavoriteListEntryDtogetReferenceEntries);
							col_referenceEntries.set(tmp_index,(FavoriteListEntryDto) dto);
						}
					});
				}
				dto.setReferenceEntries(col_referenceEntries);
			}

		}

		return dto;
	}


}
