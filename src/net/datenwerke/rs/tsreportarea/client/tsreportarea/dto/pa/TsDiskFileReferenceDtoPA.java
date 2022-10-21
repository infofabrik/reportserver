package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFileReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskFileReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskGeneralReferenceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFileReference.class)
public interface TsDiskFileReferenceDtoPA extends TsDiskGeneralReferenceDtoPA {


	public static final TsDiskFileReferenceDtoPA INSTANCE = GWT.create(TsDiskFileReferenceDtoPA.class);


	/* Properties */
	public ValueProvider<TsDiskFileReferenceDto,String> iconStr();
	public ValueProvider<TsDiskFileReferenceDto,String> typeStr();


}
