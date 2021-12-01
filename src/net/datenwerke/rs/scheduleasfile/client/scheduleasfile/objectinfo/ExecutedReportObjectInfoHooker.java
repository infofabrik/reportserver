package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.objectinfo;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;

public class ExecutedReportObjectInfoHooker extends ObjectInfoKeyInfoProviderImpl<ExecutedReportFileReferenceDto> {
	
	@Override
	public boolean consumes(Object object) {
		return object instanceof ExecutedReportFileReferenceDto;
	}

	@Override
	protected String doGetName(ExecutedReportFileReferenceDto node) {
		return node.getName();
	}

	@Override
	protected String doGetDescription(ExecutedReportFileReferenceDto node) {
		return node.getDescription();
	}

	@Override
	protected String doGetType(ExecutedReportFileReferenceDto node) {
		return ScheduleAsFileMessages.INSTANCE.executedReportNodeType();
	}

	@Override
	protected Date doGetLastUpdatedOn(ExecutedReportFileReferenceDto node) {
		return ((ExecutedReportFileReferenceDtoDec)node).getLastUpdated();
	}

	@Override
	protected Date doGetCreatedOn(ExecutedReportFileReferenceDto node) {
		return ((ExecutedReportFileReferenceDtoDec)node).getCreatedOn();
	}

	@Override
	protected ImageResource doGetIconSmall(ExecutedReportFileReferenceDto node) {
		return node.toIcon().toImageResource();
	}


}
