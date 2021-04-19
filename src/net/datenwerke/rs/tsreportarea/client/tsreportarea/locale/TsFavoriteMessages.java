package net.datenwerke.rs.tsreportarea.client.tsreportarea.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface TsFavoriteMessages extends Messages {

	public final static TsFavoriteMessages INSTANCE = GWT.create(TsFavoriteMessages.class);
	
	String addFolderText();
	String appDescription();
	
	String appName();
	String catalog();

	String containerHeadline();
	
	String explorer();
	String exportLabel();
	
	String importPostProcessorDescription();
	
	String importPostProcessorHeadline();
	
	String importPostProcessorIntoTeamspace();
	String importPostProcessorName();
	
	String importReportText();
	
	String importVariantHookDescription();
	
	String importVariantHookName();
	
	String itemDeleted();
	String lastModified();
	
	String listPanelHeader(String folderName);
	
	String movedNode();
	String movedNodes();
	
	String newCopy();

	String newFolderDescriptionLabel();
	
	String newFolderNameLabel();
	String newReference();
	String noTeamSpaceSelectedMsg();
	String noTeamSpaceSelectedTitle();
	String objectInfoHeader(String displayTitle);
	
	String reportNotInTeamSpacesMessages();
	String rootFolderName();
	String selectTeamspaceLabel();
	
	String importVariantIntoTeamSpaceLabel();
	String duplicateLabel();
	
	String lastUpdatedFormat();
	
	String idLabel();
	
	String reportCouldNotBeLoaded();

	String violatedSecurityTitle();	
	String violatedSecurityMessage();
	
	String selectFromTeamSpaceText();
	String selectFileFromTeamSpace();
	
	String folderDescription();
	String reportDescription();
	String referenceDescription();
	
	String referencedBy();

}
