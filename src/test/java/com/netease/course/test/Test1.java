package com.netease.course.test;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.junit.Test;

import com.netease.course.web.utils.valid.MustNum;
import com.netease.course.web.utils.valid.MustNumber;

import junit.framework.Assert;

public class Test1 {
	
	@Test
	public void Test01(){
		String num = "99";
		Double.parseDouble(num);
	}
	
	@Test
	public void Test02(){
		AnnotationDescriptor<MustNum> annotationDescriptor = new AnnotationDescriptor<MustNum>(MustNum.class);
		MustNum mustNum = AnnotationFactory.create(annotationDescriptor);
		MustNumber mustNumber = new MustNumber();
		mustNumber.initialize(mustNum);
		System.out.println(mustNumber.isValid("123", null));
		Assert.assertTrue(mustNumber.isValid("1111111", null));
		Assert.assertFalse(mustNumber.isValid("aaa", null));
	}
}
