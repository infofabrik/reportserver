package net.datenwerke.rs.base.service.reportengines.table.utils;

public class ColumnMetadata {

	private String description;
	private String defaultAlias;
	private String defaultPreviewWidth;
	private String semanticType;
	private boolean indexColumn;
	
	public ColumnMetadata(String defaultAlias, String description, String previewWidth) {
		this.defaultAlias = defaultAlias;
		this.description = description;
		this.defaultPreviewWidth = previewWidth;
	}
	
	public String getDefaultAlias() {
		return defaultAlias;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDefaultPreviewWidth() {
		return defaultPreviewWidth;
	}
	
	public String getSemanticType() {
		return semanticType;
	}

	public void setSemanticType(String semanticType) {
		this.semanticType = semanticType;
	}
	
	public boolean isIndexColumn() {
		return indexColumn;
	}
	
	public void setIndexColumn(boolean indexColumn) {
		this.indexColumn = indexColumn;
	}
	
}