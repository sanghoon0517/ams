package com.ams.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BodyStyle {
	Background background() default @Background;
	int fontSize() default 11;
	HorizontalAlignment horizontalAlignment() default HorizontalAlignment.GENERAL;
	VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
	String numberFormat() default "";
	String dateFormat() default "YYYY-MM-DD HH:mm:ss";
}
