package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.AbstractTsDiskNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.pa.AbstractNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode.class)
public interface AbstractTsDiskNodeDtoPA extends AbstractNodeDtoPA {


	public static final AbstractTsDiskNodeDtoPA INSTANCE = GWT.create(AbstractTsDiskNodeDtoPA.class);


	/* Properties */
	public ValueProvider<AbstractTsDiskNodeDto,Long> teamSpaceId();


}
