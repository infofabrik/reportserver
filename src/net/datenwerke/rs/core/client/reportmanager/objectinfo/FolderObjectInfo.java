package net.datenwerke.rs.core.client.reportmanager.objectinfo;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProviderImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FolderObjectInfo extends ObjectInfoKeyInfoProviderImpl<ReportFolderDto> {

	
	
	@Override
	public boolean consumes(Object object) {
		return object instanceof ReportFolderDto;
	}

	@Override
	protected String doGetName(ReportFolderDto folder) {
		return folder.getName();
	}

	@Override
	protected String doGetDescription(ReportFolderDto folder) {
		return folder.getDescription();
	}

	@Override
	protected String doGetType(ReportFolderDto folder) {
		return "Ordner";
	}

	@Override
	public ImageResource doGetIconSmall(ReportFolderDto object) {
		return BaseIcon.FOLDER_O.toImageResource();
	}

	@Override
	protected Date doGetLastUpdatedOn(ReportFolderDto object) {
		return object.getLastUpdated();
	}

	@Override
	protected Date doGetCreatedOn(ReportFolderDto object) {
		return object.getCreatedOn();
	}


}
