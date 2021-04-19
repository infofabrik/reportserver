package net.datenwerke.security.client.security.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.client.security.dto.HierarchicalAceDto;
import net.datenwerke.security.client.security.dto.InheritanceTypeDto;
import net.datenwerke.security.client.security.dto.pa.AceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.security.service.security.entities.HierarchicalAce.class)
public interface HierarchicalAceDtoPA extends AceDtoPA {


	public static final HierarchicalAceDtoPA INSTANCE = GWT.create(HierarchicalAceDtoPA.class);


	/* Properties */
	public ValueProvider<HierarchicalAceDto,InheritanceTypeDto> inheritancetype();


}
