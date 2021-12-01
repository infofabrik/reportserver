package net.datenwerke.rs.base.service.datasources.table.impl.utils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.sql.StringStatement;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;
import net.sf.jasperreports.engine.query.JRClauseFunction;
import net.sf.jasperreports.engine.query.JRClauseTokens;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuter;
import net.sf.jasperreports.engine.type.ParameterEvaluationTimeEnum;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;

@SuppressWarnings("unchecked")
public class JasperStyleParameterParser extends JRAbstractQueryExecuter {

	private static final Log log = LogFactory.getLog(JRJdbcQueryExecuter.class);

	protected static final String CLAUSE_ID_IN = "IN"; //$NON-NLS-1$
	protected static final String CLAUSE_ID_NOTIN = "NOTIN"; //$NON-NLS-1$

	private static LocalJasperReportsContext CONTEXT;

	private Connection connection;

	/**
	 * The statement used to fire the query.
	 */
	private PreparedStatement statement;
	private ParameterSet parameterSet;
	private Map parameterMap;

	public JasperStyleParameterParser(Connection connection, JRDataset dataset,
			Map parameters, ParameterSet parameterSet) {
		super(getContext(), dataset, parameters);
		this.parameterMap = parameters;
		this.parameterSet = parameterSet;
		this.connection = connection;
//		registerFunctions();
		parseQuery();
	}
	
	public JasperStyleParameterParser(Connection connection, JRDataset dataset,
			Map parameters, ParameterSet parameterSet, boolean ignoreParameters) {
		super(getContext(), dataset, parameters);
		this.parameterMap = parameters;
		this.parameterSet = parameterSet;
		this.connection = connection;
		parseQueryCollectingParameterNames();
	}
	
	private static JasperReportsContext getContext() {
		if(null == CONTEXT){
			CONTEXT = new LocalJasperReportsContext(new SimpleJasperReportsContext());
		}
		return CONTEXT;
	}
	
	protected String getCanonicalQueryLanguage() {
		return "SQL";
	}

	public JasperStyleParameterParser(Connection connection, String query,
			Map<String, ParameterValue> parameters, ParameterSet parameterSet) {
		this(connection, getDatasetFromQuery(query),
				createProperDataset(parameters), parameterSet);
	}

	public JasperStyleParameterParser(String query,
			Map<String, ParameterValue> parameters, ParameterSet parameterSet) {
		this(null, getDatasetFromQuery(query), createProperDataset(parameters),
				parameterSet);
	}
	
	public JasperStyleParameterParser(String query,
			Map<String, ParameterValue> parameters, ParameterSet parameterSet, boolean ignoreParameters) {
		this(null, getDatasetFromQuery(query), createProperDataset(parameters),
				parameterSet, ignoreParameters);
	}

	private static JRDataset getDatasetFromQuery(String query) {
		JRDesignQuery jrquery = new JRDesignQuery();
		jrquery.setText(query);

		JRDesignDataset dataset = new JRDesignDataset(true);
		dataset.setQuery(jrquery);

		return dataset;
	}
	
	protected void parseQueryCollectingParameterNames()
	{
		JRQuery query = dataset.getQuery();
		
		if (query != null)
		{
			JRQueryChunk[] chunks = query.getChunks();
			if (chunks != null && chunks.length > 0)
			{
				for(int i = 0; i < chunks.length; i++)
				{
					JRQueryChunk chunk = chunks[i];
					String chunkText = chunk.getText();
					switch (chunk.getType())
					{
						case JRQueryChunk.TYPE_PARAMETER_CLAUSE :
						{
							addQueryParameter(chunk.getText());
							break;
						}
						case JRQueryChunk.TYPE_PARAMETER :
						{
							addQueryParameter(chunk.getText());
							break;
						}
						case JRQueryChunk.TYPE_CLAUSE_TOKENS :
						{
							JRClauseTokens tokens = new JRClauseTokens(chunk.getTokens());
							String param = tokens.getToken(2);
							addQueryParameter(param);
							
							String param2 = tokens.getToken(3);
							if (null != param2)
								addQueryParameter(param2);
							break;
						}
						case JRQueryChunk.TYPE_TEXT :
						default :
						{
							Pattern p = Pattern.compile("(\\$\\{.*?\\})"); //$NON-NLS-1$
							Matcher m = p.matcher(chunkText);

							while (m.find()) {
								String group = m.group();
								String paramName = group.replaceAll("\\$\\{", "");
								paramName = paramName.replace("}", "");
								addQueryParameter(paramName);
							}
							
							p = Pattern.compile("(\\$!\\{.*?\\})"); //$NON-NLS-1$
							m = p.matcher(chunkText);

							while (m.find()) {
								String group = m.group();
								String paramName = group.replaceAll("\\$!\\{", "");
								paramName = paramName.replace("}", "");
								addQueryParameter(paramName);
							}
							break;
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Map createProperDataset(
			Map<String, ParameterValue> parameters) {
		Map map = new HashMap();

		for (String key : (Set<String>) parameters.keySet())
			map.put(key,
					createValueParameter(key, parameters.get(key).getValue(),
							parameters.get(key).getType()));

		return map;
	}

	/**
	 * Registers built-in {@link JRClauseFunction clause functions}.
	 * 
	 * @see #registerFunctions()
	 * @see #appendClauseChunk(StringBuffer, String[])
	 */
	@Deprecated
	protected void registerFunctions() {
//		registerClauseFunction(CLAUSE_ID_IN, JRSqlInClause.instance());
//		registerClauseFunction(CLAUSE_ID_NOTIN, JRSqlNotInClause.instance());
	}

	protected String getParameterReplacement(String parameterName) {
		return "?"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#createDatasource()
	 */
	public JRDataSource createDatasource() throws JRException {
		throw new UnsupportedOperationException();
	}

	public PreparedStatement getStatement() throws JRException {
		if (null == statement)
			createStatement();
		return statement;
	}

	@Override
	protected void appendQueryChunk(StringBuffer sbuffer, JRQueryChunk chunk) {
		switch (chunk.getType()) {
		case JRQueryChunk.TYPE_PARAMETER_CLAUSE: {
			appendParameterClauseChunk(sbuffer, chunk.getText());
			break;
		}
		case JRQueryChunk.TYPE_PARAMETER: {
			appendParameterChunk(sbuffer, chunk.getText());
			break;
		}
		case JRQueryChunk.TYPE_CLAUSE_TOKENS: {
			appendClauseChunk(sbuffer, chunk.getTokens());
			break;
		}
		case JRQueryChunk.TYPE_TEXT:
		default: {
			// handle null
			if (null == chunk.getText())
				break;

			/* temporary buffer */
			StringBuffer tmpBuf = new StringBuffer();

			/* juel parameter ${...} */
			Pattern p = Pattern.compile("(\\$\\{.*?\\})"); //$NON-NLS-1$
			Matcher m = p.matcher(chunk.getText());

			while (m.find()) {
				String group = m.group();
				addQueryParameter(group);

				Object evaluatedExpr = parameterSet.evaluateExpression(group);
				parameterMap.put(
						group,
						createValueParameter(
								group,
								evaluatedExpr,
								null != evaluatedExpr ? evaluatedExpr
										.getClass() : String.class));
				m.appendReplacement(tmpBuf, getParameterReplacement(group));
			}

			/* append rest to temporary buffer */
			m.appendTail(tmpBuf);

			/*
			 * juel preexecuted parameter $!{...} -> read input from temp buffer
			 * and now use real buffer
			 */
			Pattern p1 = Pattern.compile("(\\$!\\{.*?\\})"); //$NON-NLS-1$
			Matcher m1 = p1.matcher(tmpBuf.toString());

			while (m1.find()) {
				String group = m1.group().replace("$!{", "${"); //$NON-NLS-1$ //$NON-NLS-2$
				m1.appendReplacement(sbuffer,
						(null != parameterSet.evaluateExpression(group)? 
								parameterSet.evaluateExpression(group).toString(): 
									"null"));
			}
			m1.appendTail(sbuffer);

			break;
		}
		}
	}

	protected void createStatement() throws JRException {
		String queryString = getQueryString();

		if (log.isDebugEnabled()) {
			log.debug("SQL query string: " + queryString); //$NON-NLS-1$
		}

		if (connection != null && queryString != null
				&& queryString.trim().length() > 0) {
			try {
				statement = connection.prepareStatement(queryString);

				List parameterNames = getCollectedParameters();
				if (!parameterNames.isEmpty()) {
					for (int i = 0, paramIdx = 1; i < parameterNames.size(); i++) {
						QueryParameter queryParameter = (QueryParameter) parameterNames
								.get(i);
						if (queryParameter.isMulti()) {
							paramIdx += setStatementMultiParameters(paramIdx,
									queryParameter.getName());
						} else {
							setStatementParameter(paramIdx,
									queryParameter.getName());
							++paramIdx;
						}
					}
				}
			} catch (SQLException e) {
				throw new JRException(
						"Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
	}

	public String createQueryAsString() throws JRException {
		String queryString = getQueryString();

		if (log.isDebugEnabled()) {
			log.debug("SQL query string: " + queryString); //$NON-NLS-1$
		}

		if (queryString != null && queryString.trim().length() > 0) {
			try {
				statement = new StringStatement(queryString);
				
				List parameterNames = getCollectedParameters();
				if (!parameterNames.isEmpty()) {
					for (int i = 0, paramIdx = 1; i < parameterNames.size(); i++) {
						QueryParameter queryParameter = (QueryParameter) parameterNames
								.get(i);
						if (queryParameter.isMulti()) {
							paramIdx += setStatementMultiParameters(paramIdx,
									queryParameter.getName());
						} else {
							setStatementParameter(paramIdx,
									queryParameter.getName());
							++paramIdx;
						}
					}
				}
			} catch (SQLException e) {
				throw new JRException(
						"Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
		
		return ((StringStatement)statement).getQuery();
	}

	protected void setStatementParameter(int parameterIndex,
			String parameterName) throws SQLException {
		JRValueParameter parameter = getValueParameter(parameterName);
		Class clazz = parameter.getValueClass();
		String name = parameter.getName();
		
		Object parameterValue = parameter.getValue();
		
		if (log.isDebugEnabled()) {
			log.debug("Parameter #" + parameterIndex + " (" + parameterName + " of type " + clazz.getName() + "): " + parameterValue); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}

		setStatementParameter(parameterIndex, clazz, parameterValue, parameterName);
	}
	
	protected int getDateTimeParameterType(String parameterName, int defaultType) {
	    int type = defaultType;
	    
	    /* parse parameters if they arrive in the form %{param} */
	    String parsedParameterName = parameterName;
	    Pattern p = Pattern.compile("(\\$\\{.*?\\})"); //$NON-NLS-1$
        Matcher m = p.matcher(parameterName);

        if (m.find()) {
            String group = m.group();
            parsedParameterName = group.replaceAll("\\$\\{", "");
            parsedParameterName = parsedParameterName.replace("}", "");
        }
	    
	    Iterator<ParameterInstance> paramSetIterator = parameterSet.iterator();
        while (paramSetIterator.hasNext()) {
            ParameterInstance paramInstance = paramSetIterator.next();
            if (paramInstance.getKey().contentEquals(parsedParameterName)) {
                if (paramInstance instanceof DateTimeParameterInstance) {
                    DateTimeParameterInstance dateParam = (DateTimeParameterInstance) paramInstance;
                    DateTimeParameterDefinition dateParamDefinition = dateParam.getDefinition();
                    switch (dateParamDefinition.getMode()) {
                    case Date:
                        type = Types.DATE;
                        break;
                    case DateTime:
                        type = Types.TIMESTAMP;
                        break;
                    case Time:
                        type = Types.TIME;
                        break;
                    } 
                }
            }
        }
        return type;
	}

	protected int setStatementMultiParameters(int parameterIndex,
			String parameterName) throws SQLException {
		Object paramValue = getParameterValue(parameterName);

		int count;
		if (paramValue.getClass().isArray()) {
			int arrayCount = Array.getLength(paramValue);
			for (count = 0; count < arrayCount; ++count) {
				Object value = Array.get(paramValue, count);
				setStatementMultiParameter(parameterIndex + count,
						parameterName, count, value);
			}
		} else if (paramValue instanceof Collection) {
			Collection values = (Collection) paramValue;
			count = 0;
			for (Iterator it = values.iterator(); it.hasNext(); ++count) {
				Object value = it.next();
				setStatementMultiParameter(parameterIndex + count,
						parameterName, count, value);
			}
		} else {
			throw new JRRuntimeException(
					"Multi parameter value is not array nor collection."); //$NON-NLS-1$
		}

		return count;
	}

	protected void setStatementMultiParameter(int parameterIndex,
			String parameterName, int valueIndex, Object value)
			throws SQLException {
		if (value == null) {
			throw new JRRuntimeException(
					"Multi parameters cannot contain null values."); //$NON-NLS-1$
		}

		Class type = value.getClass();

		if (log.isDebugEnabled()) {
			log.debug("Parameter #" + parameterIndex + //$NON-NLS-1$
					" ("
					+ parameterName
					+ "[" + valueIndex + "] of type " + type.getName() + "): " + value); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}

		setStatementParameter(parameterIndex, type, value, parameterName);
	}
	
	protected void setStatementParameter(int parameterIndex,
			Class parameterType, Object parameterValue, String parameterName) throws SQLException {
		assert null != parameterType;

		if (java.lang.Boolean.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.BIT);
			} else {
				statement.setBoolean(parameterIndex,
						((Boolean) parameterValue).booleanValue());
			}
		} else if (java.lang.Byte.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.TINYINT);
			} else {
				statement.setByte(parameterIndex,
						((Byte) parameterValue).byteValue());
			}
		} else if (java.lang.Double.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.DOUBLE);
			} else {
				statement.setDouble(parameterIndex,
						((Double) parameterValue).doubleValue());
			}
		} else if (java.lang.Float.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.FLOAT);
			} else {
				statement.setFloat(parameterIndex,
						((Float) parameterValue).floatValue());
			}
		} else if (java.lang.Integer.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.INTEGER);
			} else {
				statement.setInt(parameterIndex,
						((Integer) parameterValue).intValue());
			}
		} else if (java.lang.Long.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.BIGINT);
			} else {
				statement.setLong(parameterIndex,
						((Long) parameterValue).longValue());
			}
		} else if (java.lang.Short.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.SMALLINT);
			} else {
				statement.setShort(parameterIndex,
						((Short) parameterValue).shortValue());
			}
		} else if (java.math.BigDecimal.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.DECIMAL);
			} else {
				statement.setBigDecimal(parameterIndex,
						(BigDecimal) parameterValue);
			}
		} else if (java.lang.String.class.isAssignableFrom(parameterType)) {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.VARCHAR);
			} else {
				statement.setString(parameterIndex, parameterValue.toString());
			}
		} else if ( java.sql.Timestamp.class.isAssignableFrom(parameterType) ||
		        java.sql.Time.class.isAssignableFrom(parameterType) ||
		        java.util.Date.class.isAssignableFrom(parameterType) ) {
		    
		    int dateTimeType = getDateTimeParameterType(parameterName, Types.DATE);
		    if (dateTimeType == Types.DATE) {
		        if (parameterValue == null) {
	                statement.setNull(parameterIndex, Types.DATE);
	            } else {
	                Date trimmed = DateUtils.trimTimeInformation((java.util.Date) parameterValue);   
	                statement.setDate(parameterIndex, new java.sql.Date(
	                        (trimmed.getTime())));
	            }
		    } else if (dateTimeType == Types.TIMESTAMP) {
		        if (parameterValue == null) {
	                statement.setNull(parameterIndex, Types.TIMESTAMP);
	            } else {
                    Date trimmed = DateUtils.trimSecondsAndMillis((java.util.Date) parameterValue);   
	                statement.setTimestamp(parameterIndex,
	                        new Timestamp(trimmed.getTime()));
	            }
		    } else if (dateTimeType == Types.TIME) {
		        if (parameterValue == null) {
	                statement.setNull(parameterIndex, Types.TIME);
	            } else {
	                Date trimmed = DateUtils.trimSecondsAndMillis((java.util.Date) parameterValue);
	                trimmed = DateUtils.trimDateInformation(trimmed);
	                statement.setTime(parameterIndex,
	                        new Time(trimmed.getTime()));
	            }
		    }
		} else {
			if (parameterValue == null) {
				statement.setNull(parameterIndex, Types.JAVA_OBJECT);
			} else {
				statement.setObject(parameterIndex, parameterValue);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#close()
	 */
	public synchronized void close() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#cancelQuery()
	 */
	public synchronized boolean cancelQuery() throws JRException {
		throw new UnsupportedOperationException();
	}

	public static JRValueParameter createValueParameter(final String name,
			final Object value, final Class<?> type) {
		return new JRValueParameter() {

			public boolean hasProperties() {
				return false;
			}

			public JRPropertiesMap getPropertiesMap() {
				return null;
			}

			public JRPropertiesHolder getParentProperties() {
				return null;
			}

			public void setDescription(String description) {

			}

			public boolean isSystemDefined() {
				return false;
			}

			public boolean isForPrompting() {
				return false;
			}

			public String getValueClassName() {
				return null;
			}

			public Class getValueClass() {
				if (null == value)
					return type;
				return value.getClass();
			}

			public String getNestedTypeName() {
				return null;
			}

			public Class getNestedType() {
				return null;
			}

			public String getName() {
				return name;
			}

			public String getDescription() {
				return null;
			}

			public JRExpression getDefaultValueExpression() {
				return null;
			}

			public void setValue(Object value) {

			}

			public Object getValue() {
				return value;
			}

			public Object clone() {
				throw new UnsupportedOperationException();
			}

			@Override
			public ParameterEvaluationTimeEnum getEvaluationTime() {
				return null;
			}
		};
	}

	@Override
	public Object getParameterValue(String parameterName) {
		return super.getParameterValue(parameterName);
	}

	@Override
	public List getCollectedParameters() {
		return super.getCollectedParameters();
	}
	
	@Override
	public List<String> getCollectedParameterNames() {
		return super.getCollectedParameterNames();
	}

	@Override
	public String getQueryString() {
		return super.getQueryString();
	}

}
