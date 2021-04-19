package net.datenwerke.rs.utils.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.inject.ImplementedBy;

@ImplementedBy(ReflectionServiceImpl.class)
public interface ReflectionService {

	/**
	 * Handles primitive types.
	 * 
	 * @param type
	 * @throws ClassNotFoundException 
	 */
	public Class<?> getClassForName(String type) throws ClassNotFoundException;

	public boolean isPrimitiveTypeName(String typeName);
	
	public boolean isSimpleField(Field exportableField);

	public boolean isSimpleType(Class<?> type);

	boolean isCollection(Class<?> type);
	
	public boolean isCollection(Field exportableField);

	public Class<?> getGenericType(Class<?> type);
	
	public Class<?> getGenericType(Field exportableField);

	public Class<?> getGenericType(ParameterizedType type);

	public Object convertStringToSimpleType(String value, Class<?> type);

	public Collection<?> createCollection(Class<?> type);

	public Object getEnumByString(Class<?> enumType, String name);

	public boolean isList(Class<?> type);

	boolean isSet(Class<?> type);
	
	public Field getFieldByAnnotation(Object object, Class<? extends Annotation> annotation);
	
	public Field getFieldByAnnotation(Class<?> object, Class<? extends Annotation> annotation);

	public Method getMethodByAnnotation(Object object, Class<? extends Annotation> annotation);
	
	public Method getMethodByAnnotation(Class<?> object, Class<? extends Annotation> annotation);
	
	List<Field> getFieldsByAnnotation(Class<?> type,
			Class<? extends Annotation> annotation);

	public Field getFieldByName(Object object, String fieldName);
	public Field getFieldByName(Class<?> type, String fieldName);
	
	public <A extends Annotation> A getAnnotationRecursive(Class<A> annotationClass, Class<?> type);

	public boolean representsNull(Class<?> type);

	Set<Field> getAllFields(Class<?> clazz);

	Object getFieldValueNoSecurity(Field f, Object o);


	

}
