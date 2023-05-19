package net.datenwerke.rs.base.service.reportengines.table.entities.format;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ColumnFormatNumber.class)
public abstract class ColumnFormatNumber_ extends net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat_ {

	public static volatile SingularAttribute<ColumnFormatNumber, Boolean> thousandSeparator;
	public static volatile SingularAttribute<ColumnFormatNumber, NumberType> type;
	public static volatile SingularAttribute<ColumnFormatNumber, Integer> numberOfDecimalPlaces;

}

