package net.datenwerke.rs.base.service.reportengines.jasper;

import java.sql.Connection;

import org.apache.commons.lang3.NotImplementedException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 *
 */
public class JasperDBDataSource implements JRDataSource {

	private Connection connection;
	
	public JasperDBDataSource(Connection connection) {
		super();
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Object getFieldValue(JRField jrField) throws JRException {
		throw new NotImplementedException("This object cannot be used like other JRDataSource objects. Sorry for the inconvenience."); //$NON-NLS-1$
	}

	public boolean next() throws JRException {
		throw new NotImplementedException("This object cannot be used like other JRDataSource objects. Sorry for the inconvenience."); //$NON-NLS-1$
	}

}
