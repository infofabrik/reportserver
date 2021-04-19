package net.datenwerke.rs.base.service.parameters.datetime.dtogen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.inject.Inject;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.UserLocale;

public class DateTimeParameterInstance2PosoPostProcessor implements Dto2PosoPostProcessor<DateTimeParameterInstanceDto, DateTimeParameterInstance> {

	@Inject
	private static Provider<LocalizationServiceImpl> localizationService;
	
	@Override
	public void posoCreated(DateTimeParameterInstanceDto dto, DateTimeParameterInstance poso) {

		if(dto instanceof DateTimeParameterInstanceDtoDec) {
			DateTimeParameterInstanceDtoDec dec = (DateTimeParameterInstanceDtoDec) dto;
			String strval = dec.getStrValue();
			if(null != strval) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				try {
					Date date = sdf.parse(strval);
					poso.setValue(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		String timezone = localizationService.get().getUserTimezone();
//		TimeZone clientTZ = TimeZone.getTimeZone(timezone);
//		TimeZone serverTZ = TimeZone.getDefault();
//		
//		Date clientdate = poso.getValue();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss Z");
//		
//		sdf.setTimeZone(clientTZ);
//		System.out.println(sdf.format(clientdate));
//		
//		int clientoffset = clientTZ.getRawOffset(); //clientTZ.getOffset(clientdate.getTime())/60000;
//		System.out.println("+ "  + clientoffset / 60000 / 60);
//		Date utc = new Date(clientdate.getTime() + clientoffset);
//		
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//		System.out.println(sdf.format(utc));
//		
//		int serveroffset = serverTZ.getOffset(utc.getTime());
//		System.out.println("- " + serveroffset / 60000 / 60);
//		Date serverdate = new Date(utc.getTime() - serveroffset);
//		
//		
//		sdf.setTimeZone(serverTZ);
//		System.out.println(sdf.format(serverdate));
//
//		poso.setValue(serverdate);
	}

	@Override
	public void posoCreatedUnmanaged(DateTimeParameterInstanceDto dto, DateTimeParameterInstance poso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posoInstantiated(DateTimeParameterInstance poso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posoMerged(DateTimeParameterInstanceDto dto, DateTimeParameterInstance poso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posoLoaded(DateTimeParameterInstanceDto dto, DateTimeParameterInstance poso) {
		// TODO Auto-generated method stub
		
	}




}
