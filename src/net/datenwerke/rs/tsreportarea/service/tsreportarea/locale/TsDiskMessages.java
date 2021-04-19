package net.datenwerke.rs.tsreportarea.service.tsreportarea.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface TsDiskMessages extends Messages{

	public final static TsDiskMessages INSTANCE = LocalizationServiceImpl.getMessages(TsDiskMessages.class);
	
	String historyUrlBuilderName(String tsName);
	
	String tsDiskReportReferenceName();
	String tsFolderTypeName();
	String errorVariantNeedsToBeDuplicated();
	String referencedBy();
}

