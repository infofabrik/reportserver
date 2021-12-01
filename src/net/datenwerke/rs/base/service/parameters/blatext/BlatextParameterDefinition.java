package net.datenwerke.rs.base.service.parameters.blatext;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="BLATEXT_PARAM_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.blatext.dto",
	displayTitle="RsMessages.INSTANCE.blaTextParameterText()",
	additionalImports=RsMessages.class
)
public class BlatextParameterDefinition extends ParameterDefinition<BlatextParameterInstance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458837856638298648L;
	
	@ExposeToClient(allowArbitraryLobSize=true,
			disableHtmlEncode=true,
			enableSimpleHtmlPolicy=true)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String value = ""; //$NON-NLS-1$
	
 	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	protected BlatextParameterInstance doCreateParameterInstance() {
		return new BlatextParameterInstance();
	}
	
	@Override
	public boolean isSeparator() {
	    return true;
	}

}
