package net.datenwerke.security.service.security.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.security.dto.decorator.AceDtoDec;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.dtogen.Ace2DtoGenerator;

/**
 * Poso2DtoGenerator for Ace
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Ace2DtoGenerator implements Poso2DtoGenerator<Ace,AceDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Ace2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AceDtoDec instantiateDto(Ace poso)  {
		AceDtoDec dto = new AceDtoDec();
		return dto;
	}

	public AceDtoDec createDto(Ace poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AceDtoDec dto = new AceDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set accessMaps */
			final Set<AceAccessMapDto> col_accessMaps = new HashSet<AceAccessMapDto>();
			if( null != poso.getAccessMaps()){
				for(AceAccessMap refPoso : poso.getAccessMaps()){
					final Object tmpDtoAceAccessMapDtogetAccessMaps = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_accessMaps.add((AceAccessMapDto) tmpDtoAceAccessMapDtogetAccessMaps);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAceAccessMapDtogetAccessMaps, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (accessMaps)");
							col_accessMaps.remove(tmpDtoAceAccessMapDtogetAccessMaps);
							col_accessMaps.add((AceAccessMapDto) dto);
						}
					});
				}
				dto.setAccessMaps(col_accessMaps);
			}

			/*  set accesstype */
			Object tmpDtoAccessTypeDtogetAccesstype = dtoServiceProvider.get().createDto(poso.getAccesstype(), referenced, referenced);
			dto.setAccesstype((AccessTypeDto)tmpDtoAccessTypeDtogetAccesstype);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAccessTypeDtogetAccesstype, poso.getAccesstype(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setAccesstype((AccessTypeDto)refDto);
				}
			});

			/*  set folk */
			Object tmpDtoAbstractUserManagerNodeDtogetFolk = dtoServiceProvider.get().createDto(poso.getFolk(), referenced, referenced);
			dto.setFolk((AbstractUserManagerNodeDto)tmpDtoAbstractUserManagerNodeDtogetFolk);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractUserManagerNodeDtogetFolk, poso.getFolk(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFolk((AbstractUserManagerNodeDto)refDto);
				}
			});

			/*  set n */
			dto.setN(poso.getN() );

		}

		return dto;
	}


}
