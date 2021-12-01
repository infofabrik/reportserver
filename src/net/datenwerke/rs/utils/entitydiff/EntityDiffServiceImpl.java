package net.datenwerke.rs.utils.entitydiff;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Version;

import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuide;
import net.datenwerke.rs.utils.entitydiff.annotations.EntityDiffGuides;
import net.datenwerke.rs.utils.entitydiff.config.EntityDiffConfig;
import net.datenwerke.rs.utils.entitydiff.config.GuidedDiffConfig;
import net.datenwerke.rs.utils.entitydiff.config.StrictDiffConfig;
import net.datenwerke.rs.utils.entitydiff.result.BaseFieldDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.BooleanFieldDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.EnclosedEntityFieldDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.EntityDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.EntityFieldDiffResult;
import net.datenwerke.rs.utils.entitydiff.result.FieldDiffResult;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.rs.utils.reflection.ReflectionService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EntityDiffServiceImpl implements EntityDiffService {

	public class UnequalException extends Exception{
		private static final long serialVersionUID = 1L;
	}
	
	private final EntityUtils entityUtils;
	private final ReflectionService reflectionService;
	
	
	@Inject
	public EntityDiffServiceImpl(
		EntityUtils entityUtils,
		ReflectionService reflectionService
		){
		
		/* store objects */
		this.entityUtils = entityUtils;
		this.reflectionService = reflectionService;
	}
	
	@Override
	public EntityDiffResult diff(Object a, Object b) {
		try {
			return diff(a, b, null, null, false);
		} catch (UnequalException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	public EntityDiffResult diff(Object a, Object b, String guideName) {
		try {
			return diff(a, b, null, guideName, false);
		} catch (UnequalException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	public EntityDiffResult diff(Object a, Object b, EntityDiffConfig config) {
		try {
			return diff(a, b, config, null, false);
		} catch (UnequalException e) {
			throw new IllegalStateException(e);
		}
	}
	

	@Override
	public boolean isEqual(Object a, Object b) {
		try {
			diff(a, b, null, null, true);
			return true;
		} catch (UnequalException e) {
			return false;
		}
	}

	@Override
	public boolean isEqual(Object a, Object b, EntityDiffConfig config) {
		try {
			diff(a, b, config, null, true);
			return true;
		} catch (UnequalException e) {
			return false;
		}
	}

	@Override
	public boolean isEqual(Object a, Object b, String guideName) {
		try {
			diff(a, b, null, guideName, true);
			return true;
		} catch (UnequalException e) {
			return false;
		}
	}

	
	public EntityDiffResult diff(Object a, Object b, EntityDiffConfig config, String guideName, boolean throwUnequal) throws UnequalException{
		if(null == a || null == b)
			return new EntityDiffResult(a, b);
		
		if(! a.getClass().isAssignableFrom(b.getClass())){
			if(! b.getClass().isAssignableFrom(a.getClass()))
				throw new IllegalArgumentException("classes are incompatible");
			
			/* switch objects */
			Object tmp = b;
			b = a;
			a = tmp;
		}

		
		/* prepare reuslt */
		EntityDiffResult result = new EntityDiffResult(a,b);

		/* compare fields */
		compareFields(a,b,config, guideName, result, throwUnequal);
		
		return result;
	}

	private void compareFields(Object a, Object b, EntityDiffConfig config, String guideName, EntityDiffResult result, boolean throwUnequal) throws UnequalException {
		a = entityUtils.unproxy(a);
		b = entityUtils.unproxy(b);
		for(Field field : entityUtils.getPersistantFields(a.getClass()))
			compareFields(a, b, config, guideName, field, result, throwUnequal);
	}

	private void compareFields(Object a, Object b, EntityDiffConfig config, String guideName, Field field,
			EntityDiffResult result, boolean throwUnequal) throws UnequalException {
		config = getConfig(config, guideName, a, b);
		
		Class<?> type = a.getClass();
		while(null != type){
			if(config.getFieldsToCompareBlackList().containsKey(type) && config.getFieldsToCompareBlackList().get(type).contains(field.getName()))
				return;
			if(config.getFieldsToCompareWhiteList().containsKey(type) && ! config.getFieldsToCompareWhiteList().get(type).contains(field.getName()))
				return;	
			if(config.getFieldsToCompareWhiteList().containsKey(type) && config.getFieldsToCompareWhiteList().get(type).contains(field.getName()))
				break;
			
			type = type.getSuperclass();
		}
			
		if(field.isAnnotationPresent(Id.class) && config.ignoreId())
			return;
		
		if(field.isAnnotationPresent(Version.class) && config.ignoreVersion())
			return;
		
		field.setAccessible(true);
		
		try {
			Object valA = field.get(a);
			Object valB = field.get(b);
			Class<?> fieldType = field.getType();
			
			compareValues(a,b,valA,valB, field, fieldType, config, result, throwUnequal);
		} catch (UnequalException e) {
			throw e;
		} catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	private EntityDiffConfig getConfig(EntityDiffConfig config, String guideName, Object a, Object b) {
		if(null != config && ! (config instanceof GuidedDiffConfig))
			return config;
		
		if(null == guideName && config instanceof GuidedDiffConfig)
			guideName = ((GuidedDiffConfig)config).getGuideName();
		
		EntityDiffGuide guide = getGuide(guideName, a);
		if(null == guide)
			if(null != config)
				return config;
			else
				return new StrictDiffConfig();
		
		return new GuidedDiffConfig(a.getClass(), guide, guideName);
	}

	private EntityDiffGuide getGuide(String guideName, Object a) {
		Class<?> type = a.getClass();
		EntityDiffGuide defaultGuide = null;
		
		while(null != type){
			if(type.isAnnotationPresent(EntityDiffGuides.class)){
				for(EntityDiffGuide guide : type.getAnnotation(EntityDiffGuides.class).guides()){
					if(guide.name().equals(guideName))
						return guide;
					if(null != defaultGuide && guide.defaultGuide())
						defaultGuide = guide;
				}
			}
			
			type = type.getSuperclass();
		}
		
		return defaultGuide;
	}

	private void compareValues(Object a, Object b, Object valA, Object valB, Field field, Class<?> fieldType, EntityDiffConfig config, EntityDiffResult result, boolean throwUnequal) throws UnequalException {
		if(entityUtils.isEntity(fieldType)){
			if(entityUtils.isEnclosed(field)){
				EntityDiffResult entityFieldResult = diff(valA, valB, config);
				EntityFieldDiffResult fieldDiff = new EntityFieldDiffResult(field, valA, valB, entityFieldResult);
				
				if(throwUnequal && ! fieldDiff.isEqual())
					throw new UnequalException();
				
				result.addFieldResult(fieldDiff);
			} else {
				EnclosedEntityFieldDiffResult fieldDiff = new EnclosedEntityFieldDiffResult(field, valA, valB);
				
				if(throwUnequal && ! fieldDiff.isEqual())
					throw new UnequalException();

				result.addFieldResult(fieldDiff);
			}
			
		} else if(reflectionService.isCollection(field)){
			Collection colA = ((Collection)valA);
			Collection colB = ((Collection)valB);
			
			if(null == colA || null == colB){
				FieldDiffResult fieldResult = new BaseFieldDiffResult(field, valA, valB);
				
				if(throwUnequal && ! fieldResult.isEqual())
					throw new UnequalException();
				
				result.addFieldResult(fieldResult);
			} else {
				if(colA.size() != colB.size()){
					BooleanFieldDiffResult fieldDiff = new BooleanFieldDiffResult(field, valA, valB, false);
					
					if(throwUnequal && ! fieldDiff.isEqual())
						throw new UnequalException();
					
					result.addFieldResult(fieldDiff);
					return;
				}
				
				
				Iterator itA = colA.iterator();
				List<?> compCol = new ArrayList(colB);
				while(itA.hasNext()){
					Object obj = itA.next();
					
					Object foundObject = null;
					int foundIndex = -1;
					/* try to find brute force */
					for(int i = 0; i < compCol.size(); i++){
						Object toComp = compCol.get(i);
						try{
							EntityDiffResult diffResult = diff(obj, toComp, config);
							if(diffResult.isEqual()){
								foundObject = toComp;
								foundIndex = i;
								break;
							}
						}catch(Exception e){
						}
					}
					
					if( null != foundObject)
						compCol.remove(foundIndex);
					else {
						BooleanFieldDiffResult fieldDiff = new BooleanFieldDiffResult(field, valA, valB, false);
						
						if(throwUnequal && ! fieldDiff.isEqual())
							throw new UnequalException();

						result.addFieldResult(fieldDiff);
						return;
					}
				}
				
				BooleanFieldDiffResult fieldDiff = new BooleanFieldDiffResult(field, valA, valB, true);
				
				if(throwUnequal && ! fieldDiff.isEqual())
					throw new UnequalException();

				result.addFieldResult(fieldDiff);
				return;
			}
			
		} else {
			FieldDiffResult fieldResult = new BaseFieldDiffResult(field, valA, valB);
			
			if(throwUnequal && ! fieldResult.isEqual())
				throw new UnequalException();

			
			result.addFieldResult(fieldResult);
		}
	}

}
