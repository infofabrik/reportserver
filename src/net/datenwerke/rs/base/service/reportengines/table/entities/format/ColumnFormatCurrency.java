package net.datenwerke.rs.base.service.reportengines.table.entities.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COLUMN_FORMAT_CURRENCY")
@Inheritance(strategy=InheritanceType.JOINED)
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	createDecorator=true
)
public class ColumnFormatCurrency extends ColumnFormatNumber {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8924716725557026930L;

	@ExposeToClient
	private CurrencyType currencyType = CurrencyType.EURO;

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	@Override
	public void setType(NumberType type) {
		/* do not allow to change the type from default to something else */
	}

	@Override
	public NumberFormat getNumberFormat() {
		NumberFormat format = DecimalFormat.getCurrencyInstance(getCurrencyType().getLocale());
		((DecimalFormat)format).setCurrency(getCurrencyType().getCurrency());
		return format;
	}
	
	@Override
	public String getPattern() {
		return getDecimalPattern() + " \u00A4" ;
	}
}
