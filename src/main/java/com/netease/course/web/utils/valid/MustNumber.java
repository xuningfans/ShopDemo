package com.netease.course.web.utils.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MustNumber implements ConstraintValidator<MustNum, Object> {

	@Override
	public void initialize(MustNum constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			if(value instanceof String ){
				Double.parseDouble((String) value);
				return true;
			}
			if( value instanceof Double ){
				return true;
			}
			
		} catch (Exception e) {
		}
		return false;
	}
}
