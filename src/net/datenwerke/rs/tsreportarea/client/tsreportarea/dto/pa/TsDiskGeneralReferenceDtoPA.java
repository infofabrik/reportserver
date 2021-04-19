package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.AbstractTsDiskNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference.class)
public interface TsDiskGeneralReferenceDtoPA extends AbstractTsDiskNodeDtoPA {


	public static final TsDiskGeneralReferenceDtoPA INSTANCE = GWT.create(TsDiskGeneralReferenceDtoPA.class);


	/* Properties */
	public ValueProvider<TsDiskGeneralReferenceDto,String> description();
	public ValueProvider<TsDiskGeneralReferenceDto,String> name();
	public ValueProvider<TsDiskGeneralReferenceDto,Date> referenceLastUpdated();


}
