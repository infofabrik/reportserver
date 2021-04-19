package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;

/**
 * Dto Decorator for {@link GridEditorColumnConfigDto}
 *
 */
public class GridEditorColumnConfigDtoDec extends GridEditorColumnConfigDto {


	private static final long serialVersionUID = 1L;

	public GridEditorColumnConfigDtoDec() {
		super();
	}

	@Override
	public Boolean isPrimaryKey() {
		return Boolean.TRUE.equals(super.isPrimaryKey());
	}

}
